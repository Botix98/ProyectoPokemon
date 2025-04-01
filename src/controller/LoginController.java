package controller;

import java.io.File;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Entrenador;

public class LoginController {

	//PRUEBA
	Entrenador entrenador = new Entrenador("Carlos", "123456", 1000);
	public Stage stage;
	public boolean sonido = false;
	public MediaPlayer mediaPlayer;
	
	@FXML
    private ImageView imgSonido;
	
    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lbContrasena;

    @FXML
    private Label lbError;

    @FXML
    private Label lbUsuario;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private TextField txtUsuario;

    @FXML
    void aceptarLoguin(ActionEvent event) {
    	if (txtUsuario.getText().isEmpty()) {
    		lbError.setText("Error: Insertar nombre usuario");
    		lbError.setVisible(true);
    		
    		//CON EL JOptionPane.showMessageDialog muestra una ventana emergente con el mensaje
    		JOptionPane.showMessageDialog(null, "Error: escribe nombre de usuario");
    	} else if (txtContrasena.getText().isEmpty()) {
    		lbError.setText("Error: Insertar contraseña");
    		lbError.setVisible(true);
    	} else {
    		String usuario = txtUsuario.getText();
    		String pass = txtContrasena.getText();
    		
    		if (entrenador.getUsuario().equals(usuario)) {
    			if (entrenador.getPass().equals(pass)) {
    				lbError.setText("Correcto");
    	    		lbError.setVisible(true);
    	    		
    	    		abrirPantallaMenu(entrenador);
    			} else {
    				lbError.setText("Error: contraseña incorrecta");
    	    		lbError.setVisible(true);
    			}
    		} else {
    			lbError.setText("Error: Usuario no existe");
        		lbError.setVisible(true);
    		}
    	}
    }

    @FXML
    void registrarUsuario(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void activarDesactivarSonido(MouseEvent event) {
    	sonido();
    }
    
    @FXML
    public void initialize() {
    	String rutaSonido = "./sonidos/Opening.mp3";
		Media sound = new Media(new File(rutaSonido).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    	sonido();
    }

    public void setStage(Stage primaryStage) {
    	stage = primaryStage;
    }
    
    private void abrirPantallaMenu(Entrenador entr) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/menu.fxml"));
	    	Parent root = loader.load();
	    	
	    	MenuController menuController = loader.getController();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.setTitle("Menu");
	    	stage.setScene(scene);
	    	
	    	menuController.init(entr, stage, this);
	    	
	    	stage.show();
	    	
	    	this.stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void show() {
    	stage.show();
    	lbError.setVisible(false);
    	txtUsuario.setText("");
    	txtContrasena.setText("");
    }
    
    public void sonido() {
    	if (!this.sonido) {
    		mediaPlayer.play();
    		
    		imgSonido.setImage(new Image(new File("./img/conSonido.png").toURI().toString()));
    		this.sonido = true;
    	} else {
    		mediaPlayer.pause();
    		this.sonido = false;
    		imgSonido.setImage(new Image(new File("./img/sinSonido.png").toURI().toString()));
    	}
    }
}
