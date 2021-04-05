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
import javafx.scene.paint.Color;
import studsluzba.model.Predmet;
import studsluzba.model.StudProgram;
import studsluzba.services.PredmetService;
import studsluzba.services.StudProgramService;

@Component
public class FindPredmetController {

	@Autowired
	PredmetService predmetService;
	
	@Autowired
	StudProgramService smeroviService;
	
	@FXML private ComboBox<StudProgram> studProgramCb;
	@FXML private TableView<Predmet> predmetiTable;
	@FXML private Label actionTarget;
	
	private ObservableList<Predmet> sviPredmeti;
	
	
	@FXML
	public void initialize() {
		List<StudProgram> smerovi = smeroviService.loadAll();
		studProgramCb.setItems(FXCollections.observableArrayList(smerovi));
		
		sviPredmeti = FXCollections.observableList(predmetService.loadAll());
		predmetiTable.setItems(sviPredmeti);
		
		actionTarget.setTextFill(Color.FIREBRICK);
	}
	
	public void handleFindPredmet(ActionEvent event) {
		if(studProgramCb.getValue() == null) {
			actionTarget.setText("Studijski program nije izabran!");
			return;
		}
		
		actionTarget.setText("");
		
		StudProgram smer = studProgramCb.getSelectionModel().getSelectedItem();
		List<Predmet> res = predmetService.findPredmetByStudProgram(smer);
		sviPredmeti = FXCollections.observableList(res);
		predmetiTable.setItems(sviPredmeti);
	}
	
	public void handleLoadAll(ActionEvent event) {
		actionTarget.setText("");
		predmetiTable.setItems(FXCollections.observableArrayList(predmetService.loadAll()));		
	}
	
	
}
