package studsluzba.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import studsluzba.model.Ispit;
import studsluzba.model.IzlazakNaIspit;
import studsluzba.services.IspitService;

@Component
public class FindRezultatiIspitaController {

	
	@Autowired
	IspitService ispitService;
	
	@FXML private ComboBox<Ispit> ispitiCb;
	@FXML private TableView<IzlazakNaIspit> rezultatiTable;
	@FXML private Label actionTarget;
	
	
	@FXML
	public void initialize() {
		ispitiCb.setItems(FXCollections.observableArrayList(ispitService.loadAllIspiti()));
		
	    rezultatiTable.setItems(FXCollections.observableArrayList(ispitService.loadlAllIzlasciNaIspit()));
	    
	    actionTarget.setTextFill(Color.FIREBRICK);
	}
	
	public void handleFindRezultati(ActionEvent event) {
		if(ispitiCb.getValue() == null) {
			actionTarget.setText("Ispit nije izabran!");
			return;
		}
		
		actionTarget.setText("");
		
		Ispit ispit = ispitiCb.getSelectionModel().getSelectedItem();
		
		List<IzlazakNaIspit> list = ispitService.findRezultati(ispit);
		rezultatiTable.setItems(FXCollections.observableArrayList(list));
	}
	
}
