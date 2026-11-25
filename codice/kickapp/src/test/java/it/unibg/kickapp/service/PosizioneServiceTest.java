package it.unibg.kickapp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import it.unibg.kickapp.model.Abbonamento;
import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.enums.TipoAbbonamento;

/**
 * test: certifacati medici e abbonamenti
 */
class PosizioneServiceTest {

    private PosizioneService service;
    private Atleta mario;
    private LocalDate data;

    void setUp() {
        service = new PosizioneService();
        mario = new Atleta("Mario", "Rossi", "mario@test.it", "pwd", LocalDate.of(2000, 5, 1));
        data = LocalDate.of(2026, 1, 10);
    }
    
    @Test
    void certificatoConScadenzaPrimaDelRilascioDaErrore() {
    	setUp();
        boolean errore = false;
        try {
            service.registraCertificato(mario, data, data.minusDays(1));
        } catch (IllegalArgumentException e) {
            errore = true;
        }
        assertTrue(errore);
    }
    
    @Test 
    void rinnovaAbbonamento(){
    	setUp();
    	Abbonamento abb = service.rinnovaAbbonamento(mario, TipoAbbonamento.ANNUALE, data, 450);
    	assertTrue(mario.getAbbonamenti().contains(abb));
    }
    
}