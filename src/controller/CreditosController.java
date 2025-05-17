package controller;

import dao.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Entrenador;

public class CreditosController {

	private Entrenador entrenador;
	private Stage Stage;
	private MenuController menuController;
	private LoginController loginController;


    @FXML
    private Button btnSalir;
	
    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgTxt2;

    @FXML
    private ImageView imgTxt3;

    @FXML
    private ImageView imgtxt3;

    public void init(Entrenador entrenador, Stage stage,
                     LoginController loginController,
                     CentroPokemonController centroCtrl,
                     CrianzaController crianzaCtrl,
                     CapturaController capturaCtrl,
                     EquipoController equipoCtrl,
                     EntrenamientoController entrenamientoCtrl,
                     CombateController combateCtrl,
                     TiendaController tiendaCtrl) {
        this.entrenador = entrenador;
        this.Stage = stage;

    }
    
    @FXML
    public void initialize() {
    	SonidoController.reproducirFondo("./sonidos/MusicaCreditos.mp3");
    }
    
    @FXML
    void salir(ActionEvent event) {
    	try {
    		SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/MusicaCreditos.mp3");
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
    	    Parent root = loader.load();
 
    	    MenuController menuController = loader.getController();
    	    menuController.init(entrenador, Stage, loginController, null, null, null, null, null, null, null);
 
    	    Scene scene = new Scene(root);
    	    Stage.setScene(scene);
    	    Stage.setTitle("Menú");
    	    Stage.show();
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    }
}
