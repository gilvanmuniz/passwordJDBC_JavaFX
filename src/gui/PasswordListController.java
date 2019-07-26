package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Sites;
import model.services.SiteService;


public class PasswordListController implements Initializable {
	
	private SiteService service;
	
	@FXML
	private TableView<Sites> tableViewSites;
	
	@FXML
	private TableColumn<Sites, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Sites, String> tableColumnUserLogin;
	
	@FXML
	private TableColumn<Sites, String> tableColumnPassword;
	
	@FXML
	private TableColumn<Sites, String> tableColumnSite;
	
	@FXML
	private Button btnNew;
	
	private ObservableList<Sites> obsList;
	
	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Sites obj = new Sites();
		createDialogForm(obj, "/gui/SitesForm.fxml", parentStage);
	}
	
	public void setSiteService(SiteService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();		
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnUserLogin.setCellValueFactory(new PropertyValueFactory<>("userLogin"));
		tableColumnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
		tableColumnSite.setCellValueFactory(new PropertyValueFactory<>("site"));
		
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Sites> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewSites.setItems(obsList);
	}
	
	private void createDialogForm(Sites obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			SitesFormController controller = loader.getController();
			controller.setSites(obj);
			controller.setSiteService(new SiteService());
			controller.updateSitesData();
						
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Site and password");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch(IOException e) {
			Alerts.showAlert("IO Excption", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
