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
import studsluzba.client.MainViewManager;
import studsluzba.model.Ispit;
import studsluzba.model.IspitniRok;
import studsluzba.services.IspitService;

@Component
public class FindIspitController {

	
	@Autowired
	IspitService ispitService;
	
	@Autowired
	MainViewManager mainViewManager;
	
	@FXML private ComboBox<IspitniRok> rokoviCb;
	@FXML private ComboBox<Ispit> ispitiCb;
	@FXML private TableView<Ispit> ispitiTable;
	@FXML private Label actionTarget;
	
	private ObservableList<Ispit> ispiti;
	
	
	@FXML
	public void initialize() {
		rokoviCb.setItems(FXCollections.observableArrayList(ispitService.loadAllIspitniRokovi()));
		ispitiCb.setItems(FXCollections.observableArrayList(ispitService.loadAllIspiti()));
		
		ispiti = FXCollections.observableArrayList(ispitService.loadAllIspiti());
		ispitiTable.setItems(ispiti);
		
		actionTarget.setTextFill(Color.FIREBRICK);
	}
	
	public void handleSaveIspit(ActionEvent event) {
		if(ispitiCb.getValue() == null || rokoviCb.getValue() == null) {
			actionTarget.setText("Svi podaci nisu izabrani!");
			return;
		}
		
		actionTarget.setText("");
		
		Ispit i = ispitiCb.getSelectionModel().getSelectedItem();
		ispitService.matchIspitAndIspitniRok(i, rokoviCb.getSelectionModel().getSelectedItem());

		ispiti = FXCollections.observableArrayList(ispitService.loadAllIspiti());
		ispitiTable.setItems(ispiti);
		
		ispitiCb.setItems(FXCollections.observableArrayList(ispitService.loadAllIspiti()));
	}
	
	public void handleOpenModalIspit(ActionEvent event) {
		actionTarget.setText("");
		
		mainViewManager.openModal("addIspit");
	}
	
	public void update() {
		ispitiCb.setItems(FXCollections.observableArrayList(ispitService.loadAllIspiti()));
	}
	
}
