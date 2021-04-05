package studsluzba.client.fxmlcontrollers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import studsluzba.client.reports.ReportForm;
import studsluzba.client.reports.ReportsManager;
import studsluzba.model.Ispit;
import studsluzba.model.IspitniRok;
import studsluzba.model.Nastavnik;
import studsluzba.model.Predmet;
import studsluzba.model.Student;
import studsluzba.model.StudentskiIndeks;
import studsluzba.services.IspitService;
import studsluzba.services.PredmetService;
import studsluzba.services.StudentService;

@Component
public class IzvestajIspitController {

	@Autowired
	IspitService ispitService;
	@Autowired
	PredmetService predmetService;
	@Autowired
	StudentService studentService;

	@Autowired
	ReportsManager reportsManager;

	@FXML
	private ComboBox<IspitniRok> ispitniRokoviCb;
	@FXML
	private ComboBox<Predmet> predmetiCb;
	@FXML
	private Label actionTarget;

	@FXML
	void initialize() {
		ispitniRokoviCb.setItems(FXCollections.observableArrayList(ispitService.loadAllIspitniRokovi()));
		predmetiCb.setItems(FXCollections.observableArrayList(predmetService.loadAll()));

		actionTarget.setTextFill(Color.FIREBRICK);
	}

	public void handleIzvestaj(ActionEvent event) {
		if (ispitniRokoviCb.getValue() == null || predmetiCb.getValue() == null) {
			actionTarget.setText("Svi podaci nisu uneti! Akcija nije izvršena.");
			return;
		}

		actionTarget.setText("");
		Predmet selektovaniPredmet = predmetiCb.getSelectionModel().getSelectedItem();
		IspitniRok rok = ispitniRokoviCb.getSelectionModel().getSelectedItem();
		Nastavnik nastavnik = predmetService.findNastavnikForPredajePredmet(selektovaniPredmet, rok.getSkolskaGodina());
		
		Ispit ispit = ispitService.findIspitByPredmetNastavnikIspitniRok(selektovaniPredmet, rok);
		
		List<ReportForm> reports = new ArrayList<ReportForm>();

		List<Student> studenti = ispitService.loadStudentsForIspit(
				ispitniRokoviCb.getSelectionModel().getSelectedItem(),
				predmetiCb.getSelectionModel().getSelectedItem());

		for (Student s : studenti) {
			ReportForm rf = new ReportForm();
			
			rf.setIme(decompose(s.getIme()));
			rf.setPrezime(decompose(s.getPrezime()));
			s = studentService.findStudentByIndex(s);
			StudentskiIndeks indeks = null;
			for (StudentskiIndeks si : s.getIndeksi()) {
				if (si.isAktivan()) {
					indeks = si;
					break;
				}
			}
			rf.setIndeks(indeks.getStudProgram().getSkraceniNaziv() + " " + indeks.getBroj() + "/" + indeks.getGodina());
			rf.setBrojIzlazakaNaIspit(ispitService.countIzlazakNaIspitForStudent(s, selektovaniPredmet));
			rf.setBrojPoena(ispitService.findPoeni(indeks, ispit));
			rf.setOcena(ispitService.findOcena(indeks, ispit));
			if(ispitService.findNapomena(indeks, ispit) == null) {
				rf.setNapomena("");
			}else {
				rf.setNapomena(ispitService.findNapomena(indeks, ispit));
			}
			reports.add(rf);
		}
		//System.out.println(reports);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("godina", rok.getSkolskaGodina().toString());
		params.put("predmet", selektovaniPredmet.getNazivPredmeta());
		params.put("SifraPredm", selektovaniPredmet.getId());
		params.put("nastavnik", nastavnik.toString());
		params.put("sifraNast", nastavnik.getIdNastavnik());
		params.put("ispitniRok", rok.getNaziv());

		reportsManager.openReport(reports, params, "zapisnikIspita");

	}
	
	private  String decompose(String s) {
		if (s.contains("Đ") || s.contains("đ")) {
			s = s.replace("Đ", "Dj");
			s = s.replace("đ", "dj");
		}
	    return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+","");
	}

}
