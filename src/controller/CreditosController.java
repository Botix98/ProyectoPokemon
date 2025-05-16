package controller;

import dao.ConexionBD;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Entrenador;

public class CreditosController {

	private Entrenador entrenador;
	private Stage Stage;

	
    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgTxt2;

    @FXML
    private ImageView imgTxt3;

    @FXML
    private ImageView imgtxt3;

    public void init(Entrenador entrenador, Stage stage,
                     LoginController loginCtrl,
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
}
