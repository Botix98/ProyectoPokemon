package controller;

import java.io.File;
import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedList;

import dao.ConexionBD;
import dao.MovimientoPokemonDAO;
import dao.PokedexDAO;
import dao.PokemonDAO;
import dao.RivalDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Entrenador;
import model.Pokedex;
import model.Pokemon;
import model.Rival;
import model.Rivales;

public class SeleccionarRivalController {

	private Entrenador entrenador;
	private LoginController loginController;
	private MenuController menuController;
	private Stage stage;
	private Rivales rivalEnum;
	private LinkedList<Pokedex> pokedex;
	private Rival rival;
	private String url;
	
	private Connection con;
	
    @FXML
    private Button btnSalir;

    @FXML
    private ImageView imgRival1;

    @FXML
    private ImageView imgRival2;

    @FXML
    private ImageView imgRival3;

    @FXML
    private ImageView imgRival4;

    @FXML
    private ImageView imgRival5;

    @FXML
    private ImageView imgRival6;

    @FXML
    private ImageView imgSonido;

    @FXML
    private Label lblNivelRecomendado;

    @FXML
    private Label lblNombre;
    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        
        rivalEnum = Rivales.buscarPorId((int) (Math.random() * 49) + 1);
        imgRival1.setImage(new Image(new File(rivalEnum.getRuta()).toURI().toString()));
        url = rivalEnum.getRuta();
        
