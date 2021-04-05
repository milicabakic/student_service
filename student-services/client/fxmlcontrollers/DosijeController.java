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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import studsluzba.client.MainViewManager;
import studsluzba.coders.CoderFactory;
import studsluzba.coders.CoderType;
import studsluzba.coders.SimpleCode;
import studsluzba.model.ObnovaGodine;
import studsluzba.model.PolozenPredmet;
import studsluzba.model.PredajePredmet;
import studsluzba.model.Predmet;
import studsluzba.model.SkolskaGodina;
import studsluzba.model.SrednjaSkola;
import studsluzba.model.Student;
import studsluzba.model.StudentskiIndeks;
import studsluzba.model.UpisGodine;
import studsluzba.model.VisokoskolskaUstanova;
import studsluzba.services.ObnovaGodineService;
import studsluzba.services.PredajePredmetService;
import studsluzba.services.PredmetService;
import studsluzba.services.SifarniciService;
import studsluzba.services.SkolskaGodinaService;
import studsluzba.services.StudIndeksService;
import studsluzba.services.StudProgramService;
import studsluzba.services.StudentService;
import studsluzba.services.UpisGodineService;

@Component
public class DosijeController {

	@Autowired StudentService studentService;
	
	@Autowired PredajePredmetService predajePredmetService;
	
	@Autowired UpisGodineService upisGodineService;
	
	@Autowired ObnovaGodineService obnovaGodineService;
	
	@Autowired PredmetService predmetService;
	
	@Autowired SkolskaGodinaService skolskaGodinaService;

	@Autowired StudIndeksService studIndeksService;

	@Autowired StudProgramService studProgramService;

	@Autowired SifarniciService sifarniciService;

	@Autowired MainViewManager mainViewManager;

	@FXML private TextField imeTf;
	@FXML private TextField prezimeTf;
	@FXML private TextField srednjeImeTf;
	@FXML private RadioButton muski;
	@FXML private RadioButton zenski;
	@FXML private TextField jmbgTf;
	@FXML private DatePicker datumRodjenjaDp;
	@FXML ComboBox<SimpleCode> mestoRodjenjaCb;
	
	@FXML private TextField emailPrivatniTf;
	@FXML private TextField emailFakultetTf;
	@FXML TextField brojTelefonaTf;
	@FXML TextField adresaTf;
    @FXML ComboBox<SimpleCode> mestoStanovanjaCb;
    @FXML ComboBox<SimpleCode> drzavaRodjenjaCb;
	@FXML ComboBox<SimpleCode> drzavljanstvoCb;
	@FXML TextField nacionalnostTf;
	@FXML TextField brojLicneKarteTf;
	@FXML TextField godinaUpisaTf;

	// prvi upis

	@FXML
	ComboBox<SrednjaSkola> srednjaSkolaCb;

	@FXML
	TextField uspehSrednjaSkolaTf;

	@FXML
	TextField uspehPrijemniTf;

	@FXML
	ComboBox<VisokoskolskaUstanova> visokoskolskaUstanovaCb;

	@FXML
	TableView<PolozenPredmet> polozeniPredmetiTable;

	private ObservableList<PolozenPredmet> polozeniPredmeti;
	
	@FXML
	TableView<PredajePredmet> predmetiKojeSlusaTable;
	
	private ObservableList<PredajePredmet> predmetiKojeSlusa;
	
	@FXML
	TableView<UpisGodine> upisGodineTable;
	
	private ObservableList<UpisGodine> upisiGodine;
	
	@FXML
	TableView<ObnovaGodine> obnovaGodineTable;
	
	private ObservableList<ObnovaGodine> obnovaGodine;
	
	@FXML
	TextField napomenaTf;
	
	@FXML
	DatePicker datumUpisaDp;
	
	@FXML
	ComboBox<SkolskaGodina> skolskaGodinaCb;
	
	@FXML
	ComboBox<Predmet> nepolozeniPredmetiCb;
	
	List<Predmet> nepolozeniPredmeti;
	
	@FXML
	TextField napomenaObnoveTf;
	
