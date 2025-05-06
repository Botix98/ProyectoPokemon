package controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Entrenador;

public class EquipoController {
	
	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;

    @FXML
    private Button btnCaja;
    
    @FXML
    private Button btnSalir;

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
    private ImageView imgPokemonSeleccionado;

    @FXML
    private ImageView imgSonido;

    @FXML
    private Label lblEquipo;

    @FXML
    private Label lblMote;

    @FXML
    private Label lblNivel;

    @FXML
    private Label lblNombrePokemonGrande;

    @FXML
    private Label lblPokemon;

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
    
    @FXML
    private ProgressBar pbPokemonSeleccionado;
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        pbPokemonSeleccionado.setVisible(false);
    }
    
        //falta comprobar cuantos pokemon hay en el equipo o por lo menos eso dijo carlos

        @FXML
        void hacerGrande(MouseEvent event) {
            ImageView origen = (ImageView) event.getSource();
            Image imagen = origen.getImage();
            imgPokemonSeleccionado.setImage(imagen);
            pbPokemonSeleccionado.setVisible(true);
            
            double progreso = 0;
            
            if (origen == imgPokemon1) {
            	pbPokemon1.setProgress(((double) (entrenador.getPokemon(0).getVitalidadAct())) / entrenador.getPokemon(0).getVitalidadMax());
                
            } else if (origen == imgPokemon2) {
            	pbPokemon2.setProgress(((double) (entrenador.getPokemon(0).getVitalidadAct())) / entrenador.getPokemon(0).getVitalidadMax());   
                
            } else if (origen == imgPokemon3) {
                progreso = ((double) entrenador.getPokemon(1).getVitalidadAct()) / entrenador.getPokemon(1).getVitalidadMax();
                pbPokemon3.setProgress(progreso);
            
            } else if (origen == imgPokemon4) {
                progreso = ((double) entrenador.getPokemon(1).getVitalidadAct()) / entrenador.getPokemon(1).getVitalidadMax();
                pbPokemon4.setProgress(progreso);
            
            } else if (origen == imgPokemon5) {
                progreso = ((double) entrenador.getPokemon(1).getVitalidadAct()) / entrenador.getPokemon(1).getVitalidadMax();
                pbPokemon5.setProgress(progreso);
            
            } else if (origen == imgPokemon6) {
                progreso = ((double) entrenador.getPokemon(1).getVitalidadAct()) / entrenador.getPokemon(1).getVitalidadMax();
                pbPokemon6.setProgress(progreso);     
            }
     
        }
    //hace que la barra sea visible o invisible con true o false
    public final void setVisible(boolean value) {
    	
    }

	public void initialize() {
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Equipo.mp3");
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
    void irCaja(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Caja.fxml"));
            Parent root = loader.load();

            CajaController cajaController = loader.getController();
            cajaController.init(this.entrenador, this.stage, this.loginController, menuController, this); 

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
    	try {
    		SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/Equipo.mp3");
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

}
