package controller;

import java.io.File;
import java.sql.Connection;
import java.util.LinkedList;

import dao.ConexionBD;
import dao.PokedexDAO;
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
	
	private int contPausas;
	
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
    
    @FXML
    private Button btnCambiarPokemon;

    @FXML
    private Button btnMantenerPokemon;
    
    @FXML
    private Button btnCancelarAccion;

    public void init(Entrenador entr, Stage stage, LoginController loginController, MenuController menuController) {
        this.entrenador = entr;
        this.stage = stage;
        this.loginController = loginController;
        this.menuController = menuController;
        
        prepararCombate();
        calcularPokemonVivos();
    }
    
    private void prepararCombate() {
    	//PRUEBA
    	Movimiento[] movimientos = {new Movimiento("Mira mi cuchara", 10, contPausas, contPausas, contPausas, "Ataque", 10, "Psiquico", null, 1, null, 100),
    			new Movimiento("Ahora me ves, ahora ya no", 15, contPausas, contPausas, contPausas, "Especial",10, "Psiquico", null, 1, null, 100),
    			new Movimiento("Cuidao que te tropiezas", 10, contPausas, contPausas, contPausas, "Ataque", 10, "Psiquico", null, 1, null, 80),
    			null};
    	
    	Movimiento[] movimientos2 = {new Movimiento("Esto esta mal", 10, contPausas, contPausas, contPausas, "Ataque", 1000, "Psiquico", null, 1, null, 100),
    			new Movimiento("Ya no se que hacer", 15, contPausas, contPausas, contPausas, "Especial",10, "Psiquico", null, 1, null, 100),
    			null,
    			null};

    	Pokemon[] equipo = {new Pokemon("Mewtwo", 416, 350, 447, 306, 306, 1, 100, null, movimientos),
    			new Pokemon("Pikachu", 416, 350, 447, 306, 306, 1000, 100, null, movimientos2), null, null, null, null};
    	
    	rival = new Rival("Luisre", equipo);
    	
    	lblTexto.setText(rival.getNombre() + " te ha retado a un combate pokemon!");
    	
    	PauseTransition primeraPausa = new PauseTransition(Duration.seconds(2));
    	primeraPausa.setOnFinished(event -> {
            lblTexto.setText(rival.getNombre() + " saca a " + rival.getPokemon(0).getNombre());
        });
    	
    	PauseTransition segundaPausa = new PauseTransition(Duration.seconds(2));
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
    	
    	PauseTransition terceraPausa = new PauseTransition(Duration.seconds(2));
    	terceraPausa.setOnFinished(event -> {
            lblTexto.setText(entrenador.getUsuario() + " saca a " + entrenador.getPokemon(0).getNombre());
        });
    	
    	PauseTransition cuartaPausa = new PauseTransition(Duration.seconds(2));
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
    	
    	PauseTransition quintaPausa = new PauseTransition(Duration.seconds(2));
    	quintaPausa.setOnFinished(event -> {
            lblTexto.setText("Elige una opci�n");
            imgSeleccionAccion.setVisible(true);
            btnAtacar.setVisible(true);
            btnRendirse.setVisible(true);
        });
    	
    	SequentialTransition secuencia = new SequentialTransition(primeraPausa, segundaPausa, terceraPausa, cuartaPausa, quintaPausa);
        secuencia.play();
    }

	private void calcularPokemonVivos() {
		pokActEntr = 0;
    	pokActRival = 0;
    	
    	for (int i = 0; i < entrenador.getEquipo().length; i++) {
    		if (entrenador.getPokemon(i) != null) {
    			if (entrenador.getPokemon(i).getVitalidadActual() > 0) {
        			numPokVivosEntr++;
        		}
    		}
    		if (rival.getPokemon(i) != null) {
    			if (rival.getPokemon(i).getVitalidadActual() > 0) {
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
    	
    	lblTexto.setText("Ha ha ha ha ha! C�mo te atreves a huir! Cobarde!! No te mereces aprobar el Proyecto Pokemon!");
    	
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
    	lblPP.setText("PP: " + entrenador.getPokemon(pokActEntr).getMovimiento(0).getPpActuales() + "/" + entrenador.getPokemon(pokActEntr).getMovimiento(0).getPpMax());
    	lblType.setText("Type: " + entrenador.getPokemon(pokActEntr).getMovimiento(0).getTipo());
    }

    @FXML
    void entrarAtaque2(MouseEvent event) {
    	lblPP.setText("PP: " + entrenador.getPokemon(pokActEntr).getMovimiento(1).getPpActuales() + "/" + entrenador.getPokemon(pokActEntr).getMovimiento(1).getPpMax());
    	lblType.setText("Type: " + entrenador.getPokemon(pokActEntr).getMovimiento(1).getTipo());
    }

    @FXML
    void entrarAtaque3(MouseEvent event) {
    	lblPP.setText("PP: " + entrenador.getPokemon(pokActEntr).getMovimiento(2).getPpActuales() + "/" + entrenador.getPokemon(pokActEntr).getMovimiento(2).getPpMax());
    	lblType.setText("Type: " + entrenador.getPokemon(pokActEntr).getMovimiento(2).getTipo());
    }

    @FXML
    void entrarAtaque4(MouseEvent event) {
    	lblPP.setText("PP: " + entrenador.getPokemon(pokActEntr).getMovimiento(3).getPpActuales() + "/" + entrenador.getPokemon(pokActEntr).getMovimiento(3).getPpMax());
    	lblType.setText("Type: " + entrenador.getPokemon(pokActEntr).getMovimiento(3).getTipo());
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
    	if (entrenador.getPokemon(pokActEntr).getMovimiento(0).getPpActuales() > 0) {
    		entrenador.getPokemon(pokActEntr).getMovimiento(0).setPpActuales(entrenador.getPokemon(pokActEntr).getMovimiento(0).getPpActuales() - 1);
    		
    		desactivarBotonesAtaque();
    		
    		int ataqueRival = seleccionarAtaqueRival();
    		
    		realizarAtaque(0, ataqueRival);
    		
    		//activarBotonesAtaque();
    		
    		//HAY QUE ACTIVAR LOS BOTONES CUANDO SE HAGA TODA LA SECUENCIA DEL ATAQUE-------------------------------------------------
    		/*PauseTransition pausa = new PauseTransition(Duration.seconds(6));
	    	pausa.setOnFinished(evento -> {
	    		activarBotonesAtaque();
	        });
	    	pausa.play();*/
    	}
    	else {
    		sinPPs(entrenador.getPokemon(pokActEntr).getMovimiento(0).getNombre());
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
    
    @FXML
    void cancelarAccion(ActionEvent event) {
    	desactivarBotonesAtaque();
    	
    	btnAtacar.setVisible(true);
    	btnRendirse.setVisible(true);
    	imgSeleccionAccion.setVisible(true);
    }
    
    @FXML
    void cambiarPokemon(ActionEvent event) {
    	btnMantenerPokemon.setVisible(false);
    	btnCambiarPokemon.setVisible(false);
    	
    	//MANDAR A LA VENTANA DE EQUIPO
    	//EL RIVAL SACA UN POKEMON
    }
    
    @FXML
    void mantenerPokemon(ActionEvent event) {
    	btnMantenerPokemon.setVisible(false);
    	btnCambiarPokemon.setVisible(false);
    	
    	System.out.println(rival.getNombre() + " ha sacado a " + rival.getPokemon(pokActRival).getNombre());
    	//EL RIVAL SACA UN POKEMON
    }
    
    private int seleccionarAtaqueRival() {
		while(true) {
			int n = (int) (Math.random() * 3) + 0;
			if (rival.getPokemon(pokActRival).getMovimiento(n) != null) {
				if (rival.getPokemon(pokActRival).getMovimiento(n).getPpActuales() > 0) {
					rival.getPokemon(pokActRival).getMovimiento(n).setPpActuales(rival.getPokemon(pokActRival).getMovimiento(n).getPpActuales() - 1);
					return n;
				}
			}
		}
	}
    
    private void realizarAtaque(int movPokEntrenador, int movPokRival) {
    	contPausas = 0;
    	
    	if (entrenador.getPokemon(pokActEntr).getVelocidad() >= rival.getPokemon(pokActRival).getVelocidad()) {
    		lblTexto.setText(entrenador.getPokemon(pokActEntr).getNombre() + " ha usado " + entrenador.getPokemon(pokActEntr).getMovimiento(movPokEntrenador).getNombre());
    		
    		PauseTransition pausa1 = new PauseTransition(Duration.seconds(2));
        	pausa1.setOnFinished(event1 -> {
        		contPausas++;
        		
        		if (entrenador.getPokemon(pokActEntr).getMovimiento(movPokEntrenador).getProbabilidad() >= ((int) (Math.random() * 100) + 1)){
            	    lblTexto.setText(entrenador.getPokemon(pokActEntr).getNombre() + " ha golpeado a " + rival.getPokemon(pokActRival).getNombre());
        			rival.getPokemon(pokActRival).setVitalidadActual(rival.getPokemon(pokActRival).getVitalidadActual() - entrenador.getPokemon(pokActEntr).getMovimiento(movPokEntrenador).getPotencia());
        			
        			if (rival.getPokemon(pokActRival).getVitalidadActual() < 0) {
        				rival.getPokemon(pokActRival).setVitalidadActual(0);
        			}
        			
        			actualizarBarraVida(pbVidaPokemonRival, rival.getPokemon(pokActRival));
        		}
        		else {
        			lblTexto.setText(entrenador.getPokemon(pokActEntr).getNombre() + " ha fallado!");
        		}
        		
        		PauseTransition pausa2 = new PauseTransition(Duration.seconds(2));
            	pausa2.setOnFinished(event2 -> {
            		contPausas++;
            		if (rival.getPokemon(pokActRival).getVitalidadActual() == 0) {
            			numPokVivosRival--;
            			lblTexto.setText(rival.getPokemon(pokActRival).getNombre() + " ha sido debilitado!");
            			
            			PauseTransition pausa3 = new PauseTransition(Duration.seconds(2));
                    	pausa3.setOnFinished(event3 -> {
                    		contPausas++;
                    		
                    		if (numPokVivosRival == 0) {
                				desactivarBotonesAtaque();
                				uiFinalCombate();
                				
                				lblTexto.setText("Como has podido vencerme! Te mereces seguir a delante y enfrentarte a Antonio en segundo.");
                				volverAlMenu();
                			}
                			else {
                				pokActRival++;
                				lblTexto.setText(rival.getNombre() + " va a sacar a " + rival.getPokemon(pokActRival).getNombre() + ". �Quieres cambiar de pokemon?");
                				
                		    	btnCambiarPokemon.setVisible(true);
                		    	btnMantenerPokemon.setText("Mantener");
                		    	btnMantenerPokemon.setVisible(true);
                		    	//AJUSTAR LA APARICION DE LAS COSAS
                			}
                    	});
                    	pausa3.play();
            		}
            		else {
            			lblTexto.setText(rival.getPokemon(pokActRival).getNombre() + " ha usado " + rival.getPokemon(pokActRival).getMovimiento(movPokRival).getNombre());
        	    		
            			PauseTransition pausa3 = new PauseTransition(Duration.seconds(2));
                    	pausa3.setOnFinished(event3 -> {
                    		contPausas++;
                    		
                    		if (rival.getPokemon(pokActRival).getMovimiento(movPokRival).getProbabilidad() >= ((int) (Math.random() * 100) + 1)){
                    			lblTexto.setText(rival.getPokemon(pokActRival).getNombre() + " ha golpeado a " + entrenador.getPokemon(pokActEntr).getNombre());
                    			entrenador.getPokemon(pokActEntr).setVitalidadActual(entrenador.getPokemon(pokActEntr).getVitalidadActual() - rival.getPokemon(pokActRival).getMovimiento(movPokRival).getPotencia());
            	    			
                    			if (entrenador.getPokemon(pokActEntr).getVitalidadActual() <= 0) {
                    				entrenador.getPokemon(pokActEntr).setVitalidadActual(0);
                    			}
                    			
                    			actualizarBarraVida(pbVidaPokemonEntrenador, entrenador.getPokemon(pokActEntr));
                    		}
            	    		else {
            	    			lblTexto.setText(rival.getPokemon(pokActRival).getNombre() + " ha fallado!");
            	    		}
            	    		
                    		PauseTransition pausa4 = new PauseTransition(Duration.seconds(2));
                        	pausa4.setOnFinished(event4 -> {
                        		contPausas++;
                        		
                        		if (entrenador.getPokemon(pokActEntr).getVitalidadActual() == 0) {
                	    			numPokVivosEntr--;
                	    			lblTexto.setText(entrenador.getPokemon(pokActEntr).getNombre() + " ha sido debilitado!");
                	    			
                	    			PauseTransition pausa5 = new PauseTransition(Duration.seconds(2));
                                	pausa5.setOnFinished(event5 -> {
                                		contPausas++;
                                		
                                		if (numPokVivosEntr == 0) {
                    	    				desactivarBotonesAtaque();
                    	    				uiFinalCombate();
                    	    				
                    	    				lblTexto.setText("Lo he intentado todo pero desde el principio sab�a que no eras merecedor de la matr�cula. No me queda m�s remedio que darsela a Adriano.");
                    	    				volverAlMenu();
                    	    			}
                    	    			else {
                    	    				lblTexto.setText("Quieres sacar otro pokemon?");
                    	    				btnCambiarPokemon.setVisible(true);
                    	    				btnMantenerPokemon.setText("Rendirse");
                    	    				btnMantenerPokemon.setVisible(true);
                    	    				//HAY QUE ABRIR LA ESCENA DEL EQUIPO PARA QUE ELIGA UN POKEMON?
                    	    		    	//ACTIVAR BOTONES DE SELECCION DE ACCION
                    	    			}
                                	});
                                	pausa5.play();
                	    		}
                        	});
                        	pausa4.play();
                    	});
                    	pausa3.play();
            		}
            	});
            	pausa2.play();
            });
        	pausa1.play();
    	}
    	//CUANDO EL POKEMON RIVAL TENGA MAS VELOCIDAD QUE EL POKEMON DEL ENTRENADOR
    	else {
    		lblTexto.setText(rival.getPokemon(pokActRival).getNombre() + " ha usado " + rival.getPokemon(pokActRival).getMovimiento(movPokRival).getNombre());
    		
    		PauseTransition pausa1 = new PauseTransition(Duration.seconds(2));
        	pausa1.setOnFinished(event1 -> {
        		contPausas++;
        		
        		if (rival.getPokemon(pokActRival).getMovimiento(movPokRival).getProbabilidad() >= ((int) (Math.random() * 100) + 1)){
        			lblTexto.setText(rival.getPokemon(pokActRival).getNombre() + " ha golpeado a " + entrenador.getPokemon(pokActEntr).getNombre());
        			entrenador.getPokemon(pokActEntr).setVitalidadActual(entrenador.getPokemon(pokActEntr).getVitalidadActual() - rival.getPokemon(pokActRival).getMovimiento(movPokRival).getPotencia());
        			
        			if (entrenador.getPokemon(pokActEntr).getVitalidadActual() < 0) {
        				entrenador.getPokemon(pokActEntr).setVitalidadActual(0);
        			}
        			
        		}
        		else {
        			lblTexto.setText(rival.getPokemon(pokActRival).getNombre() + " ha fallado!");
        		}
        		
        		PauseTransition pausa2 = new PauseTransition(Duration.seconds(2));
            	pausa2.setOnFinished(event2 -> {
            		contPausas++;
            		
            		if (entrenador.getPokemon(pokActEntr).getVitalidadActual() == 0) {
            			numPokVivosEntr--;
            			lblTexto.setText(entrenador.getPokemon(pokActEntr).getNombre() + " ha sido debilitado!");
            			
            			PauseTransition pausa3 = new PauseTransition(Duration.seconds(2));
                    	pausa3.setOnFinished(event3 -> {
                    		contPausas++;
                    		
                    		if (numPokVivosEntr == 0) {
                				desactivarBotonesAtaque();
                				uiFinalCombate();
                				
                				lblTexto.setText("Lo he intentado todo pero desde el principio sab�a que no eras merecedor de la matr�cula. No me queda m�s remedio que darsela a Adriano.");
                				volverAlMenu();
                			}
                			else {
                				lblTexto.setText("Quieres sacar otro pokemon?");
                				btnCambiarPokemon.setVisible(true);
                				btnMantenerPokemon.setText("Rendirse");
                				btnMantenerPokemon.setVisible(true);
                				//HAY QUE ABRIR LA ESCENA DEL EQUIPO PARA QUE ELIGA UN POKEMON?
                		    	//ACTIVAR BOTONES DE SELECCION DE ACCION
                			}
                    	});
            			pausa3.play();
            		}
            		else {
            			lblTexto.setText(entrenador.getPokemon(pokActEntr).getNombre() + " ha usado " + entrenador.getPokemon(pokActEntr).getMovimiento(movPokEntrenador).getNombre());
        	    		
            			PauseTransition pausa3 = new PauseTransition(Duration.seconds(2));
                    	pausa3.setOnFinished(event3 -> {
                    		contPausas++;
                    		
                    		if (entrenador.getPokemon(pokActEntr).getMovimiento(movPokEntrenador).getProbabilidad() >= ((int) (Math.random() * 100) + 1)){
            	    			lblTexto.setText(entrenador.getPokemon(pokActEntr).getNombre() + " ha golpeado a " + rival.getPokemon(pokActRival).getNombre());
            	    			rival.getPokemon(pokActRival).setVitalidadActual(rival.getPokemon(pokActRival).getVitalidadActual() - entrenador.getPokemon(pokActEntr).getMovimiento(movPokEntrenador).getPotencia());
            	    			
            	    			if (rival.getPokemon(pokActRival).getVitalidadActual() < 0) {
            	    				rival.getPokemon(pokActRival).setVitalidadActual(0);
            	    			}
            	    			
            	    		}
            	    		else {
            	    			lblTexto.setText(entrenador.getPokemon(pokActEntr).getNombre() + " ha fallado!");
            	    		}
            	    		
                    		PauseTransition pausa4 = new PauseTransition(Duration.seconds(2));
                        	pausa4.setOnFinished(event4 -> {
                        		contPausas++;
                        		
                        		if (rival.getPokemon(pokActRival).getVitalidadActual() == 0) {
                	    			numPokVivosRival--;
                	    			lblTexto.setText(rival.getPokemon(pokActRival).getNombre() + " ha sido debilitado!");
                	    			
                	    			PauseTransition pausa5 = new PauseTransition(Duration.seconds(2));
                                	pausa5.setOnFinished(event5 -> {
                                		contPausas++;
                                		
                                		if (numPokVivosRival == 0) {
                    	    				desactivarBotonesAtaque();
                    	    				uiFinalCombate();
                    	    				
                    	    				lblTexto.setText("Como has podido vencerme! Te mereces seguir a delante y enfrentarte a Antonio en segundo.");
                    	    				volverAlMenu();
                    	    			}
                    	    			else {
                    	    				pokActRival++;
                    	    				lblTexto.setText(rival.getNombre() + " va a sacar a " + rival.getPokemon(pokActRival).getNombre() + ". �Quieres cambiar de pokemon?");
                    	    				
                    	    		    	btnCambiarPokemon.setVisible(true);
                    	    		    	btnMantenerPokemon.setText("Mantener");
                    	    		    	btnMantenerPokemon.setVisible(true);
                    	    		    	//AJUSTAR LA APARICION DE LAS COSAS
                    	    			}
                                	});
                                	pausa5.play();
                	    		}
                        	});
                    		pausa4.play();
                    	});
            			pausa3.play();
            		}
            	});
            	pausa2.play();
        	});
        	pausa1.play();
    	}
    	
    	//SI ME DICE DE CAMBIAR DE POKEMON Y NO LE DOY RAPIDO A LA OPCION SE EJECUTA LA PAUSA FINAL
    	PauseTransition pausaFinal = new PauseTransition(Duration.seconds(12));
    	pausaFinal.setOnFinished(eventFinal -> {
    		activarBotonesAtaque();
    	});
		pausaFinal.play();
    }

	private void actualizarBarraVida(ProgressBar pb, Pokemon pokemon) {
		double porcentajeFinal = ((double) (pokemon.getVitalidadActual())) / pokemon.getVitalidadMax();
		
		Timeline timeline = new Timeline(
		    new KeyFrame(Duration.seconds(0.025), e -> {
		        double current = pb.getProgress();
		        
		        if (current < 0.25){
		        	pb.setStyle("-fx-accent: yellow;");
		        }
		        else if (current < 0.5){
		        	pb.setStyle("-fx-accent: red;");
		        }
		        
		        if (current > porcentajeFinal) {
		        	pb.setProgress(current - 0.01);
		        }
		    })
		);
		timeline.setCycleCount(100); // 100 pasos de 0.01 = de 1.0 a 0.0
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
    	lblPP.setText("PP: ");
    	lblPP.setVisible(true);
    	lblType.setText("Type: ");
    	lblType.setVisible(true);
    	btnAtaque1.setText(entrenador.getPokemon(pokActEntr).getMovimiento(0).getNombre());
    	btnAtaque1.setVisible(true);
    	btnAtaque2.setText(entrenador.getPokemon(pokActEntr).getMovimiento(1).getNombre());
    	btnAtaque2.setVisible(true);
    	btnAtaque3.setText(entrenador.getPokemon(pokActEntr).getMovimiento(2).getNombre());
    	btnAtaque3.setVisible(true);
    	btnAtaque4.setText(entrenador.getPokemon(pokActEntr).getMovimiento(3).getNombre());
    	btnAtaque4.setVisible(true);
    }
}
