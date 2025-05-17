package controller;

import java.io.File;
import java.sql.Connection;
import java.util.LinkedList;

import dao.ConexionBD;
import dao.EntrenadorDAO;
import dao.MovimientoDAO;
import dao.MovimientoPokemonDAO;
import dao.PokedexDAO;
import dao.PokemonDAO;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.util.Duration;
import model.Entrenador;
import model.Movimiento;
import model.MovimientoPokemon;
import model.Pokedex;
import model.Pokemon;
import model.TipoEstados;
import model.TipoPokemon;

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
	private LinkedList<MovimientoPokemon> listaMovPokEntr;
	private LinkedList<Movimiento> listaMovPokEntrAUX;
	private LinkedList<Movimiento> listaMovPosiblesEntr;
	private int pokActEntr;
	private int numPokVivosEntr;
	private int indicePokSeleccionado;
	
	private Movimiento movimientoNuevo;
	private MovimientoPokemon movimientoViejo;
	
	private SimpleBooleanProperty activo;
	private int experienciaLevelUp;
	
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
    private Button btnCambiarPokemon;

    @FXML
    private Button btnCambiarRival;

    @FXML
    private Button btnCambiarRival2;

    @FXML
    private Button btnCancelarAccion;

    @FXML
    private Button btnConfirmarCambio;

    @FXML
    private Button btnConfirmarCambioMov;

    @FXML
    private Button btnHuir;

    @FXML
    private Button btnMov1;

    @FXML
    private Button btnMov2;

    @FXML
    private Button btnMov3;

    @FXML
    private Button btnMov4;

    @FXML
    private Button btnNoAprender;

    @FXML
    private Button btnNoAprenderMov;

    @FXML
    private Button btnOlvidarMovimiento;

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
    private Button btnSalir;

    @FXML
    private ImageView imgCambioDesactivado;

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
    private ImageView imgMov1;

    @FXML
    private ImageView imgMov2;

    @FXML
    private ImageView imgMov3;

    @FXML
    private ImageView imgMov4;

    @FXML
    private ImageView imgMovNuevo;

    @FXML
    private ImageView imgMovSeleccionado;

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
    private Label lblEfectoNuevo;

    @FXML
    private Label lblEfectoSelec;

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
    private Label lblMov1;

    @FXML
    private Label lblMov2;

    @FXML
    private Label lblMov3;

    @FXML
    private Label lblMov4;

    @FXML
    private Label lblMovSelec;

    @FXML
    private Label lblNivelPokemonEntrenador;

    @FXML
    private Label lblNivelPokemonRival;

    @FXML
    private Label lblNomNuevo;

    @FXML
    private Label lblNombrePokemonEntrenador;

    @FXML
    private Label lblNombrePokemonRival;

    @FXML
    private Label lblPP;

    @FXML
    private Label lblPPMov1;

    @FXML
    private Label lblPPMov2;

    @FXML
    private Label lblPPMov3;

    @FXML
    private Label lblPPMov4;

    @FXML
    private Label lblPPNuevo;

    @FXML
    private Label lblPPSelec;

    @FXML
    private Label lblTexto;

    @FXML
    private Label lblTipoMovNuevo;

    @FXML
    private Label lblTipoMovSelec;

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
    private VBox vBoxMovimientos;

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
        
        listaMovPokEntr = MovimientoPokemonDAO.buscarPorIdPokemon(con, equipoEntrenador.get(pokActEntr).getIdPokemon());
        
        listaMovPokEntrAUX = new LinkedList<Movimiento>();
        for (MovimientoPokemon mov : listaMovPokEntr) {
			listaMovPokEntrAUX.add(MovimientoDAO.buscarPorId(con, mov.getIdMovimiento()));
		}
        
        calcularPokemonVivos();
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
        
        listaMovPokEntr = MovimientoPokemonDAO.buscarPorIdPokemon(con, equipoEntrenador.get(pokActEntr).getIdPokemon());
        
        listaMovPokEntrAUX = new LinkedList<Movimiento>();
        for (MovimientoPokemon mov : listaMovPokEntr) {
			listaMovPokEntrAUX.add(MovimientoDAO.buscarPorId(con, mov.getIdMovimiento()));
		}
        
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
		int nivel = Math.max(pokemonEntrenador.getNivel() + (int) (Math.random() * 6) - 2, 1);
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
    	
    	movimientosRival = new LinkedList<Movimiento>();
    	
		for (int i = 0; i < numMovsRival; i++) {
			Movimiento mov;
			do {
				mov = listaMovPosibles.get((int) (Math.random() * listaMovPosibles.size())); //Habra que ver si esto esta bien
			} while (!comprobarMovimiento(mov));
			movimientosRival.add(mov);
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
		desactivarBotonesSeleccionAccion();
		activarBotonesMovimientos();
		
		btnAtaque4.setVisible(false);
		btnAtaque3.setVisible(false);
		btnAtaque2.setVisible(false);
		btnAtaque1.setVisible(false);
		
		switch (listaMovPokEntr.size()) {
			case 4:
				btnAtaque4.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(3).getIdMovimiento()).getNombre());
	        	btnAtaque4.setVisible(true);
			case 3:
				btnAtaque3.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(2).getIdMovimiento()).getNombre());
	        	btnAtaque3.setVisible(true);
			case 2:
				btnAtaque2.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(1).getIdMovimiento()).getNombre());
	        	btnAtaque2.setVisible(true);
			case 1:
				btnAtaque1.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(0).getIdMovimiento()).getNombre());
	        	btnAtaque1.setVisible(true);
	        	break;
		}
    }
	
	@FXML
    void cambiarPokemon(ActionEvent event) {
		desactivarBotonesSeleccionAccion();
		
		activarMenuEquipo();
    }
	
	@FXML
    void cambiarPokemon2(ActionEvent event) {
		desactivarBotonesSeleccionAccion();
		btnCambiarPokemon.setVisible(false);
		
		vBoxEquipo.setVisible(true);

		lblTexto.setText("Elije un pokemon.");
    	lblTexto.setVisible(true);
    }

    @FXML
    void cambiarRival(ActionEvent event) {
    	generarPokemonRival();
    	activarBotonesSeleccionAccion();
    	
    	btnCambiarRival2.setVisible(false);
    	btnSalir.setVisible(false);
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
    
    @FXML
    void cancelarAccion(ActionEvent event) {
    	desactivarBotonesMovimientos();
    	activarBotonesSeleccionAccion();
    	vBoxEquipo.setVisible(false);
    }
    
    @FXML
    void ataque1(ActionEvent event) {
    	llamarAtaque(0);
    }

    @FXML
    void ataque2(ActionEvent event) {
    	llamarAtaque(1);
    }

    @FXML
    void ataque3(ActionEvent event) {
    	llamarAtaque(2);
    }

    @FXML
    void ataque4(ActionEvent event) {
    	llamarAtaque(3);
    }
    
    private void realizarAtaque(int movPokEntrenador, int movPokRival) {
        Movimiento movEntr = MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(movPokEntrenador).getIdMovimiento());
        Movimiento movRival = movimientosRival.get(movPokRival);

        // Compara velocidades y realiza los ataques en consecuencia
        if (equipoEntrenador.get(pokActEntr).getVelocidad() > pokemonRival.getVelocidad()) {
            realizarAtaqueSiPrimerAtacante(movEntr, movRival, equipoEntrenador.get(pokActEntr), pokemonRival, true);
        } else if (equipoEntrenador.get(pokActEntr).getVelocidad() < pokemonRival.getVelocidad()) {
            realizarAtaqueSiPrimerAtacante(movRival, movEntr, pokemonRival, equipoEntrenador.get(pokActEntr), false);
        } else {
            realizarAtaqueAleatorio(movEntr, movRival, equipoEntrenador.get(pokActEntr), pokemonRival);
        }
    }

    // Método que gestiona la lógica de los ataques cuando un Pokémon es el primero en atacar
    private void realizarAtaqueSiPrimerAtacante(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor, boolean esTurnoEntrenador) {
        if (comprobarEstadoAntesAtaque(atacante, defensor, esTurnoEntrenador)) {
            tipoDeMovimiento(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, true);
        } else if (!btnAtaque1.isVisible() && comprobarEstadoAntesAtaque(defensor, atacante, !esTurnoEntrenador)) {
            tipoDeMovimiento(movDefensor, movAtacante, defensor, atacante, !esTurnoEntrenador, false);
        } else {
        	PauseTransition pausa = new PauseTransition(Duration.seconds(2));
            pausa.setOnFinished(event -> {
                //activarBotonesMovimientos();
            	activarBotonesSeleccionAccion();
            });
            pausa.play();
        }
    }
    
    // Método que maneja el caso cuando ambos Pokémon tienen la misma velocidad
    private void realizarAtaqueAleatorio(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor) {
        if ((int) (Math.random() * 2) + 1 == 1) {
            realizarAtaqueSiPrimerAtacante(movAtacante, movDefensor, atacante, defensor, true);
        } else {
            realizarAtaqueSiPrimerAtacante(movDefensor, movAtacante, defensor, atacante, false);
        }
    }
    
    private void calcularPokemonVivos() {
    	for (int i = 0; i < equipoEntrenador.size(); i++) {
    		if (equipoEntrenador.get(i) != null) {
    			if (equipoEntrenador.get(i).getVitalidadAct() > 0) {
    				if (pokActEntr == -1) {
    					pokActEntr = i;
    				}
        			numPokVivosEntr++;
        		}
    		}
    	}
	}
    
    private boolean comprobarEstadoAntesAtaque(Pokemon pokemon, Pokemon contrario, boolean esTurnoEntrenador) {
    	switch (pokemon.getEstado().toString()) {
    	case "CONGELADO":
    	case "DORMIDO":
    	case "PARALIZADO":
    	case "ENAMORADO":
    		if ((int) (Math.random() * 100) + 1 > 75) {
				return quitarEstado(pokemon, esTurnoEntrenador);
			} else {
				lblTexto.setText(pokemon.getMote() + " esta " + pokemon.getEstado().toString().toLowerCase());
				return false;
			}
    	case "CONFUSO":
    		if ((int) (Math.random() * 100) + 1 > 75) { //EN TEORIA DURA DE 1 A 4 TURNOS
				return quitarEstado(pokemon, esTurnoEntrenador);
			} else if ((int) (Math.random() * 100) + 1 > 67){
				lblTexto.setText(pokemon.getMote() + " se ha golpeado a si mismo!");
				//ESTO PUEDE ESTAR MAL PUEDE QUE SALGA DEL METODO SIN HACER LO DE LA TRANSICION --------------------------------------------------------
				PauseTransition pausa = new PauseTransition(Duration.seconds(2));
				pausa.setOnFinished(event -> {
					actualizarVida(pokemon, esTurnoEntrenador ? false : true, calcularDano(null, pokemon, pokemon));
					
					if (pokemon.getVitalidadAct() == 0) {
						manejarPokemonDebilitado(pokemon, contrario, esTurnoEntrenador ? false : true);
					}
				});
				pausa.play();
				return false;
			} else {
				return true;
			}
    		default:
    			return true;
    	}
	}
    
    private void tipoDeMovimiento(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor, boolean esTurnoEntrenador, boolean primeraParte) {
		if (movAtacante.getTipo().equals("ESPECIAL") || movAtacante.getTipo().equals("FISICO")){
			realizarAtaquePokemon(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, primeraParte);
		}
		else if (movAtacante.getTipo().equals("MEJORA")){
			movimientoMejora(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, primeraParte);
		}
		else { //Moviemientos de estado
			movimientoEstado(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, primeraParte);
		}
	}
    
    private void realizarAtaquePokemon(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor, boolean esTurnoEntrenador, boolean primeraParte) {
    	lblTexto.setText(atacante.getMote() + " ha usado " + movAtacante.getNombre());

        PauseTransition pausa1 = new PauseTransition(Duration.seconds(2));
        pausa1.setOnFinished(event1 -> {
            if (movAtacante.getProbabilidad() > ((int) (Math.random() * 100) + 1)) {
                lblTexto.setText(atacante.getMote() + " ha golpeado a " + defensor.getMote());
                
                actualizarVida(defensor, esTurnoEntrenador, calcularDano(movAtacante, atacante, defensor));
                
                PauseTransition pausa2 = new PauseTransition(Duration.seconds(2));
                pausa2.setOnFinished(event2 -> {
                    if (defensor.getVitalidadAct() == 0) {
                        manejarPokemonDebilitado(defensor, atacante, esTurnoEntrenador);
                    } else {
                    	turnoDefensor(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, primeraParte);
                    }
                });
                pausa2.play();
            } else {
                lblTexto.setText(atacante.getMote() + " ha fallado!");
                
                PauseTransition pausa2 = new PauseTransition(Duration.seconds(2));
                pausa2.setOnFinished(event2 -> {
                	turnoDefensor(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, primeraParte);
                });
                pausa2.play();
            }
        });
        pausa1.play();
}
    
    private void movimientoMejora(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor, boolean esTurnoEntrenador, boolean primeraParte) {
		lblTexto.setText(atacante.getMote() + " ha usado " + movAtacante.getNombre());
		
		PauseTransition pausa1 = new PauseTransition(Duration.seconds(2));
		pausa1.setOnFinished(event1 -> {
			switch (movAtacante.getMejora()) {
			case "SUBIR_ATAQUE":
				lblTexto.setText(atacante.getMote() + " le ha subido el ataque");
				atacante.setAtaque((int)(atacante.getAtaque() * 1.5));
				break;
			case "SUBIR_DEFENSA":
				lblTexto.setText(atacante.getMote() + " le ha subido la defensa");
				atacante.setDefensa((int)(atacante.getDefensa() * 1.5));
				break;
			case "SUBIR_ATAQUE_ESP":
				lblTexto.setText(atacante.getMote() + " le ha subido el ataque especial");
				atacante.setAtEspecial(((int)(atacante.getAtEspecial() * 1.5)));
				break;
			case "SUBIR_DEFENSA_ESP":
				lblTexto.setText(atacante.getMote() + " le ha subido la defensa especial");
				atacante.setDefEspecial((int)(atacante.getDefEspecial() * 1.5));
				break;
			case "SUBIR_VIDA":
				lblTexto.setText(atacante.getMote() + " ha recuperado vida");
				atacante.setVitalidadAct(Math.min(atacante.getVitalidadAct() * 2, atacante.getVitalidadMax()));
				break;
			case "SUBIR_VELOCIDAD":
				lblTexto.setText(atacante.getMote() + " le ha subido la velocidad");
				atacante.setVelocidad((int)(atacante.getVelocidad() * 1.5));
				break;
			case "BAJAR_ATAQUE":
				lblTexto.setText(defensor.getMote() + " le ha bajado el ataque");
				defensor.setAtaque((int)(defensor.getAtaque() / 1.5));
				break;
			case "BAJAR_DEFENSA":
				lblTexto.setText(defensor.getMote() + " le ha bajado la defensa");
				defensor.setDefensa((int)(defensor.getDefensa() / 1.5));
				break;
			case "BAJAR_ATAQUE_ESP":
				lblTexto.setText(defensor.getMote() + " le ha bajado el ataque especial");
				defensor.setAtEspecial((int)(defensor.getAtEspecial() / 1.5));
				break;
			case "BAJAR_DEFENSA_ESP":
				lblTexto.setText(defensor.getMote() + " le ha bajado la defensa especial");
				defensor.setDefEspecial((int)(defensor.getDefEspecial() / 1.5));
				break;
			case "BAJAR_VELOCIDAD":
				lblTexto.setText(defensor.getMote() + " le ha bajado la velocidad");
				defensor.setVelocidad((int)(defensor.getVelocidad() / 1.5));
				break;
			}	  
		});
		
		PauseTransition pausa2 = new PauseTransition(Duration.seconds(2));
		pausa2.setOnFinished(event2 -> {
			turnoDefensor(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, primeraParte);
		});
		
		SequentialTransition secuencia = new SequentialTransition(pausa1, pausa2);
		secuencia.play();
	}
    
    private void turnoDefensor(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor,
			boolean esTurnoEntrenador, boolean primeraParte) {
		//Antes de que le toque al defensor vemos si el atacante ha sido debilitado por algun efecto
		if (efectoEstadoFinalTurno(atacante, esTurnoEntrenador ? false : true)) {//he cambiado esto. antes era solo esTurnoEntrenador
			PauseTransition pausa = new PauseTransition(Duration.seconds(2));
            pausa.setOnFinished(event -> {
            	if (primeraParte) {
    				if (comprobarEstadoAntesAtaque(defensor, atacante, esTurnoEntrenador ? false : true)) {
    					PauseTransition pausa1 = new PauseTransition(Duration.seconds(2));
    					pausa1.setOnFinished(event1 -> {
    						tipoDeMovimiento(movDefensor, movAtacante, defensor, atacante, esTurnoEntrenador ? false : true, false);
    					});
    					pausa1.play();
    				} else {
    					PauseTransition pausa1 = new PauseTransition(Duration.seconds(2));
    					pausa1.setOnFinished(event1 -> {
    						activarBotonesSeleccionAccion();
    						//activarBotonesMovimientos();
    					});
    					pausa1.play();
    				}
    			} else {
    				activarBotonesSeleccionAccion();
    				//activarBotonesMovimientos();
    			}
            });
            pausa.play();
		} else {
			manejarPokemonDebilitado(atacante, defensor, esTurnoEntrenador ? false : true); //ESPEREMOS QUE ESTO ESTE BIEN. SI ESTA MAL ME VOY A ENTERAR
		}
	}
    
    private boolean efectoEstadoFinalTurno(Pokemon pokemon, boolean esTurnoEntrenador) {
		if (!pokemon.getEstado().equals(TipoEstados.valueOf("SIN_ESTADO"))) {
			if ((int) (Math.random() * 100) + 1 > 75){
				lblTexto.setText(pokemon.getMote() + " ya no esta " + pokemon.getEstado().toString().toLowerCase());
				pokemon.setEstado(TipoEstados.valueOf("SIN_ESTADO"));
				
				if (esTurnoEntrenador) {
					lblEstadoPokemonRival.setText("");
				} else {
					lblEstadoPokemonEntrenador.setText("");
				}
			}
			else if (pokemon.getEstado().equals(TipoEstados.valueOf("QUEMADO")) || pokemon.getEstado().equals(TipoEstados.valueOf("ENVENENADO")) 
					|| pokemon.getEstado().equals(TipoEstados.valueOf("GRAVEMENTE_ENVENENADO"))){
				switch (pokemon.getEstado().toString()) {
					case "QUEMADO":
						lblTexto.setText(pokemon.getMote() + " es golpeado por la quemadura");
						actualizarVida(pokemon, esTurnoEntrenador, (int) (pokemon.getVitalidadMax() * (1.0 / 16.0)));
						break;
					case "ENVENENADO":
						lblTexto.setText(pokemon.getMote() + " es golpeado por el envenenamiento");
						actualizarVida(pokemon, esTurnoEntrenador, (int) (pokemon.getVitalidadMax() * (1.0 / 8.0)));
						break;
					case "GRAVEMENTE_ENVENENADO":
						lblTexto.setText(pokemon.getMote() + " es golpeado por el envenenamiento grave");
						actualizarVida(pokemon, esTurnoEntrenador, (int) (pokemon.getVitalidadMax() * (1.0 / 6.0)));
						break;
				}
			}
		}
		if (pokemon.getVitalidadAct() == 0) {
			return false;
		}
		return true;
	}
    
    private void movimientoEstado(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor,
			boolean esTurnoEntrenador, boolean primeraParte) {
		lblTexto.setText(atacante.getMote() + " ha usado " + movAtacante.getNombre());
		
		defensor.setEstado(movAtacante.getEstado());
		
		PauseTransition pausa1 = new PauseTransition(Duration.seconds(2));
		pausa1.setOnFinished(event1 -> {
			lblTexto.setText(defensor.getMote() + " ha sido " + movAtacante.getEstado().toString().toLowerCase());
			
			if (esTurnoEntrenador) {
				lblEstadoPokemonRival.setText(defensor.getEstado().getPseudonimo());
			} else {
				lblEstadoPokemonEntrenador.setText(defensor.getEstado().getPseudonimo());
			}
		});
		
		PauseTransition pausa2 = new PauseTransition(Duration.seconds(2));
		pausa2.setOnFinished(event2 -> {
			if (!(defensor.getEstado().equals(TipoEstados.valueOf("CONGELADO")) || defensor.getEstado().equals(TipoEstados.valueOf("DORMIDO"))
					|| defensor.getEstado().equals(TipoEstados.valueOf("PARALIZADO")) || defensor.getEstado().equals(TipoEstados.valueOf("ENAMORADO")))) {
				turnoDefensor(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, primeraParte);
			} else {
				activarBotonesSeleccionAccion();
			}
		});
		
		SequentialTransition secuencia = new SequentialTransition(pausa1, pausa2);
		secuencia.play();
	}
    
    private void manejarPokemonDebilitado(Pokemon defensor, Pokemon atacante, boolean esTurnoEntrenador) {
    	activo = new SimpleBooleanProperty(false);
    	defensor.setEstado(TipoEstados.valueOf("DEBILITADO"));
    	
    	if (esTurnoEntrenador) {
            imgPokemonRival.setImage(null);
        } else {
            numPokVivosEntr--;
            imgPokemonEntrenador.setImage(null);
        }
    	
    	System.out.println(numPokVivosEntr);

        lblTexto.setText(defensor.getMote() + " ha sido debilitado!");
        
        activo.addListener((observable, oldValue, newValue) -> {
        	desactivarBotonesMovimientos();
        	comprobarPokemones(esTurnoEntrenador);
        });
        
        if (equipoEntrenador.get(pokActEntr).getNivel() < 100 && pokemonRival.getVitalidadAct() == 0) {
        	int xp = (atacante.getNivel() + defensor.getNivel() * 10) / 4;
        	
        	System.out.println("Experiencia antes: " + equipoEntrenador.get(pokActEntr).getExperiencia());
        	
        	equipoEntrenador.get(pokActEntr).setExperiencia(xp + equipoEntrenador.get(pokActEntr).getExperiencia());
        	
        	System.out.println("Experiencia despues: " + equipoEntrenador.get(pokActEntr).getExperiencia());
        	
        	PauseTransition pausa2 = new PauseTransition(Duration.seconds(2));
            pausa2.setOnFinished(event2 -> {
            	lblTexto.setText(equipoEntrenador.get(pokActEntr).getMote() + " ha ganado " + xp + " puntos de experiencia");
            });
            
            PauseTransition pausa3 = new PauseTransition(Duration.seconds(2));
            pausa3.setOnFinished(event3 -> {
            	actualizarXP(activo);
            	PokemonDAO.actualizarPokemonSubirNivel(con, equipoEntrenador.get(pokActEntr));
            });
            
            SequentialTransition secuencia = new SequentialTransition(pausa2, pausa3);
            secuencia.play();
        } else {
        	desactivarBotonesMovimientos();
        	comprobarPokemones(esTurnoEntrenador);
        }
    }
    
    public void actualizarXP(SimpleBooleanProperty activo) {
		experienciaLevelUp = 10 * equipoEntrenador.get(pokActEntr).getNivel();
		double porcentajeFinal;
		int nivelAux = equipoEntrenador.get(pokActEntr).getNivel();
		
		System.out.println("Nivel: " + equipoEntrenador.get(pokActEntr).getNivel());
		System.out.println("ExperienciaNextLvl: " + experienciaLevelUp);
		System.out.println("Experiencia acumulada: " + equipoEntrenador.get(pokActEntr).getExperiencia());
		
		if (equipoEntrenador.get(pokActEntr).getExperiencia() >= experienciaLevelUp) {
			porcentajeFinal = 1;
			equipoEntrenador.get(pokActEntr).setNivel(equipoEntrenador.get(pokActEntr).getNivel() + 1);
			equipoEntrenador.get(pokActEntr).setExperiencia(equipoEntrenador.get(pokActEntr).getExperiencia() - experienciaLevelUp);
		} else {
			porcentajeFinal = (double) (equipoEntrenador.get(pokActEntr).getExperiencia()) / experienciaLevelUp;
		}
		
		int numCiclos = 90;
		double aumPorPaso = (porcentajeFinal - pbXpPokemonEntrenador.getProgress()) / numCiclos;
		
		Timeline timeline = new Timeline(
		    new KeyFrame(Duration.seconds(0.017), e -> {
		        double current = pbXpPokemonEntrenador.getProgress();
		        
		        if (current < porcentajeFinal) {
		        	pbXpPokemonEntrenador.setProgress(Math.min(current + aumPorPaso, 1));
		        }
		    })
		);
		timeline.setCycleCount(numCiclos);
		timeline.play();
		
		timeline.setOnFinished(e -> {
			if (nivelAux != equipoEntrenador.get(pokActEntr).getNivel()) {
				lblTexto.setText(equipoEntrenador.get(pokActEntr).getMote() + " ha subido al nivel " + equipoEntrenador.get(pokActEntr).getNivel() + "! Sus estadisticas tambien suben!");
				
				int rand = (int) (Math.random() * 5) + 1;
				
				equipoEntrenador.get(pokActEntr).setVitalidadMax(equipoEntrenador.get(pokActEntr).getVitalidadMax() + rand);
				equipoEntrenador.get(pokActEntr).setVitalidadAct(equipoEntrenador.get(pokActEntr).getVitalidadAct() + rand);
				equipoEntrenador.get(pokActEntr).setAtaque(equipoEntrenador.get(pokActEntr).getAtaque() + (int) (Math.random() * 5) + 1);
				equipoEntrenador.get(pokActEntr).setAtEspecial(equipoEntrenador.get(pokActEntr).getAtEspecial() + (int) (Math.random() * 5) + 1);
				equipoEntrenador.get(pokActEntr).setDefensa(equipoEntrenador.get(pokActEntr).getDefensa() + (int) (Math.random() * 5) + 1);
				equipoEntrenador.get(pokActEntr).setDefEspecial(equipoEntrenador.get(pokActEntr).getDefEspecial() + (int) (Math.random() * 5) + 1);
				
				lblVidaPokemonEntrenador.setText(equipoEntrenador.get(pokActEntr).getVitalidadAct() + "/" + equipoEntrenador.get(pokActEntr).getVitalidadMax());
				pbVidaPokemonEntrenador.setProgress((double) (equipoEntrenador.get(pokActEntr).getVitalidadAct()) / equipoEntrenador.get(pokActEntr).getVitalidadMax());
				lblNivelPokemonEntrenador.setText(Integer.toString(equipoEntrenador.get(pokActEntr).getNivel()));
				
				if (pbVidaPokemonEntrenador.getProgress() < 0.25){
					pbVidaPokemonEntrenador.setStyle("-fx-accent: red;");
		        }
		        else if (pbVidaPokemonEntrenador.getProgress() < 0.5){
		        	pbVidaPokemonEntrenador.setStyle("-fx-accent: yellow;");
		        } else {
		        	pbVidaPokemonEntrenador.setStyle("-fx-accent: #00a135;");
		        }
			}
	        if ((equipoEntrenador.get(pokActEntr).getNivel() % 3) == 1 && equipoEntrenador.get(pokActEntr).getNivel() != nivelAux) {
	        	listaMovPosiblesEntr = MovimientoDAO.buscarPorTipoMov(con, TipoPokemon.NORMAL.toString());
	        	listaMovPosiblesEntr.addAll(MovimientoDAO.buscarPorTipoMov(con, PokedexDAO.cargarPorNumPokedex(con, equipoEntrenador.get(pokActEntr).getNumPokedex()).getTipo(0)));
	        	if (PokedexDAO.cargarPorNumPokedex(con, equipoEntrenador.get(pokActEntr).getNumPokedex()).getTipo(1) != null) {
	        		listaMovPosiblesEntr.addAll(MovimientoDAO.buscarPorTipoMov(con, PokedexDAO.cargarPorNumPokedex(con, equipoEntrenador.get(pokActEntr).getNumPokedex()).getTipo(0)));
	        	}
	        	do {
	        		movimientoNuevo = listaMovPosiblesEntr.get((int) (Math.random() * listaMovPosiblesEntr.size()));
	        	} while(comprobarMovimientos(movimientoNuevo));
	        	
	        	if (listaMovPokEntr.size() < 4) {
	        		lblTexto.setText(equipoEntrenador.get(pokActEntr).getMote() + " ha aprendido " + movimientoNuevo.getNombre());
	        		listaMovPokEntr.add(new MovimientoPokemon(equipoEntrenador.get(pokActEntr).getIdPokemon(), movimientoNuevo.getIdMovimiento(), movimientoNuevo.getPpMax()));
	        		
	        		MovimientoPokemonDAO.insertarMovimientoPokemon(con, new MovimientoPokemon(equipoEntrenador.get(pokActEntr).getIdPokemon(), movimientoNuevo.getIdMovimiento(), movimientoNuevo.getPpMax()));
	        		listaMovPokEntrAUX.add(movimientoNuevo);
	        		
	        		siguienteNivel();
	        	} else {
	        		System.out.println(movimientoNuevo.getTipoMov());
	        		System.out.println("./img/movimientos/tarjetas/" + movimientoNuevo.getTipoMov() + ".png");
	        		imgMovNuevo.setImage(new Image(new File("./img/movimientos/tarjetas/" + movimientoNuevo.getTipoMov() + ".png").toURI().toString()));
	        		
	        		lblNomNuevo.setText(movimientoNuevo.getNombre());
	        		lblPPNuevo.setText(movimientoNuevo.getPpMax() + "/" + movimientoNuevo.getPpMax());
	        		lblTipoMovNuevo.setText("Tipo de movimiento: " + movimientoNuevo.getTipo());
	        		
	        		switch (movimientoNuevo.getTipo()) {
	        		case "MEJORA":
	        			lblEfectoNuevo.setText("Efecto: " + movimientoNuevo.getMejora());
	        			break;
	        		case "ESTADO":
	        			lblEfectoNuevo.setText("Efecto: " + movimientoNuevo.getEstado());
	        			break;
	        		case "FISICO":
	        			lblEfectoNuevo.setText("Efecto: daña al rival con ataque fisico");
	        			break;
	        		case "ESPECIAL":
	        			lblEfectoNuevo.setText("Efecto: daña al rival con ataque especial");
	        			break;
	        		}
	        		
	        		lblTexto.setText(equipoEntrenador.get(pokActEntr).getMote() + " quiere aprender " + movimientoNuevo.getNombre() + " pero ya tiene 4 movimientos. ¿Quieres que olvide uno?");
	        		btnOlvidarMovimiento.setVisible(true);
	        		btnNoAprender.setVisible(true);
	        		imgSeleccionAccion.setVisible(true);
	        	}
	        }
	        else {
	        	siguienteNivel();
	        }
		});
	}

    private boolean comprobarMovimientos(Movimiento movimiento) {
		for (MovimientoPokemon movimientoEntrenador : listaMovPokEntr) {
			if (movimientoEntrenador.getIdMovimiento() == movimiento.getIdMovimiento()) {
				return true;
			}
		}
		return false;
	}
    
	private void comprobarPokemones(boolean esTurnoEntrenador) {
		if (pokemonRival.getVitalidadAct() == 0) {
			lblTexto.setText("¿Quieres enfrentarte a otro pokemon?");
			btnCambiarRival2.setVisible(true);
			btnSalir.setVisible(true);
			imgSeleccionAccion.setVisible(true);
		} else if (numPokVivosEntr == 0) {
			uiFinalCombate();
			lblTexto.setText("Todo tu equipo se ha debilitado");
			volverAlMenu();
		} else {
		    cambiarPokemon(esTurnoEntrenador);
		}
	}
	
	private void cambiarPokemon(boolean esTurnoEntrenador) {
        lblTexto.setText("Quieres sacar otro pokemon?");

        btnSalir.setVisible(true);
        btnCambiarPokemon.setVisible(true);
        imgSeleccionAccion.setVisible(true);
    }
    
    private void actualizarVida(Pokemon defensor, boolean esTurnoEntrenador, int dano) {
		defensor.setVitalidadAct(Math.max(defensor.getVitalidadAct() - dano, 0));
		
		if (esTurnoEntrenador) {
			actualizarBarraVida(pbVidaPokemonRival, defensor);
		}
		else {
			actualizarBarraVida(pbVidaPokemonEntrenador, defensor);
			
			//BAJAR POCO A POCO EL TEXTO DE LA VIDA DEL POKEMON DEL ENTRENADOR
			double tiempoPorCiclo = 1.5 / dano;
			
			Timeline timeline = new Timeline(
			    new KeyFrame(Duration.seconds(tiempoPorCiclo), e -> {
			    	int current;
			    	
			    	try {
			    		current = Integer.parseInt(lblVidaPokemonEntrenador.getText().substring(0,3));
					} catch (Exception e2) {
						try {
							current = Integer.parseInt(lblVidaPokemonEntrenador.getText().substring(0,2));
						} catch (Exception e3) {
							current = Integer.parseInt(lblVidaPokemonEntrenador.getText().substring(0,1));
						}
					}
			        
			        if (current > defensor.getVitalidadAct()) {
			        	lblVidaPokemonEntrenador.setText((Math.max(current - 1, defensor.getVitalidadAct())) + "/" + defensor.getVitalidadMax());
			        }
			    })
			);
			timeline.setCycleCount(dano);
			timeline.play();
			
			PauseTransition pausa1 = new PauseTransition(Duration.seconds(1.5));
	        pausa1.setOnFinished(event1 -> {
	        	lblVidaPokemonEntrenador.setText(defensor.getVitalidadAct() + "/" + defensor.getVitalidadMax());
	        });
			pausa1.play();
		}
	}
    
    private void actualizarBarraVida(ProgressBar pb, Pokemon pokemon) {
		double porcentajeFinal = ((double) (pokemon.getVitalidadAct())) / pokemon.getVitalidadMax();
		
		int numCiclos = 90;
		double decrPorPaso = (pb.getProgress() - porcentajeFinal) / numCiclos;
		
		Timeline timeline = new Timeline(
		    new KeyFrame(Duration.seconds(0.017), e -> {
		        double current = pb.getProgress();
		        
		        if (current < 0.25){
		        	pb.setStyle("-fx-accent: red;");
		        }
		        else if (current < 0.5){
		        	pb.setStyle("-fx-accent: yellow;");
		        }
		        
		        if (current > porcentajeFinal) {
		        	pb.setProgress(Math.max(current - decrPorPaso, 0.0001));
		        }
		    })
		);
		timeline.setCycleCount(numCiclos);
		timeline.play();
	}

	private boolean quitarEstado(Pokemon pokemon, boolean esTurnoEntrenador) {
		lblTexto.setText(pokemon.getMote() + " ya no esta " + pokemon.getEstado());
		pokemon.setEstado(TipoEstados.valueOf("SIN_ESTADO"));
		
		if (esTurnoEntrenador) {
		    lblEstadoPokemonEntrenador.setText("");
		} else {
			lblEstadoPokemonRival.setText("");
		}
		return true;
	}
	
	private int calcularDano(Movimiento movAtacante, Pokemon atacante, Pokemon defensor) {
		String[] tiposAtacante = PokedexDAO.cargarPorNumPokedex(ConexionBD.getConnection(), atacante.getNumPokedex()).getTipos();
		double efectividad = 1;
		double bonus = 1;
		
		if (movAtacante != null) {
			efectividad = defensor.comprobarVentajaDesventaja(movAtacante);
	    	bonus = tiposAtacante[0].equals(movAtacante.getTipoMov()) ? 1.5 : 1.0;
	    	
	    	if (bonus != 1.5 && tiposAtacante[1] != null) {
	    		bonus = tiposAtacante[1].equals(movAtacante.getTipoMov()) ? 1.5 : 1.0;
	    	}
		} else {
			movAtacante = new Movimiento(40);
		}
		
    	int variacion = (int)(Math.random() * (100 - 85 + 1) + 85);
    	int dano;
    	
    	
    	
    	if (movAtacante.getTipo().equals("FISICO")) {
    		if (atacante.getEstado().equals(TipoEstados.valueOf("QUEMADO"))) { //ataque x 0.5
    			dano = (int) (0.01 * bonus * efectividad * variacion * (((0.2 * atacante.getNivel() + 1) * atacante.getAtaque() * 0.5 * movAtacante.getPotencia()) / (25 * defensor.getDefensa()) + 2));
    		} else {
    			dano = (int) (0.01 * bonus * efectividad * variacion * (((0.2 * atacante.getNivel() + 1) * atacante.getAtaque() * movAtacante.getPotencia()) / (25 * defensor.getDefensa()) + 2));
    		}
    	} else {
    		dano = (int) (0.01 * bonus * efectividad * variacion * (((0.2 * atacante.getNivel() + 1) * atacante.getAtEspecial() * movAtacante.getPotencia()) / (25 * defensor.getDefEspecial()) + 2));
    	}
    	
    	//if (OBJETO DEL ATACANTE == ANILLO UNICO && registroCombate.size() < 3) {
    	//dano += 10;
    	//} else if (OBJETO DEL DEFENSOR == ANILLO UNICO && registroCombate.size() < 3) {
    	//dano = 0;
    	//}
    	
    	return dano;
	}
    
    private void llamarAtaque(int numMov) {
    	desactivarBotonesMovimientos();
    	
    	lblTexto.setVisible(true);
    	imgSeleccionAccion.setVisible(false);
    	
		if (listaMovPokEntr.get(numMov).getPpActuales() > 0) {
    		listaMovPokEntr.get(numMov).setPpActuales(listaMovPokEntr.get(numMov).getPpActuales() - 1);
    		
    		realizarAtaque(numMov, seleccionarAtaqueRival());
    	}
    	else {
    		sinPPs(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(numMov).getIdMovimiento()).getNombre());
    	}
	}
    
    private int seleccionarAtaqueRival() {
		while(true) {
			int n = (int) (Math.random() * 3);
			if (n <= movimientosRival.size() - 1) {
				if (movimientosRival.get(n).getPpActuales() > 0) {
					movimientosRival.get(n).setPpActuales(movimientosRival.get(n).getPpActuales() - 1);
					return n;
				}
			}
		}
	}
    
    private void sinPPs(String movimiento) {
    	lblTexto.setVisible(false);
    	lblTexto.setText("El ataque " + movimiento + " no tiene PPs");
    	
    	PauseTransition pausa = new PauseTransition(Duration.seconds(2));
    	pausa.setOnFinished(evento -> {
            activarBotonesMovimientos();
        });
    	pausa.play();
    }
    
    @FXML
    void entrarAtaque1(MouseEvent event) {
    	mostrarPPsYTipo(0);
    }

    @FXML
    void entrarAtaque2(MouseEvent event) {
    	mostrarPPsYTipo(1);
    }

    @FXML
    void entrarAtaque3(MouseEvent event) {
    	mostrarPPsYTipo(2);
    }

    @FXML
    void entrarAtaque4(MouseEvent event) {
    	mostrarPPsYTipo(3);
    }

    @FXML
    void salirAtaque1(MouseEvent event) {
    	defaultPPType();
    }

    @FXML
    void salirAtaque2(MouseEvent event) {
    	defaultPPType();
    }

    @FXML
    void salirAtaque3(MouseEvent event) {
    	defaultPPType();
    }

    @FXML
    void salirAtaque4(MouseEvent event) {
    	defaultPPType();
    }

    private void mostrarPPsYTipo(int i) {
		lblPP.setText("PP: " + listaMovPokEntr.get(i).getPpActuales() + "/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(i).getIdMovimiento()).getPpMax());
    	lblType.setText("Type: " + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(i).getIdMovimiento()).getTipoMov());
	}
    
    private void defaultPPType() {
    	lblPP.setText("PP: ");
    	lblType.setText("Type: ");
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
					btnCambiarRival2.setVisible(false);
				}
			}
		}
	}
    
    @FXML
    void confirmarCambio(ActionEvent event) {
    	vBoxEquipo.setVisible(false);
    	btnCancelarAccion.setVisible(false);
    	btnConfirmarCambio.setVisible(false);
    	btnSalir.setVisible(false);
    	
    	activarBotonesSeleccionAccion();
    	
    	lblTexto.setText(entrenador.getUsuario() + " saca a " + equipoEntrenador.get(indicePokSeleccionado).getMote());
    	pokActEntr = indicePokSeleccionado;
    	pokemonEntrenador = equipoEntrenador.get(pokActEntr);
    	cargarInfoPokemonEntrenador();
    }
    
    private void volverAlMenu() {
		for (Pokemon pokemon : equipoEntrenador) {
			PokemonDAO.actualizarEstadisticasTrasCombate(con, pokemon);
			
			for (MovimientoPokemon movimiento : MovimientoPokemonDAO.buscarPorIdPokemon(con, pokemon.getIdPokemon())) {
				MovimientoPokemonDAO.actualizarPPMovimiento(con, pokemon.getIdPokemon(), movimiento.getIdMovimiento(), MovimientoDAO.buscarPorId(con, movimiento.getIdMovimiento()).getPpMax());
			}
		}
		
		EntrenadorDAO.actualizarPokedolares(con, entrenador.getIdEntrenador(), entrenador.getPokedolares() - (entrenador.getPokedolares() / 3));
		EntrenadorDAO.actualizarRivalesVencidos(con, entrenador.getIdEntrenador(), entrenador.getRivalesVencidos());
		
		PauseTransition pausa = new PauseTransition(Duration.seconds(5));
    	pausa.setOnFinished(evento -> {
    		if (numPokVivosEntr == 0) {
    			try {
            	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CentroPokemon.fxml"));
            	    Parent root = loader.load();

            	    CentroPokemonController centroPokemonController = loader.getController();
            	    centroPokemonController.init(entrenador, stage, loginController, menuController, null, new CombateController());

            	    Scene scene = new Scene(root);
            	    stage.setScene(scene);
            	    stage.setTitle("Centro Pokemon");
            	    stage.show();
            	} catch (Exception e) {
            	    e.printStackTrace();
            	}
    		} else {
    			try {
            	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
            	    Parent root = loader.load();

            	    MenuController menuController = loader.getController();
            	    menuController.init(entrenador, stage, loginController, null, null, null, null, null, null, null);

            	    Scene scene = new Scene(root);
            	    stage.setScene(scene);
            	    stage.setTitle("Menu");
            	    stage.show();
            	} catch (Exception e) {
            	    e.printStackTrace();
            	}
    		}
        });
    	pausa.play();
	}
    
    @FXML
    void confirmarCambioMov(ActionEvent event) {
    	//Mostrar una ventana indicando que se han cambiado los movs
    	MovimientoPokemonDAO.eliminarMovimientoPokemon(con, equipoEntrenador.get(pokActEntr).getIdPokemon(), movimientoViejo.getIdMovimiento());
    	
    	MovimientoPokemon movAux = new MovimientoPokemon(movimientoViejo.getIdPokemon(), movimientoNuevo.getIdMovimiento(), movimientoNuevo.getPpMax());
    	for (int i = 0; i < listaMovPokEntr.size(); i++) {
			if (listaMovPokEntr.get(i).getIdMovimiento() == movimientoViejo.getIdMovimiento()) {
				listaMovPokEntr.remove(i);
				listaMovPokEntrAUX.remove(i);
				
				listaMovPokEntr.add(movAux);
				listaMovPokEntrAUX.add(movimientoNuevo);
				break;
			}
		}
    	
    	MovimientoPokemonDAO.insertarMovimientoPokemon(con, movAux);
    	
    	vBoxMovimientos.setVisible(false);
    	imgSeleccionAccion.setVisible(false);
    	
    	siguienteNivel();
    }

    @FXML
    void noAprenderMov(ActionEvent event) {
    	vBoxMovimientos.setVisible(false);
    	
    	btnOlvidarMovimiento.setVisible(false);
		btnNoAprender.setVisible(false);
		imgSeleccionAccion.setVisible(false);
		lblTexto.setText((equipoEntrenador.get(pokActEntr).getMote() + " no ha aprendido el movimiento."));
		
    	siguienteNivel();
    }

    @FXML
    void seleccionarMov1(ActionEvent event) {
    	mostrarInfoMov(0);
    }

    @FXML
    void seleccionarMov2(ActionEvent event) {
    	mostrarInfoMov(1);
    }

    @FXML
    void seleccionarMov3(ActionEvent event) {
    	mostrarInfoMov(2);
    }

    @FXML
    void seleccionarMov4(ActionEvent event) {
    	mostrarInfoMov(3);
    }
    
    @FXML
    void noAprender(ActionEvent event) {
    	btnOlvidarMovimiento.setVisible(false);
		btnNoAprender.setVisible(false);
		imgSeleccionAccion.setVisible(false);
		lblTexto.setText((equipoEntrenador.get(pokActEntr).getMote() + " no ha aprendido el movimiento."));
		
    	siguienteNivel();
    }
    
    private void siguienteNivel() {
		/*if (equipoEntrenador.get(numPokVivosEntr).getNivel() == 100) {
			equipoEntrenador.get(numPokVivosEntr).setExperiencia(0);
		}*/
		PauseTransition pausa = new PauseTransition(Duration.seconds(2));
        pausa.setOnFinished(event1 -> {
        	pbXpPokemonEntrenador.setProgress(0.0001); //ESTO GENERA UN BUG
        	if (PokedexDAO.cargarPorNumPokedex(ConexionBD.getConnection(), equipoEntrenador.get(pokActEntr).getNumPokedex()).getNivelEvo() == equipoEntrenador.get(pokActEntr).getNivel()) {
        		imgPokemonEntrenador.setImage(equipoEntrenador.get(pokActEntr).evolucionar());
        		actualizarXP(activo);
        	} else if (equipoEntrenador.get(pokActEntr).getExperiencia() >= experienciaLevelUp) {
        		actualizarXP(activo);
        	} else {
        		double porcentajeFinal2 = (double) (equipoEntrenador.get(pokActEntr).getExperiencia()) / experienciaLevelUp;
        		double aumPorPaso2 = (porcentajeFinal2 - pbXpPokemonEntrenador.getProgress()) / 90;
        		
        		Timeline timeline2 = new Timeline(
        		    new KeyFrame(Duration.seconds(0.017), e2 -> {
        		        double current = pbXpPokemonEntrenador.getProgress();
        		        
        		        if (current < porcentajeFinal2) {
        		        	pbXpPokemonEntrenador.setProgress(Math.min(current + aumPorPaso2, 1));
        		        }
        		    })
        		);
        		timeline2.setCycleCount(90);
        		timeline2.play();
        		
        		timeline2.setOnFinished(e2 -> {
        			PauseTransition pausa1 = new PauseTransition(Duration.seconds(2));
                    pausa1.setOnFinished(event2 -> {
                    	activo.set(true);
                    });
        			pausa1.play();
        		});
        	}
        });
        pausa.play();
	}

    @FXML
    void olvidarMovimiento(ActionEvent event) {
    	btnOlvidarMovimiento.setVisible(false);
    	btnNoAprender.setVisible(false);
    	
    	imgMov4.setImage(new Image(new File("./img/movimientos/iconos/" + listaMovPokEntrAUX.get(3).getTipoMov() + "2.2.png").toURI().toString()));
		lblMov4.setText(listaMovPokEntrAUX.get(3).getNombre());
		lblPPMov4.setText(listaMovPokEntr.get(3).getPpActuales() + "/" + listaMovPokEntrAUX.get(3).getPpMax());
		
		imgMov3.setImage(new Image(new File("./img/movimientos/iconos/" + listaMovPokEntrAUX.get(2).getTipoMov() + "2.2.png").toURI().toString()));
		lblMov3.setText(listaMovPokEntrAUX.get(2).getNombre());
		lblPPMov3.setText(listaMovPokEntr.get(2).getPpActuales() + "/" + listaMovPokEntrAUX.get(2).getPpMax());
		
		imgMov2.setImage(new Image(new File("./img/movimientos/iconos/" + listaMovPokEntrAUX.get(1).getTipoMov() + "2.2.png").toURI().toString()));
		lblMov2.setText(listaMovPokEntrAUX.get(1).getNombre());
		lblPPMov2.setText(listaMovPokEntr.get(1).getPpActuales() + "/" + listaMovPokEntrAUX.get(1).getPpMax());
		
		imgMov1.setImage(new Image(new File("./img/movimientos/iconos/" + listaMovPokEntrAUX.get(0).getTipoMov() + "2.2.png").toURI().toString()));
		lblMov1.setText(listaMovPokEntrAUX.get(0).getNombre());
		lblPPMov1.setText(listaMovPokEntr.get(0).getPpActuales() + "/" + listaMovPokEntrAUX.get(0).getPpMax());
		
		vBoxMovimientos.setVisible(true);
    }
    
    private void mostrarInfoMov(int n) {
    	imgCambioDesactivado.setVisible(false);
    	
    	movimientoViejo = listaMovPokEntr.get(n);
    	
    	imgMovSeleccionado.setImage(new Image(new File("./img/movimientos/tarjetas/" + listaMovPokEntrAUX.get(n).getTipoMov() + ".png").toURI().toString()));
    	
		lblMovSelec.setText(listaMovPokEntrAUX.get(n).getNombre());
    	lblPPSelec.setText(listaMovPokEntr.get(n).getPpActuales() + "/" + listaMovPokEntrAUX.get(n).getPpMax());
		lblTipoMovSelec.setText("Tipo de movimiento: " + listaMovPokEntrAUX.get(n).getTipo());
		
		switch (listaMovPokEntrAUX.get(n).getTipo()) {
		case "MEJORA":
			lblEfectoSelec.setText("Efecto: " + listaMovPokEntrAUX.get(n).getMejora());
			break;
		case "ESTADO":
			lblEfectoSelec.setText("Efecto: " + listaMovPokEntrAUX.get(n).getEstado());
			break;
		case "FISICO":
			lblEfectoSelec.setText("Efecto: daña al rival con ataque fisico");
			break;
		case "ESPECIAL":
			lblEfectoSelec.setText("Efecto: daña al rival con ataque especial");
			break;
		}
	}
    
    private void activarBotonesMovimientos() {
    	lblTexto.setVisible(false);
    	
    	btnCancelarAccion.setVisible(true);
    	btnAtaque1.setVisible(true);
    	btnAtaque2.setVisible(true);
    	btnAtaque3.setVisible(true);
    	btnAtaque4.setVisible(true);
    	
    	imgSeleccionAtaque.setVisible(true);
    	
    	lblPP.setText("PP: ");
    	lblPP.setVisible(true);
    	lblType.setText("Type: ");
    	lblType.setVisible(true);
	}
    
    private void desactivarBotonesMovimientos() {
    	btnCancelarAccion.setVisible(false);
    	btnAtaque1.setVisible(false);
    	btnAtaque2.setVisible(false);
    	btnAtaque3.setVisible(false);
    	btnAtaque4.setVisible(false);
    	
    	imgSeleccionAtaque.setVisible(false);
    	
    	lblPP.setVisible(false);
    	lblType.setVisible(false);
	}
    
    private void activarBotonesSeleccionAccion() {
    	imgSeleccionAccion.setVisible(true);
    	
		lblTexto.setText("Elije una accion.");
    	lblTexto.setVisible(true);
		
    	btnAtacar.setVisible(true);
		btnCambiarRival.setVisible(true);
		btnHuir.setVisible(true);
		btnAccionCambiarPokemon.setVisible(true);
	}

	private void desactivarBotonesSeleccionAccion() {
		lblTexto.setVisible(false);
		
		btnAtacar.setVisible(false);
		btnCambiarRival.setVisible(false);
		btnHuir.setVisible(false);
		btnAccionCambiarPokemon.setVisible(false);
	}

	private void activarMenuEquipo() {
		vBoxEquipo.setVisible(true);
    	
		btnCancelarAccion.setVisible(true);
		
		lblTexto.setText("Elije un pokemon.");
    	lblTexto.setVisible(true);
	}
    
	private void uiFinalCombate() {
		btnAtacar.setVisible(false);
    	btnHuir.setVisible(false);
    	btnSalir.setVisible(false);
    	btnAccionCambiarPokemon.setVisible(false);
    	btnCambiarRival.setVisible(false);
    	
    	imgPokemonRival.setVisible(false);
        imgInfoPokemonRival.setVisible(false);
        lblNombrePokemonRival.setVisible(false);
        lblNivelPokemonRival.setVisible(false);
        pbVidaPokemonRival.setVisible(false);
        lblEstadoPokemonRival.setVisible(false);
        
        imgPokemonEntrenador.setVisible(false);
        imgInfoPokemonEntrenador.setVisible(false);
        lblNombrePokemonEntrenador.setVisible(false);
        lblNivelPokemonEntrenador.setVisible(false);
        lblVidaPokemonEntrenador.setVisible(false);
        pbVidaPokemonEntrenador.setVisible(false);
        pbXpPokemonEntrenador.setVisible(false);
        lblEstadoPokemonEntrenador.setVisible(false);
	}
	
    @FXML
    void activarDesactivarSonido(MouseEvent event) {
    	
    }

    @FXML
    void huir(ActionEvent event) {
    	desactivarBotonesSeleccionAccion();
    	btnSalir.setVisible(false);
    	vBoxEquipo.setVisible(false);
    	btnCambiarPokemon.setVisible(false);
    	imgSeleccionAccion.setVisible(false);
    	
    	lblTexto.setVisible(true);
    	lblTexto.setText("Has huido con exito!");
    	
    	for (Pokemon pokemon : equipoEntrenador) {
			PokemonDAO.actualizarEstadisticasTrasCombate(con, pokemon);
			
			for (MovimientoPokemon movimiento : MovimientoPokemonDAO.buscarPorIdPokemon(con, pokemon.getIdPokemon())) {
				MovimientoPokemonDAO.actualizarPPMovimiento(con, pokemon.getIdPokemon(), movimiento.getIdMovimiento(), MovimientoDAO.buscarPorId(con, movimiento.getIdMovimiento()).getPpMax());
			}
		}
		
		EntrenadorDAO.actualizarPokedolares(con, entrenador.getIdEntrenador(), entrenador.getPokedolares() - (entrenador.getPokedolares() / 3));
		EntrenadorDAO.actualizarRivalesVencidos(con, entrenador.getIdEntrenador(), entrenador.getRivalesVencidos());
    	
    	PauseTransition pausa = new PauseTransition(Duration.seconds(2));
		pausa.setOnFinished(evento -> {
			try {
	    		SonidoController.detenerFondo("C:/ProyectoPokemon/sonidos/Entrenamiento.mp3");
	    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
	    	    Parent root = loader.load();

	    	    MenuController menuController = loader.getController();
	    	    menuController.init(entrenador, stage, loginController, null, null, null, null, null, null, null);

	    	    Scene scene = new Scene(root);
	    	    stage.setScene(scene);
	    	    stage.setTitle("Menu");
	    	    stage.centerOnScreen();
	    	    stage.show();
	    	} catch (Exception e) {
	    	    e.printStackTrace();
	    	}
		});
    	pausa.play();
    }
}
