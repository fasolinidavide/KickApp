package it.unibg.kickapp.service;

import java.time.LocalDate;
import java.util.List;

import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.Insegnante;
import it.unibg.kickapp.model.PassaggioCintura;
import it.unibg.kickapp.model.enums.Cintura;

public class ProgressiService {

    public List<PassaggioCintura> getStoricoCinture(Atleta a) {
        return a.getPassaggiCintura();
    }

    public int getTotalePresenze(Atleta a) {
        return a.contaPresenza();
    }

    public PassaggioCintura registraPassaggioCintura(Atleta a, Cintura c, Insegnante i) {
        if (c.ordinal() <= a.getCinturaAttuale().ordinal()) {
            throw new IllegalArgumentException("La nuova cintura deve essere superiore a quella attuale");
        }
        PassaggioCintura p = new PassaggioCintura(LocalDate.now(), c, i);
        a.getPassaggiCintura().add(p);
        i.getPassaggiCintura().add(p);
        a.setCinturaAttuale(c);
        return p;
    }
}
