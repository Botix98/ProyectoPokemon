package controller;

import java.io.File;
import java.sql.Connection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import dao.ConexionBD;
import dao.PokemonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Entrenador;
import model.Pokemon;

public class EquipoController {
	
	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	private LinkedList<Pokemon> equipo;

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
    
    Connection con = ConexionBD.getConnection();
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        pbPokemonSeleccionado.setVisible(false);
    }
    
    public void initialize() {
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Equipo.mp3");
    }
    
        //falta comprobar cuantos pokemon hay en el equipo o por lo menos eso dijo carlos
    
    /**
     
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
        
          * 
     * @param event
     */
        
        @FXML
        void hacerGrande(MouseEvent event) {
            ImageView origen = (ImageView) event.getSource();
            Image imagen = origen.getImage();
            imgPokemonSeleccionado.setImage(imagen);
            pbPokemonSeleccionado.setVisible(true);
            
            double progreso = 0;
            
            if (origen == imgPokemon1) {
          
                
            } else if (origen == imgPokemon2) {
         
                
            } else if (origen == imgPokemon3) {
            
                pbPokemon3.setProgress(progreso);
            
            } else if (origen == imgPokemon4) {

                pbPokemon4.setProgress(progreso);
            
            } else if (origen == imgPokemon5) {
            
                pbPokemon5.setProgress(progreso);
            
            } else if (origen == imgPokemon6) {
 
                pbPokemon6.setProgress(progreso);     
            }
     
        }
        
    
    //hace que la barra sea visible o invisible con true o false
    public final void setVisible(boolean value) {
    	
    }
    
	private void mostrarEquipo() {
        List<Pokemon> equipo = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1);

        ImageView[] imagenes = {
            imgPokemon1, imgPokemon2, imgPokemon3,
            imgPokemon4, imgPokemon5, imgPokemon6
        };

        for (int i = 0; i < imagenes.length; i++) {
            if (i < equipo.size() && equipo.get(i) != null) {
                int numPokedex = equipo.get(i).getNumPokedex();
                String rutaImagen = "./img/Pokemon/Front/" + numPokedex + ".png";
                imagenes[i].setImage(new Image(new File(rutaImagen).toURI().toString()));
            } else {
                imagenes[i].setImage(null);
            }
        }
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
