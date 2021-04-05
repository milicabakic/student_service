package studsluzba.client.fxmlcontrollers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import studsluzba.model.Nastavnik;
import studsluzba.model.Zvanje;
import studsluzba.services.NastavnikService;

@Component
public class FindNastavnikController {

	@Autowired
	NastavnikService nastavnikService;
	
	@FXML private ComboBox<Nastavnik> nastavniciCb;
	@FXML private TextField nazivZvanjaTf;
	@FXML private DatePicker datumIzboraDp;
	@FXML private TextField naucnaOblastTf;
	@FXML private TableView<Zvanje> zvanjeTable;
	@FXML private Label actionTarget;
	
	private ObservableList<Zvanje> svaZvanjaNastavnika; 
	
	
	@FXML
	public void initialize() {
		List<Nastavnik> nastavnici = nastavnikService.loadAll();
		nastavniciCb.setItems(FXCollections.observableArrayList(nastavnici));
		
		svaZvanjaNastavnika = FXCollections.observableArrayList(nastavnikService.loadAllZvanjaNastavnika());
		zvanjeTable.setItems(svaZvanjaNastavnika);
		
		actionTarget.setTextFill(Color.FIREBRICK);
	}
	
	public void handleAddZvanjeNastavnika(ActionEvent event) {
		if(nastavniciCb.getValue()==null || datumIzboraDp.getValue()==null || nazivZvanjaTf.getText().equals("")
				|| naucnaOblastTf.getText().equals("")) {
			actionTarget.setText("Svi podaci nisu uneti!Akcija nije izvršena.");
			return;
		}
		
		actionTarget.setText("");
		
		Zvanje z = nastavnikService.addZvanjeNastavnika(nastavniciCb.getSelectionModel().getSelectedItem(), 
				nazivZvanjaTf.getText(), convertToDate(datumIzboraDp.getValue()), naucnaOblastTf.getText());
	    svaZvanjaNastavnika.add(z);
	    
	    nastavniciCb.getSelectionModel().clearSelection();
	    nazivZvanjaTf.clear();
	    datumIzboraDp.getEditor().clear();
	    naucnaOblastTf.clear();
	}
	
	private Date convertToDate(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}
}
