package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Entrenador;

public class MenuController {

	private Entrenador entrenador;
	private Stage stage;
	private LoginController loginController;
	
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

	public void init(Entrenador entr, Stage stage, LoginController loginController) {
		this.loginController = loginController;
		this.stage = stage;
		this.entrenador = entr;
		
		lbJugador.setText(entrenador.getUsuario());
		lbPokedolares.setText(Integer.toString(entrenador.getPokedolares()));
	}
	
	@FXML
    void salir(ActionEvent event) {
		loginController.show();
		this.stage.close();
    }

}
