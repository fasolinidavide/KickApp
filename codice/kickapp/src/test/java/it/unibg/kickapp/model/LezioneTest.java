package it.unibg.kickapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import it.unibg.kickapp.model.enums.Stato;
import it.unibg.kickapp.model.enums.StatoPrenotazione;

/**
 * test:stato iniziale, posti disponibili e lezione piena
 */
class LezioneTest {
    private static final int CAPIENZA = 2;

    private Lezione lezione;
    private Atleta atleta1;
    private Atleta atleta2;
    
    
    void creaLezione() {
        Insegnante insegnante = new Insegnante("Luca", "Bianchi", "luca@gmail.it", "123");
        lezione = new Lezione(insegnante, LocalDate.of(2026, 3, 1), LocalTime.of(18, 45),
                60, "Tecnica", CAPIENZA);
        atleta1 = new Atleta("Mario", "Rossi", "mario@gmail.it", "456", LocalDate.of(2004, 6, 6));
        atleta2 = new Atleta("Anna", "Verdi", "anna@gmail.it", "789", LocalDate.of(2001, 7, 2));
    }
    
    @Test
    void nuovaLezione() {
    	creaLezione();
        assertEquals(Stato.PROGRAMMATA, lezione.getStato());
        assertEquals(CAPIENZA, lezione.postiDisponibili());
        assertFalse(lezione.isPiena());
    }
    
    @Test
    void occupazioneUnPosto() {
    	creaLezione();
        lezione.getPrenotazioni().add(new Prenotazione(atleta1, lezione));
        assertEquals(CAPIENZA - 1, lezione.postiDisponibili());
    }
    
    @Test
    void prenotazioneDisdettaNonOccupaPosti(){
    	creaLezione();
    	Prenotazione p = new Prenotazione(atleta1, lezione);
        p.setStato(StatoPrenotazione.DISDETTA);
        lezione.getPrenotazioni().add(p);
        assertEquals(CAPIENZA, lezione.postiDisponibili());
    }
    
    @Test
    void pienaConPrenotazioniAttiveCapienzaMassima() {
    	creaLezione();
        lezione.getPrenotazioni().add(new Prenotazione(atleta1, lezione));
        lezione.getPrenotazioni().add(new Prenotazione(atleta2, lezione));
        assertEquals(0, lezione.postiDisponibili());
        assertTrue(lezione.isPiena());
    }
    
    @Test
    void nonPienaSeUnaPrenotazioneVieneDisdetta() {
    	creaLezione();
        Prenotazione p1 = new Prenotazione(atleta1, lezione);
        lezione.getPrenotazioni().add(p1);
        lezione.getPrenotazioni().add(new Prenotazione(atleta2, lezione));
        p1.setStato(StatoPrenotazione.DISDETTA);
        assertFalse(lezione.isPiena());
    }
    
}
