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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Entrenador;
import java.util.List;

import javax.swing.JOptionPane;

import java.util.Arrays;
import java.util.LinkedList;

import dao.MochilaDAO;
import dao.MovimientoDAO;
import dao.MovimientoPokemonDAO;
import dao.ObjetoDAO;
import dao.PokedexDAO;
import dao.PokemonDAO;
import dao.EntrenadorDAO;
import dao.ConexionBD;
import model.Entrenador;
import model.Mochila;
import model.Movimiento;
import model.MovimientoPokemon;
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
	private String tipoFondo1 = "PLANTA";
	private String tipoFondo2 = "BICHO";
    Connection con = ConexionBD.getConnection();
    
    @FXML private Button btnSalir;
    @FXML private ImageView imgSalir;
    @FXML private Text txtSalir;
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

    @FXML private ImageView imgCambiarPokemon;

    @FXML private ImageView imgPokeball, imgSuperball, imgUltraball;
    @FXML private Label lblNumeroPokeballs, lblNumeroSuperballs, lblNumeroUltraballs;
    @FXML private Label lblRatioCatchPokeball, lblRatioCatchSuperball, lblRatioCatchUltraball;
    
    //CUANDO POKEMON TIENE UN ATAQUE NO SE BORRA LA IMAGEN HASTA QUE RECARGO
    //CUANDO EL POKEMON SE CAPTURE METERLE EL MOVIMIENTO_POKEMON PLACAJE (CRIANZA)
    //QUE AL CAPTURARLO TENGA UN ATAQUE ALEATORIO DE SU TIPO
    //CUANDO HAY EL MAXIMO EN LA CAJA QUE SALGA UN MENSAJE PARA LIBERAR QUE CUANDO HAYA 30 QUE NO SE PUEDAN METER MAS
    //QUE EL TEXTO QUE DESAPARECE EN 2 SEGUNDO SE PONGA BLANCO SI ESTA EN LAS VISTAS ESAS
    //COMPROBAR QUE NO TENGA PALABRAS MALAS, ESPACIOS NI NUMEROS AL MOTE en la linea 300 y algo hayq que meterlo
    //PODER CAMBIAR ENTRE POKEMON EN EL EQUIPO, PODER EQUIPAR OBJETOS
    //BOTON EN CAJA IR A CAJA 1 O 2
    
    //EN LA LINEA 120 DESCOMENTAR PARA USAR LO DE LOS RIVALES VENCIDOS
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        cargarObjetosMochila();
        pokedex = PokedexDAO.cargarPokedexCompleta(con);
        LinkedList<Pokedex> posibles = PokedexDAO.cargarPorVariosTipos(con, tipoFondo1, tipoFondo2);
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
        //usar los pokemon por tipo
        LinkedList<Pokedex> posibles = PokedexDAO.cargarPorVariosTipos(con, tipoFondo1, tipoFondo2);

        if (posibles == null || posibles.isEmpty()) {
            System.err.println("No hay Pokémon disponibles para los tipos: " + tipoFondo1 + ", " + tipoFondo2);
            return null;
        }

        // uno aleatorio de la lista filtrada por los tipos
        int indice = (int) (Math.random() * posibles.size());
        Pokedex pokemonData = posibles.get(indice);

        int nivel = 1;

        // Calcular los stats como antes
        int vitalidadMax = 10 + (int)((double)(nivel) / 50 * (pokemonData.getVitalidad() * 2) + (Math.random() * 32)) + nivel;
        int ataque = 5 + (int)((double)(nivel) / 50 * ((pokemonData.getAtaque() * 2) + (Math.random() * 32)));
        int ataqueEsp = 5 + (int)((double)(nivel) / 50 * ((pokemonData.getAtEspecial() * 2) + (Math.random() * 32)));
        int defensa = 5 + (int)((double)(nivel) / 50 * ((pokemonData.getDefensa() * 2) + (Math.random() * 32)));
        int defensaEsp = 5 + (int)((double)(nivel) / 50 * ((pokemonData.getDefEspecial() * 2) + (Math.random() * 32)));
        int velocidad = 5 + (int)((double)(nivel) / 50 * ((pokemonData.getVelocidad() * 2) + (Math.random() * 32)));

        String sexo = Math.random() < 0.5 ? "H" : "M";
        String mote = pokemonData.getNomPokemon();
        int numPokedex = pokemonData.getNumPokedex();

        return new Pokemon(0, 0, 0, "ENTRENADOR", numPokedex, mote, vitalidadMax, vitalidadMax, ataque, ataqueEsp, defensa, defensaEsp, velocidad, nivel, 5, sexo, "SIN_ESTADO", 1, 0);
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
            	mostrarMensajeTemporal(pokemonSalvaje.getMote() + " ¡Se escapó!", 2);
            }
            SonidoController.continuarFondo(null);
            estaLanzandoPokeball = false;
        });
        pause.play();

        cargarObjetosMochila();
    }
    
    private void capturarPokemon() {
    	 // Contar cuantos Pokémon hay en el equipo
        int cantidadPokemonsEnEquipo = PokemonDAO.contarSoloPokemonsEnEquipo(con, entrenador.getIdEntrenador());
        int cantidadPokemonsEnCaja = PokemonDAO.contarSoloPokemonsEnCaja(con, entrenador.getIdEntrenador());
        int limiteEquipo = 6;  
        int limiteCaja = 30;

        // Verificar si la caja está llena
        if (cantidadPokemonsEnEquipo >= limiteEquipo && cantidadPokemonsEnCaja >= limiteCaja) {
            JOptionPane.showMessageDialog(null, "No puedes tener mas de 30 Pokémon en la caja. Libera alguno antes de capturar mas.");
            mostrarMensajeTemporal("¡No puedes capturar más Pokemon!", 2);
            return;
        }

        // Pedir al usuario que ingrese el mote del nuevo Pokémon
        String mote = JOptionPane.showInputDialog(null, "Ingresa el mote del nuevo Pokémon:");

        if (mote == null || mote.trim().isEmpty()) {
            mostrarMensajeTemporal("Captura cancelada. Mote inválido.", 2);
            return;
        }

        int nuevoId = PokemonDAO.obtenerMaxIdPokemon(con) + 1;
        pokemonSalvaje.setIdPokemon(nuevoId);
        pokemonSalvaje.setIdEntrenador(entrenador.getIdEntrenador());
        pokemonSalvaje.setTipoPropietario("ENTRENADOR");
        pokemonSalvaje.setMote(mote);

        boolean insertado = PokemonDAO.anyadirPokemon(con, pokemonSalvaje);
        if (!insertado) {
            mostrarMensajeTemporal("¡Error al capturar " + pokemonSalvaje.getMote() + "!", 2);
            return;
        }

        if (cantidadPokemonsEnEquipo < limiteEquipo) {
            PokemonDAO.actualizarEquipoPokemon(con, pokemonSalvaje.getIdPokemon(), 1);
            mostrarMensajeTemporal("¡" + pokemonSalvaje.getMote() + " se ha unido a tu equipo!", 2);
        } else {
            PokemonDAO.actualizarEquipoPokemon(con, pokemonSalvaje.getIdPokemon(), 2);
            mostrarMensajeTemporal("¡" + pokemonSalvaje.getMote() + " se ha enviado a la caja!", 2);
        }
     // Obtiene el movimiento especifico usando el MovimientoDAO
        Movimiento placaje = MovimientoDAO.buscarPorId(con, 60);
        if (placaje != null) {

            MovimientoPokemon movimientoPokemon = new MovimientoPokemon();
            movimientoPokemon.setIdPokemon(pokemonSalvaje.getIdPokemon());
            movimientoPokemon.setIdMovimiento(placaje.getIdMovimiento());
            movimientoPokemon.setPpActuales(placaje.getPpMax());

            // Insertar el movimiento en la base de datos
            MovimientoPokemonDAO.insertarMovimientoPokemon(con, movimientoPokemon);
        } else {
            System.out.println("No se encuentra el movimiento en la bd.");
        }
        cambiarPokemon(null);
    }
    
    @FXML
    void cambiarPokemon(MouseEvent event) {
    	pokemonSalvaje = generarPokemonSalvaje();  // Genera el Pokémon salvaje de nuevo
    	lblNombrePokemon.setText(PokedexDAO.cargarPorNumPokedex(con, pokemonSalvaje.getNumPokedex()).getNomPokemon());
        if (pokemonSalvaje != null) { 
            lblNivelPokemon.setText("Nivel: " + pokemonSalvaje.getNivel());

            // Asignar la imagen
            String rutaImagen = "./img/Pokemon/Front/" + pokemonSalvaje.getNumPokedex() + ".png";
            File file = new File(rutaImagen);
            if (file.exists()) {
                imgPokemon.setImage(new Image(file.toURI().toString()));
            } else {
                System.out.println("Imagen no encontrada para: " + rutaImagen);
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
    
    private void mostrarMensajeTemporal(String mensaje, int segundos) {
        lblCapturadoNoCapturado.setText(mensaje);
        lblCapturadoNoCapturado.setVisible(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(segundos));
        pause.setOnFinished(event -> lblCapturadoNoCapturado.setText(""));
        pause.play();
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
        tipoFondo1 = "ELECTRICO";
        tipoFondo2 = "VENENO";
        cambiarPokemon(null); 
    }


    @FXML
    void cambiarFondoHierba(ActionEvent event) {
        imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoHierba.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
        tipoFondo1 = "PLANTA";
        tipoFondo2 = "BICHO";
        cambiarPokemon(null); 
    }
    
    @FXML
    void cambiarFondoNoche(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoNoche.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
        tipoFondo1 = "FANTASMA";
        tipoFondo2 = "PSIQUICO";
        cambiarPokemon(null); 
    }

    @FXML
    void cambiarFondoNube(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoNube.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
        tipoFondo1 = "VOLADOR";
        tipoFondo2 = "DRAGON";
        cambiarPokemon(null); 
    	}
    
    @FXML
    void cambiarFondoPiedra(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoPiedra.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
        tipoFondo1 = "TIERRA";
        tipoFondo2 = "LUCHA";
        cambiarPokemon(null); 
    }

    @FXML
    void cambiarFondoPlaya(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoPlaya.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
        tipoFondo1 = "AGUA";
        tipoFondo2 = "NORMAL";
        cambiarPokemon(null); 
    }

    @FXML
    void cambiarFondoVolcan(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoVolcan.png").toURI().toString()));
        cambiarColorTextoFondo(true); //letra blanca
        tipoFondo1 = "FUEGO";
        tipoFondo2 = "ROCA";
        cambiarPokemon(null); 
    }


    @FXML
    void cambiarFondoNieve(ActionEvent event) {
    	imgFondo.setImage(new Image(new File("C:/ProyectoPokemon/img/captura/fondoNieve.png").toURI().toString()));
        cambiarColorTextoFondo(false); //letra negra
        tipoFondo1 = "HIELO";
        tipoFondo2 = "NULL";
        cambiarPokemon(null); 
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
