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
import dao.PokedexDAO;
import dao.PokemonDAO;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Entrenador;
import model.Movimiento;
import model.MovimientoPokemon;
import model.Pokedex;
import model.Pokemon;
import model.TipoEstados;

public class CrianzaController {

	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	
    private Pokemon pokemonMachoSeleccionado;
    private Pokemon pokemonHembraSeleccionada;
    private Pokemon pokemonHijoGenerado;
    
    private boolean incubadoraAbierta = false;
	
    @FXML
    private VBox vbCaja;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnHembra;

    @FXML
    private Button btnMacho;

    @FXML
    private ImageView imgAdn1;

    @FXML
    private ImageView imgAdn2;

    @FXML
    private ImageView imgAdn3;

    @FXML
    private ImageView imgAdn4;

    @FXML
    private ImageView imgBtnPokeH;

    @FXML
    private ImageView imgBtnPokeM;

    @FXML
    private ImageView imgCria;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgFusionar;

    @FXML
    private ImageView imgIncubadora;

    @FXML
    private ImageView imgPokeH;

    @FXML
    private ImageView imgPokeM;

    @FXML
    private ImageView imgSonido;
    
    @FXML
    private ImageView imgSalir;

    @FXML
    private ProgressBar pbFusion;

    @FXML
    private ScrollPane sbCaja;
    
    @FXML
    private Text txtSalir;

    @FXML
    private Text txtFusionar;

    @FXML
    private Text txtHembra;

    @FXML
    private Text txtMacho;
    
    Connection con = ConexionBD.getConnection();
    
 // Lista de palabras prohibidas
    private static final String[] PALABRAS_PROHIBIDAS = {
        "puta","puto","mierda","idiota","gilipollas","tonto","imbecil","cabron",
        "perra","subnormal","maricon","zorra","culo","caca","teta","polla",
        "pene","rabo","follar","verga","sexo"
    };

