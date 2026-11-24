package it.unibg.kickapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.Insegnante;
import it.unibg.kickapp.model.Utente;

/**
 * test: login e logout.
 */

class AutenticazioneTest {
    private AccountService accountService;
    private AutenticazioneService auth;
    private Atleta mario;

    void setUp() {
        accountService = new AccountService();
        auth = new AutenticazioneService(accountService);
        mario = accountService.creaAccount("Mario", "Rossi", "mario@gmail.com", "pass123", LocalDate.of(2000, 1, 1));
    }

    @Test
    void loginRiuscito() {
    	setUp();
        accountService.accettaAtleta(mario);
        Utente u = auth.login("mario@gmail.com", "pass123");
        assertEquals(mario, u);
        assertEquals(mario, auth.getUtenteCorrente());
    }
    
    @Test 
    void emailMaiuscola() {
    	setUp();
    	accountService.accettaAtleta(mario);
    	assertEquals(mario, auth.login("MARIO@gmail.com", "pass123"));
    }
    
    @Test 
    void passwordMaiuscola() {
    	setUp();
    	accountService.accettaAtleta(mario);
    	assertTrue(auth.login("mario@gmail.com", "PASS123") == null);
    	assertTrue(auth.getUtenteCorrente() == null); 
    }
    
    @Test 
    void passwordSbagliata() {
    	setUp();
    	accountService.accettaAtleta(mario);
    	assertTrue(auth.login("mario@gmail.com", "pas123") == null);
    	assertTrue(auth.getUtenteCorrente() == null);    	
    }
    
    @Test
    void loginFallitoMail() {
    	setUp();
        assertTrue(auth.login("luca@test.com", "pass123") == null);
    }
    
    @Test
    void atletaInAttesaNonAccede() {
    	setUp();
        assertTrue(auth.login("mario@gmail.com", "pass123") == null);
    }

    @Test
    void atletaRifiutatoNonPuoAccedere() {
    	setUp();
        accountService.rifiutaAtleta(mario);
        assertTrue(auth.login("mario@gmail.com", "pass123") == null);
    }
    
}
