package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	public void onBtNewAction() {
		System.out.println("Novo Site!!!");
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

}
