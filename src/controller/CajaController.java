package controller;

import java.io.File;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import dao.ConexionBD;
import dao.PokemonDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Entrenador;
import model.Pokemon;

public class CajaController {

    private Stage stage;
    private Entrenador entrenador;
    private MenuController menuController;
    private LoginController loginController;
    private ImageView seleccionadoPrimero = null;
    private List<ImageView> imagenesCaja;
    private LinkedList<Pokemon> caja;
    private Connection con;
    @FXML private Button btnSalir;
    @FXML private ImageView imgFondo;
    @FXML private ImageView imgPokemonCaja1, imgPokemonCaja2, imgPokemonCaja3, imgPokemonCaja4, imgPokemonCaja5;
    @FXML private ImageView imgPokemonCaja6, imgPokemonCaja7, imgPokemonCaja8, imgPokemonCaja9, imgPokemonCaja10;
    @FXML private ImageView imgPokemonCaja11, imgPokemonCaja12, imgPokemonCaja13, imgPokemonCaja14, imgPokemonCaja15;
    @FXML private ImageView imgPokemonCaja16, imgPokemonCaja17, imgPokemonCaja18, imgPokemonCaja19, imgPokemonCaja20;
    @FXML private ImageView imgPokemonCaja21, imgPokemonCaja22, imgPokemonCaja23, imgPokemonCaja24, imgPokemonCaja25;
    @FXML private ImageView imgPokemonCaja26, imgPokemonCaja27, imgPokemonCaja28, imgPokemonCaja29, imgPokemonCaja30;
    @FXML private ImageView imgPokemonEquipo1, imgPokemonEquipo2, imgPokemonEquipo3;
    @FXML private ImageView imgPokemonEquipo4, imgPokemonEquipo5, imgPokemonEquipo6;
    @FXML private ImageView imgPokemonGrande;
    @FXML private ImageView imgSonido;
    @FXML private Label lblCaja1;

    public void init(Entrenador entrenador, Stage stage, LoginController loginController, MenuController menuController, EquipoController equipoController) {
        this.entrenador = entrenador;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        con = ConexionBD.getConnection();
        //el 2 es que el equipo 2 es la caja
        this.caja = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(),2 );
        cargarImagenes();
    }
    
    @FXML
    public void initialize() {
        SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Caja.mp3");

        imagenesCaja = List.of(
            imgPokemonCaja1, imgPokemonCaja2, imgPokemonCaja3, imgPokemonCaja4, imgPokemonCaja5,
            imgPokemonCaja6, imgPokemonCaja7, imgPokemonCaja8, imgPokemonCaja9, imgPokemonCaja10,
            imgPokemonCaja11, imgPokemonCaja12, imgPokemonCaja13, imgPokemonCaja14, imgPokemonCaja15,
            imgPokemonCaja16, imgPokemonCaja17, imgPokemonCaja18, imgPokemonCaja19, imgPokemonCaja20,
            imgPokemonCaja21, imgPokemonCaja22, imgPokemonCaja23, imgPokemonCaja24, imgPokemonCaja25,
            imgPokemonCaja26, imgPokemonCaja27, imgPokemonCaja28, imgPokemonCaja29, imgPokemonCaja30
        );

        for (final ImageView iv : imagenesCaja) {

            iv.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (iv.getImage() != null) {
                        javafx.scene.input.Dragboard db = iv.startDragAndDrop(javafx.scene.input.TransferMode.MOVE);
                        javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
                        content.putImage(iv.getImage());
                        db.setContent(content);

                        iv.setUserData("arrastrado");
                        event.consume();
                    }
                }
            });

            iv.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    if (event.getGestureSource() != iv && event.getDragboard().hasImage()) {
                        event.acceptTransferModes(javafx.scene.input.TransferMode.MOVE);
                    }
                    event.consume();
                }
            });

            iv.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    javafx.scene.input.Dragboard db = event.getDragboard();
                    boolean success = false;

                    if (db.hasImage()) {
                        ImageView origen = null;

                        for (ImageView other : imagenesCaja) {
                            if ("arrastrado".equals(other.getUserData())) {
                                origen = other;
                                break;
                            }
                        }

                        if (origen != null) {
                            Image tempImage = iv.getImage();
                            iv.setImage(origen.getImage());
                            origen.setImage(tempImage);

                            int indexOrigen = imagenesCaja.indexOf(origen);
                            int indexDestino = imagenesCaja.indexOf(iv);

                            if (indexOrigen >= 0 && indexDestino >= 0 &&
                                indexOrigen < caja.size() && indexDestino < caja.size()) {
                                Pokemon tempPokemon = caja.get(indexOrigen);
                                caja.set(indexOrigen, caja.get(indexDestino));
                                caja.set(indexDestino, tempPokemon);
                            }

                            origen.setUserData(null);
                            success = true;
                        }
                    }

                    event.setDropCompleted(success);
                    event.consume();
                }
            });

            iv.setOnDragDone(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    event.consume();
                }
            });

            
        }

        if (caja != null) {
            cargarImagenes();
        }
    }
    
    private void cargarImagenes() {
    	List<Pokemon> Caja = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 2);
        for (int i = 0; i < caja.size(); i++) {
            Pokemon p = caja.get(i);
            String ruta = "C:/ProyectoPokemon/img/Pokemon/Front/" + p.getNumPokedex() + ".png";
            File archivo = new File(ruta);
            imagenesCaja.get(i).setImage(new Image(archivo.toURI().toString()));
        }
    }
    
    private void mostrarEquipo() {
        List<Pokemon> equipo = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 2);

        ImageView[] imagenes = {
        		imgPokemonCaja1, imgPokemonCaja2, imgPokemonCaja3, imgPokemonCaja4, imgPokemonCaja5,
                imgPokemonCaja6, imgPokemonCaja7, imgPokemonCaja8, imgPokemonCaja9, imgPokemonCaja10,
                imgPokemonCaja11, imgPokemonCaja12, imgPokemonCaja13, imgPokemonCaja14, imgPokemonCaja15,
                imgPokemonCaja16, imgPokemonCaja17, imgPokemonCaja18, imgPokemonCaja19, imgPokemonCaja20,
                imgPokemonCaja21, imgPokemonCaja22, imgPokemonCaja23, imgPokemonCaja24, imgPokemonCaja25,
                imgPokemonCaja26, imgPokemonCaja27, imgPokemonCaja28, imgPokemonCaja29, imgPokemonCaja30
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
    
    
    
    //TENGO QUE HACER EL MÉTODO PARA PASAR DE POKEMON ENTRE LA CAJA, EL EQUIPO Y VICEVERSA Y AÑADIR LOS BOTONES AQUÍ
    
    @FXML
    void activarDesactivarSonido(MouseEvent event) {
        loginController.sonido();

        // Cambiar icono según nuevo estado
        if (loginController.sonido) {
            imgSonido.setImage(new Image(new File("./img/conSonido.png").toURI().toString()));
        } else {
            imgSonido.setImage(new Image(new File("./img/sinSonido.png").toURI().toString()));
        }
    }

    @FXML
    void salir(ActionEvent event) {
        try {
            SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/Caja.mp3");

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