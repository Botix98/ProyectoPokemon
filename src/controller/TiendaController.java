package controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Entrenador;

public class TiendaController {

	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	private CentroPokemonController centroPokemonController;

	
    @FXML
    private Button btnCambiar;

    @FXML
    private Button btnSalir;

    @FXML
    private ImageView imgCambio;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgSonido;

    @FXML
    private ScrollPane sbMochila;

    @FXML
    private ScrollPane sbTienda;

    @FXML
    private Text txtMochila;

    @FXML
    private Text txtTienda;
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController, CentroPokemonController centroPokemonController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        this.centroPokemonController = centroPokemonController;
    }


    @FXML
    void activarDesactivarSonido(MouseEvent event) {
    	loginController.sonido();
    	if (loginController.sonido) {
    		imgSonido.setImage(new Image(new File("./img/conSonido.png").toURI().toString()));
    	}
    	else {
    		imgSonido.setImage(new Image(new File("./img/sinSonido.png").toURI().toString()));
    	}
    }

    @FXML
    void cambio(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CentroPokemon.fxml"));
            Parent root = loader.load();

            CentroPokemonController centroPokemonController = loader.getController();
            centroPokemonController.init(entrenador, stage, loginController, menuController, this);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Centro Pokémon");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void salir(ActionEvent event) {
    	try {
    		SonidoController.detener("C:/ProyectoPokemon/sonidos/Tienda.mp3");
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
    	    Parent root = loader.load();

    	    MenuController menuController = loader.getController();
    	    menuController.init(entrenador, stage, loginController, null, null, null, null, null, null, null);

    	    Scene scene = new Scene(root);
    	    stage.setScene(scene);
    	    stage.setTitle("Menú");
    	    stage.show();
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    }

}
