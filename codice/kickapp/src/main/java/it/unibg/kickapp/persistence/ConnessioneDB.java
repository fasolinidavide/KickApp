package it.unibg.kickapp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Accesso al database embedded H2 (pattern Singleton).
 * Il percorso del file è relativo alla cartella di esecuzione:
 * nessun percorso assoluto, come richiesto dalle regole del progetto.
 */
public final class ConnessioneDB {

    private static final Logger LOG = LogManager.getLogger(ConnessioneDB.class);
    private static final String URL_DEFAULT = "jdbc:h2:./data/kickapp";

    private static ConnessioneDB istanza;

    private final String url;
    private Connection connessione;

    private ConnessioneDB(String url) {
        this.url = url;
    }

    /** Restituisce l'unica istanza, creata al primo utilizzo. */
    public static synchronized ConnessioneDB getIstanza() {
        if (istanza == null) {
            istanza = new ConnessioneDB(URL_DEFAULT);
        }
        return istanza;
    }

    /**
     * Solo per i test: usa un database diverso (es. in memoria).
     * Chiude l'eventuale connessione precedente.
     */
    static synchronized void configuraPerTest(String urlTest) {
        if (istanza != null) {
            istanza.chiudi();
        }
        istanza = new ConnessioneDB(urlTest);
    }

    /** Restituisce una connessione aperta, riaprendola se necessario. */
    public synchronized Connection getConnessione() throws SQLException {
        if (connessione == null || connessione.isClosed()) {
            LOG.debug("Apertura connessione a {}", url);
            connessione = DriverManager.getConnection(url, "sa", "");
        }
        return connessione;
    }

    /** Chiude la connessione, se aperta. */
    public synchronized void chiudi() {
        if (connessione != null) {
            try {
                connessione.close();
            } catch (SQLException e) {
                LOG.warn("Errore in chiusura connessione", e);
            }
            connessione = null;
        }
    }
}
