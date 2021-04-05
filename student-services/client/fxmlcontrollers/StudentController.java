package studsluzba.client.fxmlcontrollers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import studsluzba.client.MainViewManager;
import studsluzba.coders.CoderFactory;
import studsluzba.coders.CoderType;
import studsluzba.coders.SimpleCode;
import studsluzba.model.SrednjaSkola;
import studsluzba.model.StudProgram;
import studsluzba.model.Student;
import studsluzba.model.StudentskiIndeks;
import studsluzba.model.VisokoskolskaUstanova;
import studsluzba.services.SifarniciService;
import studsluzba.services.StudIndeksService;
import studsluzba.services.StudProgramService;
import studsluzba.services.StudentService;

@Component
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	StudIndeksService studIndeksService;
	
	@Autowired
	StudProgramService studProgramService;

	@Autowired
	SifarniciService sifarniciService;

	@Autowired
	MainViewManager mainViewManager;

	// forma ce se koristiti za unos novog i za azuriranje postojeceg studenta
	private Student s;
	private StudentskiIndeks indeks;
	int flag = 0;

	@FXML
	private TextField imeTf;
	@FXML
	private TextField prezimeTf;
	@FXML
	private TextField srednjeImeTf;

	@FXML
	private RadioButton muski;
	@FXML
	private RadioButton zenski;

	@FXML
	private TextField jmbgTf;
	@FXML
	private DatePicker datumRodjenjaDp;
	@FXML
	private DatePicker datumAktivacijeDp;
	@FXML
	ComboBox<SimpleCode> mestoRodjenjaCb;

	@FXML
	private TextField emailPrivatniTf;
	@FXML
	private TextField emailFakultetTf;

	@FXML
	TextField brojTelefonaTf;
	@FXML
	TextField adresaTf;

	@FXML
	ComboBox<SimpleCode> mestoStanovanjaCb;

	@FXML
	ComboBox<SimpleCode> drzavaRodjenjaCb;

	@FXML
	ComboBox<SimpleCode> drzavljanstvoCb;

	@FXML
	TextField nacionalnostTf;

	@FXML
	TextField brojLicneKarteTf;
	
	@FXML
	TextField godinaUpisaTf;
	
	@FXML
	TextField brojIndeksaTf;
	
	@FXML
	TextField godinaIndeksaTf;
	
	@FXML
	ComboBox<StudProgram> studProgramCb;

	// prvi upis

	@FXML
	ComboBox<SrednjaSkola> srednjaSkolaCb;

	@FXML
	TextField uspehSrednjaSkolaTf;

	@FXML
	TextField uspehPrijemniTf;

	@FXML
	ComboBox<VisokoskolskaUstanova> visokoskolskaUstanovaCb;
	
	@Autowired
	CoderFactory coderFactory;

	@FXML
	public void initialize() {
		s = null;
		indeks = null;
		drzavaRodjenjaCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.DRZAVA).getCodes()));	
		drzavaRodjenjaCb.setValue(new SimpleCode("Serbia"));
		
		
		drzavljanstvoCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.DRZAVA).getCodes()));	
		drzavljanstvoCb.setValue(new SimpleCode("Serbia"));
		
		mestoRodjenjaCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.MESTO).getCodes()));
		mestoRodjenjaCb.setValue(new SimpleCode("Beograd"));
		
		mestoStanovanjaCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.MESTO).getCodes()));
		mestoStanovanjaCb.setValue(new SimpleCode("Beograd"));
		
		List<SrednjaSkola> srednjeSkole = sifarniciService.getSrednjeSkole();
		srednjaSkolaCb.setItems(FXCollections.observableArrayList(srednjeSkole));
		
		List<VisokoskolskaUstanova> visokeUstanove = sifarniciService.getVisokoskolskeUstanove();
		visokoskolskaUstanovaCb.setItems(FXCollections.observableArrayList(visokeUstanove));
		
		List<StudProgram> studProgrami = studProgramService.loadAll();
		studProgramCb.setItems(FXCollections.observableArrayList(studProgrami));
	}

	public void handleOpenModalSrednjeSkole(ActionEvent ae) {
		mainViewManager.openModal("addSrednjaSkola");
	}

	public void handleOpenModalVisokoskolskeUstanove(ActionEvent ae) {
		mainViewManager.openModal("addVisaUstanovaForStudent");
	}

	public void handleSaveStudent(ActionEvent event) {
		if(s == null) {
			s = new Student();
		}
		
		s.setIme(imeTf.getText());
		s.setPrezime(prezimeTf.getText());
		s.setSrednjeIme(srednjeImeTf.getText());
		
		if (muski.isSelected()) s.setPol("M");
		else s.setPol("Z");
		
		s.setJmbg(jmbgTf.getText());
		
		LocalDate localDate = datumRodjenjaDp.getValue();
		if (localDate != null) {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			s.setDatumRodjenja(date);
		}
		
		s.setMestoRodjenja(mestoRodjenjaCb.getValue().toString());
		s.setEmailPrivatni(emailPrivatniTf.getText());
		s.setEmailFakultet(emailFakultetTf.getText());
		s.setBrojTelefona(brojTelefonaTf.getText());
		s.setAdresa(adresaTf.getText());
		s.setMestoStanovanja(mestoStanovanjaCb.getValue().toString());
		s.setDrzavaRodjenja(drzavaRodjenjaCb.getValue().toString());
		s.setDrzavljanstvo(drzavljanstvoCb.getValue().toString());
		s.setNacionalnost(nacionalnostTf.getText());
		s.setLicnaKarta(brojLicneKarteTf.getText());
		if (isNumeric(godinaUpisaTf.getText())) s.setGodinaUpisa(Integer.parseInt(godinaUpisaTf.getText()));
		s.setSrednjaSkola(srednjaSkolaCb.getValue());
		
		if (isNumeric(uspehSrednjaSkolaTf.getText())) s.setUspehIzSrednje(Double.parseDouble(uspehSrednjaSkolaTf.getText()));
		if (isNumeric(uspehPrijemniTf.getText())) s.setUspehSaPrijemnog(Double.parseDouble(uspehPrijemniTf.getText()));
		s.setPrelaz(visokoskolskaUstanovaCb.getValue());
		
		
		if(indeks == null) {
			indeks = new StudentskiIndeks();
			flag = 1;
		}
		indeks.setStudProgram(studProgramCb.getValue());
		if (isNumeric(brojIndeksaTf.getText())) indeks.setBroj(Integer.parseInt(brojIndeksaTf.getText()));
		if (isNumeric(godinaIndeksaTf.getText())) indeks.setGodina(Integer.parseInt(godinaIndeksaTf.getText()));
		indeks.setAktivan(true);
		localDate = datumAktivacijeDp.getValue();
		if (localDate != null) {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			indeks.setDatumAktivacije(date);
		}
		
		studentService.saveStudent(s);
		indeks.setStudent(s);
		studIndeksService.saveStudIndeks(indeks);
		if(flag == 1) {
			Student stud = studentService.findStudentByIndex(s);
			stud.getIndeksi().add(indeks);
			studentService.saveStudent(stud);
			flag = 0;
		}
			
		
	}

	public void updateSrednjeSkole() {
		List<SrednjaSkola> srednjeSkole = sifarniciService.getSrednjeSkole();
		System.out.println(srednjeSkole);
		srednjaSkolaCb.setItems(FXCollections.observableArrayList(srednjeSkole));
	}
	
	public void updateStudProgram() {
		List<StudProgram> studProgrami = studProgramService.loadAll();
		studProgramCb.setItems(FXCollections.observableArrayList(studProgrami));
	}

	public void updateVisokoskolskeUstanove() {
		List<VisokoskolskaUstanova> visokoskolskeUstanove = sifarniciService.getVisokoskolskeUstanove();
		visokoskolskaUstanovaCb.setItems(FXCollections.observableArrayList(visokoskolskeUstanove));
	}
	
	private boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}

	public Student getStudent() {
		return s;
	}

	public void setStudent(Student student) {
		this.s = student;
	}
}
