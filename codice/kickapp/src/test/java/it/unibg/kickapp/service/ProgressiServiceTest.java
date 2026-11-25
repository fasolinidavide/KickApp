package it.unibg.kickapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.Insegnante;
import it.unibg.kickapp.model.enums.Cintura;


public class ProgressiServiceTest {
	private ProgressiService service;
    private Atleta mario;
    private Insegnante luca;

    void setUp() {
        service = new ProgressiService();
        mario = new Atleta("mario", "rossi", "mario@gmail.com", "123", LocalDate.of(2000, 1, 1));
        luca = new Insegnante("luca", "bianchi", "luca@gmail.com", "123");
    }
    
    void passaggioDiCinturaSuperioreAggiornaProfiloAtl() {
    	setUp();
    	service.registraPassaggioCintura(mario, Cintura.GIALLA, luca);
    	
    	assertEquals(Cintura.GIALLA, mario.getCinturaAttuale());
    }
    
    @Test
    void nonSiPuoPassareAllaStessaCintura() {
    	setUp();
        boolean errore = false;
        try {
            service.registraPassaggioCintura(mario, Cintura.BIANCA, luca);
        } catch (IllegalArgumentException e) {
            errore = true;
        }
        assertTrue(errore);
    }
    
    @Test
    void nonSiPuoTornareAUnaCinturaInferiore() {
    	setUp();
        service.registraPassaggioCintura(mario, Cintura.VERDE, luca);

        boolean errore = false;
        try {
            service.registraPassaggioCintura(mario, Cintura.GIALLA, luca);
        } catch (IllegalArgumentException e) {
            errore = true;
        }
        assertTrue(errore);
    }
    

}
