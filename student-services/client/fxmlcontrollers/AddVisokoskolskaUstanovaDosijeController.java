package studsluzba.client.fxmlcontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import studsluzba.coders.CoderFactory;
import studsluzba.coders.CoderType;
import studsluzba.coders.SimpleCode;
import studsluzba.model.VisokoskolskaUstanova;
import studsluzba.services.SifarniciService;

@Component
public class AddVisokoskolskaUstanovaDosijeController {
	
	@Autowired
	SifarniciService sifarnici;
	
	@Autowired DosijeController dosijeController;
	
	@FXML TextField nazivNoveVisokoskolskeUstanoveTf;
	@FXML ComboBox<SimpleCode> mestoNoveVisokoskolskeUstanoveCb;
	@FXML Label actionTarget;
	
	@Autowired
	CoderFactory coderFactory;
	
	String flag;
	
	
	@FXML public void addVisokoskolskuUstanovu(ActionEvent event) {
		if(mestoNoveVisokoskolskeUstanoveCb.getValue() == null || nazivNoveVisokoskolskeUstanoveTf.getText().equals("")) {
			actionTarget.setTextFill(Color.FIREBRICK);
			actionTarget.setText("Nisu uneti svi podaci! Akcija nije izvršena.");
			return;
		}
		
		actionTarget.setText("");
		
		VisokoskolskaUstanova skola = new VisokoskolskaUstanova();
		skola.setMesto(mestoNoveVisokoskolskeUstanoveCb.getValue().toString());
		skola.setNaziv(nazivNoveVisokoskolskeUstanoveTf.getText());
		sifarnici.saveVisokoskolskaUstanova(skola);
		dosijeController.updateVisokoskolskeUstanove();
		closeStage(event);
	}
	
	@FXML
	public void initialize() {
		mestoNoveVisokoskolskeUstanoveCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.MESTO).getCodes()));
		mestoNoveVisokoskolskeUstanoveCb.setValue(new SimpleCode("Beograd"));
	}
	
	private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
	
	
}
