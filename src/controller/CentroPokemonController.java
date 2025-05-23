package controller;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ConexionBD;
import dao.MovimientoDAO;
import dao.MovimientoPokemonDAO;
import dao.PokemonDAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Entrenador;
import model.Movimiento;
import model.MovimientoPokemon;
import model.Pokemon;
import model.TipoEstados;
 
public class CentroPokemonController {
 
	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	private TiendaController tiendaController;
	private CombateController combateController;

	private LinkedList<Pokemon> equipo;
	

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnBucle;

    @FXML
    private Button btnCurarEquipo;

    @FXML
    private ImageView imgBtnCurar;

    @FXML
    private ImageView imgBucle;

    @FXML
    private ImageView imgEnfermera;

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
    private ImageView imgSonido;

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
    private Text txtCurarEquipo;

    @FXML
    private Text txtCurarEquipo1;
    
    Connection con = ConexionBD.getConnection();


    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController, TiendaController tiendaController, CombateController combateController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        this.tiendaController = tiendaController;
        this.combateController = combateController;
        
        equipo = new LinkedList<>(PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1));

        if (combateController != null) {
    		JOptionPane.showMessageDialog(null, "Tu equipo Pokemon a sido derrotado, se estan recuperando pero a uno le falta una pierna");
    		curarInit();
        }
        mostrarEquipo();
        actualizarColorPB();
    }
    public void initialize() {
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/CentroPokemon.mp3");
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
    void cambio(ActionEvent event) {
        try {
        	SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/CurarPokemon.mp3");
        	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Tienda.mp3");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Tienda.fxml"));
            Parent root = loader.load();
 
            TiendaController tiendaController = loader.getController();
            tiendaController.init(entrenador, stage, loginController, menuController, this);
 
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Tienda");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void actualizarColorPB() {
    	for(int i = 0; i  < equipo.size(); i++) {
    		ProgressBar pb = getProgressBarPorIndex(i);
            double current = pb.getProgress();
		        if (current < 0.25){
		        	pb.setStyle("-fx-accent: red;");
		        }
		        else if (current < 0.5){
		        	pb.setStyle("-fx-accent: yellow;");
		        } else {
                    pb.setStyle("-fx-accent: green;");
                }
    	}		
    }
    
	private void actualizarBarraVida(ProgressBar pb, Pokemon pokemon) {
		double porcentajeFinal = 1;
		int numCiclos = 90;
		
		double decrPorPaso = (porcentajeFinal - pb.getProgress()) / numCiclos;
		Timeline timeline = new Timeline(
		    new KeyFrame(Duration.seconds(0.017), e -> {
		        double current = pb.getProgress();
		        if (current < 0.25){
		        	pb.setStyle("-fx-accent: red;");
		        }
		        else if (current < 0.5){
		        	pb.setStyle("-fx-accent: yellow;");
		        } else {
                    pb.setStyle("-fx-accent: green;");
                }
		        if (porcentajeFinal > current) {
		        	pb.setProgress(Math.min(current + decrPorPaso, pokemon.getVitalidadMax()));
		        }
		    })
		);
		timeline.setCycleCount(numCiclos);
		timeline.play();
	}
    @FXML
    void curarEquipo(ActionEvent event) {
    	curarInit();
    }
    private void curarInit() {
        SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/CentroPokemon.mp3");

        SonidoController.reproducirEfecto("C:/ProyectoPokemon/sonidos/CurarPokemon.mp3", () -> {
            SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/CentroPokemon.mp3");
        });

        List<Pokemon> equipo = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1);

        for (int i = 0; i < equipo.size(); i++) {
            Pokemon pokemon = equipo.get(i);
            if (pokemon != null) {
                ProgressBar pbPokemon = getProgressBarPorIndex(i);
                actualizarBarraVida(pbPokemon, pokemon);

                // Curar vida y estado
                pokemon.setVitalidadAct(pokemon.getVitalidadMax());
                pokemon.setEstado(TipoEstados.SIN_ESTADO);
                PokemonDAO.actualizarVitalidadPokemon(con, pokemon);
                PokemonDAO.actualizarEstadoPokemon(con, pokemon);

                // Restaurar PP de movimientos
                List<MovimientoPokemon> movimientos = MovimientoPokemonDAO.buscarPorIdPokemon(con, pokemon.getIdPokemon());
                for (MovimientoPokemon mp : movimientos) {
                    Movimiento movimiento = MovimientoDAO.buscarPorId(con, mp.getIdMovimiento());
                    if (movimiento != null) {
                        mp.setPpActuales(movimiento.getPpMax());
                        MovimientoPokemonDAO.actualizarPPMovimiento(con, mp.getIdPokemon(), mp.getIdMovimiento(), mp.getPpActuales());

                    }
                }
            }
        }

        actualizarEstadoPokemon();
    }
    
    private ProgressBar getProgressBarPorIndex(int index) {
        switch (index) {
            case 0: return pbPokemon1;
            case 1: return pbPokemon2;
            case 2: return pbPokemon3;
            case 3: return pbPokemon4;
            case 4: return pbPokemon5;
            case 5: return pbPokemon6;
            default: return null;
        }
    }
 
    private ImageView getImageViewPorIndex(int index) {
        switch (index) {
            case 0: return imgPokemon1;
            case 1: return imgPokemon2;
            case 2: return imgPokemon3;
            case 3: return imgPokemon4;
            case 4: return imgPokemon5;
            case 5: return imgPokemon6;
            default: return null;
        }
    }
    
    private void mostrarEquipo() {
        for (int i = 0; i < 6; i++) {
            ImageView imgPokemon = getImageViewPorIndex(i);
            ProgressBar pbPokemon = getProgressBarPorIndex(i);

            if (i < equipo.size() && equipo.get(i) != null) {
                String rutaImagen = "./img/Pokemon/Front/" + equipo.get(i).getNumPokedex() + ".png";
                imgPokemon.setImage(new Image(new File(rutaImagen).toURI().toString()));
                pbPokemon.setProgress((double) equipo.get(i).getVitalidadAct() / equipo.get(i).getVitalidadMax());

                // Mostrar si tiene Pokémon
                imgPokemon.setVisible(true);
                pbPokemon.setVisible(true);
            } else {
                // Ocultar si no hay Pokémon
                imgPokemon.setVisible(false);
                pbPokemon.setVisible(false);
            }
        }
    }
    private void actualizarEstadoPokemon() {
        for (int i = 0; i < equipo.size(); i++) {
            if (equipo.get(i) != null) {
                // Actualizar imagen del Pokémon
                ImageView imgPokemon = getImageViewPorIndex(i);
                String rutaImagen = "./img/Pokemon/Front/" + equipo.get(i).getNumPokedex() + ".png";
                imgPokemon.setImage(new Image(new File(rutaImagen).toURI().toString()));
 
                // Actualizar barra de vida con animación
                ProgressBar pbPokemon = getProgressBarPorIndex(i);
                actualizarBarraVida(pbPokemon, equipo.get(i));
            }
        }
    }
    @FXML
    void salir(ActionEvent event) {
    	try {
    		SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/CurarPokemon.mp3");
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
    	    Parent root = loader.load();
 
    	    MenuController menuController = loader.getController();
    	    menuController.init(entrenador, stage, loginController, null, null, null, null, null, null, null);
 
    	    Scene scene = new Scene(root);
    	    stage.setScene(scene);
    	    stage.setTitle("Menú");
    	    stage.show();
            modificacionCursor("C:/ProyectoPokemon/img/menu/rojoChivi.png");
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    }
    
	public void modificacionCursor(String ruta) {
	    try {
	        InputStream is = getClass().getResourceAsStream(ruta);
	        if (is == null) {
	            is = new FileInputStream(ruta);
	        }
	        Image originalImage = new Image(is);

	        // Tamano deseado
	        int width  = 55;
	        int height = 69;

	        // Canvas para escalar
	        Canvas canvas = new Canvas(width, height);
	        GraphicsContext gc = canvas.getGraphicsContext2D();
	        gc.clearRect(0, 0, width, height);

	        // Dibujar la imagen escalada
	        gc.drawImage(originalImage, 0, 0, width, height);

	        // Ajusta la transpariencia del fondo para evitar fondos blancos
	        SnapshotParameters sp = new SnapshotParameters();
	        sp.setFill(Color.TRANSPARENT);
	        WritableImage scaledImage = new WritableImage(width, height);
	        canvas.snapshot(sp, scaledImage);

	        // Crear cursor centrado
	        ImageCursor customCursor = new ImageCursor(scaledImage, width/2.0, height/2.0);

	        // Aplicar a la escena
	        if (stage.getScene() != null) {
	            stage.getScene().setCursor(customCursor);
	        } else {
	            stage.sceneProperty().addListener((obs, o, n) -> {
	                if (n != null) n.setCursor(customCursor);
	            });
	        }
	    } catch (Exception e) {
	        System.err.println("No se pudo cargar el cursor desde: " + ruta);
	        e.printStackTrace();
	    }
	}
 
	public void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
}