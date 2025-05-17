package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Entrenador;

public class CreditosController {

    private Entrenador entrenador;
    private Stage stage;
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
        this.stage = stage;
        this.loginController = loginController;
    }
    
    @FXML
    public void initialize() {
        SonidoController.reproducirFondo("./sonidos/MusicaCreditos.mp3");
    }
    
    @FXML
    void salir(ActionEvent event) {
        // Detener la música y cerrar toda la aplicación
        SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/MusicaCreditos.mp3");
        Platform.exit();
    }
}
