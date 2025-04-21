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

    public void init(Entrenador entr, Stage stage, LoginController loginController, CentroPokemonController centroPokemonController, CrianzaController crianzaController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.centroPokemonController = centroPokemonController;
        this.crianzaController = crianzaController;
        
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
	        centroPokeController.init(this.entrenador, this.stage, this.loginController, this); 

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
    void salir(ActionEvent event) {
		loginController.show();
		this.stage.close();
    }

}
