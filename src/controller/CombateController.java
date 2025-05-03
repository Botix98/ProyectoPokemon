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
import dao.RivalDAO;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Entrenador;
import model.Movimiento;
import model.MovimientoPokemon;
import model.Pokedex;
import model.Rival;
import model.Pokemon;

public class CombateController {
	
	private Entrenador entrenador;
	private Stage stage;
	private LoginController loginController;
	private MenuController menuController;
	
	private int pokActEntr;
	private int pokActRival;
	private int numPokVivosEntr;
	private int numPokVivosRival;
	
	private Rival rival;
	
	Connection con;
	
	LinkedList<Pokemon> equipoEntrenador;
	LinkedList<Pokemon> equipoRival;
	LinkedList<MovimientoPokemon> listaMovPokEntr;
	LinkedList<MovimientoPokemon> listaMovPokRival;

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
    private Button btnMochila;
    
    @FXML
    private Button btnAccionCambiarPokemon;

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
    
    @FXML
    private Button btnCambiarPokemon;

    @FXML
    private Button btnMantenerPokemon;
    
    @FXML
    private Button btnCancelarAccion;

    @FXML
    public void initialize() {
    	con = ConexionBD.getConnection();
    }

    
    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        
        this.rival = RivalDAO.cargarRival(con, 1); //AQUI HABRA QUE PASARLE LA ID DEL RIVAL SELECCIONADO
        
        calcularPokemonVivos();
        
        this.listaMovPokEntr = MovimientoPokemonDAO.buscarPorIdPokemon(con, equipoEntrenador.get(0).getIdPokemon());
        this.listaMovPokRival = MovimientoPokemonDAO.buscarPorIdPokemon(con, equipoRival.get(0).getIdPokemon());
        
