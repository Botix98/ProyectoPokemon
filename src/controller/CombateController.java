package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Set;

import dao.ConexionBD;
import dao.EntrenadorDAO;
import dao.MovimientoDAO;
import dao.MovimientoPokemonDAO;
import dao.PokedexDAO;
import dao.PokemonDAO;
import dao.RivalDAO;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Entrenador;
import model.Movimiento;
import model.MovimientoPokemon;
import model.Pokedex;
import model.Rival;
import model.TipoEstados;
import model.TipoPokemon;
import model.Turno;
import model.Pokemon;
import model.TiposMejora;

public class CombateController {
	
	private Entrenador entrenador;
	private Stage stage;
	private LoginController loginController;
	private MenuController menuController;
	
	private int pokActEntr;
	private int pokActRival;
	private int numPokVivosEntr;
	private int numPokVivosRival;
	
	private int turnosAnillo;
	
	private int indicePokSeleccionado;
	
	private Rival rival;
	
	private Connection con;
	
	private LinkedList<Turno> registroCombate;
	private LinkedList<Pokemon> equipoEntrenador;
	private LinkedList<Pokemon> equipoRival;
	private LinkedList<MovimientoPokemon> listaMovPokEntr;
	private LinkedList<Movimiento> listaMovPokEntrAUX;
	private LinkedList<MovimientoPokemon> listaMovPokRival;
	private LinkedList<Movimiento> listaMovimientos;
	private LinkedList<Movimiento> listaMovPosiblesEntr;
	private LinkedList<Pokedex> pokedex;
	
	private Movimiento movimientoNuevo;
	private MovimientoPokemon movimientoViejo;
	
	private int experienciaLevelUp;
	
	private SimpleBooleanProperty activo;
	
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
    private Button btnCancelarAccion;

    @FXML
    private Button btnCancelarCambio;

    @FXML
    private Button btnConfirmarCambio;

    @FXML
    private Button btnConfirmarCambioMov;

    @FXML
    private Button btnMantenerPokemon;

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
    private Button btnRendirse;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img10;

    @FXML
    private ImageView img11;

    @FXML
    private ImageView img12;

    @FXML
    private ImageView img13;

    @FXML
    private ImageView img14;

    @FXML
    private ImageView img15;

    @FXML
    private ImageView img16;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;

    @FXML
    private ImageView img7;

    @FXML
    private ImageView img8;

    @FXML
    private ImageView img9;

    @FXML
    private ImageView imgCancelar;
    
    @FXML
    private ImageView imgAtaque;

    @FXML
    private ImageView imgAtaque1;

    @FXML
    private ImageView imgAtaque2;

    @FXML
    private ImageView imgAtaque3;

    @FXML
    private ImageView imgAtaque4;

    @FXML
    private ImageView imgCambiarPokemon;

    @FXML
    private ImageView imgCambioDesactivado;

    @FXML
    private ImageView imgCuadricula;

    @FXML
    private ImageView imgEntrenador;

    @FXML
    private ImageView imgEstadoEquipo1;

    @FXML
    private ImageView imgEstadoEquipo2;

    @FXML
    private ImageView imgEstadoEquipo3;

    @FXML
    private ImageView imgEstadoEquipo4;

    @FXML
    private ImageView imgEstadoEquipo5;

    @FXML
    private ImageView imgEstadoEquipo6;

    @FXML
    private ImageView imgEstadoPokemonEntrenador;

    @FXML
    private ImageView imgEstadoPokemonRival;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgFondoEquipo1;

    @FXML
    private ImageView imgFondoEquipo111;

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
    private ImageView imgConfirmar;
    
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
    private ImageView imgRendirse;

    @FXML
    private ImageView imgRival;

    @FXML
    private ImageView imgSeleccionAccion;

    @FXML
    private ImageView imgSeleccionAtaque;

    @FXML
    private ImageView imgSonido;

    @FXML
    private Label lblAtaque;

    @FXML
    private Label lblAtaque1;

    @FXML
    private Label lblAtaque2;

    @FXML
    private Label lblAtaque3;

    @FXML
    private Label lblAtaque4;

    @FXML
    private Label lblAtaqueEsp;

    @FXML
    private Label lblDefensa;

    @FXML
    private Label lblDefensaEsp;

    @FXML
    private Label lblEfectoNuevo;

    @FXML
    private Label lblEfectoSelec;

    @FXML
    private Label lblHP;

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
    private Label lblNombrePokemon1;

    @FXML
    private Label lblNombrePokemon2;

    @FXML
    private Label lblNombrePokemon3;

    @FXML
    private Label lblNombrePokemon4;

    @FXML
    private Label lblNombrePokemon5;

    @FXML
    private Label lblNombrePokemon6;

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
    private Label lblVelocidad;

    @FXML
    private Label lblVidaPokemon1;

    @FXML
    private Label lblVidaPokemon2;

    @FXML
    private Label lblVidaPokemon3;

    @FXML
    private Label lblVidaPokemon4;

    @FXML
    private Label lblVidaPokemon5;

    @FXML
    private Label lblVidaPokemon6;

    @FXML
    private Label lblVidaPokemonEntrenador;

    @FXML
    private Label lblVitalidad;

    @FXML
    private ProgressBar pbVidaPokemonEntrenador;

    @FXML
    private ProgressBar pbVidaPokemonRival;

    @FXML
    private ProgressBar pbXpPokemonEntrenador;

    @FXML
    private VBox vBoxEquipo;

    @FXML
    private VBox vBoxEstadisticas;

    @FXML
    private VBox vBoxMovimientos;
    
    @FXML
    public void initialize() {
    	con = ConexionBD.getConnection();
    }

