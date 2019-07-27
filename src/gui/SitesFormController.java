package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Sites;
import model.services.SiteService;

public class SitesFormController implements Initializable {
	private Sites entity;
	
	private SiteService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList();
	
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
	
	public void setSiteService(SiteService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
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
	public void onBtSaveAction(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		}
		catch (DbException e) {
			Alerts.showAlert("Error saving obj", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Sites getFormData() {
		Sites obj = new Sites();
		obj.setId(Utils.tryParsetoInt(txtId.getText()));
		obj.setUserLogin(txtUser.getText());
		obj.setPassword(txtPassword.getText());
		obj.setSite(txtSite.getText());		
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
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
