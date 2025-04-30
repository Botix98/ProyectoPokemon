package controller;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
	private Button btnCambiarPokemon;

	@FXML
    private Button btnCambioCascada;

    @FXML
    private Button btnCambioHierba;

    @FXML
    private Button btnCambioNoche;

    @FXML
    private Button btnCambioPiedra;

    @FXML
    private Button btnCambioPlaya;

    @FXML
    private Button btnCambioVolcan;
	
    @FXML
    private Button btnSalir;
    
    @FXML
    private ImageView imgCambiarPokemon;
    
    @FXML
    private ImageView imgCambioCascada;

    @FXML
    private ImageView imgCambioHierba;

    @FXML
    private ImageView imgCambioNoche;

    @FXML
    private ImageView imgCambioPiedra;

    @FXML
    private ImageView imgCambioPlaya;

    @FXML
    private ImageView imgCambioVolcan;

    @FXML
    private ImageView imgFondo;
    
    @FXML
    private ImageView imgPokeball;

    @FXML
    private ImageView imgPokemon;

    @FXML
    private ImageView imgSonido;
    
    @FXML
    private ImageView imgSuperball;

    @FXML
    private ImageView imgUltraball;

    @FXML
    private Label lblNivelPokemon;

    @FXML
    private Label lblNombrePokemon;
    
    @FXML
    private Label lblNumeroPokeballs;

    @FXML
    private Label lblNumeroSuperballs;

    @FXML
    private Label lblNumeroUltraballs;

    @FXML
    private Label lblRatioCatchPokeball;

    @FXML
    private Label lblRatioCatchSuperball;

    @FXML
    private Label lblRatioCatchUltraballs;

    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
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
    
    public void initialize() {
    	SonidoController.reproducir("C:/ProyectoPokemon/sonidos/Captura.mp3");
    }
    
    @FXML
    void cambiarFondoCascada(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoCascada.png").toURI().toString()));
    }

    @FXML
    void cambiarFondoHierba(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoHierba.png").toURI().toString()));
    }

    @FXML
    void cambiarFondoNoche(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoNoche.png").toURI().toString()));
    }

    @FXML
    void cambiarFondoPiedra(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoPiedra.png").toURI().toString()));
    }

    @FXML
    void cambiarFondoPlaya(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoPlaya.png").toURI().toString()));
    }

    @FXML
    void cambiarFondoVolcan(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoVolcan.png").toURI().toString()));
    }

    @FXML
    void salir(ActionEvent event) {
    	try {
    		SonidoController.detener("C:/ProyectoPokemon/sonidos/Captura.mp3");
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
