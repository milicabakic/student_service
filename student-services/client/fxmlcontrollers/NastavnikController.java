package studsluzba.client.fxmlcontrollers;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import studsluzba.client.MainViewManager;
import studsluzba.model.Nastavnik;
import studsluzba.model.ObrazovanjeNastavnika;
import studsluzba.model.VisokoskolskaUstanova;
import studsluzba.model.VrstaStudija;
import studsluzba.model.Zvanje;
import studsluzba.services.NastavnikService;
import studsluzba.services.SifarniciService;

@Component
public class NastavnikController {

	@Autowired
	NastavnikService nastavnikService;
	
	@Autowired
	SifarniciService sifarnici;
	
	@Autowired
	MainViewManager mainViewManager;

	
	//Osnovni podaci
	@FXML private TextField imeTf;
	@FXML private TextField prezimeTf;
	@FXML private TextField srednjeImeTf;
	@FXML private TextField emailTf;
	
    //Zvanje
	@FXML private TextField nazivZvanjaTf;
	@FXML private DatePicker datumIzboraDp;
	@FXML private TextField naucnaOblastTf;
	@FXML private TableView<Zvanje> zvanjeTable;
	
	//Obrazovanje
	@FXML private ComboBox<VisokoskolskaUstanova> visokoskolskeUstanoveCb;
	@FXML private ComboBox<VrstaStudija> vrsteStudijaCb;
	@FXML private TableView<ObrazovanjeNastavnika> obrazovanjeTable;
	
	private Nastavnik nastavnik;
	private List<Zvanje> zvanja;
	private List<ObrazovanjeNastavnika> zavrseneSkole;
	private ObservableList<Zvanje> svaZvanjaNastavnika;
	private ObservableList<ObrazovanjeNastavnika> svaObrazovanja;
	
	
	@FXML public void initialize() {	
		zvanja = new ArrayList<Zvanje>();
		zavrseneSkole = new ArrayList<ObrazovanjeNastavnika>();
		
		List<VisokoskolskaUstanova> skole = sifarnici.getVisokoskolskeUstanove();
		visokoskolskeUstanoveCb.setItems(FXCollections.observableArrayList(skole));
		List<VrstaStudija> vrsteStudija = sifarnici.getVrsteStudija();
		vrsteStudijaCb.setItems(FXCollections.observableArrayList(vrsteStudija));
		
		svaZvanjaNastavnika = FXCollections.observableArrayList(zvanja);
		zvanjeTable.setItems(svaZvanjaNastavnika);
		
		svaObrazovanja = FXCollections.observableArrayList(zavrseneSkole);
		obrazovanjeTable.setItems(svaObrazovanja);
		
		nastavnik = null;
	}
	
	
	public void handleSaveNastavnik(ActionEvent event) {
		if(nastavnik != null) {
			nastavnikService.updateNastavnik(nastavnik);
			nastavnikService.updateZvanje(nastavnik, zvanja);
			nastavnikService.updateObrazovanje(nastavnik, zavrseneSkole);
			return;
		}
		
		nastavnik = nastavnikService.saveNastavnik(imeTf.getText(), prezimeTf.getText(), srednjeImeTf.getText(), 
				emailTf.getText(), zvanja, zavrseneSkole);
		nastavnikService.updateZvanje(nastavnik, zvanja);
		nastavnikService.updateObrazovanje(nastavnik, zavrseneSkole);
	}
	
	
	public void handleOpenModalVisokoskolskeUstanove(ActionEvent event) {
		mainViewManager.openModal("addVisokoskolskaUstanova");
	}
	
	public void updateVisokoskolskeUstanove() {
		List<VisokoskolskaUstanova> skole = sifarnici.getVisokoskolskeUstanove();
		visokoskolskeUstanoveCb.setItems(FXCollections.observableArrayList(skole));		
	}
	
	
	
	public void handleOpenModalVrsteStudija(ActionEvent event) {
		mainViewManager.openModal("addVrstaStudija");
	}
	
	public void updateVrsteStudija() {
		List<VrstaStudija> vrsteStudija = sifarnici.getVrsteStudija();
		vrsteStudijaCb.setItems(FXCollections.observableArrayList(vrsteStudija));		
	}
	
	
	
	public void handleAddZvanje(ActionEvent event) {
		Zvanje zvanje = nastavnikService.saveZvanjeNastavnika(nazivZvanjaTf.getText(), convertToDate(datumIzboraDp.getValue()), naucnaOblastTf.getText());
		zvanja.add(zvanje);
		svaZvanjaNastavnika.add(zvanje);
		if(nastavnik != null) {
			nastavnik.setZvanje(zvanja);
		}
		
		nazivZvanjaTf.clear();
		datumIzboraDp.getEditor().clear();
		naucnaOblastTf.clear();
	}
	
	public void handleAddObrazovanje(ActionEvent event) {
		ObrazovanjeNastavnika obrazovanje = nastavnikService.saveObrazovanjeNastavnika(vrsteStudijaCb.getSelectionModel().getSelectedItem(), visokoskolskeUstanoveCb.getSelectionModel().getSelectedItem());
		zavrseneSkole.add(obrazovanje);
		svaObrazovanja.add(obrazovanje);
		if(nastavnik != null) {
			nastavnik.setObrazovanje(zavrseneSkole);
		}
		
		vrsteStudijaCb.getSelectionModel().clearSelection();
		visokoskolskeUstanoveCb.getSelectionModel().clearSelection();
	}
	
	
	
	private Date convertToDate(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}
}
