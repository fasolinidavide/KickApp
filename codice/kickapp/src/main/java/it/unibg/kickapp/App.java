package it.unibg.kickapp;

import javafx.application.Application;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.unibg.kickapp.gui.DatiDemo;
import it.unibg.kickapp.gui.Navigazione;

public class App extends Application {

    private static final Logger LOG = LogManager.getLogger(App.class);

    @Override
    public void start(Stage stage) {
        LOG.info("Avvio KickApp");
        DatiDemo.carica();

        stage.setTitle("KickApp");
        Navigazione.setStage(stage);
        Navigazione.vai("login.fxml");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}