package controller;

import java.io.File;
import java.sql.Connection;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import dao.ConexionBD;
import dao.MovimientoDAO;
import dao.MovimientoPokemonDAO;
import dao.PokedexDAO;
import dao.PokemonDAO;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Entrenador;
import model.Movimiento;
import model.MovimientoPokemon;
import model.Pokedex;
import model.Pokemon;
import model.TipoEstados;

public class SeleccionController {

    private Stage stage;
    private Entrenador entrenador;
    private LoginController loginController;

    @FXML private ImageView imgPokeball1;
    @FXML private ImageView imgPokeball2;
    @FXML private ImageView imgPokeball3;

    @FXML private ImageView imgPokemon1;
    @FXML private ImageView imgPokemon2;
    @FXML private ImageView imgPokemon3;

    @FXML private ImageView imgSonido;

    private Connection con = ConexionBD.getConnection();

    private int seleccionado = -1;

    // IDs de Pokedex de los starters
    private final int[] STARTERS = {163, 164, 165};

    public void init(Entrenador entr, Stage stage, LoginController loginController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;

        imgPokemon1.setVisible(false);
        imgPokemon2.setVisible(false);
        imgPokemon3.setVisible(false);
    }

    @FXML
    void activarDesactivarSonido(MouseEvent event) {
        loginController.sonido();
        imgSonido.setImage(new Image(new File(
            loginController.sonido
            ? "./img/conSonido.png"
            : "./img/sinSonido.png"
        ).toURI().toString()));
    }

    @FXML
    void elegirPoke1(MouseEvent event) { elegirStarter(0); }

    @FXML
    void elegirPoke2(MouseEvent event) { elegirStarter(1); }

    @FXML
    void elegirPoke3(MouseEvent event) { elegirStarter(2); }

    private void elegirStarter(int id) {
        this.seleccionado = id;
        



        for (int i = 0; i < STARTERS.length; i++) {
            ImageView imgView = switch (i) {
                case 0 -> imgPokemon1;
                case 1 -> imgPokemon2;
                default -> imgPokemon3;
            };
            if (i == id) {
                // Mostrar la imagen
                int numPokedex = STARTERS[i];
                String ruta = "C:/ProyectoPokemon/img/Pokemon/Front/" + numPokedex + ".png";
                imgView.setImage(new Image(new File(ruta).toURI().toString()));
                imgView.setVisible(true);
                
                PauseTransition pausa1 = new PauseTransition(Duration.seconds(0.1));
                pausa1.setOnFinished(event1 -> {
                	
                // Confirmación del usuario
                LinkedList<Pokedex> pokedex = PokedexDAO.cargarPokedexCompleta(con);
                System.out.println(pokedex.size());
                String nombrePokemon = pokedex.get(numPokedex - 1).getNomPokemon();

                int opcion = JOptionPane.showConfirmDialog(null,
                    "¿Quieres elegir a " + nombrePokemon + "?",
                    "Confirmar selección",
                    JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    confirmarSeleccion(null);
                } else {
                    imgView.setVisible(false);
                    seleccionado = -1;
                }
                });
                pausa1.play();
            } else {
                // Ocultar los demás
                imgView.setVisible(false);
            }
        }
    }
    
    @FXML
    void confirmarSeleccion(MouseEvent event) {
        if (seleccionado < 0) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un Pokémon inicial.");
            return;
        }

        int numPokedex = STARTERS[seleccionado];

        // Crear el nuevo Pokémon inicial
        Pokemon nuevo = new Pokemon();
        int nuevoId = PokemonDAO.obtenerMaxIdPokemon(con) + 1;
        nuevo.setIdPokemon(nuevoId);
        nuevo.setIdEntrenador(entrenador.getIdEntrenador());
        nuevo.setNumPokedex(numPokedex);

        // Pedir mote
        String mote = JOptionPane.showInputDialog(null, "Escribe un mote para tu nuevo Pokémon:");
        if (mote == null || mote.trim().isEmpty()) {
            LinkedList<Pokedex> pokedex = PokedexDAO.cargarPokedexCompleta(con);
            mote = pokedex.get(numPokedex - 1).getNomPokemon();
        }
        nuevo.setMote(mote);