        con = ConexionBD.getConnection();
        pokedex = PokedexDAO.cargarPokedexCompleta(con);
    }

    @FXML
    void activarDesactivarSonido(MouseEvent event) {

    }

    @FXML
    void entrarRival1(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad: Muy facil"); //10 niveles por debajo del tuyo
    	lblNombre.setText("Rival: " + rivalEnum.getNombre());
    }

    @FXML
    void entrarRival2(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad: Facil"); //5 niveles por debajo del tuyo
    	lblNombre.setText("Rival: " + rivalEnum.getNombre());
    }

    @FXML
    void entrarRival3(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad: Medio"); //de tu nivel
    	lblNombre.setText("Rival: " + rivalEnum.getNombre());
    }

    @FXML
    void entrarRival4(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad: Dificil"); //5 niveles por encima del tuyo
    	lblNombre.setText("Rival: " + rivalEnum.getNombre());
    }

    @FXML
    void entrarRival5(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad: Muy dificil"); //10 niveles por encima del tuyo
    	lblNombre.setText("Rival: " + rivalEnum.getNombre());
    }

    @FXML
    void entrarRival6(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad: Demencial"); //15 niveles por encima del tuyo
    	lblNombre.setText("Rival: " + rivalEnum.getNombre());
    }

    @FXML
    void salir(ActionEvent event) {
    	try {
    		SonidoController.detenerFondo(null);
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

    @FXML
    void salirRival1(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad:");
    	lblNombre.setText("Rival: ");
    }

    @FXML
    void salirRival2(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad:");
    	lblNombre.setText("Rival: ");
    }

    @FXML
    void salirRival3(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad:");
    	lblNombre.setText("Rival: ");
    }

    @FXML
    void salirRival4(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad:");
    	lblNombre.setText("Rival: ");
    }

    @FXML
    void salirRival5(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad:");
    	lblNombre.setText("Rival: ");
    }

    @FXML
    void salirRival6(MouseEvent event) {
    	lblNivelRecomendado.setText("Dificultad:");
    	lblNombre.setText("Rival: ");
    }
    
    @FXML
    void seleccionarRival1(MouseEvent event) {
    	rival = new Rival(rivalEnum.getNombre(), 0, "Bien hecho. Has conseguido vencerme", "Necesitas entrenar mas. Pasate por el entrenamiento para mejorar a tus pojemon", "Hay que saber cuando rendirse");
        irCombate(generarEquipoRival());
    }

    @FXML
    void seleccionarRival2(MouseEvent event) {
    	rival = RivalDAO.cargarRival(con, 1);
    	//PILLAR LA URL DEL RIVAL
    	irCombate(null);
    }

    @FXML
    void seleccionarRival3(MouseEvent event) {
    	rival = RivalDAO.cargarRival(con, 2);
    	//PILLAR LA URL DEL RIVAL
    	irCombate(null);
    }

    @FXML
    void seleccionarRival4(MouseEvent event) {
    	rival = RivalDAO.cargarRival(con, 3);
    	//PILLAR LA URL DEL RIVAL
    	irCombate(null);
    }

    @FXML
    void seleccionarRival5(MouseEvent event) {
    	rival = RivalDAO.cargarRival(con, 4);
    	//PILLAR LA URL DEL RIVAL
    	irCombate(null);
    }

    @FXML
    void seleccionarRival6(MouseEvent event) {
    	rival = RivalDAO.cargarRival(con, 5);
    	//PILLAR LA URL DEL RIVAL
    	irCombate(null);
    }

    public LinkedList<Pokemon> generarEquipoRival() {
    	LinkedList<Pokemon> equipoRival = new LinkedList<Pokemon>();
    	
    	int numPokRival = (int) (Math.random() * 5) + 2;
    	int nivelMedio = 0;
    	int numPokemon = 0;
    	
    	LinkedList<Pokemon> equipoEntrenador = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1);
    	
    	for (Pokemon pokemon : equipoEntrenador) {
			nivelMedio += pokemon.getNivel();
			numPokemon++;
		}
    	
    	nivelMedio = nivelMedio / numPokemon;
    	
    	for (int i = 0; i < numPokRival; i++) {
    		int numPokedex = (int) (Math.random() * 151) + 1;
    		String mote = pokedex.get(numPokedex - 1).getNomPokemon();
    		int nivel = nivelMedio - (int) (numPokRival * 1.7) + 2 * i;
    		int vitalidadMax = 10 + (int)((double)(nivel) / 100 * (pokedex.get(numPokedex - 1).getVitalidad() * 2) + (int) (Math.random() * 32)) + nivel;
    		int ataque = 5 + (int)((double)(nivel) / 100 * ((pokedex.get(numPokedex - 1).getAtaque() * 2) + (int) (Math.random() * 32)));
    		int ataqueEsp = 5 + (int)((double)(nivel) / 100 * ((pokedex.get(numPokedex - 1).getAtEspecial() * 2) + (int) (Math.random() * 32)));
    		int defensa = 5 + (int)((double)(nivel) / 100 * ((pokedex.get(numPokedex - 1).getDefensa() * 2) + (int) (Math.random() * 32)));
    		int defensaEsp = 5 + (int)((double)(nivel) / 100 * ((pokedex.get(numPokedex - 1).getDefEspecial() * 2) + (int) (Math.random() * 32)));
    		int velocidad = 5 + (int)((double)(nivel) / 100 * ((pokedex.get(numPokedex - 1).getVelocidad() * 2) + (int) (Math.random() * 32)));
    		
    		Pokemon pokemon = new Pokemon(-i, 0, 0, "RIVAL", numPokedex, mote, vitalidadMax, vitalidadMax, ataque, ataqueEsp, defensa, defensaEsp, velocidad, nivel, 0, "F", "SIN_ESTADO", 1, 0);
    		equipoRival.add(pokemon);
    		System.out.println(pokemon.toString());
    	}
    	return equipoRival;
    }
    
    private void irCombate(LinkedList<Pokemon> equipoRival) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/combate.fxml"));
            Parent root = loader.load();

            CombateController combateController = loader.getController();

            // Inicializamos la pantalla de Combate
            combateController.init(this.entrenador, this.stage, this.loginController, this.menuController, equipoRival, rival, url); 

            Scene scene = new Scene(root);
            stage.setScene(scene);
	        stage.setTitle("Combate");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
