package controller;

import java.io.File;
import java.sql.Connection;
import java.util.LinkedList;

import dao.ConexionBD;
import dao.MovimientoDAO;
import dao.PokedexDAO;
import dao.PokemonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Entrenador;
import model.Movimiento;
import model.Pokedex;
import model.Pokemon;
import model.TipoEstados;

public class EntrenamientoController {

	private Entrenador entrenador;
	private Stage stage;
	private LoginController loginController;
	private MenuController menuController;
	
	private Pokemon pokemonEntrenador;
	private Pokemon pokemonRival;
	private LinkedList<Pokedex> pokedex;
	private LinkedList<Pokemon> equipoEntrenador;
	private LinkedList<Movimiento> movimientosRival;
	private int pokActEntr;
	
	private int indicePokSeleccionado;
	
	private Connection con;
	
	@FXML
    private Button btnAccionCambiarPokemon;

    @FXML
    private Button btnAtacar;

    @FXML
    private Button btnAtaque1;

    @FXML
    private Button btnAtaque2;

    @FXML
    private Button btnAtaque3;

    @FXML
    private Button btnAtaque4;

    @FXML
    private Button btnCambiarRival;
    
    @FXML
    private Button btnConfirmarCambio;

    @FXML
    private Button btnHuir;

    @FXML
    private Button btnPokemonEquipo1;

    @FXML
    private Button btnPokemonEquipo2;

    @FXML
    private Button btnPokemonEquipo3;

    @FXML
    private Button btnPokemonEquipo4;

    @FXML
    private Button btnPokemonEquipo5;

    @FXML
    private Button btnPokemonEquipo6;

    @FXML
    private ImageView imgCuadricula;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgFondoEquipo1;

    @FXML
    private ImageView imgFondoEquipo2;

    @FXML
    private ImageView imgFondoEquipo3;

    @FXML
    private ImageView imgFondoEquipo4;

    @FXML
    private ImageView imgFondoEquipo5;

    @FXML
    private ImageView imgFondoEquipo6;

    @FXML
    private ImageView imgInfoPokemonEntrenador;

    @FXML
    private ImageView imgInfoPokemonRival;

    @FXML
    private ImageView imgPokemonEntrenador;

    @FXML
    private ImageView imgPokemonEquipo1;

    @FXML
    private ImageView imgPokemonEquipo2;

    @FXML
    private ImageView imgPokemonEquipo3;

    @FXML
    private ImageView imgPokemonEquipo4;

    @FXML
    private ImageView imgPokemonEquipo5;

    @FXML
    private ImageView imgPokemonEquipo6;

    @FXML
    private ImageView imgPokemonRival;

    @FXML
    private ImageView imgSeleccionAccion;

    @FXML
    private ImageView imgSeleccionAtaque;

    @FXML
    private ImageView imgSonido;

    @FXML
    private Label lblEstadoPokemonEntrenador;

    @FXML
    private Label lblEstadoPokemonRival;

    @FXML
    private Label lblInfoPokemon1;

    @FXML
    private Label lblInfoPokemon2;

    @FXML
    private Label lblInfoPokemon3;

    @FXML
    private Label lblInfoPokemon4;

    @FXML
    private Label lblInfoPokemon5;

    @FXML
    private Label lblInfoPokemon6;

    @FXML
    private Label lblNivelPokemonEntrenador;

    @FXML
    private Label lblNivelPokemonRival;

    @FXML
    private Label lblNombrePokemonEntrenador;

    @FXML
    private Label lblNombrePokemonRival;

    @FXML
    private Label lblPP;

    @FXML
    private Label lblTexto;

    @FXML
    private Label lblType;

    @FXML
    private Label lblVidaPokemonEntrenador;

    @FXML
    private ProgressBar pbVidaPokemonEntrenador;

    @FXML
    private ProgressBar pbVidaPokemonRival;

    @FXML
    private ProgressBar pbXpPokemonEntrenador;

    @FXML
    private VBox vBoxEquipo;

    @FXML
    public void initialize() {
    	SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/Entrenamiento.mp3");
    }
    
    public void init(Entrenador entrenador, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entrenador;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        
        con = ConexionBD.getConnection();
        equipoEntrenador = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1);
        
        pokActEntr = 0;
        for (Pokemon pokemon : equipoEntrenador) {
			if (!pokemon.getEstado().equals(TipoEstados.valueOf("DEBILITADO"))) {
				pokemonEntrenador = equipoEntrenador.get(pokActEntr);
		        pokedex = PokedexDAO.cargarPokedexCompleta(con);
		        break;
			} else {
				pokActEntr++;
			}
		}
        
