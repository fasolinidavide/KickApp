package it.unibg.kickapp.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * test: validità dei certificati medici degli atleti
 */
public class CertificatoMedicoTest {

		
		private static final LocalDate INIZIOCERTIFICATO = LocalDate.of(2026, 1, 10);
		private static final LocalDate FINECERTIFICATO = LocalDate.of(2027, 1, 10);
	   
	    private CertificatoMedico certificato;
	    
	    void creaCertificato() {
	        certificato = new CertificatoMedico(INIZIOCERTIFICATO, FINECERTIFICATO);
	    }
	    
		@Test
		void ValidoRilascioEScadenza() {
			creaCertificato();
			assertTrue(certificato.isValido(LocalDate.of(2026, 6, 15)));
		}
		
		@Test 
		void nonValidoRilascioEScadenza() {
			creaCertificato();
			assertFalse(certificato.isValido(LocalDate.of(2028, 1, 1)));
		}
	
		@Test 
		void nonValidoRilascioEScadenzaLimite() {
			creaCertificato();
	        assertTrue(certificato.isValido(INIZIOCERTIFICATO));
	        assertTrue(certificato.isValido(FINECERTIFICATO));
		}
		
	    @Test
	    void nonValidoDopoScadenza() {
	    	creaCertificato();
	        assertFalse(certificato.isValido(FINECERTIFICATO.plusDays(1)));
	    }
		
}

