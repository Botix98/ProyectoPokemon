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
import dao.PokedexDAO;
import dao.PokemonDAO;
import dao.EntrenadorDAO;
import dao.ConexionBD;
import model.Entrenador;
import model.Mochila;
import model.Objeto;
import model.Pokedex;
import model.Pokemon;


public class CapturaController {
	
	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	private List<ImageView> imageViews;
	private List<Pokedex> pokedex;
	private List<Mochila> mochila;
    Connection con = ConexionBD.getConnection();
    
    @FXML private Button btnSalir;
    @FXML private ImageView imgFondo;
    @FXML private ImageView imgSonido;
    
    @FXML private ImageView imgPokemon;
    @FXML private Label lblNombrePokemon, lblNivelPokemon;
    
    @FXML private Button btnCambioCueva, btnCambioHierba, btnCambioNieve, btnCambioPlaya; 
    @FXML private Button btnCambioNoche, btnCambioNube, btnCambioPiedra, btnCambioVolcan;
    
    @FXML private ImageView imgCambioCueva, imgCambioHierba, imgCambioNieve, imgCambioPlaya; 
    @FXML private ImageView imgCambioNoche, imgCambioCascada, imgCambioPiedra, imgCambioVolcan;
    
    @FXML private Button btnCambiarPokemon;
    @FXML private ImageView imgCambiarPokemon;

    @FXML private ImageView imgPokeball, imgSuperball, imgUltraball;
    @FXML private Label lblNumeroPokeballs, lblNumeroSuperballs, lblNumeroUltraballs;
    @FXML private Label lblRatioCatchPokeball, lblRatioCatchSuperball, lblRatioCatchUltraball;

    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        cargarObjetosMochila();
        pokedex = PokedexDAO.cargarPokedexCompleta(con);
        
        Pokemon pokemonSalvaje = generarPokemonSalvaje();
        if (pokemonSalvaje != null) {
            lblNombrePokemon.setText(pokemonSalvaje.getMote());
            lblNivelPokemon.setText("Nvl: " + pokemonSalvaje.getNivel());

            String rutaImagen = "./img/Pokemon/Front/" + pokemonSalvaje.getNumPokedex() + ".png";
            File file = new File(rutaImagen);
            if (file.exists()) {
                imgPokemon.setImage(new Image(file.toURI().toString()));
            } else {
                System.err.println("Imagen no encontrada: " + file.getAbsolutePath());
            }
        }
        
        Mochila pokeball = MochilaDAO.buscarObjetoEnMochila(con, entrenador.getIdEntrenador(), 8);
        Mochila superball = MochilaDAO.buscarObjetoEnMochila(con, entrenador.getIdEntrenador(), 9);
        Mochila ultraball = MochilaDAO.buscarObjetoEnMochila(con, entrenador.getIdEntrenador(), 10);

        lblNumeroPokeballs.setText("Tienes: " + (pokeball != null ? pokeball.getCantidad() : 0));
        lblNumeroSuperballs.setText("Tienes: " + (superball != null ? superball.getCantidad() : 0));
        lblNumeroUltraballs.setText("Tienes: " + (ultraball != null ? ultraball.getCantidad() : 0));

