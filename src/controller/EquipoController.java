package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ConexionBD;
import dao.MochilaDAO;
import dao.ObjetoDAO;
import dao.PokedexDAO;
import dao.PokemonDAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Entrenador;
import model.Mochila;
import model.Objeto;
import model.Pokemon;

public class EquipoController {
	
	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	private LinkedList<Pokemon> caja;
	private List<ImageView> imagenesEquipo;
	private LinkedList<Pokemon> equipo;
	private Pokemon pokemonSeleccionado;
    
	@FXML private Button btnCaja;
    @FXML private Button btnSalir;
    @FXML private ImageView imgFondo;
    @FXML private ImageView imgSonido;
    @FXML private ImageView imLiberar;
    @FXML private ImageView imgCaja;
    @FXML private ImageView imgMandarCaja;

    @FXML private ImageView imgSalir;
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
    
    @FXML private Button btnLiberarPokemon;
    @FXML private Button btnMandarPokemonaCaja;
    
    @FXML private Text txtLiberar;
    @FXML private Text txtMovCaja;
    @FXML private Text txtSalir;
    @FXML private Text txtCaja;
    
    @FXML private TableView<Mochila> tvMochila;
    @FXML private TableColumn<Mochila, String> tcObjetoM;
    @FXML private TableColumn<Mochila, Integer> tcCantidadM;

    @FXML
    private Button btnQuitarObjeto;

    @FXML
    private ImageView imLiberar1;

    @FXML
    private Label lblObjetoEquipado;

    @FXML
    private Text txtQuitarObjeto;
    
