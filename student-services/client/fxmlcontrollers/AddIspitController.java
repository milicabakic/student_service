package studsluzba.client.fxmlcontrollers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import studsluzba.model.Nastavnik;
import studsluzba.model.Predmet;
import studsluzba.services.IspitService;
import studsluzba.services.NastavnikService;
import studsluzba.services.PredmetService;

@Component
public class AddIspitController {

	
	@Autowired
	IspitService ispitService;
	
	@Autowired
	FindIspitController ispitController;
	
	@Autowired
	PredmetService predmetService;
	
	@Autowired
	NastavnikService nastavnikService;
	
	@FXML private DatePicker datumDp;
	@FXML private ComboBox<Predmet> predmetiCb;
	@FXML private ComboBox<Nastavnik> nastavniciCb;
	@FXML private Label actionTarget;
	
	
	@FXML
	public void initialize() {
		predmetiCb.setItems(FXCollections.observableArrayList(predmetService.loadAll()));
		nastavniciCb.setItems(FXCollections.observableArrayList(nastavnikService.loadAll()));
	}
	
	public void addIspit(ActionEvent event) {
		if(datumDp.getValue() == null || predmetiCb.getValue() == null || nastavniciCb.getValue() == null) {
			actionTarget.setTextFill(Color.FIREBRICK);
			actionTarget.setText("Nisu uneti svi podaci!Ispit nije dodat.");
			return;
		}
		actionTarget.setText("");
		
		ispitService.saveIspit(convertToDate(datumDp.getValue()),predmetiCb.getSelectionModel().getSelectedItem(), nastavniciCb.getSelectionModel().getSelectedItem()); 
		ispitController.update();
		closeStage(event);
	}
	
	private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
	
	private Date convertToDate(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}
}
