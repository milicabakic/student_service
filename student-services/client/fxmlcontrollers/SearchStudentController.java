package studsluzba.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import studsluzba.client.MainViewManager;
import studsluzba.model.Student;
import studsluzba.services.StudentService;

@Component
public class SearchStudentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	MainViewManager mainViewManager;
    
	@Autowired
	ChangeIndeksController changeIndeksController;
	
	@Autowired
	DosijeController dosijeController;
	
	@FXML
	private TextField imeTf;
	@FXML
	private TextField prezimeTf;
	@FXML
	private TextField indeksTf;

	private ObservableList<Student> sviStudenti;

	@FXML
	private TableView<Student> studentiTable;
	
	@FXML
	Label alert;

	@FXML
	protected void initialize() {
		sviStudenti = FXCollections.observableList(studentService.loadAll());
		studentiTable.setItems(sviStudenti);
	}

	public void handleSearchStudentByName(ActionEvent event) {
		List<Student> students = studentService.searchStudents(imeTf.getText(), prezimeTf.getText());
		sviStudenti = FXCollections.observableList(students);
		studentiTable.setItems(sviStudenti);
		
		alert.setText("");
	}
	
	public void handleSearchStudentByIndex(ActionEvent event) {
		
		try {
			String index = indeksTf.getText(); // 2RN9/2018
			String niz[] = index.split("/");
			int godina = Integer.parseInt(niz[1]);
			String smerIbroj = niz[0];
			StringBuilder sb = new StringBuilder(smerIbroj);
            char smer[] = new char[2];
            smer[0] = sb.charAt(0);
            smer[1] = sb.charAt(1);
			StringBuilder sb1 = new StringBuilder();
            sb1.append(smer[0]);
            sb1.append(smer[1]);
            String skraceniNazivSmera = sb1.toString();
            sb.deleteCharAt(0);
            sb.deleteCharAt(0);
            int broj = Integer.parseInt(sb.toString());
            
            List<Student> students = studentService.searchStudents(skraceniNazivSmera, broj, godina);
            sviStudenti = FXCollections.observableList(students);
    		studentiTable.setItems(sviStudenti);
            
    		alert.setText("");
			
		} catch (Exception e) {
			alert.setText("Indeks nije u dobrom formatu!");
			alert.setTextFill(Color.FIREBRICK);
		}
	}
	
	
	public void handleOpenModalIndex(ActionEvent ae) {
		Student student = new Student();
		if (studentiTable.getSelectionModel().getSelectedItem() != null) {
			student = studentiTable.getSelectionModel().getSelectedItem();
			changeIndeksController.setStudent(student);
			mainViewManager.openModal("changeIndex");
			
			
		}else if(studentiTable.getItems().size() == 1) {
			student = studentiTable.getItems().get(0);
			changeIndeksController.setStudent(student);
			mainViewManager.openModal("changeIndex");
			
		}else if (studentiTable.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Morate prvo izabrati studenta!");
			alert.showAndWait();
		}
	}
	
	public void handleOpenModalDosije(ActionEvent event) {
		
		Student student = new Student();
		if (studentiTable.getSelectionModel().getSelectedItem() != null) {
			student = studentiTable.getSelectionModel().getSelectedItem();
			dosijeController.setS(student);
			mainViewManager.changeRoot("dosijeStudenta");
			
			
		}else if(studentiTable.getItems().size() == 1) {
			student = studentiTable.getItems().get(0);
			dosijeController.setS(student);
			mainViewManager.changeRoot("dosijeStudenta");
			
		}else if (studentiTable.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Morate prvo izabrati studenta!");
			alert.showAndWait();
		}
		
	}

}
