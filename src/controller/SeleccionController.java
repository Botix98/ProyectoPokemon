package controller;

import java.sql.Connection;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SeleccionController {
	
	private int idPoke;
	public boolean sonido = false;
	
	private Connection con;
	
    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgPokemon1;

    @FXML
    private ImageView imgPokemon2;

    @FXML
    private ImageView imgPokemon3;

    @FXML
    private ImageView imgSonido;

    @FXML
    void activarDesactivarSonido(MouseEvent event) {

    }

    @FXML
    void elegirPoke1(MouseEvent event) {
    	elegirPoke(idPoke);
    }

    @FXML
    void elegirPoke2(MouseEvent event) {
    	elegirPoke(idPoke);
    }

    @FXML
    void elegirPoke3(MouseEvent event) {
    	elegirPoke(idPoke);
    }
    
    private void elegirPoke(int idPoke) {
    	
    }

}
