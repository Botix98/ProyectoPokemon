package controller;

import java.io.File;
import java.sql.Connection;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Entrenador;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

import dao.MochilaDAO;
import dao.ObjetoDAO;
import dao.EntrenadorDAO;
import dao.ConexionBD;
import model.Entrenador;
import model.Mochila;
import model.Objeto;


public class CapturaController {
	
	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	private List<ImageView> imageViews;
	
	
	private List<Mochila> mochila;
    Connection con = ConexionBD.getConnection();

	@FXML
	private Button btnCambiarPokemon;

	@FXML
	private Button btnCambioCueva;
	
	@FXML
	private Button btnCambioHierba;
	
	@FXML
	private Button btnCambioNieve;

	 @FXML
	private Button btnCambioNoche;

	@FXML
	private Button btnCambioNube;

	@FXML
	private Button btnCambioPiedra;

	@FXML
	private Button btnCambioPlaya;

	@FXML
	private Button btnCambioVolcan;

    @FXML
    private Button btnSalir;
    
    @FXML
    private ImageView imgCambiarPokemon;
    
    @FXML
    private ImageView imgCambioCascada;

    @FXML
    private ImageView imgCambioHierba;

    @FXML
    private ImageView imgCambioNoche;

    @FXML
    private ImageView imgCambioPiedra;

    @FXML
    private ImageView imgCambioPlaya;

    @FXML
    private ImageView imgCambioVolcan;

    @FXML
    private ImageView imgFondo;
    
    @FXML
    private ImageView imgPokemon;

    @FXML
    private ImageView imgSonido;
    
    @FXML
    private ImageView imgPokeball;
    
    @FXML
    private ImageView imgSuperball;

    @FXML
    private ImageView imgUltraball;

    @FXML
    private Label lblNivelPokemon;

    @FXML
    private Label lblNombrePokemon;
    
    @FXML
    private Label lblNumeroPokeballs;

    @FXML
    private Label lblNumeroSuperballs;

    @FXML
    private Label lblNumeroUltraballs;

    @FXML
    private Label lblRatioCatchPokeball;

    @FXML
    private Label lblRatioCatchSuperball;

    @FXML
    private Label lblRatioCatchUltraball;
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        cargarObjetosMochila();
    }
    
    @FXML
    public void initialize() {
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Captura.mp3");
        imageViews = Arrays.asList(imgPokeball, imgSuperball, imgUltraball);

      //hacer oscuras las otras bolas
        for (ImageView imageView : imageViews) {
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            	ponerOscuridad(imageView);
            });

            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            	quitarOscuridad();
            });
        }
    }
    //el 0.X es el ratio de captura
    @FXML
    void usarPokeball(MouseEvent event) {
    	double ratio = 0.3;
    	lblRatioCatchPokeball.setText("Ratio de captura: " + ratio);
    	lanzarBolas(8, 0.3, "Pokéball");
    }

    @FXML
    void usarSuperball(MouseEvent event) {
    	double ratio = 0.5;
    	lblRatioCatchSuperball.setText("Ratio de captura: " + ratio);
    	lanzarBolas(9, 0.5, "Superball");
    }

    @FXML
    void usarUltraball(MouseEvent event) {
    	double ratio = 0.7;
    	lblRatioCatchUltraball.setText("Ratio de captura: " + ratio);
    	lanzarBolas(10, 0.7, "Ultraball");
    }
    
    private void cargarObjetosMochila() {
        mochila = MochilaDAO.cargarMochilaPorEntrenador(con, entrenador.getIdEntrenador());

        for (Mochila m : mochila) {
        	int idObjeto = m.getIdObjeto();
            int cantidad = m.getCantidad();

            switch (idObjeto) {
                case 1:
                    lblNumeroPokeballs.setText("x" + cantidad);
                    break;
                case 2:
                    lblNumeroSuperballs.setText("x" + cantidad);
                    break;
                case 3:
                    lblNumeroUltraballs.setText("x" + cantidad);
                    break;
            }
        }
    }
    
    //TODAVIA NO HE CONSEGUIDO QUE FUNCIONE QUE SE PARE LA MUSICA, SE ACTIVE EL EFECTO Y CUANDO TERMINE EL EFECTO QUE CONTINUE LA MUSICA DE FONDO
    private void lanzarBolas(int idObjeto, double ratioExito, String nombreBall) {
        Mochila m = MochilaDAO.buscarObjetoEnMochila(con, entrenador.getIdEntrenador(), idObjeto);

        
        if (m != null && m.getCantidad() > 0) {
            // Disminuir la cantidad y actualizarla
            m.setCantidad(m.getCantidad() - 1);
            if (m.getCantidad() > 0) {
                MochilaDAO.actualizarCantidad(con, m);
            } else {
            	//si hay 0 se elimina
                MochilaDAO.eliminarDeMochila(con, m.getIdEntrenador(), m.getIdObjeto());
            }
            SonidoController.pausarFondo("C:/ProyectoPokemon/sonidos/Captura.mp3");
            // Intentar capturar
            if (Math.random() < ratioExito) {
            	SonidoController.reproducirEfecto("C:/ProyectoPokemon/sonidos/PokemonCapturado.mp3");
                capturarPokemon();
                lblNombrePokemon.setText("¡Capturado con " + nombreBall + "!");
            } else {
            	SonidoController.reproducirEfecto("C:/ProyectoPokemon/sonidos/PokemonNoCapturado.mp3");
                lblNombrePokemon.setText("¡Se escapó! (" + nombreBall + ")");
            }

            cargarObjetosMochila();
        } else {
            lblNombrePokemon.setText("¡No tienes " + nombreBall + "s!");
        }
        SonidoController.continuarFondo("C:/ProyectoPokemon/sonidos/Captura.mp3");
    }
	    
    private void capturarPokemon() {
    	 System.out.println("esto es que ha funcionado ahora hay que hacer que se vayan al equipo");
	}

    private void ponerOscuridad(ImageView hoveredImageView) {
        for (ImageView imageView : imageViews) {
            if (imageView != hoveredImageView) {
                //ajustar la oscuridad
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(-0.5);
                imageView.setEffect(colorAdjust);
            }
        }
    }

    private void quitarOscuridad() {
        for (ImageView imageView : imageViews) {
            imageView.setEffect(null);
        }
    }
    
    @FXML
    void cambiarFondoCueva(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoCueva.png").toURI().toString()));
    }

    @FXML
    void cambiarFondoHierba(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoHierba.png").toURI().toString()));
    }

    @FXML
    void cambiarFondoNoche(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoNoche.png").toURI().toString()));
    }

    @FXML
    void cambiarFondoNube(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoNube.png").toURI().toString()));
    	}
    
    @FXML
    void cambiarFondoPiedra(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoPiedra.png").toURI().toString()));
    }

    @FXML
    void cambiarFondoPlaya(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoPlaya.png").toURI().toString()));
    }

    @FXML
    void cambiarFondoVolcan(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoVolcan.png").toURI().toString()));
    }


    @FXML
    void cambiarFondoNieve(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoNieve.png").toURI().toString()));
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
    	try {
    		SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/Captura.mp3");
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
