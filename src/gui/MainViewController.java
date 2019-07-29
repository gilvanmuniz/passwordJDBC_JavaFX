package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.entities.Sites;
import model.services.SiteService;


public class MainViewController implements Initializable{
	
	private SiteService service = new SiteService();
	
	@FXML
	private MenuItem menuItemRegistration;
	
	@FXML
	private TextField textSearchSite;
	
	@FXML
	private Label labelResult;
	
	@FXML
	private Button buttonSearch;
	
	@FXML
	public void onMenuItemRegistrationAction() throws IOException {
		loadView("/gui/PasswordList.fxml", (PasswordListController controller)->{
			controller.setSiteService(new SiteService());
			controller.updateTableView();
		});
		//loadView2("/gui/PasswordList.fxml");
	}
	
	@FXML
	public void onButtonSearchAction() {
//		System.out.println("Busca acionada!!");
		String site = textSearchSite.getText();		
		showSiteByName(site);
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {			
	}
	
//	private synchronized void loadView2(String absoluteName ) {
//		try {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
//		VBox newVbox = loader.load();
//		Scene scene = new Scene(newVbox);
//		Stage stage = new Stage();
//		stage.setScene(scene);
//		stage.show();
//		PasswordListController controller = loader.getController();
//		controller.setSiteService(new SiteService());
//		controller.updateTableView();
//		
//		}catch (IOException e ) {
//			Alerts.showAlert("IOException", "Error Load View", e.getMessage(), AlertType.ERROR);
//		}
//		
///		initializeAction.accept(controller);
//	}
	
	private <T> void loadView(String absoluteName, Consumer<T> initializeAction ) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVbox = loader.load();
		Scene scene = new Scene(newVbox);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
		T controller = loader.getController();
		initializeAction.accept(controller);
	}
	
//	private synchronized void loadView(String absoluteName ) {
//		try {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
//		VBox newVbox = loader.load();
//		Scene scene = new Scene(newVbox);
//		Stage stage = new Stage();
//		stage.setScene(scene);
//		stage.show();
//		}catch (IOException e ) {
//			Alerts.showAlert("IOException", "Error Load View", e.getMessage(), AlertType.ERROR);
//		}
////		T controller = loader.getController();
////		initializeAction.accept(controller);
//	}
	
	public void showSiteByName(String name) {
		Sites obj = service.findByName(name);
		System.out.println(obj.getUserLogin() + " - " + obj.getPassword());
		labelResult.setText(obj.getUserLogin() + " - " + obj.getPassword());
	}
	
	

}//class end