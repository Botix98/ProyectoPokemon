package controller;

import java.io.File;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ConexionBD;
import dao.PokedexDAO;
import dao.PokemonDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Entrenador;
import model.Pokemon;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class CajaController {

    private Stage stage;
    private Entrenador entrenador;
    private MenuController menuController;
    private LoginController loginController;
    private Pokemon pokemonSeleccionado;
    private List<ImageView> imagenesCaja;
    private List<ImageView> imagenesEquipo;
    private List<Pokemon> equipo;
    private LinkedList<Pokemon> caja;
    private boolean pokemonSeleccionadoEsDeCaja = false;
    private DropShadow efectoGlow = new DropShadow(20, Color.YELLOW);
    Connection con = ConexionBD.getConnection();
    
    @FXML private Button btnSalir;
    @FXML private ImageView imgFondo;
    @FXML private ImageView imgSonido;
    @FXML private Label lblCaja1;
    @FXML private Button btnLiberarPokemon;

    @FXML private ImageView imgPokemonCaja1,imgPokemonCaja2, imgPokemonCaja3, imgPokemonCaja4, imgPokemonCaja5;
    @FXML private ImageView imgPokemonCaja6, imgPokemonCaja7, imgPokemonCaja8, imgPokemonCaja9, imgPokemonCaja10;
    @FXML private ImageView imgPokemonCaja11, imgPokemonCaja12, imgPokemonCaja13, imgPokemonCaja14, imgPokemonCaja15;
    @FXML private ImageView imgPokemonCaja16, imgPokemonCaja17, imgPokemonCaja18, imgPokemonCaja19, imgPokemonCaja20;
    @FXML private ImageView imgPokemonCaja21, imgPokemonCaja22, imgPokemonCaja23, imgPokemonCaja24, imgPokemonCaja25;
    @FXML private ImageView imgPokemonCaja26, imgPokemonCaja27, imgPokemonCaja28, imgPokemonCaja29, imgPokemonCaja30;
    
    @FXML private ImageView imgPokemonEquipo1, imgPokemonEquipo2, imgPokemonEquipo3;
    @FXML private ImageView imgPokemonEquipo4, imgPokemonEquipo5, imgPokemonEquipo6;
    @FXML private ImageView imgPokemonGrande;
    
    
    @FXML private ProgressBar pbPokemonEquipo1, pbPokemonEquipo2, pbPokemonEquipo3;
    @FXML private ProgressBar pbPokemonEquipo4, pbPokemonEquipo5, pbPokemonEquipo6;
    @FXML private ProgressBar pbPokemonSeleccionado;
    
    @FXML private ImageView imgPokemonSeleccionado;
    
    @FXML private Label lblMotePokemonSeleccionado;

    @FXML private Label lblNivelPokemonSeleccionado;

    @FXML private Label lblNombrePokemonSeleccionado;

    
    public void init(Entrenador entrenador, Stage stage, LoginController loginController, MenuController menuController, EquipoController equipoController) {
        this.entrenador = entrenador;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        con = ConexionBD.getConnection();

        this.caja = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 2);
        mostrarPokemonCaja();
        mostrarEquipo();
        btnLiberarPokemon.setVisible(false);

        imagenesEquipo = List.of(
        	    imgPokemonEquipo1, imgPokemonEquipo2, imgPokemonEquipo3,
        	    imgPokemonEquipo4, imgPokemonEquipo5, imgPokemonEquipo6
        	);

        equipo = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1);

        for (final ImageView iv : imagenesEquipo) {
        	
        	iv.setOnMouseClicked(event -> {
        	    btnLiberarPokemon.setVisible(true);
        	    int index = imagenesEquipo.indexOf(iv);

        	    // Limpiar efectos anteriores tanto en caja como en equipo
        	    for (ImageView img : imagenesCaja) {
        	        img.setEffect(null);
        	    }
        	    for (ImageView img : imagenesEquipo) {
        	        img.setEffect(null);
        	    }

        	    hacerGrande(event);

        	    // Aplicar efecto glow al seleccionado
        	    iv.setEffect(efectoGlow);

        	    if (index >= 0 && index < equipo.size()) {
        	        pokemonSeleccionado = equipo.get(index);
        	        pokemonSeleccionadoEsDeCaja = false;
        	    }
        	});
        	
            iv.setOnDragDetected(event -> {
                if (iv.getImage() != null) {
                    javafx.scene.input.Dragboard db = iv.startDragAndDrop(javafx.scene.input.TransferMode.MOVE);
                    javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
                    content.putImage(iv.getImage());
                    db.setContent(content);
                    iv.setUserData("arrastrado_equipo");
                    event.consume();
                }
            });

            iv.setOnDragOver(event -> {
                if (event.getGestureSource() != iv && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(javafx.scene.input.TransferMode.MOVE);
                }
                event.consume();
            });

            iv.setOnDragDropped(event -> {
                javafx.scene.input.Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasImage()) {
                    ImageView origen = null;
                    boolean desdeCaja = false;

                    for (ImageView other : imagenesCaja) {
                        if ("arrastrado".equals(other.getUserData())) {
                            origen = other;
                            desdeCaja = true;
                            break;
                        }
                    }

                    if (origen == null) {
                        for (ImageView other : imagenesEquipo) {
                            if ("arrastrado_equipo".equals(other.getUserData())) {
                                origen = other;
                                break;
                            }
                        }
                    }

                    if (origen != null) {
                        Image tempImage = iv.getImage();
                        iv.setImage(origen.getImage());
                        origen.setImage(tempImage);

                        int indexOrigen = desdeCaja ? imagenesCaja.indexOf(origen) : imagenesEquipo.indexOf(origen);
                        int indexDestino = imagenesEquipo.indexOf(iv);

                        if (desdeCaja && indexOrigen < caja.size() && indexDestino < equipo.size()) {
                            Pokemon temp = equipo.get(indexDestino);
                            equipo.set(indexDestino, caja.get(indexOrigen));
                            caja.set(indexOrigen, temp);

                            PokemonDAO.actualizarEquipo(con, caja.get(indexOrigen).getIdPokemon(), 2);
                            PokemonDAO.actualizarEquipo(con, equipo.get(indexDestino).getIdPokemon(), 1);
                        } else if (!desdeCaja && indexOrigen < equipo.size() && indexDestino < equipo.size()) {
                            Pokemon temp = equipo.get(indexOrigen);
                            equipo.set(indexOrigen, equipo.get(indexDestino));
                            equipo.set(indexDestino, temp);
                        }

                        origen.setUserData(null);
                        success = true;
                    }
                }

                event.setDropCompleted(success);
                event.consume();
            });

            iv.setOnDragDone(event -> event.consume());
        }
    }
    
    @FXML
    public void initialize() {
        SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Caja.mp3");
        lblNombrePokemonSeleccionado.setVisible(false);
        lblMotePokemonSeleccionado.setVisible(false);
        lblNivelPokemonSeleccionado.setVisible(false);
        pbPokemonSeleccionado.setVisible(false);
        imgPokemonSeleccionado.setVisible(false);

        imagenesCaja = List.of(
            imgPokemonCaja1, imgPokemonCaja2, imgPokemonCaja3, imgPokemonCaja4, imgPokemonCaja5,
            imgPokemonCaja6, imgPokemonCaja7, imgPokemonCaja8, imgPokemonCaja9, imgPokemonCaja10,
            imgPokemonCaja11, imgPokemonCaja12, imgPokemonCaja13, imgPokemonCaja14, imgPokemonCaja15,
            imgPokemonCaja16, imgPokemonCaja17, imgPokemonCaja18, imgPokemonCaja19, imgPokemonCaja20,
            imgPokemonCaja21, imgPokemonCaja22, imgPokemonCaja23, imgPokemonCaja24, imgPokemonCaja25,
            imgPokemonCaja26, imgPokemonCaja27, imgPokemonCaja28, imgPokemonCaja29, imgPokemonCaja30
        );
       
        for (final ImageView iv : imagenesCaja) {
        	
        	//listener para cuando haces click en alguna imagen de caja
        	iv.setOnMouseClicked(event -> {
        	    btnLiberarPokemon.setVisible(true);
        	    int index = imagenesCaja.indexOf(iv);

        	    aplicarGlowAImagen(event);

        	    if (index >= 0 && index < caja.size()) {
        	        pokemonSeleccionado = caja.get(index);
        	        pokemonSeleccionadoEsDeCaja = true;
        	    }
        	});

        	
        	//Se activa cuando se empieza a arrastrar la imagen
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
            
            //Se activa cuando estás arrastrando algo sobre otro ImageView
            iv.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    if (event.getGestureSource() != iv && event.getDragboard().hasImage()) {
                        event.acceptTransferModes(javafx.scene.input.TransferMode.MOVE);
                    }
                    event.consume();
                }
            });
            
            //Se activa cuando sueltas la imagen en otro ImageView
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

            //Se ejecuta al terminar la operación de arrastrar
            iv.setOnDragDone(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    event.consume();
                }
            });
 
        }
        
        //si hay se muestra un pokemon en caja, si no hay se muestra vacio
        if (caja != null) {
        	mostrarPokemonCaja();
        }
    }
    
    //Recorre la caja y muestra las imagenes
    private void mostrarPokemonCaja() {
    	List<Pokemon> c = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 2);
        for (int i = 0; i < caja.size(); i++) {
            Pokemon p = caja.get(i);
            String ruta = "C:/ProyectoPokemon/img/Pokemon/Front/" + p.getNumPokedex() + ".png";
            File archivo = new File(ruta);
            imagenesCaja.get(i).setImage(new Image(archivo.toURI().toString()));
        }
    }
    
   
    //Recorre el equipo y muestra las imagenes del equipo
    private void mostrarEquipo() {
        List<Pokemon> equipo = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1);

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
    
    //Metodo para que se vea en grande el pokemon seleccionado en el equipo
    @FXML
    void hacerGrande(MouseEvent event) {
        ImageView origen = (ImageView) event.getSource();
        imgPokemonSeleccionado.setImage(origen.getImage());
        pbPokemonSeleccionado.setVisible(true);
        lblNombrePokemonSeleccionado.setVisible(true);
        lblMotePokemonSeleccionado.setVisible(true);
        lblNivelPokemonSeleccionado.setVisible(true);
        imgPokemonSeleccionado.setVisible(true);

        List<ImageView> imagenesEquipo = List.of(
            imgPokemonEquipo1, imgPokemonEquipo2, imgPokemonEquipo3,
            imgPokemonEquipo4, imgPokemonEquipo5, imgPokemonEquipo6
        );

        for (int i = 0; i < imagenesEquipo.size(); i++) {
            if (origen == imagenesEquipo.get(i) && i < equipo.size()) {
                Pokemon pokemon = equipo.get(i);

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
      
    @FXML
    void liberarPokemon(ActionEvent event) {
        if (pokemonSeleccionado == null) {
            System.out.println("No se ha seleccionado ningún Pokémon.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
            "¿Estás seguro de que quieres liberar a " + pokemonSeleccionado.getMote() + "?",
            "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        boolean eliminado = PokemonDAO.eliminarPokemon(con, pokemonSeleccionado.getIdPokemon());

        if (eliminado) {
            if (pokemonSeleccionadoEsDeCaja) {
                caja.remove(pokemonSeleccionado);
                mostrarPokemonCaja();
            } else {
                if (equipo.size() <= 1) {
                    JOptionPane.showMessageDialog(null, "No puedes tener menos de 1 Pokémon en el equipo");
                    return;
                }

                equipo.remove(pokemonSeleccionado);
                mostrarEquipo();
            }

            //limpia el glow anterior en caja y equipo
            for (ImageView img : imagenesCaja) {
                img.setEffect(null);
            }
            for (ImageView img : imagenesEquipo) {
                img.setEffect(null);
            }

            pokemonSeleccionado = null;
            btnLiberarPokemon.setVisible(false);
            imgPokemonSeleccionado.setVisible(false);
            lblNombrePokemonSeleccionado.setVisible(false);
            lblMotePokemonSeleccionado.setVisible(false);
            lblNivelPokemonSeleccionado.setVisible(false);
            pbPokemonSeleccionado.setVisible(false);

            System.out.println("Pokémon liberado correctamente.");
        } else {
            System.out.println("No se pudo liberar el Pokémon.");
        }
    }

    @FXML
    void aplicarGlowAImagen(MouseEvent event) {
        ImageView imgClicada = (ImageView) event.getSource();

        // Verificar que la imagen corresponde a un Pokémon en la caja
        if (!imagenesCaja.contains(imgClicada)) {
            return; // No aplicar glow si no es una imagen de la caja
        }

        // Crea un efecto de luz
        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW);
        glow.setRadius(20);
        glow.setSpread(0.5);

     // Limpiar efectos anteriores de todos (caja y equipo)
        for (ImageView img : imagenesCaja) {
            img.setEffect(null);
        }
        for (ImageView img : imagenesEquipo) {
            img.setEffect(null);
        }
        // Aplicar el efecto glow solo a esa imagen
        imgClicada.setEffect(glow);
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