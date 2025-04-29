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
	private Button btnCambiarPokémon;

	@FXML
	private Button btnCambiarZona;
	
    @FXML
    private Button btnSalir;
    
    @FXML
    private ImageView imgCambiarPokemon;

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

    //esto está mal luego se usará par los fondos pero es un prototipo
    void imagenFondo(ActionEvent event) {
    imgFondo.setImage(new Image(new File("./img/fondoHierba.png").toURI().toString()));
    imgFondo.setImage(new Image(new File("./img/fondoCascada.png").toURI().toString()));
    imgFondo.setImage(new Image(new File("./img/fondoPiedra.png").toURI().toString()));
    imgFondo.setImage(new Image(new File("./img/fondoPlaya.png").toURI().toString()));
    imgFondo.setImage(new Image(new File("./img/fondoVolcan.png").toURI().toString()));
    imgFondo.setImage(new Image(new File("./img/fondoNoche.png").toURI().toString()));
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
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
    	    Parent root = loader.load();

    	    MenuController menuController = loader.getController();
    	    menuController.init(entrenador, stage, loginController, null, null, null, null, null, null);

    	    Scene scene = new Scene(root);
    	    stage.setScene(scene);
    	    stage.setTitle("Menú");
    	    stage.show();
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}

    }

}
