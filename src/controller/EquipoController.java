package controller;

import java.io.File;
import java.sql.Connection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import dao.ConexionBD;
import dao.PokedexDAO;
import dao.PokemonDAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import model.Entrenador;
import model.Pokemon;

public class EquipoController {
	
	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	private List<ImageView> imagenesEquipo;
	private LinkedList<Pokemon> equipo;

    
	@FXML private Button btnCaja;
    @FXML private Button btnSalir;
    @FXML private ImageView imgFondo;
    @FXML private ImageView imgSonido;
    @FXML private Label lblEquipo;
    @FXML private Label lblPokemon;
    
    
    @FXML private ImageView imgPokemonEquipo1, imgPokemonEquipo2, imgPokemonEquipo3;
    @FXML private ImageView imgPokemonEquipo4, imgPokemonEquipo5, imgPokemonEquipo6;

    @FXML private ProgressBar pbPokemon1, pbPokemon2, pbPokemon3;
    @FXML private ProgressBar pbPokemon4, pbPokemon5, pbPokemon6;
    
    @FXML private Label lblMotePokemonSeleccionado;
    @FXML private Label lblNivelPokemonSeleccionado;
    @FXML private Label lblNombrePokemonSeleccionado;
    @FXML private ProgressBar pbPokemonSeleccionado;
    @FXML private ImageView imgPokemonSeleccionado;
    
    Connection con = ConexionBD.getConnection();
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        pbPokemonSeleccionado.setVisible(false);
        mostrarEquipo();
    }
    
    public void initialize() {
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Equipo.mp3");
    	lblNombrePokemonSeleccionado.setVisible(false);
        lblMotePokemonSeleccionado.setVisible(false);
        lblNivelPokemonSeleccionado.setVisible(false);
        pbPokemonSeleccionado.setVisible(false);
        imgPokemonSeleccionado.setVisible(false);
    }

    @FXML
    void hacerGrande(MouseEvent event) {
        ImageView origen = (ImageView) event.getSource();
        imgPokemonSeleccionado.setImage(origen.getImage());
        pbPokemonSeleccionado.setVisible(true);
        lblNombrePokemonSeleccionado.setVisible(true);
        lblMotePokemonSeleccionado.setVisible(true);
        lblNivelPokemonSeleccionado.setVisible(true);
        imgPokemonSeleccionado.setVisible(true);

        List<ImageView> imagenesEquipo = List.of(
            imgPokemonEquipo1, imgPokemonEquipo2, imgPokemonEquipo3,
            imgPokemonEquipo4, imgPokemonEquipo5, imgPokemonEquipo6
        );

        for (int i = 0; i < imagenesEquipo.size(); i++) {
            if (origen == imagenesEquipo.get(i) && i < equipo.size()) {
                Pokemon pokemon = equipo.get(i);

                double progreso = (double) pokemon.getVitalidadAct() / pokemon.getVitalidadMax();
                pbPokemonSeleccionado.setProgress(progreso);
                actualizarColorPB(pbPokemonSeleccionado);

                lblNombrePokemonSeleccionado.setText(
                    PokedexDAO.cargarPorNumPokedex(con, pokemon.getNumPokedex()).getNomPokemon()
                );
                lblNivelPokemonSeleccionado.setText("Nvl: " + pokemon.getNivel());
                lblMotePokemonSeleccionado.setText("Mote: " + pokemon.getMote());
                break;
            }
        }
    }
        
        private void mostrarEquipo() {
            equipo = new LinkedList<>(PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1));

        ImageView[] imagenes = {
            imgPokemonEquipo1, imgPokemonEquipo2, imgPokemonEquipo3,
            imgPokemonEquipo4, imgPokemonEquipo5, imgPokemonEquipo6
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
        
        private void actualizarColorPB(ProgressBar pb) {
            double progreso = pb.getProgress();
            if (progreso < 0.25) {
                pb.setStyle("-fx-accent: red;");
            } else if (progreso < 0.5) {
                pb.setStyle("-fx-accent: yellow;");
            } else {
                pb.setStyle("-fx-accent: green;");
            }
        }		

    //hace que la barra sea visible o invisible con true o false
    public final void setVisible(boolean value) {
    	
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
