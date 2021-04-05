package studsluzba.client.fxmlcontrollers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import studsluzba.client.MainViewManager;
import studsluzba.client.importer.CSVPoeniImporter;

@Component
public class ImportController {

	@FXML Label nazivFajla;
	@FXML Label izvestajImportLabel;
	
	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	CSVPoeniImporter csvImporter;
	
	private File fajl;

	
	
	public void izaberiFajl(ActionEvent event) {	
		fajl = mainViewManager.openFileChooser();
		nazivFajla.setText(fajl.getName());
		
	}
	
	public void zapocniImport(ActionEvent ecent) {		
		String poruka = csvImporter.importCSV(fajl);
		izvestajImportLabel.setText(poruka);
	}
}
