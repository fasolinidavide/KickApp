package it.unibg.kickapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibg.kickapp.model.Insegnante;
import it.unibg.kickapp.model.Lezione;
import it.unibg.kickapp.model.enums.Stato;

/**
 * test: creazione della lezione, limitazioni del calendario
 */
class LezioneServiceTest {

    private static final LocalDate GIORNO = LocalDate.of(2026, 1, 25);
    private static final LocalTime ORA = LocalTime.of(18, 45);

    private LezioneService service;
    private Insegnante luca;

    void setUp() {
        service = new LezioneService();
        luca = new Insegnante("Luca", "bianchi", "luca@gmail.com", "123");
    }

    @Test
    void creaLezioneLaAggiungeAllInsegnante() {
    	setUp();
        Lezione l = service.creaLezione(luca, GIORNO, ORA, 60, "Kickboxing", 10);
        assertEquals(Stato.PROGRAMMATA, l.getStato());
        assertTrue(luca.getLezioni().contains(l));
    }
    
    @Test
    void calendarioLimiti() {
    	setUp();
        Lezione prima = service.creaLezione(luca, GIORNO.minusDays(1), ORA, 60, "K", 10);
        Lezione inizio = service.creaLezione(luca, GIORNO, ORA, 60, "K", 10);
        Lezione fine = service.creaLezione(luca, GIORNO.plusDays(6), ORA, 60, "K", 10);
        Lezione dopo = service.creaLezione(luca, GIORNO.plusDays(7), ORA, 60, "K", 10);

        List<Lezione> calendario = service.getCalendario(GIORNO, GIORNO.plusDays(6));

        assertEquals(List.of(inizio, fine), calendario);
        assertFalse(calendario.contains(prima));
        assertFalse(calendario.contains(dopo));
    }
    
 
}