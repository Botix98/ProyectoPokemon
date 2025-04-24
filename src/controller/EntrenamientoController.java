package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Entrenador;

public class EntrenamientoController {

	private Entrenador entrenador;
	private Stage stage;
	private LoginController loginController;
	private MenuController menuController;
	
    @FXML
    private Button btnAtacar;

    @FXML
    private Button btnAtaque1;

    @FXML
    private Button btnAtaque2;

    @FXML
    private Button btnAtaque3;

    @FXML
    private Button btnAtaque4;

    @FXML
    private Button btnHuir;

    @FXML
    private ImageView imgCuadricula;

    @FXML
    private ImageView imgEstadoPokemonEntrenador;

    @FXML
    private ImageView imgEstadoPokemonRival;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgInfoPokemonEntrenador;

    @FXML
    private ImageView imgInfoPokemonRival;

    @FXML
    private ImageView imgPokemonEntrenador;

    @FXML
    private ImageView imgPokemonRival;

    @FXML
    private ImageView imgSeleccionAccion;

    @FXML
    private ImageView imgSeleccionAtaque;

    @FXML
    private ImageView imgSexoPokemonEntrenador;

    @FXML
    private ImageView imgSexoPokemonRival;

    @FXML
    private ImageView imgSonido;

    @FXML
    private ProgressBar imgVidaPokemonRival;

    @FXML
    private Label lblNivelPokemonEntrenador;

    @FXML
    private Label lblNivelPokemonRival;

    @FXML
    private Label lblNombrePokemonEntrenador;

    @FXML
    private Label lblNombrePokemonRival;

    @FXML
    private Label lblPP;

    @FXML
    private Label lblTexto;

    @FXML
    private Label lblType;

    @FXML
    private Label lblVidaPokemonEntrenador;

    @FXML
    private ProgressBar pbVidaPokemonEntrenador;

    @FXML
    private ProgressBar pbXpPokemonEntrenador;

    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
    }
    
    @FXML
    void hiur(ActionEvent event) {
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
    
    @FXML
    void activarDesactivarSonido(MouseEvent event) {

    }

}
