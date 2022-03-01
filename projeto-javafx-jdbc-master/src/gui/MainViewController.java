package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	//Este método carrega uma cena na tela principal, a partir de uma referencia da tela principal, seja, carrega dentro da propria tela principal, sem chamar outra tela
	private synchronized void loadView (String absoluteNameView) {
		
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteNameView));
			VBox newVbox = loader.load();
			
			//pega uma referencia para a cena da tela principal
			Scene mainScene = Main.getMainScene();
			//Aqui, pego a referencia para o VBox da tela principal
			VBox mainVBox =  (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			//pego o menu da tela principal, com a itenção de replicado, mantendo-o na tela que será carregada.
			Node mainMenu = mainVBox.getChildren().get(0);
			
			//Limpo todos os filhos do Vbox da tela principal para que possa carrega-lo com os dados da nova tela
			mainVBox.getChildren().clear();
			
			//Adiciono o menu que é padrão em todas as telas, e carrego todos os filhos da tela que está sendo carregada
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());
			
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar Página", e.getMessage(), AlertType.ERROR);
			
		}
	}

	
}
