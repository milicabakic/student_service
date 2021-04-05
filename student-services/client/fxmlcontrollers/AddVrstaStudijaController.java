package studsluzba.client.fxmlcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import studsluzba.model.VrstaStudija;
import studsluzba.services.SifarniciService;

@Component
public class AddVrstaStudijaController {

	@Autowired
	SifarniciService sifarnici;
	
	@Autowired
	NastavnikController nastavnikController;
	
	@FXML TextField nazivNoveVrsteStudijaTf;
	@FXML TextField nazivNovogSkracenogNazivaVrsteStudijaTf;
	@FXML Label actionTarget;
	
	
	@FXML public void addVrstaStudija(ActionEvent event) {
		if(nazivNoveVrsteStudijaTf.getText().equals("")||nazivNovogSkracenogNazivaVrsteStudijaTf.getText().equals("")) {
			actionTarget.setTextFill(Color.FIREBRICK);
			actionTarget.setText("Nisu uneti svi podaci! Akcija nije izvršena.");
		}
		
		actionTarget.setText("");
		
		VrstaStudija vrstaStud = new VrstaStudija();
		vrstaStud.setNaziv(nazivNoveVrsteStudijaTf.getText());
		vrstaStud.setSkraceniNaziv(nazivNovogSkracenogNazivaVrsteStudijaTf.getText());
		sifarnici.saveVrstaStudija(vrstaStud);
		nastavnikController.updateVrsteStudija();
		closeStage(event);
	}
	
	private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }	
}
