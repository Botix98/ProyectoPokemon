package main;

import java.io.File;
import java.sql.Connection;

import controller.LoginController;
import dao.ConexionBD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	
        File logs = new File("C:/ProyectoPokemon/logs");

        if (!logs.exists()) {
            if (logs.mkdir()) {
                System.out.println("Carpeta creada exitosamente.");
            } else {
                System.out.println("No se pudo crear la carpeta.");
            }
        } else {
            System.out.println("La carpeta ya existe.");
        }
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
    	Parent root = loader.load();
    	Scene scene = new Scene(root);
    	
    	primaryStage.setTitle("Proyecto Pokemon: Los Apendices");
    	primaryStage.setScene(scene);
    	
    	LoginController controller = loader.getController();
    	controller.setStage(primaryStage);
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
