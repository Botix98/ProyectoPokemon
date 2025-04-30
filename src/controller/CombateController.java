package controller;

import java.io.File;
import java.sql.Connection;
import java.util.LinkedList;

import dao.ConexionBD;
import dao.PokedexDAO;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Entrenador;
import model.Movimiento;
import model.Rival;
import model.Pokemon;

public class CombateController {
	
	private Entrenador entrenador;
	private Stage stage;
	private LoginController loginController;
	private MenuController menuController;
	
	private Rival rival;

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
    private Button btnRendirse;

    @FXML
    private ImageView imgCuadricula;

    @FXML
    private ImageView imgEntrenador;

    @FXML
    private ImageView imgEstadoPokemonEntrenador;

    @FXML
    private ImageView imgEstadoPokemonRival;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgInfoPokemonEntrenador;

    @FXML
    private ImageView imgInfoPokemonRival;

    @FXML
    private ImageView imgPokemonEntrenador;

    @FXML
    private ImageView imgPokemonRival;

    @FXML
    private ImageView imgRival;

    @FXML
    private ImageView imgSeleccionAccion;

    @FXML
    private ImageView imgSeleccionAtaque;

    @FXML
    private ImageView imgSexoPokemonEntrenador;

    @FXML
    private ImageView imgSexoPokemonRival;

    @FXML
    private ImageView imgSonido;

