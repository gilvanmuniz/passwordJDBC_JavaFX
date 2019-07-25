package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Sites;

public class SitesFormController implements Initializable {
	private Sites entity;
	
	@FXML 
	private TextField txtId;
	
	@FXML 
	private TextField txtUser;
	
	@FXML 
	private TextField txtPassword;
	
	@FXML 
	private TextField txtSite;
	
	@FXML 
	private Label labelErrorSite;
	
	@FXML 
	private Button btSave;
	
	@FXML 
	private Button btCancel;
	
	public void setSites(Sites entity) {
		this.entity = entity;
	}
	
	public void updateSitesData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtUser.setText(entity.getUserLogin());
		txtPassword.setText(entity.getPassword());
		txtSite.setText(entity.getSite());
	}
	
	@FXML
	public void onBtSaveAction() {
		System.out.println("OnBtSave");
	}
	
	@FXML
	public void onBtCancelAction() {
		System.out.println("OnBtCancel");
	}
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtUser, 60);
	}

}
