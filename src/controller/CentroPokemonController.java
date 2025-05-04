package controller;

import java.io.File;
import java.sql.Connection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import dao.ConexionBD;
import dao.PokemonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Entrenador;
import model.Pokemon;

public class CentroPokemonController {

	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	private TiendaController tiendaController;
	private LinkedList<Pokemon> equipo;
	
    @FXML
    private Button btnAtras;

    @FXML
    private Button btnBucle;
    
    @FXML
    private Button btnCurarEquipo;
    
    @FXML
    private ImageView imgBucle;

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
    
    Connection con = ConexionBD.getConnection();


    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController, TiendaController tiendaController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        this.tiendaController = tiendaController;

    }
    
    public void initialize() {
    	SonidoController.reproducir("C:/ProyectoPokemon/sonidos/CentroPokemon.mp3");
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
        	SonidoController.detener(null);
        	SonidoController.reproducir("C:/ProyectoPokemon/sonidos/Tienda.mp3");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Tienda.fxml"));
            Parent root = loader.load();

            TiendaController tiendaController = loader.getController();
            tiendaController.init(entrenador, stage, loginController, menuController, this);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Tienda");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void curarEquipo(ActionEvent event) {
    	SonidoController.reproducir("C:/ProyectoPokemon/sonidos/CurarPokemon.mp3");
    	List<Pokemon> equipo = Arrays.asList(entrenador.getEquipo());
    	for(int i = 0; i < 6; i++) {
    		if (equipo.get(i) != null) {
    			PokemonDAO.actualizarVitalidadPokemon(con, equipo.get(i));
    			PokemonDAO.actualizarEstadoPokemon(con, equipo.get(i));
    		}
    	}
    }
    
    @FXML
    void salir(ActionEvent event) {
    	try {
    		SonidoController.detener(null);
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

	public void setStage(Stage primaryStage) {
		stage = primaryStage;
		
	}
}
