/**
 * Modulo principale di KickApp.
 *
 * ATTENZIONE: si può fare "exports"/"opens" solo di package che contengono
 * almeno una classe (package-info.java non basta, altrimenti errore
 * "package is empty or does not exist"). Togliete il commento alle righe
 * quando aggiungete le prime classi in quei package.
 */
module it.unibg.kickapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires org.apache.logging.log4j;
    requires com.h2database; // driver JDBC del database embedded

    exports it.unibg.kickapp;
    exports it.unibg.kickapp.persistence;
    exports it.unibg.kickapp.model;
    exports it.unibg.kickapp.model.enums;
    exports it.unibg.kickapp.service;
    exports it.unibg.kickapp.gui;

    // solo se userete file FXML con i controller nel package gui:
    opens it.unibg.kickapp.gui to javafx.fxml;
}
