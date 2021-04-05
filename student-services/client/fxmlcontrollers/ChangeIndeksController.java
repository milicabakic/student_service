package studsluzba.client.fxmlcontrollers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import studsluzba.client.MainViewManager;
import studsluzba.model.StudProgram;
import studsluzba.model.Student;
import studsluzba.model.StudentskiIndeks;
import studsluzba.services.StudIndeksService;
import studsluzba.services.StudProgramService;
import studsluzba.services.StudentService;

@Component
public class ChangeIndeksController {

	@Autowired
	MainViewManager mainViewManager;

	@Autowired
	StudProgramService studProgramService;

	@Autowired
	StudentService studentService;

	@Autowired
	StudIndeksService studIndeksService;

	Student student;

	@FXML
	TextField godinaUpisaTf;

	@FXML
	TextField brojIndeksaTf;

	@FXML
	ComboBox<StudProgram> studProgramCb;

	@FXML
	private DatePicker datumAktivacijeDp;
	
	@FXML Label alert;

	@FXML
	protected void initialize() {
		List<StudProgram> studProgrami = studProgramService.loadAll();
		studProgramCb.setItems(FXCollections.observableArrayList(studProgrami));

	}

	public void handleOpenModalStudijskiProgram(ActionEvent ae) {
		mainViewManager.openModal("addStudProgram");
	}

	public void handleSaveIndex(ActionEvent event) {
		List<Student> students = new ArrayList<Student>();
		if (isNumeric(brojIndeksaTf.getText()) && isNumeric(godinaUpisaTf.getText()))
			students = studentService.searchStudents(studProgramCb.getValue().getSkraceniNaziv(),
					Integer.parseInt(brojIndeksaTf.getText()), Integer.parseInt(godinaUpisaTf.getText()));
		if (students.size() == 0) {
			StudentskiIndeks indeks = new StudentskiIndeks();
			indeks.setStudProgram(studProgramCb.getValue());
			if (isNumeric(brojIndeksaTf.getText()))
				indeks.setBroj(Integer.parseInt(brojIndeksaTf.getText()));
			if (isNumeric(godinaUpisaTf.getText()))
				indeks.setGodina(Integer.parseInt(godinaUpisaTf.getText()));
			indeks.setAktivan(true);
			LocalDate localDate = datumAktivacijeDp.getValue();
			if (localDate != null) {
				Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
				Date date = Date.from(instant);
				indeks.setDatumAktivacije(date);
			}

			indeks.setStudent(this.student);
			studIndeksService.saveStudIndeks(indeks);
			for (StudentskiIndeks si : studentService.findStudentByIndex(this.student).getIndeksi()) {
				si.setAktivan(false);
				studIndeksService.saveStudIndeks(si);
			}
			Student stud = studentService.findStudentByIndex(this.student);
			stud.getIndeksi().add(indeks);
			studentService.saveStudent(stud);
			closeStage(event);
		} else {
			alert.setText("Ovaj indeks već postoji!");
			alert.setTextFill(Color.FIREBRICK);
		}

	}

	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void closeStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Student getStudent() {
		return student;
	}

}
