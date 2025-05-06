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

public class CajaController {
	
	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;

    @FXML
    private Button btnSalir;
    
    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgPokemonCaja1;

    @FXML
    private ImageView imgPokemonCaja10;

    @FXML
    private ImageView imgPokemonCaja11;

    @FXML
    private ImageView imgPokemonCaja12;

    @FXML
    private ImageView imgPokemonCaja13;

    @FXML
    private ImageView imgPokemonCaja14;

    @FXML
    private ImageView imgPokemonCaja15;

    @FXML
    private ImageView imgPokemonCaja16;

    @FXML
    private ImageView imgPokemonCaja17;

    @FXML
    private ImageView imgPokemonCaja18;

    @FXML
    private ImageView imgPokemonCaja19;

    @FXML
    private ImageView imgPokemonCaja2;

    @FXML
    private ImageView imgPokemonCaja20;

    @FXML
    private ImageView imgPokemonCaja21;

    @FXML
    private ImageView imgPokemonCaja22;

    @FXML
    private ImageView imgPokemonCaja23;

    @FXML
    private ImageView imgPokemonCaja24;

    @FXML
    private ImageView imgPokemonCaja25;

    @FXML
    private ImageView imgPokemonCaja26;

    @FXML
    private ImageView imgPokemonCaja27;

    @FXML
    private ImageView imgPokemonCaja28;

    @FXML
    private ImageView imgPokemonCaja29;

    @FXML
    private ImageView imgPokemonCaja3;

    @FXML
    private ImageView imgPokemonCaja30;

    @FXML
    private ImageView imgPokemonCaja4;

    @FXML
    private ImageView imgPokemonCaja5;

    @FXML
    private ImageView imgPokemonCaja6;

    @FXML
    private ImageView imgPokemonCaja7;

    @FXML
    private ImageView imgPokemonCaja8;

    @FXML
    private ImageView imgPokemonCaja9;

    @FXML
    private ImageView imgPokemonEquipo1;

    @FXML
    private ImageView imgPokemonEquipo2;

    @FXML
    private ImageView imgPokemonEquipo3;

    @FXML
    private ImageView imgPokemonEquipo4;

    @FXML
    private ImageView imgPokemonEquipo5;

    @FXML
    private ImageView imgPokemonEquipo6;

    @FXML
    private ImageView imgPokemonGrande;

    @FXML
    private ImageView imgSonido;

    @FXML
    private Label lblCaja1;

    public void init(Entrenador entrenador, Stage stage, LoginController loginController, MenuController menuController) {
		this.entrenador = entrenador;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
		
	}
    
	public void init(Entrenador entrenador2, Stage stage2, LoginController loginController2,
			EquipoController equipoController) {
		// TODO Auto-generated method stub
		
	}
    
    
    public void initialize() {
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Caja.mp3");
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
    void salir(ActionEvent event) {
    	try {
    		SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/Caja.mp3");
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