	@FXML
	DatePicker datumObnoveDp;
	
	@FXML
	ComboBox<SkolskaGodina> skolskaGodinaObnoveCb;
	
	@FXML
	ComboBox<Predmet> nepolozeniPredmetiZaObnovuCb;
	
	List<Predmet> nepolozeniPredmetiZaObnovu;
	
	@Autowired
	CoderFactory coderFactory;
	
	Student s;

	@FXML
	public void initialize() {
		
		this.nepolozeniPredmeti = new ArrayList<Predmet>();
		this.nepolozeniPredmetiZaObnovu = new ArrayList<Predmet>();
		
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
		List<SkolskaGodina> skolskeGodine  = skolskaGodinaService.loadAll();
		skolskaGodinaCb.setItems(FXCollections.observableList(skolskeGodine));
		skolskaGodinaObnoveCb.setItems(FXCollections.observableList(skolskeGodine));
		List<Predmet> nepolozeniPredmeti = predmetService.loadAll();
		nepolozeniPredmetiCb.setItems(FXCollections.observableList(nepolozeniPredmeti));
		nepolozeniPredmetiZaObnovuCb.setItems(FXCollections.observableList(nepolozeniPredmeti));

		imeTf.setText(s.getIme());
		prezimeTf.setText(s.getPrezime());
		srednjeImeTf.setText(s.getSrednjeIme());
		jmbgTf.setText(s.getJmbg());
		if (s.getDatumRodjenja() != null)
			datumRodjenjaDp.setValue(s.getDatumRodjenja().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		mestoRodjenjaCb.setValue(new SimpleCode(s.getMestoRodjenja()));
		drzavaRodjenjaCb.setValue(new SimpleCode(s.getDrzavaRodjenja()));
		if (s.getPol() != null && s.getPol().equals("M"))
			muski.setSelected(true);
		else if (s.getPol() != null && s.getPol().equals("Z"))
			zenski.setSelected(true);
		drzavljanstvoCb.setValue(new SimpleCode(s.getDrzavljanstvo()));
		nacionalnostTf.setText(s.getNacionalnost());
		brojLicneKarteTf.setText(s.getLicnaKarta());
		godinaUpisaTf.setText(String.valueOf(s.getGodinaUpisa()));

		emailFakultetTf.setText(s.getEmailFakultet());
		emailPrivatniTf.setText(s.getEmailPrivatni());
		brojTelefonaTf.setText(s.getBrojTelefona());
		adresaTf.setText(s.getAdresa());
		mestoStanovanjaCb.setValue(new SimpleCode(s.getMestoStanovanja()));
		srednjaSkolaCb.setValue(s.getSrednjaSkola());
		uspehSrednjaSkolaTf.setText(String.valueOf(s.getUspehIzSrednje()));
		uspehPrijemniTf.setText(String.valueOf(s.getUspehSaPrijemnog()));
		visokoskolskaUstanovaCb.setValue(s.getPrelaz());
		
		polozeniPredmeti = FXCollections.observableList(new ArrayList<PolozenPredmet>());
		for (StudentskiIndeks si : studentService.findStudentByIndex(s).getIndeksi()) {
			polozeniPredmeti.addAll(predmetService.getPolozeniPredmeti(si));
		}
		polozeniPredmetiTable.setItems(polozeniPredmeti);
		
		predmetiKojeSlusa = FXCollections.observableList(new ArrayList<PredajePredmet>());
		for (StudentskiIndeks si : studentService.findStudentByIndex(s).getIndeksi()) {
			if (si.isAktivan()) predmetiKojeSlusa.addAll(predajePredmetService.findPredajePredmet(si));
		}
		predmetiKojeSlusaTable.setItems(predmetiKojeSlusa);
		
		
		upisiGodine = FXCollections.observableList(upisGodineService.loadAll(s));
		upisGodineTable.setItems(upisiGodine);
		
		obnovaGodine = FXCollections.observableList(obnovaGodineService.loadAllObnove(s));
		obnovaGodineTable.setItems(obnovaGodine);
		
	}
	
	public void dodajNepolozeniPredmet() {
		this.nepolozeniPredmeti.add(nepolozeniPredmetiCb.getValue());
	}
	
	public void dodajNepolozeniPredmetZaObnovu(){
		this.nepolozeniPredmetiZaObnovu.add(nepolozeniPredmetiZaObnovuCb.getValue());
	}

	public void handleSaveStudent(ActionEvent event) {
		s.setIme(imeTf.getText());
		s.setPrezime(prezimeTf.getText());
		s.setSrednjeIme(srednjeImeTf.getText());

		if (muski.isSelected())
			s.setPol("M");
		else
			s.setPol("Z");

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
		if (isNumeric(godinaUpisaTf.getText()))
			s.setGodinaUpisa(Integer.parseInt(godinaUpisaTf.getText()));
		s.setSrednjaSkola(srednjaSkolaCb.getValue());
		if (isNumeric(uspehSrednjaSkolaTf.getText()))
			s.setUspehIzSrednje(Double.parseDouble(uspehSrednjaSkolaTf.getText()));
		if (isNumeric(uspehPrijemniTf.getText()))
			s.setUspehSaPrijemnog(Double.parseDouble(uspehPrijemniTf.getText()));
		s.setPrelaz(visokoskolskaUstanovaCb.getValue());

		studentService.saveStudent(s);
		
		
		
	}
	
	public void dodajUpisGodine() {
		UpisGodine ug = new UpisGodine();
		ug.setDatumUpisa(Date.from(datumUpisaDp.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		ug.setNapomena(napomenaTf.getText());
		ug.setSkolskaGodina(skolskaGodinaCb.getValue());
		if (nepolozeniPredmeti.size() > 0) ug.setNepolozenPredmet(nepolozeniPredmeti);
		for (StudentskiIndeks si : studentService.findStudentByIndex(s).getIndeksi()) {
			if (si.isAktivan()) {
				ug.setStudentskiIndeks(si);
				upisGodineService.saveUpisGodine(ug);
                StudentskiIndeks index = studIndeksService.findStudIndeksForUpisGodine(si);
                index.getUpisaneGodine().add(ug);
                studIndeksService.saveStudIndeks(index);
                upisiGodine.add(ug);
				break;
			}
		}
	}
	
	public void dodajObnovuGodine() {
		ObnovaGodine og = new ObnovaGodine();
		og.setDatumObnavljanja(Date.from(datumObnoveDp.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		og.setNapomena(napomenaObnoveTf.getText());
		og.setSkolskaGodina(skolskaGodinaObnoveCb.getValue());
		if (nepolozeniPredmetiZaObnovu.size() > 0) og.setNepolozeniPredmeti(nepolozeniPredmetiZaObnovu);
		for (StudentskiIndeks si : studentService.findStudentByIndex(s).getIndeksi()) {
			if (si.isAktivan()) {
				og.setIndeksStudenta(si);
				obnovaGodineService.saveObnovaGodine(og);
                StudentskiIndeks index = studIndeksService.findStudIndeksForObnovaGodine(si);
                index.getObnovljeneGodine().add(og);
                studIndeksService.saveStudIndeks(index);
                obnovaGodine.add(og);
				break;
			}
		}
	}

	public void updateSrednjeSkole() {
		List<SrednjaSkola> srednjeSkole = sifarniciService.getSrednjeSkole();
		srednjaSkolaCb.setItems(FXCollections.observableArrayList(srednjeSkole));
	}

	public void updateVisokoskolskeUstanove() {
		List<VisokoskolskaUstanova> visokoskolskeUstanove = sifarniciService.getVisokoskolskeUstanove();
		visokoskolskaUstanovaCb.setItems(FXCollections.observableArrayList(visokoskolskeUstanove));
	}

	public void handleOpenModalSrednjeSkole(ActionEvent ae) {
		mainViewManager.openModal("addSrednjaSkolaDosije");
	}
	
	public void handleOpenModalVisokoskolskeUstanove(ActionEvent ae) {
		mainViewManager.openModal("addVisokoskolskaUstanovaDosije");
	}

	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

}
