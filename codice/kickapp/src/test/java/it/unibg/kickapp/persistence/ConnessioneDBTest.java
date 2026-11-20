package it.unibg.kickapp.persistence;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ConnessioneDBTest {

    @BeforeAll
    static void usaDatabaseInMemoria() {
        ConnessioneDB.configuraPerTest("jdbc:h2:mem:kickapp_test;DB_CLOSE_DELAY=-1");
    }

    @AfterAll
    static void chiudi() {
        ConnessioneDB.getIstanza().chiudi();
    }

    @Test
    void singletonRestituisceSempreLaStessaIstanza() {
        assertSame(ConnessioneDB.getIstanza(), ConnessioneDB.getIstanza());
    }

    @Test
    void connessioneValida() throws SQLException {
        Connection c = ConnessioneDB.getIstanza().getConnessione();
        assertTrue(c.isValid(2));
    }
}
