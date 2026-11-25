package it.unibg.kickapp.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigazione {

    private static Stage stage;

    private Navigazione() {
    }

    public static void setStage(Stage s) {
        stage = s;
    }

    public static void vai(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(Navigazione.class.getResource("/it/unibg/kickapp/gui/" + fxml));
            stage.setScene(new Scene(loader.load()));
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException("Impossibile caricare " + fxml, e);
        }
    }
}