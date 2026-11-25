package it.unibg.kickapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import it.unibg.kickapp.model.Abbonamento;
import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.CertificatoMedico;
import it.unibg.kickapp.model.Insegnante;
import it.unibg.kickapp.model.Lezione;
import it.unibg.kickapp.model.Prenotazione;
import it.unibg.kickapp.model.enums.StatoAccount;
import it.unibg.kickapp.model.enums.StatoPrenotazione;
import it.unibg.kickapp.model.enums.TipoAbbonamento;

/**
 * test: verifica funzionamento observer, prenotazioni in regole e non,
 * limiti sulla prenotazione della lezione
 */
class PrenotazioneServiceTest {
	   private PrenotazioneService service;
	    private LezioneService lezioneService;
	    private Insegnante luca;
	    private Lezione lezioneSuccessiva;
	    private Atleta mario;
	    private ObserverTest observer;
	    private LocalDate oggi;


    void setUp() {
        oggi = LocalDate.now();
        service = new PrenotazioneService();
        lezioneService = new LezioneService();
        luca = new Insegnante("luca", "bianchi", "luca@gmail.com", "123");

        lezioneSuccessiva = lezioneService.creaLezione(luca, oggi.plusDays(7), LocalTime.of(18, 45), 60, "K", 1);

        mario = creaAtletaInRegola("mario@test.it");

        observer = new ObserverTest();
        service.addObserver(observer);
    }
    

     // Crea un atleta attivo con abbonamento annuale e certificato validi.
    private Atleta creaAtletaInRegola(String email) {
        Atleta atleta = new Atleta("mario", "rossi", email, "123", LocalDate.of(2000, 1, 1));
        atleta.setStato(StatoAccount.ATTIVO);

        Abbonamento abbonamento = new Abbonamento(TipoAbbonamento.ANNUALE, oggi.minusMonths(1), 450);
        atleta.getAbbonamenti().add(abbonamento);

        CertificatoMedico certificato = new CertificatoMedico(oggi.minusMonths(1), oggi.plusYears(1));
        atleta.getCertificati().add(certificato);

        return atleta;
    }
    
    // funzionamento corretto dell'observer
    @Test
    void prenotaAvvisaLObserver() {
    	setUp();
        Prenotazione p = service.prenota(mario, lezioneSuccessiva);

        assertEquals(1, observer.numeroNotifiche);
        assertEquals(p, observer.ultimaPrenotazione);
    }

    @Test
    void nonSiPuoPrenotareUnaLezioneCancellata() {
    	setUp();
        lezioneService.cancellaLezione(lezioneSuccessiva);

        boolean errore = false;
        try {
            service.prenota(mario, lezioneSuccessiva);
        } catch (IllegalStateException e) {
            errore = true;
        }
        assertTrue(errore);
    }

    // atleta attivo ma senza abbonamento e senza certificato
    @Test
    void nonSiPuoPrenotareSeNonInRegola() {
    	setUp();
        Atleta pino = new Atleta("pino", "pino", "pino@gmail.com", "123", LocalDate.of(2000, 1, 1));
        pino.setStato(StatoAccount.ATTIVO);

        boolean errore = false;
        try {
            service.prenota(pino, lezioneSuccessiva);
        } catch (IllegalStateException e) {
            errore = true;
        }
        assertTrue(errore);
    }
    
    @Test
    void nonSiPuoPrenotareLezionePiena() {
    	setUp();
    	service.prenota(mario, lezioneSuccessiva);
    	Atleta pino = creaAtletaInRegola("pino@gmail.com"); 
    	
    	boolean errore = false;
    	try {
    		service.prenota(pino, lezioneSuccessiva);
    	}catch(IllegalStateException e){
    		errore = true;
    	}
    	
    	assertTrue(errore);
    }
    
    void nonSiPuoPrenotareDueVolteLaStessaLezione() {
    	setUp();
        Lezione lezione = lezioneService.creaLezione(luca, oggi.plusDays(7), LocalTime.of(20, 0), 60, "K", 10);
        service.prenota(mario, lezione);

        boolean errore = false;
        try {
            service.prenota(mario, lezione);
        } catch (IllegalStateException e) {
            errore = true;
        }
        assertTrue(errore);
    }
    
    @Test
    void disdiciLiberaIlPosto() {
    	setUp();
        Prenotazione p = service.prenota(mario, lezioneSuccessiva);

        service.disdici(p);

        assertEquals(StatoPrenotazione.DISDETTA, p.getStato());
        assertFalse(lezioneSuccessiva.isPiena());
    }
    
    @Test
    void dopoLaDisdettPrenotaNuovamente() {
    	setUp();
        Prenotazione p = service.prenota(mario, lezioneSuccessiva);
        service.disdici(p);

        Prenotazione nuova = service.prenota(mario, lezioneSuccessiva);

        assertEquals(StatoPrenotazione.ATTIVA, nuova.getStato());
    }
    

}