    @FXML
    private ProgressBar pbVidaPokemonRival;

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
    private ProgressBar pbXpPokemonEntrenador;

    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
    }
    
    @FXML
    public void initialize() {
        	SonidoController.reproducir("C:/ProyectoPokemon/sonidos/Combate.mp3");
    	prepararCombate();
    }
    
    private void prepararCombate() {
    	//PRUEBA
    	Movimiento[] movimientos = {new Movimiento("Mira mi cuchara", 10, "Ataque", 70, "Psiquico", 1, 100),
    			new Movimiento("Ahora me ves, ahora ya no", 15, "Especial", 0, "Psiquico", 1, 100),
    			new Movimiento("Cuidao que te tropiezas", 10, "Ataque", 125, "Psiquico", 1, 80),
    			null};

    	Pokemon[] equipo = {new Pokemon("Mewtwo", 416, 350, 447, 306, 306, 1, 100, null, movimientos), null, null, null, null, null};
    	
    	rival = new Rival("Luisre", equipo);
    	
    	lblTexto.setText(rival.getNombre() + " te ha retado a un combate pokemon!");
    	
    	PauseTransition primeraPausa = new PauseTransition(Duration.seconds(1));
    	primeraPausa.setOnFinished(event -> {
            lblTexto.setText(rival.getNombre() + " saca a " + rival.getPokemon(0).getNombre());
        });
    	
    	PauseTransition segundaPausa = new PauseTransition(Duration.seconds(1));
    	segundaPausa.setOnFinished(event -> {
            imgRival.setVisible(false);
            imgPokemonRival.setVisible(true);
            imgInfoPokemonRival.setVisible(true);
            lblNombrePokemonRival.setText(rival.getPokemon(0).getNombre());
            lblNombrePokemonRival.setVisible(true);
            lblNivelPokemonRival.setText(Integer.toString(rival.getPokemon(0).getNivel()));
            lblNivelPokemonRival.setVisible(true);
            pbVidaPokemonRival.setVisible(true);
        });
    	
    	PauseTransition terceraPausa = new PauseTransition(Duration.seconds(1));
    	terceraPausa.setOnFinished(event -> {
            lblTexto.setText(entrenador.getUsuario() + " saca a " + entrenador.getPokemon(0).getNombre());
        });
    	
    	PauseTransition cuartaPausa = new PauseTransition(Duration.seconds(1));
    	cuartaPausa.setOnFinished(event -> {
            imgEntrenador.setVisible(false);
            imgPokemonEntrenador.setVisible(true);
            imgInfoPokemonEntrenador.setVisible(true);
            lblVidaPokemonEntrenador.setText(entrenador.getPokemon(0).getVitalidadActual() + "/" + entrenador.getPokemon(0).getVitalidadMax());
            lblVidaPokemonEntrenador.setVisible(true);
            lblNombrePokemonEntrenador.setText(entrenador.getPokemon(0).getNombre());
            lblNombrePokemonEntrenador.setVisible(true);
            lblNivelPokemonEntrenador.setText(Integer.toString(entrenador.getPokemon(0).getNivel()));
            lblNivelPokemonEntrenador.setVisible(true);
            pbVidaPokemonEntrenador.setVisible(true);
            pbXpPokemonEntrenador.setVisible(true);
            lblVidaPokemonEntrenador.setText(entrenador.getPokemon(0).getVitalidadActual() + "/" + entrenador.getPokemon(0).getVitalidadMax());
        });
    	
    	PauseTransition quintaPausa = new PauseTransition(Duration.seconds(1));
    	quintaPausa.setOnFinished(event -> {
            lblTexto.setText("Elige una opci�n");
            imgSeleccionAccion.setVisible(true);
            btnAtacar.setVisible(true);
            btnRendirse.setVisible(true);
        });
    	
    	SequentialTransition secuencia = new SequentialTransition(primeraPausa, segundaPausa, terceraPausa, cuartaPausa, quintaPausa);
        secuencia.play();
    }
    
    @FXML
    void atacar(ActionEvent event) {
    	activarBotonesAtaque();
    }
    
    @FXML
    void rendirse(ActionEvent event) {
    	SonidoController.detener("C:/ProyectoPokemon/sonidos/Combate.mp3");
    	desactivarBotonesAtaque();
    	btnAtacar.setVisible(false);
    	btnRendirse.setVisible(false);
    	
    	imgPokemonRival.setVisible(false);
    	imgRival.setVisible(true);
        imgInfoPokemonRival.setVisible(false);
        lblNombrePokemonRival.setVisible(false);
        lblNivelPokemonRival.setVisible(false);
        pbVidaPokemonRival.setVisible(false);
        
        imgPokemonEntrenador.setVisible(false);
    	imgEntrenador.setVisible(true);
        imgInfoPokemonEntrenador.setVisible(false);
        lblNombrePokemonEntrenador.setVisible(false);
        lblNivelPokemonEntrenador.setVisible(false);
        pbVidaPokemonEntrenador.setVisible(false);
        pbXpPokemonEntrenador.setVisible(false);
    	
    	lblTexto.setText("Ha ha ha ha ha! C�mo te atreves a huir! Cobarde!! No te mereces aprobar el Proyecto Pokemon!");
    	
    	PauseTransition pausa = new PauseTransition(Duration.seconds(5));
    	pausa.setOnFinished(evento -> {
    		try {
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
        });
    	pausa.play();
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
    void entrarAtaque1(MouseEvent event) {
    	lblPP.setText("PP: " + entrenador.getPokemon(0).getMovimiento(0).getPpActuales() + "/" + entrenador.getPokemon(0).getMovimiento(1).getPpMax());
    	lblType.setText("Type: " + entrenador.getPokemon(0).getMovimiento(0).getTipo());
    }

    @FXML
    void entrarAtaque2(MouseEvent event) {
    	lblPP.setText("PP: " + entrenador.getPokemon(0).getMovimiento(1).getPpActuales() + "/" + entrenador.getPokemon(0).getMovimiento(1).getPpMax());
    	lblType.setText("Type: " + entrenador.getPokemon(0).getMovimiento(1).getTipo());
    }

    @FXML
    void entrarAtaque3(MouseEvent event) {
    	lblPP.setText("PP: " + entrenador.getPokemon(0).getMovimiento(2).getPpActuales() + "/" + entrenador.getPokemon(0).getMovimiento(2).getPpMax());
    	lblType.setText("Type: " + entrenador.getPokemon(0).getMovimiento(2).getTipo());
    }

    @FXML
    void entrarAtaque4(MouseEvent event) {
    	lblPP.setText("PP: " + entrenador.getPokemon(0).getMovimiento(3).getPpActuales() + "/" + entrenador.getPokemon(0).getMovimiento(3).getPpMax());
    	lblType.setText("Type: " + entrenador.getPokemon(0).getMovimiento(3).getTipo());
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
    
    private void defaultPPType() {
    	lblPP.setText("PP: ");
    	lblType.setText("Type: ");
    }
    
    @FXML
    void ataque1(ActionEvent event) {
    	if (entrenador.getPokemon(0).getMovimiento(0).getPpActuales() > 0) {
    		desactivarBotonesAtaque();
    		realizarAtaque(0, 0);
    		
    		
    	}
    	else {
    		sinPPs(entrenador.getPokemon(0).getMovimiento(0).getNombre());
    	}
    }

	@FXML
    void ataque2(ActionEvent event) {
    	LinkedList<Pokemon> pokedex = new LinkedList<Pokemon>();
    	Connection con = ConexionBD.getConnection();
    	
    	pokedex = PokedexDAO.cargarPokedex(con);
    	
    	for (Pokemon pokemon : pokedex) {
			System.out.println(pokemon.toString());
		}
    }

    @FXML
    void ataque3(ActionEvent event) {

    }

    @FXML
    void ataque4(ActionEvent event) {

    }
    
    private void desactivarBotonesAtaque() {
    	lblTexto.setVisible(true);
    	imgSeleccionAtaque.setVisible(false);
    	imgSeleccionAccion.setVisible(false);
    	lblPP.setVisible(false);
    	lblType.setVisible(false);
    	btnAtaque1.setVisible(false);
    	btnAtaque2.setVisible(false);
    	btnAtaque3.setVisible(false);
    	btnAtaque4.setVisible(false);
    }
    
    private void activarBotonesAtaque() {
    	lblTexto.setVisible(false);
    	imgSeleccionAtaque.setVisible(true);
    	btnAtacar.setVisible(false);
    	btnRendirse.setVisible(false);
    	lblPP.setText("PP: ");
    	lblPP.setVisible(true);
    	lblType.setText("Type: ");
    	lblType.setVisible(true);
    	btnAtaque1.setText(entrenador.getPokemon(0).getMovimiento(0).getNombre());
    	btnAtaque1.setVisible(true);
    	btnAtaque2.setText(entrenador.getPokemon(0).getMovimiento(1).getNombre());
    	btnAtaque2.setVisible(true);
    	btnAtaque3.setText(entrenador.getPokemon(0).getMovimiento(2).getNombre());
    	btnAtaque3.setVisible(true);
    	btnAtaque4.setText(entrenador.getPokemon(0).getMovimiento(3).getNombre());
    	btnAtaque4.setVisible(true);
    }
    
    private void sinPPs(String movimiento) {
    	desactivarBotonesAtaque();
		
    	lblTexto.setText("El ataque " + movimiento + " no tiene PPs");
    	
    	PauseTransition pausa = new PauseTransition(Duration.seconds(1));
    	pausa.setOnFinished(evento -> {
            activarBotonesAtaque();
        });
    	pausa.play();
    }
    
    private void realizarAtaque(int numPok, int numMov) {
    	entrenador.getPokemon(numPok).getMovimiento(numMov).setPpActuales(entrenador.getPokemon(numPok).getMovimiento(numMov).getPpActuales() - 1);
    	
    	//Seleccionar ataque del pokemon rival
    	Movimiento movRival = seleccionarAtaqueRival();
    	
    	//Comprobar las velocidades de los pokemon. Ataca el mas rapido
    	if (entrenador.getPokemon(numPok).getVelocidad() >= rival.getPokemon(0).getVelocidad()) {
    		ataqueEntrenador(numPok, numMov);
    		
    		if (rival.getPokemon(0).getVitalidadActual() == 0) {
    			//El pokemon rival sa matao
    			//Hacer tremendo break con un return 0
    		}
    		else {
    			ataqueRival(numPok, numMov);
    			
    			if (entrenador.getPokemon(numPok).getVitalidadActual() == 0) {
    				//El pokemon del enrtenador sa matao
        			//Hacer tremendo break con un return 0
    			}
    		}
    	}
    	else {
    		ataqueRival(numPok, numMov);
    		
    		if (entrenador.getPokemon(numPok).getVitalidadActual() == 0) {
    			//El pokemon del entrenador sa matao
    			//Hacer tremendo break con un return 0
    		}
    		else {
    			ataqueEntrenador(numPok, numMov);
    			
    			if (rival.getPokemon(0).getVitalidadActual() == 0) {
    				//El pokemon rival sa matao
        			//Hacer tremendo break con un return 0
    			}
    		}
    	}
    	
    	
    	
    	
    	//Se comprueba si el ataque golpea y se le quita la vida o aplica el efecto al pokemon objetivo
    	//Comprobar si el pokemon objetivo sigue vivo. En ese caso realiza su ataque
    	//Realiza el ataque el otro pokmemon.
    	
    	//Si alguno de los dos muere hacer un break
    	//Si no tiene m�s pokemon terminar batalla
    	//Si tiene mas pokemon sacar otro
    	
		lblTexto.setText("Has golpeado al enemigo!");
		
		PauseTransition pausa = new PauseTransition(Duration.seconds(1));
    	pausa.setOnFinished(evento -> {
            activarBotonesAtaque();
        });
    	pausa.play();
	}

	private Movimiento seleccionarAtaqueRival() {
		int n = (int) (Math.random() * 4) + 1;
    	
    	Movimiento movimiento = null;
    	
    	switch (n) {
			case 1:
				movimiento = rival.getPokemon(0).getMovimiento(0);
				break;
			case 2:
				movimiento = rival.getPokemon(0).getMovimiento(1);
				break;
			case 3:
				movimiento = rival.getPokemon(0).getMovimiento(2);
				break;
			case 4:
				movimiento = rival.getPokemon(0).getMovimiento(3);
				break;
		}
    	
    	return movimiento;
	}
	
	private void ataqueEntrenador(int numPok, int numMov) {
		int num = (int) (Math.random() * 100) + 1;
		
		if (entrenador.getPokemon(numPok).getMovimiento(numMov).getProbabilidad() >= num) {
			rival.getPokemon(0).setVitalidadActual(rival.getPokemon(0).getVitalidadActual() - entrenador.getPokemon(numPok).getMovimiento(numMov).getPotencia());
			if (rival.getPokemon(0).getVitalidadActual() < 0) {
				rival.getPokemon(0).setVitalidadActual(0);
			}
		}
	}
	
	private void ataqueRival(int numPok, int numMov) {
		int num = (int) (Math.random() * 100) + 1;
		
		if (rival.getPokemon(0).getMovimiento(0).getProbabilidad() >= num) {
			entrenador.getPokemon(numPok).setVitalidadActual(entrenador.getPokemon(numPok).getVitalidadActual() - rival.getPokemon(0).getMovimiento(0).getPotencia());
			if (entrenador.getPokemon(numPok).getVitalidadActual() < 0) {
				entrenador.getPokemon(numPok).setVitalidadActual(0);
			}
			
			System.out.println(entrenador.getPokemon(numPok).getVitalidadActual() + "/" + entrenador.getPokemon(numPok).getVitalidadMax());
		}
	}
}
