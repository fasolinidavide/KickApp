package it.unibg.kickapp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.unibg.kickapp.gui.DatiDemo;

public class App extends Application {

    private static final Logger LOG = LogManager.getLogger(App.class);

    @Override
    public void start(Stage stage) throws IOException {
        LOG.info("Avvio KickApp");
        DatiDemo.carica();

        FXMLLoader loader = new FXMLLoader(App.class.getResource("/it/unibg/kickapp/gui/login.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("KickApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}