package it.unibg.kickapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Punto di ingresso dell'applicazione JavaFX KickApp.
 */
public class App extends Application {

    private static final Logger LOG = LogManager.getLogger(App.class);

    @Override
    public void start(Stage stage) {
        LOG.info("Avvio KickApp");
        var scene = new Scene(new StackPane(new Label("KickApp")), 800, 600);
        stage.setTitle("KickApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
