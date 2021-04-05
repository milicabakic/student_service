package studsluzba.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import studsluzba.model.SkolskaGodina;
import studsluzba.services.SkolskaGodinaService;

@Component
public class SkolskaGodinaController {

	
	@Autowired
	SkolskaGodinaService godinaService;
	
	@FXML private TextField godina1Tf;
	@FXML private TextField godina2Tf;
	@FXML private ComboBox<String> aktivnaGodinaCb;
	@FXML private TableView<SkolskaGodina> godineTable;
	@FXML private Label actionTarget;
	
	private ObservableList<SkolskaGodina> godineList;
	
	
	
	@FXML public void initialize() {
		List<String> aktivnosti = List.of("DA", "NE");
		aktivnaGodinaCb.setItems(FXCollections.observableArrayList(aktivnosti));
		
		godineList = FXCollections.observableArrayList(godinaService.loadAll());
		godineTable.setItems(godineList);
	}
	
	
	public void handleSaveSkolskaGodina(ActionEvent event) {
		if(godina1Tf.getText().equals("") || godina2Tf.getText().equals("") || aktivnaGodinaCb.getValue()==null) {
			actionTarget.setTextFill(Color.FIREBRICK);
			actionTarget.setText("Svi podaci nisu uneti! Akcija nije izvršena.");
		}
		
		actionTarget.setText("");
		
		godinaService.saveSkolskaGodina(Integer.parseInt(godina1Tf.getText()), Integer.parseInt(godina2Tf.getText()), 
				aktivnaGodinaCb.getSelectionModel().getSelectedItem());

		godineList = FXCollections.observableArrayList(godinaService.loadAll());
		godineTable.setItems(godineList);
		
		godina1Tf.clear();
		godina2Tf.clear();
		aktivnaGodinaCb.getSelectionModel().clearSelection();
	}
	
	
}
