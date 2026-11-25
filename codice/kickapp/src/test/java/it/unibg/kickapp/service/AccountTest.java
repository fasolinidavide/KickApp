package it.unibg.kickapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.enums.StatoAccount;

/**
 * Test di unità per {@link AccountService}: creazione, accettazione,
 * ricerca e rimozione degli account.
 */
class AccountTest {
	private AccountService service;
    private Atleta mario;

    void setUp() {
        service = new AccountService();
        mario = service.creaAccount("Mario", "Rossi", "mario@gmail.com", "123", LocalDate.of(2000, 5, 1));
    }
    
    @Test
    void nuovoUtenteInAttesa() {
    	setUp();
        assertEquals(StatoAccount.IN_ATTESA, mario.getStato());
        assertTrue(service.getUtenti().contains(mario));
    }
    
    @Test
    void accettaAtleta() {
    	setUp();
    	service.accettaAtleta(mario);
    	assertEquals(StatoAccount.ATTIVO, mario.getStato());
    }
    
    @Test 
    void rifiutaAtleta() {
    	setUp();
    	service.rifiutaAtleta(mario);
    	assertEquals(StatoAccount.RIFIUTATO, mario.getStato());
    }
    
    @Test
    void cercaAtletaMaiuscole() {
    	setUp();
    	assertEquals(List.of(mario), service.cercaAtleta("MAR"));
        assertEquals(List.of(mario), service.cercaAtleta("rossi"));
        assertEquals(List.of(mario), service.cercaAtleta("@gmail.it"));
        assertTrue(service.cercaAtleta("bianchi").isEmpty());
    }
    
    @Test
    void modificaAtletaAggiornaIDati() {
    	setUp();
        service.modificaAtleta(mario, "Marco", "blu", "marco@test.it");
        assertEquals("Marco", mario.getNome());
        assertEquals("blu", mario.getCognome());
        assertEquals("marco@gmil.com", mario.getEmail());
    }
    
    @Test
    void rimuoviAtleta() {
    	setUp();
        service.rimuoviAtleta(mario);
        assertFalse(service.getUtenti().contains(mario));
    }
}
