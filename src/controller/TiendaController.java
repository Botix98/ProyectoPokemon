package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.LinkedList;

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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import dao.MochilaDAO;
import dao.ObjetoDAO;
import dao.EntrenadorDAO;
import dao.ConexionBD;
import model.Entrenador;
import model.Mochila;
import model.Objeto;


public class TiendaController {

	private Stage stage;
	private Entrenador entrenador;
	private MenuController menuController;
	private LoginController loginController;
	private CentroPokemonController centroPokemonController;

    @FXML
    private Button btnCambiar;

    @FXML
    private Button btnSalir;

    @FXML
    private ImageView imgCambio;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgObjeto;
    
    @FXML
    private ImageView imgSonido;

    @FXML
    private TableView<Mochila> tvMochila;

    @FXML
    private TableColumn<Mochila, String> tcObjetoM;

    @FXML
    private TableColumn<Mochila, Integer> tcCantidadM;

    @FXML
    private TableColumn<Mochila, Integer> tcValorM;

    @FXML
    private TableView<Objeto> tvTienda;

    @FXML
    private TableColumn<Objeto, String> tcObjetoT;

    @FXML
    private TableColumn<Objeto, Integer> tcPrecioT;

    @FXML
    private Text txtMochila;

    @FXML
    private Text txtTienda;
    
    @FXML
    private Text txtSalir;
    
    @FXML
    private Text txtTienes;
    
    @FXML
    private Label lblDienero;
    
    @FXML
    private ImageView imgSalir;
    
    Connection con = ConexionBD.getConnection();
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController, CentroPokemonController centroPokemonController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        this.centroPokemonController = centroPokemonController;
        
