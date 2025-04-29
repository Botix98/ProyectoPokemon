package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Entrenador;

public class MenuController {
	
	private Entrenador entrenador;
	private Stage stage;
	private LoginController loginController;
	private CentroPokemonController centroPokemonController;
	private CrianzaController crianzaController;
	private CapturaController capturaController;
	private EquipoController equipoController;
	private CombateController combateController;
	private EntrenamientoController entrenamientoController;
	private TiendaController tiendaController;
	
	@FXML
    private Button btnCaja;
	
    @FXML
    private Button btnCaptura;

    @FXML
    private Button btnCentroPokemon;

    @FXML
    private Button btnCombate;

    @FXML
    private Button btnCrianza;

    @FXML
    private Button btnEntrenamiento;

    @FXML
    private Button btnEquipo;

    @FXML
    private Button btnSalir;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lbJugador;

    @FXML
    private Label lbPokedolares;

    @FXML
    private Label lbTiJugador;

    @FXML
    private Label lbTiPokedolares;

    public void init(Entrenador entr, Stage stage, LoginController loginController, CentroPokemonController centroPokemonController, CrianzaController crianzaController, CapturaController capturaController, EquipoController equipoController, EntrenamientoController entrenamientoController, CombateController combateController, TiendaController tiendaController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.centroPokemonController = centroPokemonController;
        this.crianzaController = crianzaController;
        this.capturaController = capturaController;
        this.equipoController = equipoController;
        this.entrenamientoController = entrenamientoController;
        this.combateController = combateController;
        this.tiendaController = tiendaController;
        
        lbJugador.setText(entrenador.getUsuario());
        lbPokedolares.setText(Integer.toString(entrenador.getPokedolares()));
    }
	
	public void show() {
	    stage.show();
	}
	
	@FXML
	void irCentroPokemon(ActionEvent event) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CentroPokemon.fxml"));
	        Parent root = loader.load();

	        CentroPokemonController centroPokeController = loader.getController();

	        //inicializar la pantalla
	        centroPokeController.init(this.entrenador, this.stage, this.loginController, this, null); 

	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setTitle("Centro Pokémon");
	        stage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
    @FXML
    void irCrianaza(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Crianza.fxml"));
            Parent root = loader.load();

            CrianzaController crianzaController = loader.getController();

            // Inicializamos la pantalla de Crianza
            crianzaController.init(this.entrenador, this.stage, this.loginController, this); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Crianza");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void irEquipo(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Equipo.fxml"));
            Parent root = loader.load();

            EquipoController equipoController = loader.getController();

            // Inicializamos la pantalla de Equipo
            equipoController.init(this.entrenador, this.stage, this.loginController, this); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Equipo");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @FXML
    void irCaptura(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Captura.fxml"));
            Parent root = loader.load();

            CapturaController capturaController = loader.getController();

            // Inicializamos la pantalla de Captura
            capturaController.init(this.entrenador, this.stage, this.loginController, this); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Captura");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @FXML
    void irEntrenamiento(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/entrenamiento.fxml"));
            Parent root = loader.load();

            EntrenamientoController entrenamientoController = loader.getController();

            // Inicializamos la pantalla de Entrenamiento
            entrenamientoController.init(this.entrenador, this.stage, this.loginController, this); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Entrenamiento");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void irCombate(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/combate.fxml"));
            Parent root = loader.load();

            CombateController combateController = loader.getController();

            // Inicializamos la pantalla de Combate
            combateController.init(this.entrenador, this.stage, this.loginController, this); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Combate");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void irCaja(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Caja.fxml"));
            Parent root = loader.load();

            CajaController cajaController = loader.getController();

            // Inicializamos la pantalla de Caja
            cajaController.init(this.entrenador, this.stage, this.loginController, this); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Caja");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
	@FXML
    void salir(ActionEvent event) {
		SonidoController.detener();
		loginController.show();
		this.stage.close();
    }

}