    Connection con = ConexionBD.getConnection();
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController, CajaController cajaController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        pbPokemonSeleccionado.setVisible(false);
        btnLiberarPokemon.setVisible(false);
        btnMandarPokemonaCaja.setVisible(false);
        tvMochila.setVisible(false);
        mostrarEquipo();
        cargarObjetosMochila();
    }

	public void initialize() {
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Equipo.mp3");
    	lblNombrePokemonSeleccionado.setVisible(false);
        lblMotePokemonSeleccionado.setVisible(false);
        lblNivelPokemonSeleccionado.setVisible(false);
        pbPokemonSeleccionado.setVisible(false);
        imgPokemonSeleccionado.setVisible(false);
        txtLiberar.setVisible(false);
        txtMovCaja.setVisible(false);
        tvMochila.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tvMochila.getSelectionModel().getSelectedItem() != null) {
                Mochila mochilaSeleccionada = tvMochila.getSelectionModel().getSelectedItem();
                usarYEquiparObjeto(mochilaSeleccionada);
            }
        });
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
        btnLiberarPokemon.setVisible(true);
        btnMandarPokemonaCaja.setVisible(true);
        txtLiberar.setVisible(true);
        txtMovCaja.setVisible(true);
        tvMochila.setVisible(true);

        List<ImageView> imagenesEquipo = List.of(
            imgPokemonEquipo1, imgPokemonEquipo2, imgPokemonEquipo3,
            imgPokemonEquipo4, imgPokemonEquipo5, imgPokemonEquipo6
        );

        for (int i = 0; i < imagenesEquipo.size(); i++) {
            if (origen == imagenesEquipo.get(i) && i < equipo.size()) {
                Pokemon pokemon = equipo.get(i);
                pokemonSeleccionado = pokemon;
                
                if (pokemonSeleccionado.getIdObjeto() != 0) {
                	btnQuitarObjeto.setVisible(true);
                	txtQuitarObjeto.setVisible(true);
                	lblObjetoEquipado.setText("Objeto: " + ObjetoDAO.buscarObjetoPorId(con, pokemonSeleccionado.getIdObjeto()).getNomObjeto());
                	lblObjetoEquipado.setVisible(true);
                }
                else {
                	btnQuitarObjeto.setVisible(false);
                	txtQuitarObjeto.setVisible(false);
                	lblObjetoEquipado.setVisible(false);
                }

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

    // Método para cargar los objetos de la mochila en la tabla
    private void cargarObjetosMochila() {
        Connection con = ConexionBD.getConnection();
        LinkedList<Mochila> objetosMochila = MochilaDAO.cargarMochilaPorEntrenador(con, entrenador.getIdEntrenador());

        tvMochila.getItems().setAll(objetosMochila);

        tcObjetoM.setCellValueFactory(data -> {
            Objeto obj = ObjetoDAO.buscarObjetoPorId(con, data.getValue().getIdObjeto());
            return new SimpleStringProperty(obj != null ? obj.getNomObjeto() : "Desconocido");
        });

        tcCantidadM.setCellValueFactory(data -> 
            new SimpleIntegerProperty(data.getValue().getCantidad()).asObject()
        );
    }
    
    private void usarYEquiparObjeto(Mochila mochila) {
        if (pokemonSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un Pokémon para equipar el objeto.");
            return;
        }

        Objeto objeto = ObjetoDAO.buscarObjetoPorId(con, mochila.getIdObjeto());
        if (objeto == null) {
            JOptionPane.showMessageDialog(null, "Objeto no encontrado.");
            return;
        }

        if (!objeto.isEquipable()) {
            JOptionPane.showMessageDialog(null, objeto.getNomObjeto() + " no se puede equipar.");
            return;
        }

        if (pokemonSeleccionado.getIdObjeto() != 0) {
            Objeto objetoEquipado = ObjetoDAO.buscarObjetoPorId(con, pokemonSeleccionado.getIdObjeto());
            String nombreObjetoEquipado = objetoEquipado != null ? objetoEquipado.getNomObjeto() : "otro objeto";
            JOptionPane.showMessageDialog(null, pokemonSeleccionado.getMote() + " ya tiene equipado " + nombreObjetoEquipado + ". Retíralo antes de equipar otro.");
            return;
        }

        equiparObjeto(pokemonSeleccionado, objeto);
        restarUnoObjetoMochila(mochila);

        JOptionPane.showMessageDialog(null, "Has equipado " + objeto.getNomObjeto() + " a " + pokemonSeleccionado.getMote());
    }
    
    @FXML
    void quitarObjeto(ActionEvent event) {
    	System.out.println(pokemonSeleccionado.getIdObjeto());
    	
    	System.out.println(ObjetoDAO.buscarObjetoPorId(con, pokemonSeleccionado.getIdObjeto()));
    	
    	desequiparObjeto(ObjetoDAO.buscarObjetoPorId(con, pokemonSeleccionado.getIdObjeto()));
    	
    	cargarObjetosMochila();
    }

    private void desequiparObjeto(Objeto objeto) {
    	JOptionPane.showMessageDialog(null, "Has quitado " + objeto.getNomObjeto() + " a " + pokemonSeleccionado.getMote());
    	
    	objeto.eliminarBonificacionEstadisticas(pokemonSeleccionado);
    	
    	sumarUnObjetoMochila();
    	
    	pokemonSeleccionado.setIdObjeto(0);
    	
    	btnQuitarObjeto.setVisible(false);
    	txtQuitarObjeto.setVisible(false);
    	lblObjetoEquipado.setVisible(false);
	}
    
    private void sumarUnObjetoMochila() {
    	LinkedList<Mochila> mochila = MochilaDAO.cargarMochilaPorEntrenador(con, entrenador.getIdEntrenador());
    	
    	boolean objetoEnMochila = false;
    	
    	for (int i = 0; i < mochila.size(); i++) {
			if (mochila.get(i).getIdObjeto() == pokemonSeleccionado.getIdObjeto()) {
				objetoEnMochila = true;
				mochila.get(i).setCantidad(mochila.get(i).getCantidad() + 1);
				MochilaDAO.actualizarCantidad(con, mochila.get(i));
				break;
			}
		}
    	
    	if (!objetoEnMochila) {
    		MochilaDAO.insertarEnMochila(con, new Mochila(entrenador.getIdEntrenador(), pokemonSeleccionado.getIdObjeto(), 1));
    	}
    }

	private void restarUnoObjetoMochila(Mochila mochila) {
        // Verificar si hay más de una unidad
        if (mochila.getCantidad() > 1) {
            mochila.setCantidad(mochila.getCantidad() - 1);
            boolean exito = MochilaDAO.actualizarCantidad(ConexionBD.getConnection(), mochila);

            if (exito) {
                cargarObjetosMochila();
                System.out.println("Se ha restado 1 unidad del objeto.");
            } else {
                System.out.println("No se pudo actualizar la cantidad.");
            }

        } else {
            // Si solo queda una, eliminar el objeto de la mochila
            boolean exito = MochilaDAO.eliminarDeMochila(ConexionBD.getConnection(), mochila.getIdEntrenador(), mochila.getIdObjeto());

            if (exito) {
                cargarObjetosMochila();
                System.out.println("Se ha eliminado el objeto de la mochila.");
            } else {
                System.out.println("No se pudo eliminar el objeto.");
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
        
    public void equiparObjeto(Pokemon pokemon, Objeto nuevoObjeto) {
        int idObjeto = pokemon.getIdObjeto();
        Objeto objetoActual = null;

        if (idObjeto != 0) {
            objetoActual = ObjetoDAO.buscarObjetoPorId(con, idObjeto);
        }
        if (objetoActual != null && objetoActual.getIdObjeto() != 0) {
        	objetoActual.eliminarBonificacionEstadisticas(pokemon);
        }

        // Equipar nuevo objeto
        pokemon.setIdObjeto(nuevoObjeto.getIdObjeto());

        nuevoObjeto.aplicarBonificacionEstadisticas(pokemon);

        // Actualizar Pokémon en BD
        PokemonDAO.actualizarPokemonParaObjeto(con, pokemon);

        System.out.println("Objeto:" + nuevoObjeto.getNomObjeto() + " equipado correctamente a " + pokemon.getMote());
        
        lblObjetoEquipado.setText("Objeto: " + nuevoObjeto.getNomObjeto());
        lblObjetoEquipado.setVisible(true);
        txtQuitarObjeto.setVisible(true);
        btnQuitarObjeto.setVisible(true);
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

    
    @FXML
    void liberarPokemon(ActionEvent event) {
    	
    	int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres liberar a " + pokemonSeleccionado.getMote() + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
    	if (confirm != JOptionPane.YES_OPTION) return;
    	
        if (pokemonSeleccionado == null) {
            System.out.println("No se ha seleccionado ningún Pokémon.");
            return;
        }
        if (equipo.size() <= 1) {
	        JOptionPane.showMessageDialog(null, "No puedes tener menos de 1 Pokemon en el equipo");
	        return;
	    }
        boolean eliminado = PokemonDAO.eliminarPokemon(con, pokemonSeleccionado.getIdPokemon());

        if (eliminado) {
            equipo.remove(pokemonSeleccionado);
            pokemonSeleccionado = null;
            System.out.println("Pokémon liberado correctamente.");
            mostrarEquipo();
            lblNombrePokemonSeleccionado.setVisible(false);
            lblMotePokemonSeleccionado.setVisible(false);
            lblNivelPokemonSeleccionado.setVisible(false);
            pbPokemonSeleccionado.setVisible(false);
            imgPokemonSeleccionado.setVisible(false);
            btnLiberarPokemon.setVisible(false);
            btnMandarPokemonaCaja.setVisible(false);
            
        } else {
            System.out.println("No se pudo liberar el Pokémon.");
        }
    }

    @FXML
    void mandarPokemonaCaja(ActionEvent event) {
	    if (pokemonSeleccionado == null) {
	        JOptionPane.showMessageDialog(null, "Debes seleccionar un Pokemon");
	        return;
	    }

	    if (equipo.size() <= 1) {
	        JOptionPane.showMessageDialog(null, "No puedes tener menos de 1 Pokemon en el equipo");
	        return;
	    }
	    int cantidadEnCaja = PokemonDAO.contarSoloPokemonsEnCaja(con, entrenador.getIdEntrenador());
	    if (cantidadEnCaja >= 30) {
	        JOptionPane.showMessageDialog(null, "La caja ya tiene 30 Pokémon. No puedes enviar más, libera a alguno");
	        return;
	    }
	    // Actualizar el valor de equipo a 2
	    PokemonDAO.actualizarEquipo(con, pokemonSeleccionado.getIdPokemon(), 2);

	    // Eliminar el Pokémon del equipo
	    equipo.remove(pokemonSeleccionado);
	    System.out.println("Pokémon enviado a la caja.");
	    mostrarEquipo();

	    // Ocultamos los datos del Pokémon
	    pokemonSeleccionado = null;
	    lblNombrePokemonSeleccionado.setVisible(false);
	    lblMotePokemonSeleccionado.setVisible(false);
	    lblNivelPokemonSeleccionado.setVisible(false);
	    pbPokemonSeleccionado.setVisible(false);
	    imgPokemonSeleccionado.setVisible(false);
	    btnLiberarPokemon.setVisible(false);
	    btnMandarPokemonaCaja.setVisible(false);
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
