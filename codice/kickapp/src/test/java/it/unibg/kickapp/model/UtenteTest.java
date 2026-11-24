package it.unibg.kickapp.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * test: gestione delle password
 */
class UtenteTest {
    private static final String PASSWORD = "pass123";

    private Utente utente;
    
    void creaUtente() {
        utente = new Amministratore("Mario","Rossi","rossi@gmail.it", PASSWORD);
    }
    
   @Test
   void passwordCorrettaAccettata() {
	   creaUtente();
	   assertTrue(utente.verificaPassword(PASSWORD));
   }
   
   @Test
   void passwordSbagliataRifiutata() {
	   creaUtente();
	   assertFalse(utente.verificaPassword("123"));
   }
   
   @Test
   void passwordMaiuscola() {
	   creaUtente();
	   assertFalse(utente.verificaPassword("PASS123"));
   }   
}
