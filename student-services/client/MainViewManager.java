package studsluzba.client;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Component
public class MainViewManager {
	
		
		@Autowired
		ContextFXMLLoader appFXMLLoader; 
		
		private Scene scene;
		private Stage mainStage;
		
		
		public Scene createScene() {	  
		  try {		  
			  FXMLLoader loader = appFXMLLoader.getLoader(MainViewManager.class.getResource("/fxml/main.fxml"));
			  BorderPane borderPane = loader.load();
			  this.scene = new Scene(borderPane);			  
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
		  return this.scene;
		 }			
		
		public void changeRoot(String fxml) {
			FXMLLoader loader = appFXMLLoader.getLoader(MainViewManager.class.getResource("/fxml/"+fxml+".fxml"));
			try {
				scene.setRoot(loader.load());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void openModal(String fxml) {
			FXMLLoader loader = appFXMLLoader.getLoader(MainViewManager.class.getResource("/fxml/"+fxml+".fxml"));
			try {
				Parent parent = loader.load();
				Scene scene = new Scene(parent, 400, 300);
		        Stage stage = new Stage();
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.setScene(scene);
		        stage.showAndWait();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void openModal(String fxml, int width, int height) {
			FXMLLoader loader = appFXMLLoader.getLoader(MainViewManager.class.getResource("/fxml/"+fxml+".fxml"));
			try {
				Parent parent = loader.load();
				Scene scene = new Scene(parent, width, height);
		        Stage stage = new Stage();
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.setScene(scene);
		        stage.showAndWait();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void openModal(String fxml, String flag) {
			FXMLLoader loader = appFXMLLoader.getLoader(MainViewManager.class.getResource("/fxml/"+fxml+".fxml"));
			try {
				Parent parent = loader.load();
				Scene scene = new Scene(parent);
		        Stage stage = new Stage();
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.setScene(scene);
		        stage.showAndWait();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void setMainStage(Stage stage) {
			this.mainStage = stage;
		}
		
		public Stage getMainStage() {
			return mainStage;
		}
		
		public File openFileChooser() {
			FileChooser fileChooser = new FileChooser();
			return fileChooser.showOpenDialog(mainStage);
			
		}
		
}