        // Nivel inicial
        int nivel = 1;
        nuevo.setNivel(nivel);

        // Cálculo de stats al nivel 1
        LinkedList<Pokedex> pokedex = PokedexDAO.cargarPokedexCompleta(con);
        Pokedex base = pokedex.get(numPokedex - 1);

        int vitalidad = 10 + (int)((nivel / 100.0) * ((base.getVitalidad() * 2) + Math.random() * 32)) + nivel;
        int ataque = 5  + (int)((nivel / 100.0) * ((base.getAtaque()    * 2) + Math.random() * 32));
        int ataqueEsp = 5  + (int)((nivel / 100.0) * ((base.getAtEspecial()* 2) + Math.random() * 32));
        int defensa = 5  + (int)((nivel / 100.0) * ((base.getDefensa()   * 2) + Math.random() * 32));
        int defensaEsp = 5  + (int)((nivel / 100.0) * ((base.getDefEspecial()* 2) + Math.random() * 32));
        int velocidad = 5  + (int)((nivel / 100.0) * ((base.getVelocidad() * 2) + Math.random() * 32));

        nuevo.setVitalidadMax(vitalidad);
        nuevo.setVitalidadAct(vitalidad);
        nuevo.setAtaque(ataque);
        nuevo.setAtEspecial(ataqueEsp);
        nuevo.setDefensa(defensa);
        nuevo.setDefEspecial(defensaEsp);
        nuevo.setVelocidad(velocidad);

        nuevo.setFertilidad(5);
        nuevo.setSexo(Math.random() < 0.5 ? "H" : "M");
        nuevo.setEstado(TipoEstados.SIN_ESTADO);
        nuevo.setEquipo(1);
        nuevo.setTipoPropietario("ENTRENADOR");

        // Insertar en BD
        if (!PokemonDAO.anyadirPokemon(con, nuevo)) {
            JOptionPane.showMessageDialog(null, "Error al crear tu Pokémon inicial.");
            return;
        }

     // Anadir placaje (ID 54)
        Movimiento placaje = MovimientoDAO.buscarPorId(con, 50);
        if (placaje != null) {
            MovimientoPokemon mp1 = new MovimientoPokemon();
            mp1.setIdPokemon(nuevo.getIdPokemon());
            mp1.setIdMovimiento(50);
            mp1.setPpActuales(placaje.getPpMax());
            MovimientoPokemonDAO.insertarMovimientoPokemon(con, mp1);
        }

        // Anadir un ataque aleatorio de su tipo
        try {
            Pokedex pokedexInfo = PokedexDAO.cargarPorNumPokedex(con, numPokedex);
            LinkedList<Movimiento> movsTipo = MovimientoDAO.buscarPorTipoMov(con, pokedexInfo.getTipo(0));

            if (!movsTipo.isEmpty()) {
                Movimiento aleatorio = movsTipo.get((int) (Math.random() * movsTipo.size()));

                MovimientoPokemon mp2 = new MovimientoPokemon();
                mp2.setIdPokemon(nuevo.getIdPokemon());
                mp2.setIdMovimiento(aleatorio.getIdMovimiento());
                mp2.setPpActuales(aleatorio.getPpMax());

                MovimientoPokemonDAO.insertarMovimientoPokemon(con, mp2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Volver al menú principal
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
            Parent root = loader.load();

            MenuController menu = loader.getController();
            menu.init(entrenador, stage, loginController, null, null, null, null, null, null, null);

            stage.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void mostrarPoke1(MouseEvent event) {
        String ruta = "C:/ProyectoPokemon/img/Pokemon/Front/1.png";
        imgPokemon1.setImage(new Image(new File(ruta).toURI().toString()));
        imgPokemon1.setVisible(true);
    }

    @FXML
    void mostrarPoke2(MouseEvent event) {
        imgPokemon2.setVisible(true);
    }

    @FXML
    void mostrarPoke3(MouseEvent event) {
        imgPokemon3.setVisible(true);
    }
}
