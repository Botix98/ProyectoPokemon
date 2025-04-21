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

public class CrianzaController {

	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	
    @FXML
    private Button btnAtras;

    @FXML
    private Button btnHembra;

    @FXML
    private Button btnIncubadora;

    @FXML
    private Button btnMacho;

    @FXML
    private ImageView idHuevo;

    @FXML
    private ImageView idSonido;

    @FXML
    private ImageView imgAdn1;

    @FXML
    private ImageView imgAdn2;

    @FXML
    private ImageView imgAdn3;

    @FXML
    private ImageView imgAdn4;

    @FXML
    private ImageView imgCable1;

    @FXML
    private ImageView imgCable2;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgFusionar;

    @FXML
    private ProgressBar pbFusion;
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
    }

    @FXML
    void Salir(ActionEvent event) {
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

    @FXML
    void activarDesactivarSonido(MouseEvent event) {

    }

    @FXML
    void habrirIncubadora(ActionEvent event) {

    }

}