        lblDienero.setText(entrenador.getPokedolares() + "");

        
        cargarObjetosTienda();
        cargarObjetosMochila();
    }
    
    // Método para cargar los objetos de la tienda en la tabla
    private void cargarObjetosTienda() {

        LinkedList<Objeto> objetosTienda = ObjetoDAO.cargarObjetos(con);

        // Pasar la lista como ObservableList a la TableView
        tvTienda.getItems().setAll(objetosTienda);

        // Establecer columnas si aún no lo has hecho
        tcObjetoT.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomObjeto()));
        tcPrecioT.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPrecio()).asObject());

        // Manejar evento de clic
        tvTienda.setOnMouseClicked(event -> {
            Objeto objeto = tvTienda.getSelectionModel().getSelectedItem();
            if (objeto != null) {
                comprarObjeto(objeto);
            }
        });
    }

 // Método para cargar los objetos de la mochila en la tabla
    private void cargarObjetosMochila() {
        LinkedList<Mochila> objetosMochila = MochilaDAO.cargarMochilaPorEntrenador(con, entrenador.getIdEntrenador());

        tvMochila.getItems().setAll(objetosMochila);

        tcObjetoM.setCellValueFactory(data -> {
            Objeto obj = ObjetoDAO.buscarObjetoPorId(con, data.getValue().getIdObjeto());
            return new SimpleStringProperty(obj != null ? obj.getNomObjeto() : "Desconocido");
        });
        tcCantidadM.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCantidad()).asObject());
        tcValorM.setCellValueFactory(data -> {
            Objeto obj = ObjetoDAO.buscarObjetoPorId(con, data.getValue().getIdObjeto());
            return new SimpleIntegerProperty(obj != null ? obj.getPrecio() : 0).asObject();
        });

        tvMochila.setOnMouseClicked(event -> {
            Mochila mochila = tvMochila.getSelectionModel().getSelectedItem();
            if (mochila != null) {
                venderObjetoMochila(mochila);
            }
        });
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

    private void comprarObjeto(Objeto objeto) {
        if (entrenador != null && entrenador.getIdEntrenador() > 0) {
            System.out.println("ID_ENTRENADOR en compra: " + entrenador.getIdEntrenador());

            if (entrenador.getPokedolares() >= objeto.getPrecio()) {
                Connection con = ConexionBD.getConnection();

                // Verificar si el objeto ya está en la mochila
                Mochila mochilaExistente = MochilaDAO.buscarObjetoEnMochila(con, entrenador.getIdEntrenador(), objeto.getIdObjeto());

                boolean exito;

                if (mochilaExistente != null) {
                    // Ya existe, aumentar la cantidad
                    mochilaExistente.setCantidad(mochilaExistente.getCantidad() + 1);
                    exito = MochilaDAO.actualizarCantidad(con, mochilaExistente);
                } else {
                    // No existe, insertar nueva entrada
                    Mochila nuevaMochila = new Mochila(entrenador.getIdEntrenador(), objeto.getIdObjeto(), 1);
                    exito = MochilaDAO.insertarEnMochila(con, nuevaMochila);
                }

                if (exito) {
                    // Descontar el dinero
                    entrenador.setPokedolares(entrenador.getPokedolares() - objeto.getPrecio());
                    EntrenadorDAO.actualizarPokedolares(con, entrenador.getIdEntrenador(), entrenador.getPokedolares());

                    // Actualizar la interfaz
                    cargarObjetosMochila();
                }
            } else {
                System.out.println("No tienes suficientes Pokedolares.");
            }
        } else {
            System.out.println("ID_ENTRENADOR no válido.");
        }
        lblDienero.setText(entrenador.getPokedolares() + "");
    }
    
    private void venderObjetoMochila(Mochila mochila) {
        // Obtener el objeto a vender desde la base de datos
        Objeto objeto = ObjetoDAO.buscarObjetoPorId(ConexionBD.getConnection(), mochila.getIdObjeto());

        // Variable para cargar el dinero del entrenador despues de la compra
        int nuevosPokedolares = entrenador.getPokedolares() + objeto.getPrecio();

        // Verificar si la mochila tiene más de 1 unidad del objeto
        if (mochila.getCantidad() > 1) {
        	
            mochila.setCantidad(mochila.getCantidad() - 1);

            // Actualiza la cantidad del objeto en la base de datos
            boolean exito = MochilaDAO.actualizarCantidad(ConexionBD.getConnection(), mochila);

            if (exito) {
                // Agrega el precio del objeto a los Pokedólares del entrenador
                entrenador.setPokedolares(nuevosPokedolares);
                // Actualiza los Pokedólares en la base de datos
                EntrenadorDAO.actualizarPokedolares(ConexionBD.getConnection(), entrenador.getIdEntrenador(), nuevosPokedolares);
                // Actualiza la interfaz de la mochila
                cargarObjetosMochila();
                System.out.println("Has vendido un objeto: " + objeto.getNomObjeto());
            }
        } else {
            // Si la cantidad es 1, elimina el objeto de la mochila
            boolean exito = MochilaDAO.eliminarDeMochila(ConexionBD.getConnection(), mochila.getIdEntrenador(), mochila.getIdObjeto());

            if (exito) {
                // Agrega el precio del objeto a los Pokedólares del entrenador
                entrenador.setPokedolares(nuevosPokedolares);
                // Actualiza los Pokedólares en la base de datos
                EntrenadorDAO.actualizarPokedolares(ConexionBD.getConnection(), entrenador.getIdEntrenador(), nuevosPokedolares);
                // Actualiza la interfaz de la mochila
                cargarObjetosMochila();
                System.out.println("Has vendido el último objeto de tu mochila: " + objeto.getNomObjeto());
            }
        }
        lblDienero.setText(entrenador.getPokedolares() + "");
    }
    
    @FXML
    void cambio(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CentroPokemon.fxml"));
            Parent root = loader.load();

            CentroPokemonController centroPokemonController = loader.getController();
            centroPokemonController.init(entrenador, stage, loginController, menuController, this, null);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Centro Pokémon");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void salir(ActionEvent event) {
    	try {
    		SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/Tienda.mp3");
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
