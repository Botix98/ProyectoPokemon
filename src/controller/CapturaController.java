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
	private Pokemon pokemonSalvaje;
	private boolean estaLanzandoPokeball = false;
    Connection con = ConexionBD.getConnection();
    
    @FXML private Button btnSalir;
    @FXML private ImageView imgFondo;
    @FXML private ImageView imgSonido;
    
    @FXML private ImageView imgPokemon;
    @FXML private Label lblNombrePokemon, lblNivelPokemon;
    @FXML private Label lblCapturadoNoCapturado;
    
    @FXML private Button btnCambioCueva, btnCambioHierba, btnCambioNieve, btnCambioPlaya; 
    @FXML private Button btnCambioNoche, btnCambioNube, btnCambioPiedra, btnCambioVolcan;
    
    @FXML private ImageView imgCambioCueva, imgCambioHierba, imgCambioNieve, imgCambioPlaya; 
    @FXML private ImageView imgCambioNoche, imgCambioNube, imgCambioPiedra, imgCambioVolcan;
    
    @FXML private ImageView imgCandadoCueva, imgCandadoNieve, imgCandadoNoche;
    @FXML private ImageView imgCandadoNube, imgCandadoPiedra, imgCandadoPlaya, imgCandadoVolcan;

    @FXML private Button btnCambiarPokemon;
    @FXML private ImageView imgCambiarPokemon;

    @FXML private ImageView imgPokeball, imgSuperball, imgUltraball;
    @FXML private Label lblNumeroPokeballs, lblNumeroSuperballs, lblNumeroUltraballs;
    @FXML private Label lblRatioCatchPokeball, lblRatioCatchSuperball, lblRatioCatchUltraball;
    
    //EN LA LINEA 120 DESCOMENTAR PARA USAR LO DE LOS RIVALES VENCIDOS
    //LINEA 300 Y 302 BORRAR QUE SON PRUEBAS DE CAPTURA
    //EN LA LINEA 249 HACER QUE EL ENTRENADOR ID POKEMON, NUM POKEDEX Y LO QUE PONE SALVAJE TOD0 ESO SE AUTOMATICE
    //COGER EL ID ENTRENADRO DEL POKEMON QUE CAPTURA
    //LLAMAR AL METODO LLAMAR ID MAX AGREGAR 1 CUANDO FUNCIONE LA CAPTURA
    //PILLAR DE CRIANZA Y POKEMONDAO METODOS DE GUARDAR POKEMON Y QUE PASEN A SER NIVEL 1
    //CUANDO CAPTURAS PUEDES PONERLE UN MOTE Y COMPROBAR QUE NO TENGA PALABRAS MALAS, ESPACIOS NI NUMEROS
    //QUE AL CAPTURARLO TENGA UN ATAQUE ALEATORIO DE SU TIPO
    //PODER CAMBIAR ENTRE POKEMON EN EL EQUIPO, PODER EQUIPAR OBJETOS
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        cargarObjetosMochila();
        pokedex = PokedexDAO.cargarPokedexCompleta(con);
        
        pokemonSalvaje = generarPokemonSalvaje();
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
        
        //entrenador.setRivalesVencidos(5);

        switch (entrenador.getRivalesVencidos()) {
        case 5:
            
            imgCandadoNoche.setVisible(false);
            oscuridadFondoSiNoEstaDesbloqueado(btnCambioNoche);
        case 4:
        	imgCandadoVolcan.setVisible(false);
        	oscuridadFondoSiNoEstaDesbloqueado(btnCambioVolcan);
        case 3:
        	imgCandadoNube.setVisible(false);
        	oscuridadFondoSiNoEstaDesbloqueado(btnCambioNube);
        case 2:
            imgCandadoPiedra.setVisible(false);
            oscuridadFondoSiNoEstaDesbloqueado(btnCambioPiedra);
        case 1:
        	imgCandadoCueva.setVisible(false);
        	oscuridadFondoSiNoEstaDesbloqueado(btnCambioCueva);
        case 0:
        	imgCandadoNieve.setVisible(false);
            oscuridadFondoSiNoEstaDesbloqueado(btnCambioNieve);
        case -1:
        	imgCandadoPlaya.setVisible(false);
        	oscuridadFondoSiNoEstaDesbloqueado(btnCambioPlaya);
        case -2:
            break;
            
        default:
            break;
    
        }
  
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

        oscuridadSiNoEstaDisponible(imgPokeball, cantidadPokeball);
        oscuridadSiNoEstaDisponible(imgSuperball, cantidadSuperball);
        oscuridadSiNoEstaDisponible(imgUltraball, cantidadUltraball);
    }
    
    public Pokemon generarPokemonSalvaje() {
        int nivelMedio = 5;
        int numPokemon = 5;

        // Elegir Pokémon aleatorio de la pokédex
        int numPokedex = (int) (Math.random() * 151) + 1;

        // Verifica que la pokedex esté bien cargada
        if (pokedex == null || pokedex.size() < numPokedex) {
            System.err.println("No se cargó la pokédex completa");
            return null;
        }

        // Obtener el Pokémon de la pokédex
        Pokedex pokemonData = pokedex.get(numPokedex - 1);  // Obtener datos del Pokémon en la pokédex

        // Obtener nivel promedio del equipo del entrenador
        for (Pokemon pokemon : PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1)) {
            nivelMedio += pokemon.getNivel();
            numPokemon++;
        }

        if (numPokemon == 0) nivelMedio = 5; // nivel por defecto si no tiene Pokémon
        else nivelMedio = nivelMedio / numPokemon;

        // Determinar el nivel del Pokémon salvaje (basado en el nivel medio)
        int nivel = Math.max(1, nivelMedio + (int)(Math.random() * 5) - 2); // +-2 niveles o por arriba o por debajo

        // Calcular los stats del Pokémon
        int vitalidadMax = 10 + (int)((double)(nivel) / 50 * (pokemonData.getVitalidad() * 2) + (Math.random() * 32)) + nivel;
        int ataque = 5 + (int)((double)(nivel) / 50 * ((pokemonData.getAtaque() * 2) + (Math.random() * 32)));
        int ataqueEsp = 5 + (int)((double)(nivel) / 50 * ((pokemonData.getAtEspecial() * 2) + (Math.random() * 32)));
        int defensa = 5 + (int)((double)(nivel) / 50 * ((pokemonData.getDefensa() * 2) + (Math.random() * 32)));
        int defensaEsp = 5 + (int)((double)(nivel) / 50 * ((pokemonData.getDefEspecial() * 2) + (Math.random() * 32)));
        int velocidad = 5 + (int)((double)(nivel) / 50 * ((pokemonData.getVelocidad() * 2) + (Math.random() * 32)));

        // Configurar imagen
        String rutaImagen = "./img/Pokemon/Front/" + numPokedex + ".png";
        
        //50/50 de que sea macho o hembra
        String sexo = Math.random() < 0.5 ? "H" : "M";

        // Crear el Pokémon con la información generada
        String mote = pokemonData.getNomPokemon();
        return new Pokemon(0, 2, 0, "SALVAJE", numPokedex, mote, vitalidadMax, vitalidadMax, ataque, ataqueEsp, defensa, defensaEsp, velocidad, nivel, 5, sexo, "SIN_ESTADO", 1, 0);
    }
    
    private void lanzarBolas(int idObjeto, double ratioExito, String nombreBall) {
        if (estaLanzandoPokeball) {
            return; // Si está lanzando una bola, no permitir otro intento
        }

        estaLanzandoPokeball = true;

        Mochila m = MochilaDAO.buscarObjetoEnMochila(con, entrenador.getIdEntrenador(), idObjeto);

        int cantidad = (m != null) ? m.getCantidad() : 0;

        if (cantidad <= 0) {
            lblNombrePokemon.setText("¡No tienes " + nombreBall + "s!");
            estaLanzandoPokeball = false; // Permitir intentos futuros si no tiene bolas
            return;
        }

        m.setCantidad(cantidad - 1);
        if (m.getCantidad() > 0) {
            MochilaDAO.actualizarCantidad(con, m);
        } else {
            MochilaDAO.eliminarDeMochila(con, m.getIdEntrenador(), m.getIdObjeto());
        }

        SonidoController.pausarFondo(null);

        boolean capturado = Math.random() < ratioExito;
        String rutaEfecto = capturado 
                ? "C:/ProyectoPokemon/sonidos/PokemonCapturado.mp3" 
                : "C:/ProyectoPokemon/sonidos/PokemonNoCapturado.mp3";
        SonidoController.reproducirEfecto(rutaEfecto, null);

        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            if (capturado) {
                capturarPokemon();  //llamar al metodo para meterlo al equipo si se captura
            } else {
            	lblCapturadoNoCapturado.setText(pokemonSalvaje.getMote() + "¡Se escapó!");
            }
            SonidoController.continuarFondo(null);
            estaLanzandoPokeball = false;
        });
        pause.play();

        cargarObjetosMochila();
    }
    
    private void capturarPokemon() {

        System.out.println("ID Pokémon: " + pokemonSalvaje.getIdPokemon());

        // Contar cuantos Pokémon hay en el equipo, el getIdEntrenador() con el () vacío es porque usa el actual
        int cantidadPokemonsEnEquipo = PokemonDAO.contarSoloPokemonsEnEquipo(con, entrenador.getIdEntrenador());
        int limiteEquipo = 6;  // Límite máximo de Pokémon en el equipo

        // Obtener y asignar ID manual
        int nuevoId = PokemonDAO.obtenerMaxIdPokemon(con) + 1;
        pokemonSalvaje.setIdPokemon(nuevoId);
        pokemonSalvaje.setIdEntrenador(entrenador.getIdEntrenador());
        pokemonSalvaje.setTipoPropietario("ENTRENADOR");

        // Insertar el Pokémon capturado en la base de datos
        boolean insertado = PokemonDAO.anyadirPokemon(con, pokemonSalvaje);

        if (!insertado) {
            lblCapturadoNoCapturado.setText("Error al capturar el Pokémon.");
            return;
        }

        // Mostrar info para debug
        System.out.println("Se captura bien. ID Pokémon: " + pokemonSalvaje.getIdPokemon());

        // Decidir si va al equipo o a la caja según el espacio
        if (cantidadPokemonsEnEquipo < limiteEquipo) {
            PokemonDAO.actualizarEquipoPokemon(con, pokemonSalvaje.getIdPokemon(), 1);  // 1 es el equipo
            lblCapturadoNoCapturado.setText("¡" + pokemonSalvaje.getMote() + " se ha unido a tu equipo!");
        } else {
            PokemonDAO.actualizarEquipoPokemon(con, pokemonSalvaje.getIdPokemon(), 2);  // 2 es la caja
            lblCapturadoNoCapturado.setText("¡" + pokemonSalvaje.getMote() + " se ha enviado a la caja!");
        }
    }
    
    @FXML
    private void cambiarPokemon(ActionEvent event) {
        pokemonSalvaje = generarPokemonSalvaje();  //Genera el Pokémon salvaje de nuevo
        if (pokemonSalvaje != null) {
            lblNombrePokemon.setText(pokemonSalvaje.getMote());
            lblNivelPokemon.setText("Nivel: " + pokemonSalvaje.getNivel());

            // Asignar la imagen
            String rutaImagen = "./img/Pokemon/Front/" + pokemonSalvaje.getNumPokedex() + ".png";
            File file = new File(rutaImagen);
            if (file.exists()) {
                imgPokemon.setImage(new Image(file.toURI().toString()));
            } else {
            	System.out.println("algo esta fallando porque no pilla imagen");
            }

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
    	lanzarBolas(10, 0.8, "Ultraball");
    }
    
    private void cambiarColorTextoFondo(boolean fondoOscuro) {
        String color = fondoOscuro ? "white" : "black";

        lblNombrePokemon.setStyle("-fx-text-fill: " + color + ";");
        lblNivelPokemon.setStyle("-fx-text-fill: " + color + ";");

        lblNumeroPokeballs.setStyle("-fx-text-fill: " + color + ";");
        lblNumeroSuperballs.setStyle("-fx-text-fill: " + color + ";");
        lblNumeroUltraballs.setStyle("-fx-text-fill: " + color + ";");

        lblRatioCatchPokeball.setStyle("-fx-text-fill: " + color + ";");
        lblRatioCatchSuperball.setStyle("-fx-text-fill: " + color + ";");
        lblRatioCatchUltraball.setStyle("-fx-text-fill: " + color + ";");
        
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
    
    private void oscuridadSiNoEstaDisponible(ImageView imageView, int cantidad) {
        if (cantidad <= 0) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5);
            imageView.setEffect(colorAdjust);
        } else {
            imageView.setEffect(null);
        }
    }
    
    private void oscuridadFondoSiNoEstaDesbloqueado(Button button) {
        button.setEffect(null);
    }

    @FXML
    void cambiarFondoCueva(ActionEvent event) {
        imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoCueva.png").toURI().toString()));
        cambiarColorTextoFondo(true); //letra blanca
    }


    @FXML
    void cambiarFondoHierba(ActionEvent event) {
        imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoHierba.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
    }
    
    @FXML
    void cambiarFondoNoche(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoNoche.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
    }

    @FXML
    void cambiarFondoNube(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoNube.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
    	}
    
    @FXML
    void cambiarFondoPiedra(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoPiedra.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
    }

    @FXML
    void cambiarFondoPlaya(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoPlaya.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
    }

    @FXML
    void cambiarFondoVolcan(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoVolcan.png").toURI().toString()));
        cambiarColorTextoFondo(true); //letra blanca
    }


    @FXML
    void cambiarFondoNieve(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoNieve.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
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
