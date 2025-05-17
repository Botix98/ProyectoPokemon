package controller;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Entrenador;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import javafx.scene.paint.Color;

public class MenuController {
	
	private Entrenador entrenador;
	private Stage stage;
	private LoginController loginController;
	private CentroPokemonController centroPokemonController;
	private CrianzaController crianzaController;
	private CapturaController capturaController;
	private EquipoController equipoController;
	private CombateController combateController;
	private EntrenamientoController entrenamientoController;
	private TiendaController tiendaController;
	

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
    private ImageView imgSonido;
    
    @FXML
    private ImageView imgCaptura;

    @FXML
    private ImageView imgCentroPoke;

    @FXML
    private ImageView imgCombate;

    @FXML
    private ImageView imgCrianza;

    @FXML
    private ImageView imgEntrenamiento;

    @FXML
    private ImageView imgEquipo;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgLogo;
    
    @FXML
    private ImageView imgSalir;

    @FXML
    private Text txtSalir;

    public void init(Entrenador entr, Stage stage, LoginController loginController, CentroPokemonController centroPokemonController, CrianzaController crianzaController, CapturaController capturaController, EquipoController equipoController, EntrenamientoController entrenamientoController, CombateController combateController, TiendaController tiendaController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.centroPokemonController = centroPokemonController;
        this.crianzaController = crianzaController;
        this.capturaController = capturaController;
        this.equipoController = equipoController;
        this.entrenamientoController = entrenamientoController;
        this.combateController = combateController;
        this.tiendaController = tiendaController;
        
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Menu.mp3");
        
        modificacionCursor("C:/ProyectoPokemon/img/menu/rojoChivi.png");
    }
	
	public void show() {
	    stage.show();
	}
	
	@FXML
	void irCentroPokemon(ActionEvent event) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CentroPokemon.fxml"));
	        Parent root = loader.load();

	        CentroPokemonController centroPokeController = loader.getController();

	        //inicializar la pantalla
	        centroPokeController.init(this.entrenador, this.stage, this.loginController, this, null, null); 

	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setTitle("Centro Pokémon");
	        stage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
    @FXML
    void irCrianaza(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Crianza.fxml"));
            Parent root = loader.load();

            CrianzaController crianzaController = loader.getController();

            // Inicializamos la pantalla de Crianza
            crianzaController.init(this.entrenador, this.stage, this.loginController, this); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Crianza");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void irEquipo(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Equipo.fxml"));
            Parent root = loader.load();

            EquipoController equipoController = loader.getController();

            // Inicializamos la pantalla de Equipo
            equipoController.init(this.entrenador, this.stage, this.loginController, this, null); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Equipo");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @FXML
    void irCaptura(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Captura.fxml"));
            Parent root = loader.load();

            CapturaController capturaController = loader.getController();

            // Inicializamos la pantalla de Captura
            capturaController.init(this.entrenador, this.stage, this.loginController, this); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Captura");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @FXML
    void irEntrenamiento(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/entrenamiento.fxml"));
            Parent root = loader.load();

            EntrenamientoController entrenamientoController = loader.getController();

            // Inicializamos la pantalla de Entrenamiento
            entrenamientoController.init(this.entrenador, this.stage, this.loginController, this); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Entrenamiento");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void irCombate(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/seleccionRival.fxml"));
            Parent root = loader.load();

            SeleccionarRivalController seleccionarRivalController = loader.getController();

            // Inicializamos la pantalla de Combate
            seleccionarRivalController.init(this.entrenador, this.stage, this.loginController, this); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Seleccionar rival");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
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
    void salir(ActionEvent event) {
		SonidoController.detenerFondo(null);
		loginController.show();
		this.stage.close();
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
}