    //Inicializamos el controller
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController, LinkedList<Pokemon> equipoRival, Rival rival, String url) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        this.rival = rival;
        registroCombate = new LinkedList<Turno>();
        pokedex = PokedexDAO.cargarPokedexCompleta(con);
        listaMovimientos = MovimientoDAO.cargarTodos(con);
        pokActEntr = -1;
    	pokActRival = 0;
        equipoEntrenador = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1);
        turnosAnillo = 0;
        
        //Si es null significa que el rival no es aleatorio y por lo tanto sacamos el equipo de la base de datos
        if (equipoRival != null) {
        	this.equipoRival = equipoRival;
        } else {
        	this.equipoRival = PokemonDAO.cargarPokemonEquipoRival(con, rival.getIdRival());
        }
        
        //Llamamos al metodo para cargar al publico
        publico();
        //Calculamos el numero de pokemon vivos
        calcularPokemonVivos();
        //Cargamos la lista de movimientos del pokemon activo del entrenador
        listaMovPokEntr = MovimientoPokemonDAO.buscarPorIdPokemon(con, equipoEntrenador.get(pokActEntr).getIdPokemon());
        //Hacemos una lista tipo Movimiento para tener a mano informacion importante de los movimientos del entrenador
        listaMovPokEntrAUX = new LinkedList<Movimiento>();
        for (MovimientoPokemon mov : listaMovPokEntr) {
			listaMovPokEntrAUX.add(MovimientoDAO.buscarPorId(con, mov.getIdMovimiento()));
		}
        //Cargamos la imagen del rival
        imgRival.setImage(new Image(new File(url).toURI().toString()));
        //Iniciamos los movimientos del rival
        inicarMovPokRival();
        //Metodo para iniciar el combate
        prepararCombate();
    }

    //En funcion de los rivales que hayamos vencido, sale mas o menos publico
	private void publico() {
		switch (entrenador.getRivalesVencidos()) {
			case 6:
				img1.setVisible(true);
				img5.setVisible(true);
			case 5:
				img11.setVisible(true);
				img16.setVisible(true);
			case 4:
				img3.setVisible(true);
				img12.setVisible(true);
			case 3:
				img14.setVisible(true);
				img8.setVisible(true);
			case 2:
				img2.setVisible(true);
				img9.setVisible(true);
			case 1:
				img6.setVisible(true);
				img10.setVisible(true);
			case 0:
				img4.setVisible(true);
				img13.setVisible(true);
			case -1:
				img7.setVisible(true);
				img15.setVisible(true);
				break;
		}
	}

	private void inicarMovPokRival() {
		listaMovPokRival = new LinkedList<MovimientoPokemon>();
		//Si el rival no es aleatorio, sacamos los movimientos del rival de la base de datos
		if (rival.getIdRival() > 0) {
        	listaMovPokRival = MovimientoPokemonDAO.buscarPorIdPokemon(con, equipoRival.get(pokActRival).getIdPokemon());
        } else {
        	//Si el rival es aleatorio generamos los movimientos aleatoriamente entre los dos tipos del pokemon del rival y normal
        	String[] tipos = pokedex.get(equipoRival.get(pokActRival).getNumPokedex() - 1).getTipos();
        	LinkedList<Movimiento> listaMovPosibles = MovimientoDAO.buscarPorTipoMov(con, tipos[0]);
        	listaMovPosibles.addAll(MovimientoDAO.buscarPorTipoMov(con, "NORMAL"));
        	
        	if (tipos[1] != null) {
        		listaMovPosibles.addAll(MovimientoDAO.buscarPorTipoMov(con, tipos[1]));
        	}
        	
        	int numMovsRival = (int) (Math.random() * 4) + 1;
        	
        	for (int i = 0; i < numMovsRival; i++) {
    			Movimiento mov;
    			do {
    				mov = listaMovPosibles.get((int) (Math.random() * listaMovPosibles.size())); //Habra que ver si esto esta bien
    			} while (!comprobarMovimiento(mov));
    			listaMovPokRival.add(new MovimientoPokemon(equipoRival.get(pokActRival).getIdPokemon(), mov.getIdMovimiento(), mov.getPpMax()));
    		}
        }
	}
	
	//Comprobamos si el movimiento ya lo tiene el pokemon
	private boolean comprobarMovimiento(Movimiento mov) {
		for (int i = 0; i < listaMovPokRival.size(); i++) {
			if (listaMovPokRival.get(i).getIdMovimiento() == mov.getIdMovimiento()) {
				return false;
			}
		}
		return true;
	}
    
	//Vamos mostrando la informacion de los pokemon poco a poco
    private void prepararCombate() {
    	pbVidaPokemonEntrenador.setStyle("-fx-accent: #00a135;");
    	pbVidaPokemonRival.setStyle("-fx-accent: #00a135;");
    	
    	lblTexto.setText(rival.getNombre() + " te ha retado a un combate pokemon!");
    	
    	PauseTransition primeraPausa = new PauseTransition(Duration.seconds(1));
    	primeraPausa.setOnFinished(event -> {
            lblTexto.setText(rival.getNombre() + " saca a " + equipoRival.get(0).getMote());
        });
    	
    	PauseTransition segundaPausa = new PauseTransition(Duration.seconds(1));
    	segundaPausa.setOnFinished(event -> {
    		String ruta = "C:/ProyectoPokemon/sonidos/Pokemon/" + equipoRival.get(0).getNumPokedex() + ".wav";
    		SonidoController.reproducirEfecto(ruta, null);
    		
            imgRival.setVisible(false);
            imgPokemonRival.setImage(new Image(new File("./img/Pokemon/Front/" + equipoRival.get(pokActRival).getNumPokedex() + ".png").toURI().toString()));
            imgPokemonRival.setVisible(true);
            imgInfoPokemonRival.setVisible(true);
            lblNombrePokemonRival.setText(equipoRival.get(0).getMote());
            lblNombrePokemonRival.setVisible(true);
            lblNivelPokemonRival.setText(Integer.toString(equipoRival.get(0).getNivel()));
            lblNivelPokemonRival.setVisible(true);
            pbVidaPokemonRival.setVisible(true);
        });
    	
    	PauseTransition terceraPausa = new PauseTransition(Duration.seconds(1));
    	terceraPausa.setOnFinished(event -> {
            lblTexto.setText(entrenador.getUsuario() + " saca a " + equipoEntrenador.get(pokActEntr).getMote());
        });
    	
    	PauseTransition cuartaPausa = new PauseTransition(Duration.seconds(1));
    	cuartaPausa.setOnFinished(event -> {
    		String ruta = "C:/ProyectoPokemon/sonidos/Pokemon/" + equipoEntrenador.get(pokActEntr).getNumPokedex() + ".wav";
    		SonidoController.reproducirEfecto(ruta, null);
    		
            imgEntrenador.setVisible(false);
            imgPokemonEntrenador.setImage(new Image(new File("./img/Pokemon/Back/" + equipoEntrenador.get(pokActEntr).getNumPokedex() + ".png").toURI().toString()));
            imgPokemonEntrenador.setVisible(true);
            imgInfoPokemonEntrenador.setVisible(true);
            lblHP.setVisible(true);
            lblVidaPokemonEntrenador.setText(equipoEntrenador.get(pokActEntr).getVitalidadAct() + "/" + equipoEntrenador.get(pokActEntr).getVitalidadMax());
            lblVidaPokemonEntrenador.setVisible(true);
            lblNombrePokemonEntrenador.setText(equipoEntrenador.get(pokActEntr).getMote());
            lblNombrePokemonEntrenador.setVisible(true);
            lblNivelPokemonEntrenador.setText(Integer.toString(equipoEntrenador.get(pokActEntr).getNivel()));
            lblNivelPokemonEntrenador.setVisible(true);
            
            if (equipoEntrenador.get(pokActEntr).getEstado().equals(TipoEstados.valueOf("SIN_ESTADO"))) {
            	imgEstadoPokemonEntrenador.setVisible(false);
            	//lblEstadoPokemonEntrenador.setText("");
            	System.out.println("Actualizado lblEntrenador");
            } else {
            	imgEstadoPokemonEntrenador.setVisible(true);
            	imgEstadoPokemonEntrenador.setImage(new Image(new File("C:/ProyectoPokemon/img/Estados/" + equipoEntrenador.get(pokActEntr).getEstado().getPseudonimo() + ".png").toURI().toString()));
            	//lblEstadoPokemonEntrenador.setText(equipoEntrenador.get(pokActEntr).getEstado().getPseudonimo());
            }
            
            pbVidaPokemonEntrenador.setProgress(((double) (equipoEntrenador.get(pokActEntr).getVitalidadAct())) / equipoEntrenador.get(pokActEntr).getVitalidadMax());
            pbVidaPokemonEntrenador.setStyle("-fx-accent: #00a135;");
    		
    		if (pbVidaPokemonEntrenador.getProgress() < 0.25){
    			pbVidaPokemonEntrenador.setStyle("-fx-accent: red;");
	        }
	        else if (pbVidaPokemonEntrenador.getProgress() < 0.5){
	        	pbVidaPokemonEntrenador.setStyle("-fx-accent: yellow;");
	        }
            pbVidaPokemonEntrenador.setVisible(true);
            pbXpPokemonEntrenador.setProgress((double) (equipoEntrenador.get(pokActEntr).getExperiencia()) / (10 * equipoEntrenador.get(pokActEntr).getNivel()));
            pbXpPokemonEntrenador.setVisible(true);
            lblVidaPokemonEntrenador.setText(equipoEntrenador.get(pokActEntr).getVitalidadAct() + "/" + equipoEntrenador.get(pokActEntr).getVitalidadMax());
        });
    	
    	PauseTransition quintaPausa = new PauseTransition(Duration.seconds(1));
    	quintaPausa.setOnFinished(event -> {
            lblTexto.setText("Elige una opcion");
            imgSeleccionAccion.setVisible(true);
            btnAtacar.setVisible(true);
            imgAtaque.setVisible(true);
            btnRendirse.setVisible(true);
            imgRendirse.setVisible(true);
            btnAccionCambiarPokemon.setVisible(true);
            imgCambiarPokemon.setVisible(true);
        });
    	
    	SequentialTransition secuencia = new SequentialTransition(primeraPausa, segundaPausa, terceraPausa, cuartaPausa, quintaPausa);
        secuencia.play();
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
    	
    	for (int i = 0; i < equipoRival.size(); i++) {
    		if (equipoRival.get(i) != null) {
    			if (equipoRival.get(i).getVitalidadAct() > 0) {
        			numPokVivosRival++;
        		}
    		}
    	}
	}
    
    @FXML
    void atacar(ActionEvent event) {
    	btnAtaque4.setVisible(false);
		btnAtaque3.setVisible(false);
		btnAtaque2.setVisible(false);
		btnAtaque1.setVisible(false);
		
    	activarBotonesAtaque();
    }
    
    @FXML
    void rendirse(ActionEvent event) {
    	desactivarBotonesAtaque();
    	uiFinalCombate();
    	lblTexto.setText("Has perdido " + entrenador.getPokedolares() / 3 + " pokedolares");
    	entrenador.setPokedolares(entrenador.getPokedolares() - (entrenador.getPokedolares() / 3));
    	
    	PauseTransition pausa = new PauseTransition(Duration.seconds(1));
    	pausa.setOnFinished(evento -> {
    		lblTexto.setText(rival.getFraseRendicion());
    		volverAlMenu();
    	});
    	pausa.play();
    }

    //Guardamos los cambios en la base de datos y vamos a la vista correspondiente dependiendo del final
	private void volverAlMenu() {
		if (numPokVivosRival == 0) {
			SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/GanarCombate.mp3");
		} else {
			SonidoController.reproducirFondo("C:/ProyectoPokemon/sonidos/PerderCombate.mp3");
		}
		
		for (Pokemon pokemon : equipoEntrenador) {
			PokemonDAO.actualizarEstadisticasTrasCombate(con, pokemon);
			
			for (MovimientoPokemon movimiento : MovimientoPokemonDAO.buscarPorIdPokemon(con, pokemon.getIdPokemon())) {
				MovimientoPokemonDAO.actualizarPPMovimiento(con, pokemon.getIdPokemon(), movimiento.getIdMovimiento(), MovimientoDAO.buscarPorId(con, movimiento.getIdMovimiento()).getPpMax());
			}
		}
		
		EntrenadorDAO.actualizarPokedolares(con, entrenador.getIdEntrenador(), entrenador.getPokedolares() - (entrenador.getPokedolares() / 3));
		EntrenadorDAO.actualizarRivalesVencidos(con, entrenador.getIdEntrenador(), entrenador.getRivalesVencidos());
		
		logCombate();
		
		PauseTransition pausa = new PauseTransition(Duration.seconds(10));
    	pausa.setOnFinished(evento -> {
    		if (numPokVivosEntr == 0) {
    			try {
            	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CentroPokemon.fxml"));
            	    Parent root = loader.load();

            	    CentroPokemonController centroPokemonController = loader.getController();
            	    centroPokemonController.init(entrenador, stage, loginController, menuController, null, this);

            	    Scene scene = new Scene(root);
            	    stage.setScene(scene);
            	    stage.setTitle("Centro Pokemon");
            	    stage.centerOnScreen();
            	    stage.show();
                    modificacionCursor("C:/ProyectoPokemon/img/menu/rojoChivi.png");
            	} catch (Exception e) {
            	    e.printStackTrace();
            	}
    		} else if (numPokVivosRival == 0 && rival.getIdRival() == 5) {
    			try {
            	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/creditos.fxml"));
            	    Parent root = loader.load();

            	    CreditosController creditosController = loader.getController();
            	    creditosController.init(entrenador, stage, loginController, null, null, null, null, null, null, null);

            	    Scene scene = new Scene(root);
            	    stage.setScene(scene);
            	    stage.setTitle("Creditos");
            	    stage.centerOnScreen();
            	    stage.show();
                    modificacionCursor("C:/ProyectoPokemon/img/menu/rojoChivi.png");
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
            	    stage.centerOnScreen();
            	    stage.show();
                    modificacionCursor("C:/ProyectoPokemon/img/menu/rojoChivi.png");
            	} catch (Exception e) {
            	    e.printStackTrace();
            	}
    		}
        });
    	pausa.play();
	}

	//Generamos el log del combate
	private void logCombate() {
		File fs = new File("C:/ProyectoPokemon/logs/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMDDhhmmss")) + ".txt");
		try {
			FileWriter fw = new FileWriter(fs);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int i = 0; i < registroCombate.size(); i++) {
				bw.write("Turno: " + registroCombate.get(i).getNumeroTurno());
				bw.write("\n");
				bw.write("Entrenador: " + registroCombate.get(i).getAccionEntrenador());
				bw.write("\n");
				bw.write("Rival: " + registroCombate.get(i).getAccionRival());
				bw.write("\n");
			}
		if (bw!=null) bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
    //Mostramos los datos de los movimientos al pasar por encima de ellos
    @FXML
    void entrarAtaque1(MouseEvent event) {
    	lblPP.setText("PP: " + listaMovPokEntr.get(0).getPpActuales() + "/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(0).getIdMovimiento()).getPpMax());
    	lblType.setText("Type: " + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(0).getIdMovimiento()).getTipoMov());
    }

    @FXML
    void entrarAtaque2(MouseEvent event) {
    	lblPP.setText("PP: " + listaMovPokEntr.get(1).getPpActuales() + "/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(1).getIdMovimiento()).getPpMax());
    	lblType.setText("Type: " + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(1).getIdMovimiento()).getTipoMov());
    }

    @FXML
    void entrarAtaque3(MouseEvent event) {
    	lblPP.setText("PP: " + listaMovPokEntr.get(2).getPpActuales() + "/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(2).getIdMovimiento()).getPpMax());
    	lblType.setText("Type: " + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(2).getIdMovimiento()).getTipoMov());
    }

    @FXML
    void entrarAtaque4(MouseEvent event) {
    	lblPP.setText("PP: " + listaMovPokEntr.get(3).getPpActuales() + "/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(3).getIdMovimiento()).getPpMax());
    	lblType.setText("Type: " + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(3).getIdMovimiento()).getTipoMov());
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
    //Se realiza el ataque en funcion del movimiento seleccionado
    private void llamarAtaque(int numMov) {
		if (listaMovPokEntr.get(numMov).getPpActuales() > 0) {
    		listaMovPokEntr.get(numMov).setPpActuales(listaMovPokEntr.get(numMov).getPpActuales() - 1);
    		
    		desactivarBotonesAtaque();
    		
    		realizarAtaque(numMov, seleccionarAtaqueRival());
    	}
    	else {
    		sinPPs(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(numMov).getIdMovimiento()).getNombre());
    	}
	}
    
    @FXML
    void cancelarAccion(ActionEvent event) {
    	desactivarBotonesAtaque();
    	
    	vBoxEquipo.setVisible(false);
    	btnConfirmarCambio.setVisible(false);
    	imgConfirmar.setVisible(false);
    	
    	btnAtacar.setVisible(true);
        imgAtaque.setVisible(true);
        btnRendirse.setVisible(true);
        imgRendirse.setVisible(true);
        btnAccionCambiarPokemon.setVisible(true);
        imgCambiarPokemon.setVisible(true);
    	imgSeleccionAccion.setVisible(true);
    }
    
    @FXML
    void accionCambiarPokemon(ActionEvent event) {
    	//btnMantenerPokemon.setVisible(false);
    	//btnCambiarPokemon.setVisible(false);
    	btnRendirse.setVisible(false);
    	btnAtacar.setVisible(false);
        imgAtaque.setVisible(false);
        imgRendirse.setVisible(false);
        imgCambiarPokemon.setVisible(false);
    	btnAccionCambiarPokemon.setVisible(false);
    	
    	if (equipoEntrenador.get(pokActEntr).getEstado().equals(TipoEstados.valueOf("ATRAPADO"))) {
    		imgSeleccionAccion.setVisible(false);
    		lblTexto.setText(equipoEntrenador.get(pokActEntr).getMote() + " esta atrapado. No puede ser cambiado!");
    		
    		PauseTransition pausa1 = new PauseTransition(Duration.seconds(1));
    		pausa1.setOnFinished(event1 -> {
    			imgSeleccionAccion.setVisible(false);
    			btnAtacar.setVisible(true);
    			btnRendirse.setVisible(true);
    			imgAtaque.setVisible(true);
    	        imgRendirse.setVisible(true);
    	        imgCambiarPokemon.setVisible(true);
    			btnCambiarPokemon.setVisible(true);
    			imgCambiarPokemon.setVisible(true);
    		});
    	} else {
    		btnCancelarAccion.setVisible(true);
    		imgCancelar.setVisible(true);
        	vBoxEquipo.setVisible(true);
        	
        	prepararPokemonEquipo();
    	}
    }
    //Preparamos la informacion de los pokemon del equipo para cuando queramos mostrarlo
    private void prepararPokemonEquipo() {
		for (int i = 0; i < equipoEntrenador.size(); i++) {
        	switch (i) {
        	case 0:
        		imgPokemonEquipo1.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblNombrePokemon1.setText(equipoEntrenador.get(i).getMote());
        		lblVidaPokemon1.setText(equipoEntrenador.get(i).getVitalidadAct() + "/" + equipoEntrenador.get(i).getVitalidadMax());
        		if (equipoEntrenador.get(i).getEstado() != null) {
        			imgEstadoEquipo1.setImage(new Image(new File("./img/Estados/" + equipoEntrenador.get(i).getEstado() + ".png").toURI().toString()));
        		}
        		break;
        	case 1:
        		imgPokemonEquipo2.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblNombrePokemon2.setText(equipoEntrenador.get(i).getMote());
        		lblVidaPokemon2.setText(equipoEntrenador.get(i).getVitalidadAct() + "/" + equipoEntrenador.get(i).getVitalidadMax());
        		if (equipoEntrenador.get(i).getEstado() != null) {
        			imgEstadoEquipo2.setImage(new Image(new File("./img/Estados/" + equipoEntrenador.get(i).getEstado() + ".png").toURI().toString()));
        		}
        		break;
        	case 2:
        		imgPokemonEquipo3.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblNombrePokemon3.setText(equipoEntrenador.get(i).getMote());
        		lblVidaPokemon3.setText(equipoEntrenador.get(i).getVitalidadAct() + "/" + equipoEntrenador.get(i).getVitalidadMax());
        		if (equipoEntrenador.get(i).getEstado() != null) {
        			imgEstadoEquipo3.setImage(new Image(new File("./img/Estados/" + equipoEntrenador.get(i).getEstado() + ".png").toURI().toString()));
        		}
        		break;
        	case 3:
        		imgPokemonEquipo4.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblNombrePokemon4.setText(equipoEntrenador.get(i).getMote());
        		lblVidaPokemon4.setText(equipoEntrenador.get(i).getVitalidadAct() + "/" + equipoEntrenador.get(i).getVitalidadMax());
        		if (equipoEntrenador.get(i).getEstado() != null) {
        			imgEstadoEquipo4.setImage(new Image(new File("./img/Estados/" + equipoEntrenador.get(i).getEstado() + ".png").toURI().toString()));
        		}
        		break;
        	case 4:
        		imgPokemonEquipo5.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblNombrePokemon5.setText(equipoEntrenador.get(i).getMote());
        		lblVidaPokemon5.setText(equipoEntrenador.get(i).getVitalidadAct() + "/" + equipoEntrenador.get(i).getVitalidadMax());
        		if (equipoEntrenador.get(i).getEstado() != null) {
        			imgEstadoEquipo5.setImage(new Image(new File("./img/Estados/" + equipoEntrenador.get(i).getEstado() + ".png").toURI().toString()));
        		}
        		break;
        	case 5:
        		imgPokemonEquipo6.setImage(new Image(new File("./img/Pokemon/Front/" + equipoEntrenador.get(i).getNumPokedex() + ".png").toURI().toString()));
        		lblNombrePokemon6.setText(equipoEntrenador.get(i).getMote());
        		lblVidaPokemon6.setText(equipoEntrenador.get(i).getVitalidadAct() + "/" + equipoEntrenador.get(i).getVitalidadMax());
        		if (equipoEntrenador.get(i).getEstado() != null) {
        			imgEstadoEquipo6.setImage(new Image(new File("./img/Estados/" + equipoEntrenador.get(i).getEstado() + ".png").toURI().toString()));
        		}
        		break;
        	}
        }
	}
    
    @FXML
    void mantenerPokemon(ActionEvent event) {
    	imgCancelar.setVisible(false);
    	imgConfirmar.setVisible(false);
    	
    	if (btnMantenerPokemon.getText().equals("Rendirse")) {
    		btnMantenerPokemon.setVisible(false);
        	btnCambiarPokemon.setVisible(false);
        	imgCambiarPokemon.setVisible(false);
    		
    		desactivarBotonesAtaque();
        	uiFinalCombate();
        	
        	lblTexto.setText("Ha ha ha ha ha! Como te atreves a huir! Cobarde!! No te mereces aprobar el Proyecto Pokemon!");
        	
        	volverAlMenu();
    	} else {
	    	btnMantenerPokemon.setVisible(false);
	    	btnCambiarPokemon.setVisible(false);
	    	imgSeleccionAccion.setVisible(false);
	    	imgCambiarPokemon.setVisible(false);
	    	
	    	btnAtacar.setVisible(false);
	    	btnRendirse.setVisible(false);
	    	imgAtaque.setVisible(false);
	        imgRendirse.setVisible(false);
	        imgCambiarPokemon.setVisible(false);
	    	btnAccionCambiarPokemon.setVisible(false);
	    	
	    	//EL RIVAL SACA UN POKEMON
	    	rivalSacaPokemon();
    	}
    }
    
    //Extraemos la informacion del siguiente pokemon del rival en la lista y actualizamos la informacion visual
	private void rivalSacaPokemon() {
		pokActRival++;
		
		inicarMovPokRival();
		
		lblTexto.setText(rival.getNombre() + " ha sacado a " + equipoRival.get(pokActRival).getMote());
		
		String ruta = "C:/ProyectoPokemon/sonidos/Pokemon/" + equipoRival.get(pokActRival).getNumPokedex() + ".wav";
		SonidoController.reproducirEfecto(ruta, null);
		
		lblNombrePokemonRival.setText(equipoRival.get(pokActRival).getMote());
		imgPokemonRival.setImage(new Image(new File("./img/Pokemon/Front/" + equipoRival.get(pokActRival).getNumPokedex() + ".png").toURI().toString()));
		imgEstadoPokemonRival.setVisible(false);
		System.out.println("hola rival false!");
		//lblEstadoPokemonRival.setText("");
		System.out.println("Actualizado lblRival");
		
		pbVidaPokemonRival.setStyle("-fx-accent: green;");
		pbVidaPokemonRival.setProgress(equipoRival.get(pokActRival).getVitalidadAct() / equipoRival.get(pokActRival).getVitalidadMax());
		
		PauseTransition pausa1 = new PauseTransition(Duration.seconds(1));
		pausa1.setOnFinished(event1 -> {
			lblTexto.setText("Elige una accion");
			lblTexto.setVisible(true);
			btnAtacar.setVisible(true);
			btnRendirse.setVisible(true);
			btnAccionCambiarPokemon.setVisible(true);
			imgSeleccionAccion.setVisible(true);
			imgAtaque.setVisible(true);
			imgCambiarPokemon.setVisible(true);
			imgRendirse.setVisible(true);
		});
		pausa1.play();
	}
	
    //Seleccionamos el ataque del rival
    private int seleccionarAtaqueRival() {
		while(true) {
			int n = (int) (Math.random() * 3);
			if (n <= listaMovPokRival.size() - 1) {
				if (listaMovPokRival.get(n).getPpActuales() > 0) {
					listaMovPokRival.get(n).setPpActuales(listaMovPokRival.get(n).getPpActuales() - 1);
					return n;
				}
			}
		}
	}
    
    //Metodo principal de realizar el ataque
    private void realizarAtaque(int movPokEntrenador, int movPokRival) {
    	Movimiento movEntr = MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(movPokEntrenador).getIdMovimiento());
    	Movimiento movRival = MovimientoDAO.buscarPorId(con, listaMovPokRival.get(movPokRival).getIdMovimiento());
        
        // Comprobamos quien empieza y realizamos el ataque dependiendo del tipo de movimiento
        if (equipoEntrenador.get(pokActEntr).getVelocidad() > equipoRival.get(pokActRival).getVelocidad()) {
        	if (comprobarEstadoAntesAtaque(equipoEntrenador.get(pokActEntr), null, true)) {
        		tipoDeMovimiento(movEntr, movRival, equipoEntrenador.get(pokActEntr), equipoRival.get(pokActRival), true, true);
        	} else if (!btnAtaque1.isVisible() && comprobarEstadoAntesAtaque(equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false)){
        		tipoDeMovimiento(movRival, movEntr, equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false, false);
        	} else {
        		PauseTransition pausa = new PauseTransition(Duration.seconds(1));
				pausa.setOnFinished(event -> {
					activarBotonesSeleccionAccion();
					//activarBotonesAtaque();
				});
				pausa.play();
        	}
        } else if (equipoEntrenador.get(pokActEntr).getVelocidad() < equipoRival.get(pokActRival).getVelocidad()){
        	if (comprobarEstadoAntesAtaque(equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false)) {
        		tipoDeMovimiento(movRival, movEntr, equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false, true);
        	} else if (!btnAtaque1.isVisible() && comprobarEstadoAntesAtaque(equipoEntrenador.get(pokActEntr), null, true)){
        		tipoDeMovimiento(movEntr, movRival, equipoEntrenador.get(pokActEntr), equipoRival.get(pokActRival), true, false);
        	} else {
        		PauseTransition pausa = new PauseTransition(Duration.seconds(1));
				pausa.setOnFinished(event -> {
					activarBotonesSeleccionAccion();
					//activarBotonesAtaque();
				});
				pausa.play();
        	}
        } else if ( (int) (Math.random() * 2) + 1 == 1){
        	if (comprobarEstadoAntesAtaque(equipoEntrenador.get(pokActEntr), null, true)) {
        		tipoDeMovimiento(movEntr, movRival, equipoEntrenador.get(pokActEntr), equipoRival.get(pokActRival), true, true);
        	} else if (!btnAtaque1.isVisible() && comprobarEstadoAntesAtaque(equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false)){
        		tipoDeMovimiento(movRival, movEntr, equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false, false);
        	} else {
        		PauseTransition pausa = new PauseTransition(Duration.seconds(1));
				pausa.setOnFinished(event -> {
					activarBotonesSeleccionAccion();
					//activarBotonesAtaque();
				});
				pausa.play();
        	}
        } else {
        	if (comprobarEstadoAntesAtaque(equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false)) {
        		tipoDeMovimiento(movRival, movEntr, equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false, true);
        	} else if (!btnAtaque1.isVisible() && comprobarEstadoAntesAtaque(equipoEntrenador.get(pokActEntr), null, true)){
        		tipoDeMovimiento(movEntr, movRival, equipoEntrenador.get(pokActEntr), equipoRival.get(pokActRival), true, false);
        	} else {
        		PauseTransition pausa = new PauseTransition(Duration.seconds(1));
				pausa.setOnFinished(event -> {
					activarBotonesSeleccionAccion();
					//activarBotonesAtaque();
				});
				pausa.play();
        	}
        }

        //A�adimos el turno para luego hacer el log
        registroCombate.add(new Turno(0, registroCombate.size() + 1,
        		equipoEntrenador.get(pokActEntr).getMote() + " usa " + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(movPokEntrenador).getIdMovimiento()).getNombre(),
        		equipoRival.get(pokActRival).getMote() + " usa " + MovimientoDAO.buscarPorId(con, listaMovPokRival.get(movPokRival).getIdMovimiento()).getNombre()));
    }

    //Comprobamos el estado del pokemon que va a atacar antes de que lo haga para ver si tiene alguna limitacion que le impide hacerlo
	private boolean comprobarEstadoAntesAtaque(Pokemon pokemon, Pokemon contrario, boolean esTurnoEntrenador) {
		if ((pokemon.getEstado().equals(TipoEstados.valueOf("CONGELADO")) || pokemon.getEstado().equals(TipoEstados.valueOf("DORMIDO"))
				|| pokemon.getEstado().equals(TipoEstados.valueOf("PARALIZADO")) || pokemon.getEstado().equals(TipoEstados.valueOf("ENAMORADO")))) {
			if ((int) (Math.random() * 100) + 1 > 75) {
				lblTexto.setText(pokemon.getMote() + " ya no esta " + pokemon.getEstado());
				pokemon.setEstado(TipoEstados.valueOf("SIN_ESTADO"));
				
				if (esTurnoEntrenador) {
		            imgEstadoPokemonEntrenador.setVisible(false);
		            System.out.println("hola entrenador false!");
					//lblEstadoPokemonEntrenador.setText("");
		            System.out.println("Actualizado lblEntrenador");
				} else {
					imgEstadoPokemonRival.setVisible(false);
					System.out.println("hola rival false!");
					//lblEstadoPokemonRival.setText("");
					System.out.println("Actualizado lblRival");
				}
				
				return true;
			} else {
				lblTexto.setText(pokemon.getMote() + " esta " + pokemon.getEstado().toString().toLowerCase());
				return false;
			}
		} else if (pokemon.getEstado().equals(TipoEstados.valueOf("CONFUSO"))) {
			if ((int) (Math.random() * 100) + 1 > 75) { //EN TEORIA DURA DE 1 A 4 TURNOS
				lblTexto.setText(pokemon.getMote() + " ya no esta " + pokemon.getEstado());
				pokemon.setEstado(TipoEstados.valueOf("SIN_ESTADO"));
				
            	if (esTurnoEntrenador) {
		            imgEstadoPokemonEntrenador.setVisible(false);
		            System.out.println("hola entrenador false!");
					//lblEstadoPokemonEntrenador.setText("");
		            System.out.println("Actualizado lblEntrenador");
				} else {
					imgEstadoPokemonRival.setVisible(false);
					System.out.println("hola rival false!");
					//lblEstadoPokemonRival.setText("");
					System.out.println("Actualizado lblRival");
				}
				return true;
			} else if ((int) (Math.random() * 100) + 1 > 67){
				lblTexto.setText(pokemon.getMote() + " se ha golpeado a si mismo!");
				PauseTransition pausa = new PauseTransition(Duration.seconds(1));
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
		} else {
			return true;
		}
	}
	//Metodo para comprobar el tipo de movimiento que toca hacer
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
	//Este metodo realiza el ataque si es tipo estado
	private void movimientoEstado(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor,
			boolean esTurnoEntrenador, boolean primeraParte) {
		lblTexto.setText(atacante.getMote() + " ha usado " + movAtacante.getNombre());
		
		defensor.setEstado(movAtacante.getEstado());
		
		PauseTransition pausa1 = new PauseTransition(Duration.seconds(1));
		pausa1.setOnFinished(event1 -> {
			lblTexto.setText(defensor.getMote() + " ha sido " + movAtacante.getEstado().toString().toLowerCase());
			
			if (esTurnoEntrenador) {
            	imgEstadoPokemonRival.setImage(new Image(new File("C:/ProyectoPokemon/img/Estados/" + equipoRival.get(pokActRival).getEstado().getPseudonimo() + ".png").toURI().toString()));
            	imgEstadoPokemonRival.setVisible(true);
				//lblEstadoPokemonRival.setText(defensor.getEstado().getPseudonimo());
			} else {
            	imgEstadoPokemonEntrenador.setImage(new Image(new File("C:/ProyectoPokemon/img/Estados/" + equipoEntrenador.get(pokActEntr).getEstado().getPseudonimo() + ".png").toURI().toString()));
            	imgEstadoPokemonEntrenador.setVisible(true);
				//lblEstadoPokemonEntrenador.setText(defensor.getEstado().getPseudonimo());
			}
		});
		
		PauseTransition pausa2 = new PauseTransition(Duration.seconds(1));
		pausa2.setOnFinished(event2 -> {
			if (!(defensor.getEstado().equals(TipoEstados.valueOf("CONGELADO")) || defensor.getEstado().equals(TipoEstados.valueOf("DORMIDO"))
					|| defensor.getEstado().equals(TipoEstados.valueOf("PARALIZADO")) || defensor.getEstado().equals(TipoEstados.valueOf("ENAMORADO")))) {turnoDefensor(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, primeraParte);
			} else {
				activarBotonesSeleccionAccion();
				//activarBotonesAtaque();
			}
		});
		
		SequentialTransition secuencia = new SequentialTransition(pausa1, pausa2);
		secuencia.play();
	}
	//Si el movimiviento es de tipo mejora se llama a este metodo
	private void movimientoMejora(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor, boolean esTurnoEntrenador, boolean primeraParte) {
		lblTexto.setText(atacante.getMote() + " ha usado " + movAtacante.getNombre());
		
		PauseTransition pausa1 = new PauseTransition(Duration.seconds(1));
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
				atacante.setAtaque((int)(atacante.getAtaque() * 1.5));
				break;
			case "SUBIR_DEFENSA_ESP":
				lblTexto.setText(atacante.getMote() + " le ha subido la defensa especial");
				atacante.setDefensa((int)(atacante.getDefensa() * 1.5));
				break;
			case "SUBIR_VIDA":
				lblTexto.setText(atacante.getMote() + " ha recuperado vida");
				atacante.setVitalidadAct(Math.max(atacante.getVitalidadAct() * 2, atacante.getVitalidadMax()));
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
		
		PauseTransition pausa2 = new PauseTransition(Duration.seconds(1));
		pausa2.setOnFinished(event2 -> {
			turnoDefensor(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, primeraParte);
		});
		
		SequentialTransition secuencia = new SequentialTransition(pausa1, pausa2);
		secuencia.play();
	}

	//Comprueba si el pojemon atacante tenia algun estado que le afectara al final del turno y aplica su efecto. Aqui tiene una probabilidad de curarse 
	private boolean efectoEstadoFinalTurno(Pokemon pokemon, boolean esTurnoEntrenador) {
		if (!pokemon.getEstado().equals(TipoEstados.valueOf("SIN_ESTADO"))) {
			if ((int) (Math.random() * 100) + 1 > 75){
				lblTexto.setText(pokemon.getMote() + " ya no esta " + pokemon.getEstado().toString().toLowerCase());
				pokemon.setEstado(TipoEstados.valueOf("SIN_ESTADO"));
				
            	if (esTurnoEntrenador) {
		            imgEstadoPokemonEntrenador.setVisible(false);
		            System.out.println("hola entrenador false!");
					//lblEstadoPokemonEntrenador.setText("");
		            System.out.println("Actualizado lblEntrenador");
				} else {
					imgEstadoPokemonRival.setVisible(false);
					System.out.println("hola rival false!");
					//lblEstadoPokemonRival.setText("");
					System.out.println("Actualizado lblRival");
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
	//Metodo que realizar un movimiento de tipo ataque especial o fisico
    private void realizarAtaquePokemon(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor, boolean esTurnoEntrenador, boolean primeraParte) {
        	lblTexto.setText(atacante.getMote() + " ha usado " + movAtacante.getNombre());

            PauseTransition pausa1 = new PauseTransition(Duration.seconds(1));
            pausa1.setOnFinished(event1 -> {
                if (movAtacante.getProbabilidad() > ((int) (Math.random() * 100) + 1)) {
                    lblTexto.setText(atacante.getMote() + " ha golpeado a " + defensor.getMote());
                    
                    actualizarVida(defensor, esTurnoEntrenador, calcularDano(movAtacante, atacante, defensor));
                    
                    PauseTransition pausa2 = new PauseTransition(Duration.seconds(1));
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
                    
                    PauseTransition pausa2 = new PauseTransition(Duration.seconds(1));
                    pausa2.setOnFinished(event2 -> {
                    	turnoDefensor(movAtacante, movDefensor, atacante, defensor, esTurnoEntrenador, primeraParte);
                    });
                    pausa2.play();
                }
            });
            pausa1.play();
    }
    //Metodo que se llama una vez que el atacante ha terminado su turno
	private void turnoDefensor(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor,
			boolean esTurnoEntrenador, boolean primeraParte) {
		//Antes de que le toque al defensor vemos si el atacante ha sido debilitado por algun efecto
		if (efectoEstadoFinalTurno(atacante, esTurnoEntrenador ? false : true)) {//he cambiado esto. antes era solo esTurnoEntrenador
			PauseTransition pausa = new PauseTransition(Duration.seconds(1));
            pausa.setOnFinished(event -> {
            	if (primeraParte) {
    				if (comprobarEstadoAntesAtaque(defensor, atacante, esTurnoEntrenador ? false : true)) {
    					PauseTransition pausa1 = new PauseTransition(Duration.seconds(1));
    					pausa1.setOnFinished(event1 -> {
    						tipoDeMovimiento(movDefensor, movAtacante, defensor, atacante, esTurnoEntrenador ? false : true, false);
    					});
    					pausa1.play();
    				} else {
    					PauseTransition pausa1 = new PauseTransition(Duration.seconds(1));
    					pausa1.setOnFinished(event1 -> {
    						activarBotonesSeleccionAccion();
    						//activarBotonesAtaque();
    					});
    					pausa1.play();
    				}
    			} else {
    				activarBotonesSeleccionAccion();
					//activarBotonesAtaque();
    			}
            });
            pausa.play();
		} else {
			manejarPokemonDebilitado(atacante, defensor, esTurnoEntrenador ? false : true); //ESPEREMOS QUE ESTO ESTE BIEN. SI ESTA MAL ME VOY A ENTERAR
		}
	}
    //Metodo para calcular el dano del pokemon atacante al defensor
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

    	if (atacante.getIdObjeto() == 7 && turnosAnillo < 3) {
        	dano = (int) (dano * 1.1);
        	turnosAnillo++;
    	} else if (defensor.getIdObjeto() == 7 && turnosAnillo < 3) {
    		dano = 0;
    		turnosAnillo++;
    	}
    	
    	return dano;
	}
	
    private void actualizarVida(Pokemon defensor, boolean esTurnoEntrenador, int dano) {
		defensor.setVitalidadAct(Math.max(defensor.getVitalidadAct() - dano, 0));
		
		if (esTurnoEntrenador) {
			actualizarBarraVida(pbVidaPokemonRival, defensor);
		}
		else {
			actualizarBarraVida(pbVidaPokemonEntrenador, defensor);
			
			//BAJAR POCO A POCO EL TEXTO DE LA VIDA DEL POKEMON DEL ENTRENADOR
			double tiempoPorCiclo = 1 / dano;
			
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
			        	lblVidaPokemonEntrenador.setText((Math.max(current - 1, defensor.getVitalidadAct())) + " / " + defensor.getVitalidadMax());
			        }
			    })
			);
			timeline.setCycleCount(dano);
			timeline.play();
			
			PauseTransition pausa1 = new PauseTransition(Duration.seconds(1));
	        pausa1.setOnFinished(event1 -> {
	        	lblVidaPokemonEntrenador.setText(defensor.getVitalidadAct() + " / " + defensor.getVitalidadMax());
	        });
			pausa1.play();
		}
	}
    //Cuando un pokemon se debilita se llama a este metodo. Este tambien se encarga de gestionar la experiencia del pokemon
    private void manejarPokemonDebilitado(Pokemon defensor, Pokemon atacante, boolean esTurnoEntrenador) {
    	activo = new SimpleBooleanProperty(false);
    	defensor.setEstado(TipoEstados.valueOf("DEBILITADO"));
    	
    	if (esTurnoEntrenador) {
            numPokVivosRival--;
            imgPokemonRival.setImage(null);
        } else {
            numPokVivosEntr--;
            imgPokemonEntrenador.setImage(null);
        }

        lblTexto.setText(defensor.getMote() + " ha sido debilitado!");
        
        activo.addListener((observable, oldValue, newValue) -> {
        	desactivarBotonesAtaque();
        	comprobarPokemones(esTurnoEntrenador);
        });
        
        if (atacante.getNivel() < 100 && equipoRival.get(pokActRival).getVitalidadAct() == 0) {
        	int xp = Math.max((atacante.getNivel() + defensor.getNivel() * 10) / 4, 20);
        	atacante.setExperiencia(xp + atacante.getExperiencia());
        	
        	PauseTransition pausa2 = new PauseTransition(Duration.seconds(1));
            pausa2.setOnFinished(event2 -> {
            	lblTexto.setText(atacante.getMote() + " ha ganado " + xp + " puntos de experiencia");
            });
            
            PauseTransition pausa3 = new PauseTransition(Duration.seconds(1));
            pausa3.setOnFinished(event3 -> {
            	actualizarXP(activo);
            	PokemonDAO.actualizarPokemonSubirNivel(con, atacante);
            });
            
            SequentialTransition secuencia = new SequentialTransition(pausa2, pausa3);
            secuencia.play();
        } else {
        	desactivarBotonesAtaque();
        	comprobarPokemones(esTurnoEntrenador);
        }
    }
    //Actualiza la barra de vida del pokemon y gestiona el subir de nivel y aprender movimientos
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
		    new KeyFrame(Duration.seconds(0.01), e -> {
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
				
				String ruta = "C:/ProyectoPokemon/sonidos/SubirNivel.mp3";
	    		SonidoController.reproducirEfecto(ruta, null);
				
				int vitalidad = (int) (Math.random() * 5) + 1;
				lblVitalidad.setText("Vitalidad: " + equipoEntrenador.get(pokActEntr).getVitalidadMax() + " + " + vitalidad);
				equipoEntrenador.get(pokActEntr).setVitalidadMax(equipoEntrenador.get(pokActEntr).getVitalidadMax() + vitalidad);
				equipoEntrenador.get(pokActEntr).setVitalidadAct(equipoEntrenador.get(pokActEntr).getVitalidadAct() + vitalidad);
				int ataque = (int) (Math.random() * 5) + 1;
				lblAtaque.setText("Ataque: " + equipoEntrenador.get(pokActEntr).getAtaque() + " + " + ataque);
				equipoEntrenador.get(pokActEntr).setAtaque(equipoEntrenador.get(pokActEntr).getAtaque() + ataque);
				int ataqueEsp = (int) (Math.random() * 5) + 1;
				lblAtaqueEsp.setText("Ataque Esp: " + equipoEntrenador.get(pokActEntr).getAtEspecial() + " + " + ataqueEsp);
				equipoEntrenador.get(pokActEntr).setAtEspecial(equipoEntrenador.get(pokActEntr).getAtEspecial() + ataqueEsp);
				int defensa = (int) (Math.random() * 5) + 1;
				lblDefensa.setText("Defensa: " + equipoEntrenador.get(pokActEntr).getDefensa() + " + " + defensa);
				equipoEntrenador.get(pokActEntr).setDefensa(equipoEntrenador.get(pokActEntr).getDefensa() + defensa);
				int defensaEsp = (int) (Math.random() * 5) + 1;
				lblDefensaEsp.setText("Defensa Esp: " + equipoEntrenador.get(pokActEntr).getDefEspecial() + " + " + defensaEsp);
				equipoEntrenador.get(pokActEntr).setDefEspecial(equipoEntrenador.get(pokActEntr).getDefEspecial() + defensaEsp);
				int velocidad = (int) (Math.random() * 5) + 1;
				lblVelocidad.setText("Velocidad: " + equipoEntrenador.get(pokActEntr).getVelocidad() + " + " + velocidad);
				equipoEntrenador.get(pokActEntr).setVelocidad(equipoEntrenador.get(pokActEntr).getVelocidad() + velocidad);
				
				vBoxEstadisticas.setVisible(true);
				
				PauseTransition pausa = new PauseTransition(Duration.seconds(1));
		        pausa.setOnFinished(event1 -> {
		        	vBoxEstadisticas.setVisible(false);
		        });
		        pausa.play();
				
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
	        		listaMovPosiblesEntr.addAll(MovimientoDAO.buscarPorTipoMov(con, PokedexDAO.cargarPorNumPokedex(con, equipoEntrenador.get(pokActEntr).getNumPokedex()).getTipo(1)));
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
	        			lblEfectoNuevo.setText("Efecto: da�a al rival con ataque fisico");
	        			break;
	        		case "ESPECIAL":
	        			lblEfectoNuevo.setText("Efecto: da�a al rival con ataque especial");
	        			break;
	        		}
	        		
	        		lblTexto.setText(equipoEntrenador.get(pokActEntr).getMote() + " quiere aprender " + movimientoNuevo.getNombre() + " pero ya tiene 4 movimientos. �Quieres que olvide uno?");
	        		btnOlvidarMovimiento.setVisible(true);
	        		imgConfirmar.setVisible(true);
	        		btnNoAprender.setVisible(true);
	        		imgCancelar.setVisible(true);
	        		imgSeleccionAccion.setVisible(true);
	        	}
	        }
	        else {
	        	siguienteNivel();
	        }
		});
	}

    @FXML
    void noAprender(ActionEvent event) {
    	btnOlvidarMovimiento.setVisible(false);
    	imgConfirmar.setVisible(false);
		btnNoAprender.setVisible(false);
		imgSeleccionAccion.setVisible(false);
		imgCancelar.setVisible(false);
		lblTexto.setText((equipoEntrenador.get(pokActEntr).getMote() + " no ha aprendido el movimiento."));
		
    	siguienteNivel();
    }
    
	private void siguienteNivel() {
		if (equipoEntrenador.get(pokActEntr).getNivel() == 100) {
			equipoEntrenador.get(pokActEntr).setExperiencia(0);
		}
		pbXpPokemonEntrenador.setProgress(0.0001);
		PauseTransition pausa = new PauseTransition(Duration.seconds(1));
        pausa.setOnFinished(event1 -> {
        	if (PokedexDAO.cargarPorNumPokedex(ConexionBD.getConnection(), equipoEntrenador.get(pokActEntr).getNumPokedex()).getNivelEvo() == equipoEntrenador.get(pokActEntr).getNivel()) {
        		imgPokemonEntrenador.setImage(equipoEntrenador.get(pokActEntr).evolucionar());
        		
        		String ruta = "C:/ProyectoPokemon/sonidos/Evolucion.mp3";
        		SonidoController.reproducirEfecto(ruta, null);
        		
        		actualizarXP(activo);
        	} else if (equipoEntrenador.get(pokActEntr).getExperiencia() >= experienciaLevelUp) {
        		actualizarXP(activo);
        	} else {
        		double porcentajeFinal2 = (double) (equipoEntrenador.get(pokActEntr).getExperiencia()) / experienciaLevelUp;
        		double aumPorPaso2 = (porcentajeFinal2 - pbXpPokemonEntrenador.getProgress()) / 90;
        		
        		Timeline timeline2 = new Timeline(
        		    new KeyFrame(Duration.seconds(0.01), e2 -> {
        		        double current = pbXpPokemonEntrenador.getProgress();
        		        
        		        if (current < porcentajeFinal2) {
        		        	pbXpPokemonEntrenador.setProgress(Math.min(current + aumPorPaso2, 1));
        		        }
        		    })
        		);
        		timeline2.setCycleCount(90);
        		timeline2.play();
        		
        		timeline2.setOnFinished(e2 -> {
        			PauseTransition pausa1 = new PauseTransition(Duration.seconds(1));
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
    	imgConfirmar.setVisible(false);
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

	private boolean comprobarMovimientos(Movimiento movimiento) {
		for (MovimientoPokemon movimientoEntrenador : listaMovPokEntr) {
			if (movimientoEntrenador.getIdMovimiento() == movimiento.getIdMovimiento()) {
				return true;
			}
		}
		return false;
	}

	private void comprobarPokemones(boolean esTurnoEntrenador) {
		if (numPokVivosRival == 0) {
			uiFinalCombate();
			lblTexto.setText("Has ganado " + entrenador.getPokedolares() / 3 + " pokedolares");
			entrenador.setPokedolares(100 + entrenador.getPokedolares() + (entrenador.getPokedolares() / 3));
			
			actualizarRivalesVencidos();
			
			PauseTransition pausa = new PauseTransition(Duration.seconds(1));
			pausa.setOnFinished(evento -> {
				lblTexto.setText(rival.getFraseVictoria());
				volverAlMenu();
			});
			pausa.play();
		} else if (numPokVivosEntr == 0) {
			uiFinalCombate();
			lblTexto.setText("Has perdido " + entrenador.getPokedolares() / 3 + " pokedolares");
			entrenador.setPokedolares(entrenador.getPokedolares() - (entrenador.getPokedolares() / 3));
			
			PauseTransition pausa = new PauseTransition(Duration.seconds(1));
			pausa.setOnFinished(evento -> {
				lblTexto.setText(rival.getFraseDerrota());
				volverAlMenu();
			});
			pausa.play();
		} else {
		    cambiarPokemon(esTurnoEntrenador);
		}
	}

	private void actualizarRivalesVencidos() {
		if (rival.getIdRival() == 0) {
			if (entrenador.getRivalesVencidos() < 0) {
				entrenador.setRivalesVencidos(entrenador.getRivalesVencidos() + 1);
			}
		} else if (entrenador.getRivalesVencidos() < rival.getIdRival()) {
			entrenador.setRivalesVencidos(rival.getIdRival());
		}
	}

    private void cambiarPokemon(boolean esTurnoEntrenador) {
        if (esTurnoEntrenador) {
            lblTexto.setText(rival.getNombre() + " va a sacar a " + equipoRival.get(pokActRival + 1).getMote() + ". Quieres cambiar de pokemon?");
            btnMantenerPokemon.setText("Mantener");
        } else {
            lblTexto.setText("Quieres sacar otro pokemon?");
            btnMantenerPokemon.setText("Rendirse");
        }

        btnMantenerPokemon.setVisible(true);
        imgCancelar.setVisible(true);
        imgConfirmar.setVisible(true);
        btnCambiarPokemon.setVisible(true);
        imgCambiarPokemon.setVisible(true);
        imgSeleccionAccion.setVisible(true);
    }

	private void actualizarBarraVida(ProgressBar pb, Pokemon pokemon) {
		double porcentajeFinal = ((double) (pokemon.getVitalidadAct())) / pokemon.getVitalidadMax();
		
		int numCiclos = 90;
		double decrPorPaso = (pb.getProgress() - porcentajeFinal) / numCiclos;
		
		Timeline timeline = new Timeline(
		    new KeyFrame(Duration.seconds(0.01), e -> {
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

	private void sinPPs(String movimiento) {
    	desactivarBotonesAtaque();
		
    	lblTexto.setText("El ataque " + movimiento + " no tiene PPs");
    	
    	PauseTransition pausa = new PauseTransition(Duration.seconds(1));
    	pausa.setOnFinished(evento -> {
            activarBotonesAtaque();
        });
    	pausa.play();
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
				lblTexto.setText("El pokemon seleccionado ya esta en combate");
			}
			else {
				if (equipoEntrenador.get(indicePokSeleccionado).getVitalidadAct() == 0) {
					lblTexto.setText("El pokemon seleccionado esta debilitado");
				}
		    	else{
					lblTexto.setText("Seguro que quieres cambiar a " + equipoEntrenador.get(indicePokSeleccionado).getMote() + "?");
					//vBoxEquipo.setVisible(false);
					//btnCancelarCambio.setVisible(true);
					btnConfirmarCambio.setVisible(true);
					imgSeleccionAccion.setVisible(true);
					imgConfirmar.setVisible(true);
				}
			}
		}
	}
    
    @FXML
    void cancelarCambio(ActionEvent event) {
    	if (btnCancelarCambio.getText().equals("Cancelar")) {
    		//btnRendirse.setVisible(true);
    		imgConfirmar.setVisible(false);
    	}
    	else {
    		lblTexto.setText("Selecciona un pokemon");
        	
    		btnCancelarCambio.setVisible(false);
    		btnConfirmarCambio.setVisible(false);
    		imgConfirmar.setVisible(false);
    	}
    	prepararPokemonEquipo();
    	vBoxEquipo.setVisible(true);
    }

    @FXML
    void confirmarCambio(ActionEvent event) {
    	vBoxEquipo.setVisible(false);
    	btnCancelarCambio.setVisible(false);
    	imgCancelar.setVisible(false);
		btnConfirmarCambio.setVisible(false);
		imgConfirmar.setVisible(false);
		
		imgSeleccionAccion.setVisible(false);
		btnAtacar.setVisible(false);
    	btnRendirse.setVisible(false);
    	btnAccionCambiarPokemon.setVisible(false);
    	btnCancelarAccion.setVisible(false);
    	imgCancelar.setVisible(false);
    	
    	imgAtaque.setVisible(false);
		imgCambiarPokemon.setVisible(false);
		imgRendirse.setVisible(false);
		
    	btnMantenerPokemon.setVisible(false);
    	btnCambiarPokemon.setVisible(false);
    	imgCambiarPokemon.setVisible(false);
    	
		sacarPokemon();
    }

	private void sacarPokemon() {
		int pokAnteriorEntr = pokActEntr;
		
		lblTexto.setText("Has luchado bien " + equipoEntrenador.get(pokActEntr).getMote());
		imgPokemonEntrenador.setImage(null);
		
		PauseTransition pausa1 = new PauseTransition(Duration.seconds(1));
    	pausa1.setOnFinished(event1 -> {
            lblTexto.setText(entrenador.getUsuario() + " saca a " + equipoEntrenador.get(indicePokSeleccionado).getMote());
        });
    	
    	PauseTransition pausa2 = new PauseTransition(Duration.seconds(1));
    	pausa2.setOnFinished(event2 -> {
    		String ruta = "C:/ProyectoPokemon/sonidos/Pokemon/" + equipoEntrenador.get(pokActEntr).getNumPokedex() + ".wav";
    		SonidoController.reproducirEfecto(ruta, null);
    		
    		pokActEntr = indicePokSeleccionado;
    		listaMovPokEntr = MovimientoPokemonDAO.buscarPorIdPokemon(con, equipoEntrenador.get(pokActEntr).getIdPokemon());
    		
    		listaMovPokEntrAUX = new LinkedList<Movimiento>();
            for (MovimientoPokemon mov : listaMovPokEntr) {
    			listaMovPokEntrAUX.add(MovimientoDAO.buscarPorId(con, mov.getIdMovimiento()));
    		}
            
    		imgPokemonEntrenador.setImage(new Image(new File("./img/Pokemon/Back/" + equipoEntrenador.get(pokActEntr).getNumPokedex() + ".png").toURI().toString()));
    		lblVidaPokemonEntrenador.setText(equipoEntrenador.get(pokActEntr).getVitalidadAct() + "/" + equipoEntrenador.get(pokActEntr).getVitalidadMax());

    		if (equipoEntrenador.get(pokActEntr).getEstado().equals(TipoEstados.valueOf("SIN_ESTADO"))) {
            	imgEstadoPokemonEntrenador.setVisible(false);
    			//lblEstadoPokemonEntrenador.setText("");
            	System.out.println("Actualizado lblEntrenador");
            } else {
            	imgEstadoPokemonEntrenador.setVisible(true);
            	imgEstadoPokemonEntrenador.setImage(new Image(new File("C:/ProyectoPokemon/img/Estados/" + equipoEntrenador.get(pokActEntr).getEstado().getPseudonimo() + ".png").toURI().toString()));
            	//lblEstadoPokemonEntrenador.setText(equipoEntrenador.get(pokActEntr).getEstado().getPseudonimo());
            }
    		
    		pbVidaPokemonEntrenador.setProgress((double) (equipoEntrenador.get(pokActEntr).getVitalidadAct()) / equipoEntrenador.get(pokActEntr).getVitalidadMax());
    		pbVidaPokemonEntrenador.setStyle("-fx-accent: #00a135;");
    		
    		if (pbVidaPokemonEntrenador.getProgress() < 0.25){
    			pbVidaPokemonEntrenador.setStyle("-fx-accent: red;");
	        }
	        else if (pbVidaPokemonEntrenador.getProgress() < 0.5){
	        	pbVidaPokemonEntrenador.setStyle("-fx-accent: yellow;");
	        }
    		pbXpPokemonEntrenador.setProgress((double) (equipoEntrenador.get(pokActEntr).getExperiencia()) / (10 * equipoEntrenador.get(pokActEntr).getNivel()));
    		lblNivelPokemonEntrenador.setText(Integer.toString(equipoEntrenador.get(pokActEntr).getNivel()));
    		lblNombrePokemonEntrenador.setText(equipoEntrenador.get(pokActEntr).getMote());
    		
    		if (equipoRival.get(pokActRival).getVitalidadAct() <= 0) {
    			rivalSacaPokemon();
    		}
    		else if (equipoEntrenador.get(pokAnteriorEntr).getVitalidadAct() == 0){
    			imgSeleccionAccion.setVisible(true);
        		btnAtacar.setVisible(true);
            	btnRendirse.setVisible(true);
            	imgAtaque.setVisible(true);
    			imgCambiarPokemon.setVisible(true);
    			imgRendirse.setVisible(true);
            	btnAccionCambiarPokemon.setVisible(true);
        		
        		lblTexto.setText("Elige una accion");
    		}
    		else {
    			Movimiento movRival = MovimientoDAO.buscarPorId(con, listaMovPokRival.get(seleccionarAtaqueRival()).getIdMovimiento());
    			if (comprobarEstadoAntesAtaque(equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false)) {
    				PauseTransition pausa = new PauseTransition(Duration.seconds(1));
					pausa.setOnFinished(event -> {
						tipoDeMovimiento(movRival, null, equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false, false);
					});
					pausa.play();
    			} else {
    				PauseTransition pausa = new PauseTransition(Duration.seconds(1));
					pausa.setOnFinished(event -> {
						activarBotonesSeleccionAccion();
						//activarBotonesAtaque();
					});
					pausa.play();
    			}
    		}
    	});
    	
    	SequentialTransition secuencia = new SequentialTransition(pausa1, pausa2);
        secuencia.play();
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
    	imgSeleccionAccion.setVisible(false);
    	imgConfirmar.setVisible(false);
    	
    	lblTexto.setText(equipoEntrenador.get(pokActEntr).getMote() + " no ha aprendido el movimiento.");
    	
    	siguienteNivel();
    }
    
    private void dejarMostrarMov() {
		lblMovSelec.setText("Nombre pokemon");
    	lblPPSelec.setText("PPs");
		lblTipoMovSelec.setText("Tipo de movimiento: ");
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
			lblEfectoSelec.setText("Efecto: da�a al rival con ataque fisico");
			break;
		case "ESPECIAL":
			lblEfectoSelec.setText("Efecto: da�a al rival con ataque especial");
			break;
		}
	}
	
	private void uiFinalCombate() {
		btnAtacar.setVisible(false);
    	btnRendirse.setVisible(false);
    	btnAccionCambiarPokemon.setVisible(false);
    	imgAtaque.setVisible(false);
		imgCambiarPokemon.setVisible(false);
		imgRendirse.setVisible(false);
    	
    	imgPokemonRival.setVisible(false);
    	imgRival.setVisible(true);
        imgInfoPokemonRival.setVisible(false);
        lblNombrePokemonRival.setVisible(false);
        lblNivelPokemonRival.setVisible(false);
        pbVidaPokemonRival.setVisible(false);
        imgEstadoPokemonRival.setVisible(false);
        //lblEstadoPokemonRival.setVisible(false);
        
        imgPokemonEntrenador.setVisible(false);
    	imgEntrenador.setVisible(true);
        imgInfoPokemonEntrenador.setVisible(false);
        lblHP.setVisible(false);
        lblNombrePokemonEntrenador.setVisible(false);
        lblNivelPokemonEntrenador.setVisible(false);
        lblVidaPokemonEntrenador.setVisible(false);
        pbVidaPokemonEntrenador.setVisible(false);
        pbXpPokemonEntrenador.setVisible(false);
        imgEstadoPokemonEntrenador.setVisible(false);
        //lblEstadoPokemonEntrenador.setVisible(false);
	}
    
	private void desactivarBotonesAtaque() {
		btnCancelarAccion.setVisible(false);
		imgCancelar.setVisible(false);
    	lblTexto.setVisible(true);
    	imgSeleccionAtaque.setVisible(false);
    	imgSeleccionAccion.setVisible(false);
    	lblPP.setVisible(false);
    	lblType.setVisible(false);
    	btnAtaque1.setVisible(false);
    	lblAtaque1.setVisible(false);
    	btnAtaque2.setVisible(false);
    	lblAtaque2.setVisible(false);
    	btnAtaque3.setVisible(false);
    	lblAtaque3.setVisible(false);
    	btnAtaque4.setVisible(false);
    	lblAtaque4.setVisible(false);
    	
    	imgAtaque1.setVisible(false);
    	imgAtaque2.setVisible(false);
    	imgAtaque3.setVisible(false);
    	imgAtaque4.setVisible(false);
    }
	
	private void activarBotonesSeleccionAccion() {
    	imgSeleccionAccion.setVisible(true);
    	
		lblTexto.setText("Elije una accion.");
    	lblTexto.setVisible(true);
		
    	btnAtacar.setVisible(true);
		btnRendirse.setVisible(true);
		imgAtaque.setVisible(true);
		imgCambiarPokemon.setVisible(true);
		imgRendirse.setVisible(true);
		btnAccionCambiarPokemon.setVisible(true);
	}
    
    private void activarBotonesAtaque() {
    	btnCancelarAccion.setVisible(true);
    	imgCancelar.setVisible(true);
    	lblTexto.setVisible(false);
    	imgSeleccionAtaque.setVisible(true);
    	imgSeleccionAccion.setVisible(true);
    	btnAtacar.setVisible(false);
    	btnRendirse.setVisible(false);
    	imgAtaque.setVisible(false);
		imgCambiarPokemon.setVisible(false);
		imgRendirse.setVisible(false);
    	btnAccionCambiarPokemon.setVisible(false);
    	lblPP.setText("PP: ");
    	lblPP.setVisible(true);
    	lblType.setText("Type: ");
    	lblType.setVisible(true);
    	
    	switch (listaMovPokEntr.size()) {
			case 4:
				//btnAtaque4.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(3).getIdMovimiento()).getNombre());
	        	btnAtaque4.setVisible(true);
	        	lblAtaque4.setVisible(true);
	        	lblAtaque4.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(3).getIdMovimiento()).getNombre());
	        	imgAtaque4.setImage(new Image(new File("C:/ProyectoPokemon/img/Ataques/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(3).getIdMovimiento()).getTipoMov() + ".png").toURI().toString()));
	        	imgAtaque4.setVisible(true);
			case 3:
				//btnAtaque3.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(2).getIdMovimiento()).getNombre());
	        	btnAtaque3.setVisible(true);
	        	lblAtaque3.setVisible(true);
	        	lblAtaque3.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(2).getIdMovimiento()).getNombre());
	        	imgAtaque3.setImage(new Image(new File("C:/ProyectoPokemon/img/Ataques/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(2).getIdMovimiento()).getTipoMov() + ".png").toURI().toString()));
	        	imgAtaque3.setVisible(true);
			case 2:
				//btnAtaque2.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(1).getIdMovimiento()).getNombre());
	        	btnAtaque2.setVisible(true);
	        	lblAtaque2.setVisible(true);
	        	lblAtaque2.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(1).getIdMovimiento()).getNombre());
	        	imgAtaque2.setImage(new Image(new File("C:/ProyectoPokemon/img/Ataques/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(1).getIdMovimiento()).getTipoMov() + ".png").toURI().toString()));
	        	imgAtaque2.setVisible(true);
			case 1:
				//btnAtaque1.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(0).getIdMovimiento()).getNombre());
	        	btnAtaque1.setVisible(true);
	        	lblAtaque1.setVisible(true);
	        	lblAtaque1.setText(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(0).getIdMovimiento()).getNombre());
	        	imgAtaque1.setImage(new Image(new File("C:/ProyectoPokemon/img/Ataques/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(0).getIdMovimiento()).getTipoMov() + ".png").toURI().toString()));
	        	imgAtaque1.setVisible(true);
	        	break;
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