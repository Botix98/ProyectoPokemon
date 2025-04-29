package controller;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SonidoController {
    private static MediaPlayer mediaPlayer;

    //el String se llama rutaSonido porque será la ruta del audio
    public static void reproducir(String rutaSonido) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Media media = new Media(new File(rutaSonido).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(1);
        mediaPlayer.play();
    }

    public static void detener() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void pausar() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public static void continuar() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }
}