    // Método para detectar malas palabras
    private boolean contienePalabrasProhibidias(String texto) {
        if (texto == null) return false;
        String lower = texto.toLowerCase();
        for (String palabra : PALABRAS_PROHIBIDAS) {
            if (lower.contains(palabra)) return true;
        }
        return false;
    }
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
    }

    public void initialize() {
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Laboratorio.mp3");

        imgFusionar.setOnMouseClicked(this::fusionar);
    }
    
    @FXML
    void ElegirPokeH(ActionEvent event) {
    	mostrarPokemonsSexo("H");
    }

    @FXML
    void ElegirPokeM(ActionEvent event) {
    	mostrarPokemonsSexo("M");
    }
    
    private void mostrarPokemonsSexo(String sexo) {
        vbCaja.getChildren().clear();

        List<Pokemon> pokemons = PokemonDAO.cargarPokemonEntrenadorPorSexo(con, entrenador.getIdEntrenador(), sexo);

        VBox vbox = new VBox(10); // Separación entre Pokémon
        vbox.setStyle("-fx-padding: 10;");

        for (Pokemon pokemon : pokemons) {
            String rutaImagen = "./img/Pokemon/Front/" + pokemon.getNumPokedex() + ".png";
            Image imagen = new Image(new File(rutaImagen).toURI().toString());

            // Imagen del Pokémon
            ImageView pokemonImage = new ImageView(imagen);
            pokemonImage.setFitHeight(150);
            pokemonImage.setFitWidth(150);

            // Label con el nombre y sexo
            Label labelMote = new Label(pokemon.getMote() + " (" + pokemon.getSexo() + ")");
            labelMote.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
            labelMote.setWrapText(true);

            // Contenedor que incluye imagen y texto
            VBox contenedorPokemon = new VBox(5, pokemonImage, labelMote);
            contenedorPokemon.setStyle("-fx-alignment: center; -fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
            contenedorPokemon.setOnMouseClicked(e -> {
                if (sexo.equals("H")) {
                    imgPokeH.setImage(imagen);
                    pokemonHembraSeleccionada = pokemon;
                } else {
                    imgPokeM.setImage(imagen);
                    pokemonMachoSeleccionado = pokemon;
                }
            });

            vbox.getChildren().add(contenedorPokemon);
        }

        vbCaja.getChildren().add(vbox);
    }

    public void fusionar(MouseEvent event) {
        pbFusion.setStyle("-fx-accent: yellow;");
        double progresoActual = pbFusion.getProgress();
        double nuevoProgreso = progresoActual + 0.1;

        if (nuevoProgreso >= 1.0) {
            pbFusion.setProgress(1.0);
            generarPokemonHijo();
        } else {
            pbFusion.setProgress(nuevoProgreso);
        }
    }
    
    private void generarPokemonHijo() {
        if (pokemonHembraSeleccionada == null || pokemonMachoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un Pokemon macho y una hembra.");
            return;
        }

        // Verificación de fertilidad antes de crear un nuevo pokemon
        if (pokemonMachoSeleccionado.getFertilidad() == 0 || pokemonHembraSeleccionada.getFertilidad() == 0) {
            JOptionPane.showMessageDialog(null, "Uno de los Pokemon no puede criar mas debido a su fertilidad.");
            return;
        }
        
        boolean cogerDelMacho = Math.random() < 0.5;
        Pokemon base = cogerDelMacho ? pokemonMachoSeleccionado : pokemonHembraSeleccionada;

        //Filtra por malas palabras
        String nombrePorDefecto = pokemonHembraSeleccionada.getMote();
        String mote;

        while (true) {
            mote = JOptionPane.showInputDialog(null, "Ingresa el mote del nuevo Pokémon:");
            if (mote == null) {
                mote = nombrePorDefecto;
                break;
            }
            mote = mote.trim();
            if (mote.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El mote no puede estar vacío.");
            } else if (contienePalabrasProhibidias(mote)) {
                JOptionPane.showMessageDialog(null, "El mote contiene palabras inapropiadas.");
            } else {
                break;
            }
        }
        
        //Asigna los valores al pokemon hijo
        pokemonHijoGenerado = new Pokemon();
        int nuevoId = PokemonDAO.obtenerMaxIdPokemon(con) + 1;

        pokemonHijoGenerado.setIdPokemon(nuevoId);
        pokemonHijoGenerado.setIdEntrenador(entrenador.getIdEntrenador());
        pokemonHijoGenerado.setNumPokedex(base.getNumPokedex());
        pokemonHijoGenerado.setMote(mote);
        pokemonHijoGenerado.setNivel(1);
        
        LinkedList<Pokedex> pokedex = PokedexDAO.cargarPokedexCompleta(con);

        int vitalidad = 10 + (int)(1.0D / 100 * ((pokedex.get(pokemonHijoGenerado.getNumPokedex() - 1).getVitalidad() * 2) + (int) (Math.random() * 32))) + 1;
        pokemonHijoGenerado.setVitalidadMax(vitalidad);
        pokemonHijoGenerado.setVitalidadAct(pokemonHijoGenerado.getVitalidadMax());
        
        int ataque = 5 + (int)(1.0D / 100 * ((pokedex.get(pokemonHijoGenerado.getNumPokedex() - 1).getAtaque() * 2) + (int) (Math.random() * 32)));
        pokemonHijoGenerado.setAtaque(ataque);
        
        int ataqueEsp = 5 + (int)(1.0D / 100 * ((pokedex.get(pokemonHijoGenerado.getNumPokedex() - 1).getAtEspecial() * 2) + (int) (Math.random() * 32)));
        pokemonHijoGenerado.setAtEspecial(ataqueEsp);
        
        int defensa = 5 + (int)(1.0D / 100 * ((pokedex.get(pokemonHijoGenerado.getNumPokedex() - 1).getDefensa() * 2) + (int) (Math.random() * 32)));
        pokemonHijoGenerado.setDefensa(defensa);
        
        int defensaEsp = 5 + (int)(1.0D / 100 * ((pokedex.get(pokemonHijoGenerado.getNumPokedex() - 1).getDefEspecial() * 2) + (int) (Math.random() * 32)));
        pokemonHijoGenerado.setDefEspecial(defensaEsp);
        
        int velocidad = 5 + (int)(1.0D / 100 * ((pokedex.get(pokemonHijoGenerado.getNumPokedex() - 1).getVelocidad() * 2) + (int) (Math.random() * 32)));
        pokemonHijoGenerado.setVelocidad(velocidad);
        
        pokemonHijoGenerado.setFertilidad(5);
        pokemonHijoGenerado.setSexo(Math.random() < 0.5 ? "H" : "M");
        pokemonHijoGenerado.setEstado(TipoEstados.valueOf("SIN_ESTADO"));

        int pokemonsEquipo = PokemonDAO.contarPokemonsEnEquipo(con, entrenador.getIdEntrenador());
        pokemonHijoGenerado.setEquipo(pokemonsEquipo < 6 ? 1 : 2);
        pokemonHijoGenerado.setTipoPropietario("ENTRENADOR");
        
        boolean insertado = PokemonDAO.anyadirPokemon(con, pokemonHijoGenerado);
        
        // Obtiene el movimiento especifico usando el MovimientoDAO
        Movimiento placaje = MovimientoDAO.buscarPorId(con, 50);
        if (placaje != null) {

            MovimientoPokemon movimientoPokemon = new MovimientoPokemon();
            movimientoPokemon.setIdPokemon(pokemonHijoGenerado.getIdPokemon());
            movimientoPokemon.setIdMovimiento(placaje.getIdMovimiento());
            movimientoPokemon.setPpActuales(placaje.getPpMax());

            // Insertar el movimiento en la base de datos
            MovimientoPokemonDAO.insertarMovimientoPokemon(con, movimientoPokemon);
        } else {
            System.out.println("No se encuentra el movimiento en la bd.");
        }
        
        // Reducir la fertilidad de los progenitores
        pokemonMachoSeleccionado.setFertilidad(pokemonMachoSeleccionado.getFertilidad() - 1);
        pokemonHembraSeleccionada.setFertilidad(pokemonHembraSeleccionada.getFertilidad() - 1);

        // Actualiza la fertilidad en la base de datos para ambos progenitores
        PokemonDAO.actualizarFertilidadPokemon(con, pokemonMachoSeleccionado);
        PokemonDAO.actualizarFertilidadPokemon(con, pokemonHembraSeleccionada);

        if (insertado) {
            String rutaImagen = "./img/Pokemon/Front/" + pokemonHijoGenerado.getNumPokedex() + ".png";
            imgCria.setImage(new Image(new File(rutaImagen).toURI().toString()));
            System.out.println("Pokémon generado y anadido a la base de datos");
        } else {
            System.out.println("Error al anadir el nuevo Pokémon.");
        }
    }

    @FXML
    void habrir(MouseEvent event) {
        String ruta;

        if (incubadoraAbierta) {
            // Cerrar incubadora
            ruta = "C:/ProyectoPokemon/img/crianza/incubadora.png";
            imgCria.setVisible(false);
        } else {
            // Abrir incubadora
            ruta = "C:/ProyectoPokemon/img/crianza/incubadora2.png";
            imgCria.setVisible(true);
        }

        imgIncubadora.setImage(new Image(new File(ruta).toURI().toString()));

        incubadoraAbierta = !incubadoraAbierta;
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
    void abrirIncubadora(ActionEvent event) {

    }
    
    @FXML
    void Salir(ActionEvent event) {
    	try {
    		SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/Laboratorio.mp3");
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
}
