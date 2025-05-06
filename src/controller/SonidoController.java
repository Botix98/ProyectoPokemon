package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SonidoController {

    private static MediaPlayer mediaPlayerFondo;
    private static String rutaActualFondo;
    private static boolean sonidoActivo = true;

    public static void reproducirEfecto(String rutaSonidoEfecto, Runnable onFinish) {
        if (!sonidoActivo) return;

        Media media = new Media(new File(rutaSonidoEfecto).toURI().toString());
        MediaPlayer efecto = new MediaPlayer(media);
        efecto.setVolume(1);

        if (onFinish != null) {
            efecto.setOnEndOfMedia(onFinish);
        }

        efecto.play();
    }

    public static void reproducirFondo(String rutaSonidoFondo) {
        detenerFondo(rutaSonidoFondo);

        if (!sonidoActivo) {
            rutaActualFondo = rutaSonidoFondo;
            return;
        }

        rutaActualFondo = rutaSonidoFondo;

        Media media = new Media(new File(rutaSonidoFondo).toURI().toString());
        mediaPlayerFondo = new MediaPlayer(media);
        mediaPlayerFondo.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayerFondo.setVolume(1);
        mediaPlayerFondo.play();
    }

    public static void detenerFondo(String string) {
        if (mediaPlayerFondo != null) {
            mediaPlayerFondo.stop();
            mediaPlayerFondo = null;
        }
    }

    public static void pausarFondo(String string) {
        if (mediaPlayerFondo != null) {
            mediaPlayerFondo.pause();
        }
    }

    public static void continuarFondo(String string) {
        if (!sonidoActivo) return;

        if (mediaPlayerFondo != null) {
            mediaPlayerFondo.play();
        } else if (rutaActualFondo != null) {
            reproducirFondo(rutaActualFondo);
        }
    }

    public static void activarSonido() {
        sonidoActivo = true;
        continuarFondo(rutaActualFondo);
    }

    public static void desactivarSonido() {
        sonidoActivo = false;
        pausarFondo(rutaActualFondo);
    }

    public static boolean estaSonidoActivo() {
        return sonidoActivo;
    }

}