        prepararPokemonEquipo();
        generarPokemonRival();
        iniciarEntrenamiento();
        cargarInfoPokemonEntrenador();
    }
    
    private void prepararPokemonEquipo() {
		for (int i = 0; i < equipoEntrenador.size(); i++) {
        	String infoPokemon = "Nombre: " + equipoEntrenador.get(i).getMote()
    				+ "\nVida: " + equipoEntrenador.get(i).getVitalidadAct() + "/" + equipoEntrenador.get(i).getVitalidadMax()
    				+ "\nEstado: " + equipoEntrenador.get(i).getEstado().toString().toLowerCase();
        	switch (i) {
        	case 0:
        		imgPokemonEquipo1.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblInfoPokemon1.setText(infoPokemon);
        		break;
        	case 1:
        		imgPokemonEquipo2.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblInfoPokemon2.setText(infoPokemon);
        		break;
        	case 2:
        		imgPokemonEquipo3.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblInfoPokemon3.setText(infoPokemon);
        		break;
        	case 3:
        		imgPokemonEquipo4.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblInfoPokemon4.setText(infoPokemon);
        		break;
        	case 4:
        		imgPokemonEquipo5.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblInfoPokemon5.setText(infoPokemon);
        		break;
        	case 5:
        		imgPokemonEquipo6.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblInfoPokemon6.setText(infoPokemon);
        		break;
        	}
        }
	}

	private void cargarInfoPokemonEntrenador() {
		lblNombrePokemonEntrenador.setText(pokemonEntrenador.getMote());
        lblNivelPokemonEntrenador.setText(Integer.toString(pokemonEntrenador.getNivel()));
        lblEstadoPokemonEntrenador.setText(pokemonEntrenador.getEstado().getPseudonimo());
        lblVidaPokemonEntrenador.setText(pokemonEntrenador.getVitalidadAct() + "/" + pokemonEntrenador.getVitalidadMax());
        pbXpPokemonEntrenador.setProgress((double) (pokemonEntrenador.getExperiencia()) / (10 * pokemonEntrenador.getNivel()));
        pbVidaPokemonEntrenador.setProgress((double) (pokemonEntrenador.getVitalidadAct()) / pokemonEntrenador.getVitalidadMax());
        imgPokemonEntrenador.setImage(new Image(new File("./img/Pokemon/Back/" + equipoEntrenador.get(pokActEntr).getNumPokedex() + ".png").toURI().toString()));
        
		if (pbVidaPokemonEntrenador.getProgress() < 0.25){
			pbVidaPokemonEntrenador.setStyle("-fx-accent: red;");
        }
        else if (pbVidaPokemonEntrenador.getProgress() < 0.5){
        	pbVidaPokemonEntrenador.setStyle("-fx-accent: yellow;");
        } else {
        	pbVidaPokemonEntrenador.setStyle("-fx-accent: #00a135;");
        }
	}
    
    private void generarPokemonRival() {
		int numPokedex = (int) (Math.random() * 151) + 1;
		String mote = pokedex.get(numPokedex - 1).getNomPokemon();
		int nivel = pokemonEntrenador.getNivel() + (int) (Math.random() * 6) - 2;
		int vitalidadMax = 10 + (int)((double)(nivel) / 100 * (pokedex.get(numPokedex - 1).getVitalidad() * 2) + (int) (Math.random() * 32)) + nivel;
		int ataque = 5 + (int)((double)(nivel) / 100 * ((pokedex.get(numPokedex - 1).getAtaque() * 2) + (int) (Math.random() * 32)));
		int ataqueEsp = 5 + (int)((double)(nivel) / 100 * ((pokedex.get(numPokedex - 1).getAtEspecial() * 2) + (int) (Math.random() * 32)));
		int defensa = 5 + (int)((double)(nivel) / 100 * ((pokedex.get(numPokedex - 1).getDefensa() * 2) + (int) (Math.random() * 32)));
		int defensaEsp = 5 + (int)((double)(nivel) / 100 * ((pokedex.get(numPokedex - 1).getDefEspecial() * 2) + (int) (Math.random() * 32)));
		int velocidad = 5 + (int)((double)(nivel) / 100 * ((pokedex.get(numPokedex - 1).getVelocidad() * 2) + (int) (Math.random() * 32)));
        
		pokemonRival = new Pokemon(0, 0, 0, "RIVAL", numPokedex, mote, vitalidadMax, vitalidadMax, ataque, ataqueEsp, defensa, defensaEsp, velocidad, nivel, 0, "F", "SIN_ESTADO", 1, 0);
		
		lblNombrePokemonRival.setText(pokemonRival.getMote());
		lblNivelPokemonRival.setText(Integer.toString(pokemonRival.getNivel()));
		lblEstadoPokemonRival.setText("");
		pbVidaPokemonRival.setStyle("-fx-accent: #00a135;");
		pbVidaPokemonRival.setProgress(1);
		imgPokemonRival.setImage(new Image(new File("./img/Pokemon/Front/" + pokemonRival.getNumPokedex() + ".png").toURI().toString()));
		
		//Generamos un numero aleatorio de movimientos para el rival entre 1 y 4
		int numMovsRival = (int) (Math.random() * 4) + 1;

		LinkedList<Movimiento> listaMovPosibles = MovimientoDAO.buscarPorTipoMov(con, pokedex.get(pokemonRival.getNumPokedex()).getTipos()[0]);
    	listaMovPosibles.addAll(MovimientoDAO.buscarPorTipoMov(con, "NORMAL"));
    	
    	if (pokedex.get(pokemonRival.getNumPokedex()).getTipos()[1] != null) {
    		listaMovPosibles.addAll(MovimientoDAO.buscarPorTipoMov(con, pokedex.get(pokemonRival.getNumPokedex()).getTipos()[1]));
    	}
    	
    	System.out.println("-------------");
		
    	System.out.println(pokemonRival.toString());
    	System.out.println("Tipos rival: " + pokedex.get(pokemonRival.getNumPokedex()).getTipos()[0]);
    	if (pokedex.get(pokemonRival.getNumPokedex()).getTipos()[1] != null) {
    		System.out.println("\t   : " + pokedex.get(pokemonRival.getNumPokedex()).getTipos()[1]);
    	}
    	
    	System.out.println("Movimientos posibles:");
    	for (int i = 0; i < listaMovPosibles.size(); i++) {
			System.out.println("\tMoviminento " + i + ": " + listaMovPosibles.get(i));
		}
    	
    	movimientosRival = new LinkedList<Movimiento>();
    	
		for (int i = 0; i < numMovsRival; i++) {
			Movimiento mov;
			do {
				mov = listaMovPosibles.get((int) (Math.random() * listaMovPosibles.size())); //Habra que ver si esto esta bien
			} while (!comprobarMovimiento(mov));
			movimientosRival.add(mov);
		}
		
		System.out.println("Movimientos del Rival:");
		for (Movimiento movimiento : movimientosRival) {
			System.out.println("\t" + movimiento.toString());
		}
	}

	private boolean comprobarMovimiento(Movimiento mov) {
		for (int i = 0; i < movimientosRival.size(); i++) {
			if (movimientosRival.get(i).getNombre().equals(mov.getNombre())) {
				return false;
			}
		}
		return true;
	}

	private void iniciarEntrenamiento() {
		lblTexto.setText(pokemonEntrenador.getMote() + " se va a enfrentar a " + pokemonRival.getMote() + ". Elije una accion.");
	}
	
	@FXML
    void atacar(ActionEvent event) {
		
    }

    @FXML
    void cambiarPokemon(ActionEvent event) {
    	vBoxEquipo.setVisible(true);
    	lblTexto.setVisible(true);
    	lblTexto.setText("Elije un pokemon.");
    }

    @FXML
    void cambiarRival(ActionEvent event) {
    	generarPokemonRival();
    }

    @FXML
    void seleccionarPokemonEquipo1(ActionEvent event) {
		indicePokSeleccionado = 0;
		cambiarPokemonSeleccionado(imgPokemonEquipo1);
    }

	@FXML
    void seleccionarPokemonEquipo2(ActionEvent event) {
		indicePokSeleccionado = 1;
		cambiarPokemonSeleccionado(imgPokemonEquipo2);
    }

    @FXML
    void seleccionarPokemonEquipo3(ActionEvent event) {
    	indicePokSeleccionado = 2;
		cambiarPokemonSeleccionado(imgPokemonEquipo3);
    }

    @FXML
    void seleccionarPokemonEquipo4(ActionEvent event) {
    	indicePokSeleccionado = 3;
		cambiarPokemonSeleccionado(imgPokemonEquipo4);
    }

    @FXML
    void seleccionarPokemonEquipo5(ActionEvent event) {
    	indicePokSeleccionado = 4;
		cambiarPokemonSeleccionado(imgPokemonEquipo5);
    }

    @FXML
    void seleccionarPokemonEquipo6(ActionEvent event) {
    	indicePokSeleccionado = 5;
		cambiarPokemonSeleccionado(imgPokemonEquipo6);
    }
    
    private void cambiarPokemonSeleccionado(ImageView imgPokemonEquipo) {
    	lblTexto.setVisible(true);
    	
    	if (imgPokemonEquipo.getImage() != null) {
			if (pokActEntr == indicePokSeleccionado) {
				lblTexto.setText("El pokemon seleccionado ya esta en combate.");
			}
			else {
				if (equipoEntrenador.get(indicePokSeleccionado).getVitalidadAct() == 0) {
					lblTexto.setText("El pokemon seleccionado esta debilitado.");
				}
		    	else{
					lblTexto.setText("Seguro que quieres cambiar a " + equipoEntrenador.get(indicePokSeleccionado).getMote() + "?");
					btnConfirmarCambio.setVisible(true);
					imgSeleccionAccion.setVisible(true);
				}
			}
		}
	}
    
    @FXML
    void confirmarCambio(ActionEvent event) {
    	vBoxEquipo.setVisible(false);
    	lblTexto.setText(entrenador.getUsuario() + " saca a " + equipoEntrenador.get(indicePokSeleccionado).getMote());
    	pokActEntr = indicePokSeleccionado;
    	pokemonEntrenador = equipoEntrenador.get(pokActEntr);
    	cargarInfoPokemonEntrenador();
    }
    
    @FXML
    void activarDesactivarSonido(MouseEvent event) {
    	
    }

    @FXML
    void huir(ActionEvent event) {
    	try {
    		SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/Entrenamiento.mp3");
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
