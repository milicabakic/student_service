package studsluzba.client.fxmlcontrollers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import studsluzba.coders.CoderFactory;
import studsluzba.coders.CoderType;
import studsluzba.coders.SimpleCode;
import studsluzba.model.IspitniRok;
import studsluzba.model.SkolskaGodina;
import studsluzba.services.IspitService;
import studsluzba.services.SkolskaGodinaService;

@Component
public class IspitniRokoviController {
	
	
	@Autowired
	IspitService ispitService;
	
	@Autowired
	SkolskaGodinaService godinaService;
	
	@FXML private ComboBox<SimpleCode> naziviCb;
	@FXML private ComboBox<SkolskaGodina> godineCb;
	@FXML private DatePicker poceciDb;
	@FXML private DatePicker zavrseciDb;
	@FXML private TableView<IspitniRok> ispitniRokoviTable;
	@FXML private Label actionTarget;
	
	private ObservableList<IspitniRok> rokovi;
	
	@Autowired
	CoderFactory coderFactory;
	
	
	@FXML
	public void initialize() {
		
		naziviCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.ISPITNI_ROK).getCodes()));	
		godineCb.setItems(FXCollections.observableArrayList(godinaService.loadAll()));
		
		rokovi = FXCollections.observableArrayList(ispitService.loadAllIspitniRokovi());
		ispitniRokoviTable.setItems(rokovi);
		
		actionTarget.setTextFill(Color.FIREBRICK);
	}
	
	public void handleSaveIspitniRok(ActionEvent event) {
		if(naziviCb.getValue() == null || poceciDb.getValue() == null || zavrseciDb.getValue() == null
				|| godineCb.getValue() == null) {
			actionTarget.setText("Svi podaci nisu uneti!Akcija nije izvršena.");
			return;
		}
		
		actionTarget.setText("");
		
		IspitniRok rok = ispitService.saveIspitniRok(naziviCb.getSelectionModel().getSelectedItem().toString(), 
				convertToDate(poceciDb.getValue()), convertToDate(zavrseciDb.getValue()), 
				godineCb.getSelectionModel().getSelectedItem());
		rokovi.add(rok);
		
		naziviCb.getSelectionModel().clearSelection();
		godineCb.getSelectionModel().clearSelection();
		zavrseciDb.getEditor().clear();
		poceciDb.getEditor().clear();
	}
	
	private Date convertToDate(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}

}
