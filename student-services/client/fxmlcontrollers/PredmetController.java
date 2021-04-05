package studsluzba.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import studsluzba.model.Predmet;
import studsluzba.model.StudProgram;
import studsluzba.services.PredmetService;
import studsluzba.services.StudProgramService;

@Component
public class PredmetController {

	
	@Autowired
	PredmetService predmetService;
	
	@Autowired
	StudProgramService studProgramService;
	
	@FXML private TextField nazivPredmetaTf;
	@FXML private TextField sifraPredmetaTf;
	@FXML private ComboBox<StudProgram> studProgramCb;
	@FXML private TextField opisPredmetaTf;
	@FXML private TextField esbpTf;
	@FXML private TextField semestarTf;
	@FXML private TextField fondCasovaTf;
	
	
	private ObservableList<Predmet> sviPredmeti;
	
	
	@FXML
	protected void initialize() {
		sviPredmeti = FXCollections.observableList(predmetService.loadAll());
		List<StudProgram> smerovi = studProgramService.loadAll();
		studProgramCb.setItems(FXCollections.observableArrayList(smerovi));
	}
	
	
	public void handleSavePredmet(ActionEvent event) {
		Predmet p = predmetService.savePredmet(nazivPredmetaTf.getText(), sifraPredmetaTf.getText(), opisPredmetaTf.getText(),
				studProgramCb.getSelectionModel().getSelectedItem(), Integer.parseInt(esbpTf.getText()), 
				Integer.parseInt(fondCasovaTf.getText()), Integer.parseInt(semestarTf.getText()));
		
		sviPredmeti.add(p);
		
		nazivPredmetaTf.clear();
		sifraPredmetaTf.clear();
		opisPredmetaTf.clear();
		studProgramCb.getSelectionModel().clearSelection();
		esbpTf.clear();
		fondCasovaTf.clear();
		semestarTf.clear();
	}
	

}
