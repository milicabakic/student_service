package studsluzba.client.fxmlcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import studsluzba.model.Nastavnik;
import studsluzba.model.PredajePredmet;
import studsluzba.model.Predmet;
import studsluzba.model.SkolskaGodina;
import studsluzba.services.NastavnikService;
import studsluzba.services.PredajePredmetService;
import studsluzba.services.PredmetService;
import studsluzba.services.SkolskaGodinaService;

@Component
public class PredajePredmetController {

	
	@Autowired
	PredajePredmetService predajePredmetService;
	
	@Autowired
	NastavnikService nastavnikService;
	
	@Autowired
	PredmetService predmetService;
	
	@Autowired
	SkolskaGodinaService godinaService;
	
	@FXML private ComboBox<Nastavnik> nastavniciCb;
	@FXML private ComboBox<Predmet> predmetiCb;
	@FXML private ComboBox<SkolskaGodina> godineCb;
	@FXML private TableView<PredajePredmet> predajePredmetTable;
	
	private ObservableList<PredajePredmet> predmeti;
	
	
	@FXML
	public void initialize() {
		nastavniciCb.setItems(FXCollections.observableArrayList(nastavnikService.loadAll()));
		predmetiCb.setItems(FXCollections.observableArrayList(predmetService.loadAll()));
	    godineCb.setItems(FXCollections.observableArrayList(godinaService.loadAll()));
	    
	    predmeti = FXCollections.observableArrayList(predajePredmetService.loadAll());
	    predajePredmetTable.setItems(predmeti);
	}
	
	public void handleSavePredajePredmet(ActionEvent event) {
		PredajePredmet pp = predajePredmetService.savePredajePredmet(nastavniciCb.getSelectionModel().getSelectedItem(),
				godineCb.getSelectionModel().getSelectedItem(), predmetiCb.getSelectionModel().getSelectedItem());
	    predmeti.add(pp);
	    
	    nastavniciCb.getSelectionModel().clearSelection();
	    predmetiCb.getSelectionModel().clearSelection();
	    godineCb.getSelectionModel().clearSelection();
	}
}