        prepararCombate();
    }
    
    private void prepararCombate() {
    	pbVidaPokemonEntrenador.setStyle("-fx-accent: #00a135;");
    	pbVidaPokemonRival.setStyle("-fx-accent: #00a135;");
    	
    	lblTexto.setText(rival.getNombre() + " te ha retado a un combate pokemon!");
    	
    	PauseTransition primeraPausa = new PauseTransition(Duration.seconds(2));
    	primeraPausa.setOnFinished(event -> {
            lblTexto.setText(rival.getNombre() + " saca a " + equipoRival.get(0).getMote());
        });
    	
    	PauseTransition segundaPausa = new PauseTransition(Duration.seconds(2));
    	segundaPausa.setOnFinished(event -> {
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
    	
    	PauseTransition terceraPausa = new PauseTransition(Duration.seconds(2));
    	terceraPausa.setOnFinished(event -> {
            lblTexto.setText(entrenador.getUsuario() + " saca a " + equipoEntrenador.get(0).getMote());
        });
    	
    	PauseTransition cuartaPausa = new PauseTransition(Duration.seconds(2));
    	cuartaPausa.setOnFinished(event -> {
            imgEntrenador.setVisible(false);
            imgPokemonEntrenador.setImage(new Image(new File("./img/Pokemon/Back/" + equipoEntrenador.get(pokActEntr).getNumPokedex() + ".png").toURI().toString()));
            imgPokemonEntrenador.setVisible(true);
            imgInfoPokemonEntrenador.setVisible(true);
            lblVidaPokemonEntrenador.setText(equipoEntrenador.get(0).getVitalidadAct() + "/" + equipoEntrenador.get(0).getVitalidadMax());
            lblVidaPokemonEntrenador.setVisible(true);
            lblNombrePokemonEntrenador.setText(equipoEntrenador.get(0).getMote());
            lblNombrePokemonEntrenador.setVisible(true);
            lblNivelPokemonEntrenador.setText(Integer.toString(equipoEntrenador.get(0).getNivel()));
            lblNivelPokemonEntrenador.setVisible(true);
            pbVidaPokemonEntrenador.setVisible(true);
            pbXpPokemonEntrenador.setVisible(true);
            lblVidaPokemonEntrenador.setText(equipoEntrenador.get(0).getVitalidadAct() + "/" + equipoEntrenador.get(0).getVitalidadMax());
        });
    	
    	PauseTransition quintaPausa = new PauseTransition(Duration.seconds(2));
    	quintaPausa.setOnFinished(event -> {
            lblTexto.setText("Elige una opcion");
            imgSeleccionAccion.setVisible(true);
            btnAtacar.setVisible(true);
            btnRendirse.setVisible(true);
            btnAccionCambiarPokemon.setVisible(true);
            btnMochila.setVisible(true);
        });
    	
    	SequentialTransition secuencia = new SequentialTransition(primeraPausa, segundaPausa, terceraPausa, cuartaPausa, quintaPausa);
        secuencia.play();
    }

	private void calcularPokemonVivos() {
		pokActEntr = 0;
    	pokActRival = 0;
    	
    	equipoEntrenador = PokemonDAO.cargarPokemonEquipoEntrenador(con, entrenador.getIdEntrenador(), 1);
    	equipoRival = PokemonDAO.cargarPokemonEquipoRival(con, rival.getIdRival());
    	
    	for (int i = 0; i < equipoEntrenador.size(); i++) {
    		if (equipoEntrenador.get(i) != null) {
    			if (equipoEntrenador.get(i).getVitalidadAct() > 0) {
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
    	activarBotonesAtaque();
    }
    
    @FXML
    void rendirse(ActionEvent event) {
    	desactivarBotonesAtaque();
    	uiFinalCombate();
    	
    	lblTexto.setText("Ha ha ha ha ha! Como te atreves a huir! Cobarde!! No te mereces aprobar el Proyecto Pokemon!");
    	
    	volverAlMenu();
    }

	private void volverAlMenu() {
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
    	lblPP.setText("PP: " + listaMovPokEntr.get(0).getPpActuales() + "/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(0).getIdMovimiento()).getPpMax());
    	lblType.setText("Type: " + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(0).getIdMovimiento()).getTipo());
    }

    @FXML
    void entrarAtaque2(MouseEvent event) {
    	lblPP.setText("PP: " + listaMovPokEntr.get(1).getPpActuales() + "/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(1).getIdMovimiento()).getPpMax());
    	lblType.setText("Type: " + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(1).getIdMovimiento()).getTipo());
    }

    @FXML
    void entrarAtaque3(MouseEvent event) {
    	lblPP.setText("PP: " + listaMovPokEntr.get(2).getPpActuales() + "/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(2).getIdMovimiento()).getPpMax());
    	lblType.setText("Type: " + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(2).getIdMovimiento()).getTipo());
    }

    @FXML
    void entrarAtaque4(MouseEvent event) {
    	lblPP.setText("PP: " + listaMovPokEntr.get(3).getPpActuales() + "/" + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(3).getIdMovimiento()).getPpMax());
    	lblType.setText("Type: " + MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(3).getIdMovimiento()).getTipo());
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
    	if (listaMovPokEntr.get(0).getPpActuales() > 0) {
    		listaMovPokEntr.get(0).setPpActuales(listaMovPokEntr.get(0).getPpActuales() - 1);
    		
    		desactivarBotonesAtaque();
    		
    		int ataqueRival = seleccionarAtaqueRival();
    		
    		realizarAtaque(0, ataqueRival);
    	}
    	else {
    		sinPPs(MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(0).getIdMovimiento()).getNombre());
    	}
    }

	@FXML
    void ataque2(ActionEvent event) {
    	LinkedList<Pokedex> pokedex = new LinkedList<Pokedex>();
    	Connection con = ConexionBD.getConnection();
    	
    	pokedex = PokedexDAO.cargarPokedexCompleta(con);
    	
    	for (Pokedex pokemon : pokedex) {
			System.out.println(pokemon.toString());
		}
    }

    @FXML
    void ataque3(ActionEvent event) {

    }

    @FXML
    void ataque4(ActionEvent event) {

    }
    
    @FXML
    void cancelarAccion(ActionEvent event) {
    	desactivarBotonesAtaque();
    	
    	btnAtacar.setVisible(true);
    	btnRendirse.setVisible(true);
    	btnAccionCambiarPokemon.setVisible(true);
    	btnMochila.setVisible(true);
    	imgSeleccionAccion.setVisible(true);
    }
    
    @FXML
    void accionCambiarPokemon(ActionEvent event) {
    	btnMantenerPokemon.setVisible(false);
    	btnCambiarPokemon.setVisible(false);
    	
    	//MANDAR A LA VENTANA DE EQUIPO
    	//EL RIVAL SACA UN POKEMON
    }
    
    @FXML
    void mantenerPokemon(ActionEvent event) {
    	btnMantenerPokemon.setVisible(false);
    	btnCambiarPokemon.setVisible(false);
    	imgSeleccionAccion.setVisible(false);
    	
    	//EL RIVAL SACA UN POKEMON
    	lblTexto.setText(rival.getNombre() + " ha sacado a " + equipoRival.get(pokActRival).getMote());
    	
		imgPokemonRival.setImage(new Image(new File("./img/Pokemon/Front/" + equipoRival.get(pokActRival).getNumPokedex() + ".png").toURI().toString()));
    	
    	pbVidaPokemonRival.setStyle("-fx-accent: green;");
    	pbVidaPokemonRival.setProgress(equipoRival.get(pokActRival).getVitalidadAct() / equipoRival.get(pokActRival).getVitalidadMax());
    	
		PauseTransition pausa1 = new PauseTransition(Duration.seconds(2));
    	pausa1.setOnFinished(event1 -> {
    		activarBotonesAtaque();
    	});
		pausa1.play();
    }
    
    @FXML
    void irAMochila(ActionEvent event) {

    }
    
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
    
    private void realizarAtaque(int movPokEntrenador, int movPokRival) {
    	Movimiento movEntr = MovimientoDAO.buscarPorId(con, listaMovPokEntr.get(movPokEntrenador).getIdMovimiento());
        Movimiento movRival = MovimientoDAO.buscarPorId(con, listaMovPokRival.get(movPokRival).getIdMovimiento());
        
        // Compara velocidades
        if (equipoEntrenador.get(pokActEntr).getVelocidad() >= equipoRival.get(pokActRival).getVelocidad()) {
            realizarAtaquePokemon(movEntr, movRival, equipoEntrenador.get(pokActEntr), equipoRival.get(pokActRival), true, true);
        } else {
            realizarAtaquePokemon(movRival, movEntr, equipoRival.get(pokActRival), equipoEntrenador.get(pokActEntr), false, true);
        }
    }

    private void realizarAtaquePokemon(Movimiento movAtacante, Movimiento movDefensor, Pokemon atacante, Pokemon defensor, boolean esTurnoEntrenador, boolean primeraParte) {
        lblTexto.setText(atacante.getMote() + " ha usado " + movAtacante.getNombre());

        PauseTransition pausa1 = new PauseTransition(Duration.seconds(2));
        pausa1.setOnFinished(event1 -> {
            if (movAtacante.getProbabilidad() >= ((int) (Math.random() * 100) + 1)) {
                lblTexto.setText(atacante.getMote() + " ha golpeado a " + defensor.getMote());
                
                actualizarVida(movAtacante, defensor, esTurnoEntrenador);
                
                PauseTransition pausa2 = new PauseTransition(Duration.seconds(2));
                pausa2.setOnFinished(event2 -> {
                    if (defensor.getVitalidadAct() == 0) {
                        manejarPokemonDebilitado(defensor, esTurnoEntrenador);
                    } else if (primeraParte){
                    	realizarAtaquePokemon(movDefensor, movAtacante, defensor, atacante, esTurnoEntrenador ? false : true, false);
                    } else {
                    	activarBotonesAtaque();
                    }
                });
                pausa2.play();
            } else {
                lblTexto.setText(atacante.getMote() + " ha fallado!");
            }
        });
        pausa1.play();
    }
    
    private void actualizarVida(Movimiento movAtacante, Pokemon defensor, boolean esTurnoEntrenador) {
		defensor.setVitalidadAct(defensor.getVitalidadAct() - movAtacante.getPotencia());

		if (defensor.getVitalidadAct() < 0) {
		    defensor.setVitalidadAct(0);
		}

		if (esTurnoEntrenador) {
			actualizarBarraVida(pbVidaPokemonRival, defensor);
		}
		else {
			actualizarBarraVida(pbVidaPokemonEntrenador, defensor);
			
			//BAJAR POCO A POCO EL TEXTO DE LA VIDA DEL POKEMON DEL ENTRENADOR
			double tiempoPorCiclo = 1.5 / movAtacante.getPotencia();
			
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
			timeline.setCycleCount(movAtacante.getPotencia());
			timeline.play();
		}
	}
    
    private void manejarPokemonDebilitado(Pokemon defensor, boolean esTurnoEntrenador) {
        if (esTurnoEntrenador) {
            numPokVivosRival--;
        } else {
            numPokVivosEntr--;
        }

        lblTexto.setText(defensor.getMote() + " ha sido debilitado!");
        
        PauseTransition pausa3 = new PauseTransition(Duration.seconds(2));
        pausa3.setOnFinished(event3 -> {
            if (esTurnoEntrenador && numPokVivosRival == 0 || !esTurnoEntrenador && numPokVivosEntr == 0) {
                desactivarBotonesAtaque();
                uiFinalCombate();
                if (esTurnoEntrenador) {
                    lblTexto.setText("Como has podido vencerme! Te mereces seguir a delante y enfrentarte a Antonio en segundo.");
                } else {
                    lblTexto.setText("Lo he intentado todo pero desde el principio sabia que no eras merecedor de la matricula. No me queda mas remedio que darsela a Adriano.");
                }
                volverAlMenu();
            } else {
                cambiarPokemon(esTurnoEntrenador);
            }
        });
        pausa3.play();
    }

    private void cambiarPokemon(boolean esTurnoEntrenador) {
        if (esTurnoEntrenador) {
            pokActRival++;
            lblTexto.setText(rival.getNombre() + " va a sacar a " + equipoRival.get(pokActRival).getMote() + ". Quieres cambiar de pokemon?");
        } else {
            lblTexto.setText("Quieres sacar otro pokemon?");
        }

        btnCambiarPokemon.setVisible(true);
        btnMantenerPokemon.setText("Mantener");
        btnMantenerPokemon.setVisible(true);
        imgSeleccionAccion.setVisible(true);
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

	private void sinPPs(String movimiento) {
    	desactivarBotonesAtaque();
		
    	lblTexto.setText("El ataque " + movimiento + " no tiene PPs");
    	
    	PauseTransition pausa = new PauseTransition(Duration.seconds(2));
    	pausa.setOnFinished(evento -> {
            activarBotonesAtaque();
        });
    	pausa.play();
    }
	
	private void uiFinalCombate() {
		btnAtacar.setVisible(false);
    	btnRendirse.setVisible(false);
    	btnAccionCambiarPokemon.setVisible(false);
    	btnMochila.setVisible(false);
    	
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
        lblVidaPokemonEntrenador.setVisible(false);
        pbVidaPokemonEntrenador.setVisible(false);
        pbXpPokemonEntrenador.setVisible(false);
	}
    
	private void desactivarBotonesAtaque() {
		btnCancelarAccion.setVisible(false);
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
    	btnCancelarAccion.setVisible(true);
    	lblTexto.setVisible(false);
    	imgSeleccionAtaque.setVisible(true);
    	imgSeleccionAccion.setVisible(true);
    	btnAtacar.setVisible(false);
    	btnRendirse.setVisible(false);
    	btnAccionCambiarPokemon.setVisible(false);
    	btnMochila.setVisible(false);
    	lblPP.setText("PP: ");
    	lblPP.setVisible(true);
    	lblType.setText("Type: ");
    	lblType.setVisible(true);
    	
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
}
