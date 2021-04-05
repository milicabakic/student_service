package studsluzba.client.fxmlcontrollers;

import java.util.ArrayList;
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
import studsluzba.model.PredajePredmet;
import studsluzba.model.StudentskiIndeks;
import studsluzba.services.PredajePredmetService;
import studsluzba.services.StudentService;

@Component
public class FindStudentiNaPredmetuController {

	
	@Autowired
	PredajePredmetService predajePredmetService;
	
	@Autowired
	StudentService studentService;
	
	@FXML private ComboBox<StudentskiIndeks> studentiCb;
	@FXML private ComboBox<PredajePredmet> predmetiCb;
	@FXML private TableView<PredajePredmet> predajePredmetTable;
	@FXML private Label actionTarget;
	
	private ObservableList<PredajePredmet> predmeti;
	
	@FXML
	public void initialize() {
		studentiCb.setItems(FXCollections.observableArrayList(studentService.loadAllIndeks()));
		predmetiCb.setItems(FXCollections.observableArrayList(predajePredmetService.loadAllWithStudents()));
		
		predmeti = FXCollections.observableArrayList(predajePredmetService.loadAllWithStudents());
		predajePredmetTable.setItems(predmeti);
		
		actionTarget.setTextFill(Color.FIREBRICK);
	}
	
	public void handleSaveStudentaNaPredajePredmet(ActionEvent event) {
		if(predmetiCb.getValue() == null || studentiCb.getValue() == null) {
			actionTarget.setText("Izaberite sve podatke! Akcija nije izvršena.");
			return;
		}
		actionTarget.setText("");
		
		studentService.addPredmetKojiSlusa(predmetiCb.getSelectionModel().getSelectedItem(), 
				studentiCb.getSelectionModel().getSelectedItem());
		
		predajePredmetService.addStudentaNaPredmet(studentiCb.getSelectionModel().getSelectedItem(), 
				predmetiCb.getSelectionModel().getSelectedItem());
		
		predmeti = FXCollections.observableArrayList(predajePredmetService.loadAllWithStudents());
		predajePredmetTable.setItems(predmeti);
	}
	
	public void handleFilterStudent(ActionEvent event) {
		if(studentiCb.getValue() == null) {
			actionTarget.setText("Student nije izabran!");
			return;
		}
		
		StudentskiIndeks indeks = studentiCb.getSelectionModel().getSelectedItem();
		
		List<PredajePredmet> list = predajePredmetService.loadlAllForStudent(indeks);		
		predajePredmetTable.setItems(FXCollections.observableArrayList(list));
		
		predmetiCb.getSelectionModel().clearSelection();   
	}
	
	public void handleFilterPredmet(ActionEvent event) {
		if(predmetiCb.getValue() == null) {
			actionTarget.setText("Predmet nije izabran!");
			return;
		}
		
		List<PredajePredmet> list = new ArrayList<PredajePredmet>();
		PredajePredmet predmet = predmetiCb.getSelectionModel().getSelectedItem();
		list.add(predmet);
		
		studentiCb.getSelectionModel().clearSelection();
		
		predajePredmetTable.setItems(FXCollections.observableArrayList(list));
	}
	
	public void handlePrikaziSve(ActionEvent event) {
		actionTarget.setText("");
		
		predajePredmetTable.setItems(predmeti);
		
		studentiCb.getSelectionModel().clearSelection();
		predmetiCb.getSelectionModel().clearSelection();
	}
}
