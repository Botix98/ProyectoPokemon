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
    
    //el método inciar
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        pbPokemonSeleccionado.setVisible(false);
    }
    
        //falta comprobar cuantos pokemon hay en el equipo
    	//ponerle lo de invisible hasta que onclick
           
        @FXML
        void hacerGrande(MouseEvent event) {
            ImageView origen = (ImageView) event.getSource();
            Image imagen = origen.getImage();
            imgPokemonSeleccionado.setImage(imagen);
            pbPokemonSeleccionado.setVisible(true);
            
            
            if (origen == imgPokemon1) {
            	pbPokemonSeleccionado.setProgress(pbPokemon1.getProgress());
            	pbPokemon1.setProgress(((double) (entrenador.getPokemon(0).getVitalidadActual())) / entrenador.getPokemon(0).getVitalidadMax());
                
            } else if (origen == imgPokemon2) {
            	pbPokemonSeleccionado.setProgress(pbPokemon2.getProgress());
            	pbPokemon2.setProgress(((double) (entrenador.getPokemon(0).getVitalidadActual())) / entrenador.getPokemon(0).getVitalidadMax());     
                
            } else if (origen == imgPokemon3) {
            	pbPokemonSeleccionado.setProgress(pbPokemon3.getProgress());
            	pbPokemon3.setProgress(((double) (entrenador.getPokemon(0).getVitalidadActual())) / entrenador.getPokemon(0).getVitalidadMax());
            
            } else if (origen == imgPokemon4) {
            	pbPokemonSeleccionado.setProgress(pbPokemon4.getProgress());
            	pbPokemon4.setProgress(((double) (entrenador.getPokemon(0).getVitalidadActual())) / entrenador.getPokemon(0).getVitalidadMax());
            
            } else if (origen == imgPokemon5) {
            	pbPokemonSeleccionado.setProgress(pbPokemon5.getProgress());
            	pbPokemon5.setProgress(((double) (entrenador.getPokemon(0).getVitalidadActual())) / entrenador.getPokemon(0).getVitalidadMax());
            
            } else if (origen == imgPokemon6) {
            	pbPokemonSeleccionado.setProgress(pbPokemon6.getProgress());
            	pbPokemon6.setProgress(((double) (entrenador.getPokemon(0).getVitalidadActual())) / entrenador.getPokemon(0).getVitalidadMax());
            }      
     }
    //este método hace que la barra sea visible o invisible con true o false
    public final void setVisible​(boolean value) {
    	
    }

	public void initialize() {
    	SonidoController.reproducir("C:/ProyectoPokemon/sonidos/Equipo.mp3");
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
    
    //método salir
    @FXML
    void salir(ActionEvent event) {
    	try {
    		SonidoController.detener("C:/ProyectoPokemon/sonidos/Equipo.mp3");
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
