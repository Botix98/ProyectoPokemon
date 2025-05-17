package controller;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import dao.ConexionBD;
import dao.EntrenadorDAO;
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
import model.Movimiento;
import model.Pokemon;

public class LoginController {

	private Entrenador entrenador;
	
	private Stage stage;
	public boolean sonido = false;
	
	private Connection con;
	
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
    		lbError.setText("Error: Insertar contrase�a");
    		lbError.setVisible(true);
    	} else {
    		String usuario = txtUsuario.getText();
    		String pass = txtContrasena.getText();
    		
    		entrenador = EntrenadorDAO.buscarPorUsuario(con, usuario, pass);
    		
    		if (entrenador != null) {
    			lbError.setText("Correcto");
	    		lbError.setVisible(true);
	    		
	    		abrirPantallaMenu(entrenador);
    		}
    		else {
    			lbError.setText("Error: Usuario o contrasena erroneas");
        		lbError.setVisible(true);
    		}
    	}
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String pass = txtContrasena.getText().trim();

        if (usuario.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuario y contrasena no pueden estar vacios.");
            return;
        }

        try {
            // Obtiene una nuevo ID
            int nuevoId = EntrenadorDAO.obtenerSiguienteId(con);

            // Crea un nuevo entrenador
            Entrenador nuevoEntrenador = new Entrenador();
            nuevoEntrenador.setIdEntrenador(nuevoId);
            nuevoEntrenador.setUsuario(usuario);
            nuevoEntrenador.setPass(pass);
            nuevoEntrenador.setRivalesVencidos(-3);
            nuevoEntrenador.setPokedolares((int)(Math.random() * 201) + 800);

            boolean exito = EntrenadorDAO.insertarEntrenador(con, nuevoEntrenador);

            if (exito) {
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente. Ahora puedes iniciar sesion.");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/seleccion.fxml"));
                Parent root = loader.load();

                SeleccionController seleccionController = loader.getController();
                seleccionController.init(nuevoEntrenador, stage, this);

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Selecciona tu Pokémon inicial");
                stage.show();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario.");
            }

        } catch (SQLException e) {
            // Detecta si el nombre de usuario esta repetido
            if (e instanceof SQLIntegrityConstraintViolationException || e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya esta en uso.");
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error SQL durante el registro: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error general durante el registro.");
        }
    }

    @FXML
    void salir(ActionEvent event) {
    	SonidoController.detenerFondo("./sonidos/Opening.mp3");
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void activarDesactivarSonido(MouseEvent event) {
    	sonido();
    }
    
    @FXML
    public void initialize() {
    	SonidoController.reproducirFondo("./sonidos/Opening.mp3");
    	sonido();
    	con = ConexionBD.getConnection();
    }

    public void setStage(Stage primaryStage) {
    	stage = primaryStage;
    }
    
    private void abrirPantallaMenu(Entrenador entr) {
    	try {
    		SonidoController.detenerFondo("./sonidos/Opening.mp3");
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/menu.fxml"));
	    	Parent root = loader.load();
	    	
	    	MenuController menuController = loader.getController();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.setTitle("Menu");
	    	stage.setScene(scene);
	    	
	    	menuController.init(entr, stage, this, null, null, null, null, null, null, null);
	    	
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
    		SonidoController.continuarFondo("./sonidos/Opening.mp3");
    		
    		imgSonido.setImage(new Image(new File("./img/conSonido.png").toURI().toString()));
    		this.sonido = true;
    	} else {
    		SonidoController.detenerFondo("./sonidos/Opening.mp3");
    		this.sonido = false;
    		imgSonido.setImage(new Image(new File("./img/sinSonido.png").toURI().toString()));
    	}
    }

}
