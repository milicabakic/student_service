package studsluzba.client.fxmlcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import studsluzba.model.Ispit;
import studsluzba.model.PrijavljenIspit;
import studsluzba.services.IspitService;

@Component
public class FindPrijavljenIspitController {

	
	@Autowired
	IspitService ispitService;
	
	@FXML private ComboBox<Ispit> ispitiCb;
	@FXML private TableView<PrijavljenIspit> prijaveTable;
	@FXML private Label actionTarget;
	
	private ObservableList<PrijavljenIspit> prijave;
	
	
	@FXML
	public void initialize() {
		ispitiCb.setItems(FXCollections.observableArrayList(ispitService.loadAllIspiti()));
		
		prijave = FXCollections.observableArrayList(ispitService.loadlAllPrijavljeniIspiti());
		prijaveTable.setItems(prijave);
		
		actionTarget.setTextFill(Color.FIREBRICK);
	}
	
	public void handleFilterPrijavljeniIspiti(ActionEvent event) {
		if(ispitiCb.getValue() == null) {
			actionTarget.setText("Ispit nije izabran!Pretraga nije izvršena.");
			return;
		}
		
		Ispit ispit = ispitiCb.getSelectionModel().getSelectedItem();
		
		prijave = FXCollections.observableArrayList(ispitService.findPrijavljeniStudenti(ispit));
		prijaveTable.setItems(prijave);
	}
	
}
