package it.unibg.kickapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import it.unibg.kickapp.model.enums.Cintura;
import it.unibg.kickapp.model.enums.StatoAccount;
import it.unibg.kickapp.model.enums.TipoAbbonamento;

/**
 * test: stato iniziale e verifica se in regola per essere registrato
 */
class AtletaTest {
	private static final LocalDate OGGI = LocalDate.of(2026, 1, 1);

    private Atleta atleta;
    
    void creaAtleta()
    {
        atleta = new Atleta("Mario", "Rossi", "mario@gmail.com", "123", LocalDate.of(2004, 6, 6));

    }
    
    @Test
    void nuovoAtleta_InAttesa() {
        creaAtleta();
        assertEquals(StatoAccount.IN_ATTESA, atleta.getStato());
        assertEquals(Cintura.BIANCA, atleta.getCinturaAttuale());
    }
    
    @Test
    void nonInRegola_AccountNonAttivo() {
        creaAtleta();
        atleta.getAbbonamenti().add(new Abbonamento(TipoAbbonamento.MENSILE, OGGI, 70));
        atleta.getCertificati().add(new CertificatoMedico(OGGI, OGGI.plusYears(1)));
        assertFalse(atleta.isInRegola(OGGI));
    }
    
    @Test
    void nonInRegola_SenzaCertificato() {
    	creaAtleta();
        atleta.setStato(StatoAccount.ATTIVO);
        atleta.getAbbonamenti().add(new Abbonamento(TipoAbbonamento.MENSILE, OGGI, 70));
        assertFalse(atleta.isInRegola(OGGI));
    }
    
    @Test
    void nonInRegola_ConAbbonamentoScaduto() {
    	creaAtleta();
        atleta.setStato(StatoAccount.ATTIVO);
        atleta.getAbbonamenti().add(new Abbonamento(TipoAbbonamento.MENSILE, OGGI.minusMonths(3), 70));
        atleta.getCertificati().add(new CertificatoMedico(OGGI, OGGI.plusYears(1)));
        assertFalse(atleta.isInRegola(OGGI));
    }

}