        lblRatioCatchPokeball.setText("Captura: 30%");
        lblRatioCatchSuperball.setText("Captura: 50%");
        lblRatioCatchUltraball.setText("Captura: 80%");
       
    }
    
    @FXML
    public void initialize() {
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Captura.mp3");
        imageViews = Arrays.asList(imgPokeball, imgSuperball, imgUltraball);

      //hacer oscuras las otras bolas
        for (ImageView imageView : imageViews) {
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            	ponerOscuridadNoSeleccionado(imageView);
            });

            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            	quitarOscuridadNoSeleccionado();
            });
        }
    }
    //el numero es el id en BD, y el 0,X es el ratio de captura
    @FXML
    void usarPokeball(MouseEvent event) {
    	lanzarBolas(8, 0.3, "Pokéball");
    }

    @FXML
    void usarSuperball(MouseEvent event) {
    	lanzarBolas(9, 0.5, "Superball");
    }

    @FXML
    void usarUltraball(MouseEvent event) {
    	lanzarBolas(10, 0.7, "Ultraball");
    }
    
    private void cargarObjetosMochila() {
        // Inicializar como cero por si no se encuentran en la mochila
        int cantidadPokeball = 0;
        int cantidadSuperball = 0;
        int cantidadUltraball = 0;

        mochila = MochilaDAO.cargarMochilaPorEntrenador(con, entrenador.getIdEntrenador());

        for (Mochila m : mochila) {
            int idObjeto = m.getIdObjeto();
            int cantidad = m.getCantidad();

            switch (idObjeto) {
                case 8:
                    cantidadPokeball = cantidad;
                    break;
                case 9:
                    cantidadSuperball = cantidad;
                    break;
                case 10:
                    cantidadUltraball = cantidad;
                    break;
            }
        }

        lblNumeroPokeballs.setText("Tienes: " + cantidadPokeball);
        lblNumeroSuperballs.setText("Tienes:" + cantidadSuperball);
        lblNumeroUltraballs.setText("Tienes:" + cantidadUltraball);

        aplicarOscuridadSiNoDisponible(imgPokeball, cantidadPokeball);
        aplicarOscuridadSiNoDisponible(imgSuperball, cantidadSuperball);
        aplicarOscuridadSiNoDisponible(imgUltraball, cantidadUltraball);
    }
    
    public Pokemon generarPokemonSalvaje() {
        int nivelMedio = 0;
        int numPokemon = 0;

        // Elegir Pokémon aleatorio de la pokédex
        int numPokedex = (int) (Math.random() * 151) + 1;

        // Verifica que la pokedex esté bien cargada
        if (pokedex == null || pokedex.size() < numPokedex) {
            System.err.println("No se cargó la pokédex completa");
            return null;
        }

        String rutaImagen = "./img/Pokemon/Front/" + numPokedex + ".png";
        
        // Obtener nivel promedio del equipo del entrenador
        for (Pokemon pokemon : PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1)) {
            nivelMedio += pokemon.getNivel();
            numPokemon++;
        }

        if (numPokemon == 0) nivelMedio = 5; // nivel por defecto si no tiene Pokémon
        else nivelMedio = nivelMedio / numPokemon;

        String mote = pokedex.get(numPokedex - 1).getNomPokemon();
        int nivel = Math.max(1, nivelMedio + (int)(Math.random() * 5) - 2); // +-2 del promedio

        // Calcular stats
        int vitalidadMax = 10 + (int)((double)(nivel) / 50 * (pokedex.get(numPokedex - 1).getVitalidad() * 2) + (Math.random() * 32)) + nivel;
        int ataque = 5 + (int)((double)(nivel) / 50 * ((pokedex.get(numPokedex - 1).getAtaque() * 2) + (Math.random() * 32)));
        int ataqueEsp = 5 + (int)((double)(nivel) / 50 * ((pokedex.get(numPokedex - 1).getAtEspecial() * 2) + (Math.random() * 32)));
        int defensa = 5 + (int)((double)(nivel) / 50 * ((pokedex.get(numPokedex - 1).getDefensa() * 2) + (Math.random() * 32)));
        int defensaEsp = 5 + (int)((double)(nivel) / 50 * ((pokedex.get(numPokedex - 1).getDefEspecial() * 2) + (Math.random() * 32)));
        int velocidad = 5 + (int)((double)(nivel) / 50 * ((pokedex.get(numPokedex - 1).getVelocidad() * 2) + (Math.random() * 32)));

        return new Pokemon(-1, 0, 0, "SALVAJE", numPokedex, mote, vitalidadMax, vitalidadMax, ataque, ataqueEsp, defensa, defensaEsp, velocidad, nivel, 0, "F", "SIN_ESTADO", 1, 0);
    }
    
    private void lanzarBolas(int idObjeto, double ratioExito, String nombreBall) {
        Mochila m = MochilaDAO.buscarObjetoEnMochila(con, entrenador.getIdEntrenador(), idObjeto);

        int cantidad = (m != null) ? m.getCantidad() : 0;

        if (cantidad <= 0) {
            lblNombrePokemon.setText("¡No tienes " + nombreBall + "s!");
            return;
        }

        // Disminuir la cantidad y actualizarla
        m.setCantidad(cantidad - 1);
        if (m.getCantidad() > 0) {
            MochilaDAO.actualizarCantidad(con, m);
        } else {
            MochilaDAO.eliminarDeMochila(con, m.getIdEntrenador(), m.getIdObjeto());
        }

        SonidoController.pausarFondo(null);

        boolean capturado = Math.random() < ratioExito;
        String rutaEfecto;

        if (capturado) {
            capturarPokemon();
            rutaEfecto = "C:/ProyectoPokemon/sonidos/PokemonCapturado.mp3";
        } else {
            rutaEfecto = "C:/ProyectoPokemon/sonidos/PokemonNoCapturado.mp3";
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(5));
		pause.setOnFinished(event -> {
			lblNombrePokemon.setText(capturado ? "¡Capturado!" : "¡Se escapó!");
            SonidoController.continuarFondo(null);
        });
		pause.play();
                
           
        cargarObjetosMochila();
    }
    
	    //que vaya a la caja y no al equipo al ser capturado
    private void capturarPokemon() {
    	 System.out.println("esto es que ha funcionado ahora hay que hacer que se vayan al equipo");
	}
    
    @FXML
    private void cambiarPokemon(ActionEvent event) {
        Pokemon pokemonSalvaje = generarPokemonSalvaje();
        if (pokemonSalvaje != null) {
            lblNombrePokemon.setText(pokemonSalvaje.getMote());
            lblNivelPokemon.setText("Nivel: " + pokemonSalvaje.getNivel());

            String rutaImagen = "./img/Pokemon/Front/" + pokemonSalvaje.getNumPokedex() + ".png";
            File file = new File(rutaImagen);
            if (file.exists()) {
                imgPokemon.setImage(new Image(file.toURI().toString()));
            } else {
                System.err.println("Imagen no encontrada: " + file.getAbsolutePath());
            }
        }
    }
    
    private void ponerOscuridadNoSeleccionado(ImageView hoveredImageView) {
        for (ImageView imageView : imageViews) {
            if (imageView != hoveredImageView) {
                //ajustar la oscuridad
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(-0.5);
                imageView.setEffect(colorAdjust);
            }
        }
    }
    
    private void quitarOscuridadNoSeleccionado() {
        for (ImageView imageView : imageViews) {
            imageView.setEffect(null);
        }
    }
    
    private void aplicarOscuridadSiNoDisponible(ImageView imageView, int cantidad) {
        if (cantidad <= 0) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5);
            imageView.setEffect(colorAdjust);
        } else {
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
