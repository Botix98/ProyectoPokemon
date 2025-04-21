package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Entrenador;

public class CentroPokemonController {

	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	
    @FXML
    private Button btnAtras;

    @FXML
    private Button btnCurarEquipo;

    @FXML
    private ImageView imgEnfermera;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgPokemon1;

    @FXML
    private ImageView imgPokemon2;

    @FXML
    private ImageView imgPokemon3;

    @FXML
    private ImageView imgPokemon4;

    @FXML
    private ImageView imgPokemon5;

    @FXML
    private ImageView imgPokemon6;

    @FXML
    private ImageView imgSonido;

    @FXML
    private ProgressBar pbPokemon1;

    @FXML
    private ProgressBar pbPokemon2;

    @FXML
    private ProgressBar pbPokemon3;

    @FXML
    private ProgressBar pbPokemon4;

    @FXML
    private ProgressBar pbPokemon5;

    @FXML
    private ProgressBar pbPokemon6;

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
    	    menuController.init(entrenador, stage, loginController, null, null);

    	    Scene scene = new Scene(root);
    	    stage.setScene(scene);
    	    stage.setTitle("Menú");
    	    stage.show();
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    }

	public void setStage(Stage primaryStage) {
		stage = primaryStage;
		
	}
}
