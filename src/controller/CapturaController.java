package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Entrenador;

public class CapturaController {
	
	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;

	@FXML
    private Button btnCapturar;

    @FXML
    private Button btnSalir;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgPokemon;

    @FXML
    private ImageView imgSexoPokemon;

    @FXML
    private ImageView imgSonido;

    @FXML
    private Label lblNivelPokemon;

    @FXML
    private Label lblNombrePokemon;

    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
    }


    @FXML
    void activarDesactivarSonido(MouseEvent event) {

    }

    @FXML
    void salir(ActionEvent event) {
    	try {
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
    	    Parent root = loader.load();

    	    MenuController menuController = loader.getController();
    	    menuController.init(entrenador, stage, loginController, null, null, null, null);

    	    Scene scene = new Scene(root);
    	    stage.setScene(scene);
    	    stage.setTitle("Menú");
    	    stage.show();
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}

    }

}
