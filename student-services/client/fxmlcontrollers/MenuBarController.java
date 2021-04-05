package studsluzba.client.fxmlcontrollers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import studsluzba.client.MainViewManager;

@Component
public class MenuBarController { 
	
	 
	@Autowired
	MainViewManager mainViewManager;
	
		
	@FXML
	private MenuBar menuBar;
	
	
	public void openStudProgrami(ActionEvent event) {
		mainViewManager.changeRoot("studProgrami");
	}

	public void openNewStudent(ActionEvent event) {
		mainViewManager.changeRoot("newStudent");
	}
	
	public void openNewNastavnik(ActionEvent event) {
		mainViewManager.changeRoot("newNastavnik");
	}
	
	public void openNewPredmet(ActionEvent event) {
		mainViewManager.changeRoot("newPredmet");
	}
	
	public void openFindPredmet(ActionEvent event) {
		mainViewManager.changeRoot("findPredmet");
	}

	public void openFindNastavnik(ActionEvent event) {
		mainViewManager.changeRoot("findNastavnik");
	}	
	
	public void openSkolskaGodina(ActionEvent event) {
		mainViewManager.changeRoot("skolskeGodine");
	}
	
	public void openPredajePredmet(ActionEvent event) {
		mainViewManager.changeRoot("predajePredmet");
	}
	
	public void openIspitniRok(ActionEvent event) {
		mainViewManager.changeRoot("ispitniRokovi");
	}
	
	public void openFindIspit(ActionEvent event) {
		mainViewManager.changeRoot("findIspit");
	}	
	
	public void openFindStudentiNaPredmetu(ActionEvent event) {
		mainViewManager.changeRoot("findStudentiNaPredmetu");
	}
	
	public void openFindPrijavljenIspit(ActionEvent event) {
		mainViewManager.changeRoot("findPrijavljenIspit");
	}

	public void openFindRezultatiIspita(ActionEvent event) {
		mainViewManager.changeRoot("findRezultatiIspita");
	}	
	
	public void openStudenti(ActionEvent event) {
		mainViewManager.changeRoot("studenti");
	}
	
	public void openImport(ActionEvent event) {
		mainViewManager.changeRoot("importData");
	}

	public void openIzvestaji(ActionEvent event) {
		mainViewManager.changeRoot("izvestaji");
	}
}
