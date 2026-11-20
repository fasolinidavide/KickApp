package it.unibg.kickapp.service;

import java.time.LocalDate;

import it.unibg.kickapp.model.Abbonamento;
import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.CertificatoMedico;
import it.unibg.kickapp.model.enums.TipoAbbonamento;

public class PosizioneService {

    public CertificatoMedico registraCertificato(Atleta a, LocalDate rilascio, LocalDate scadenza) {
        if (!scadenza.isAfter(rilascio)) {
            throw new IllegalArgumentException("La scadenza deve essere successiva al rilascio");
        }
        CertificatoMedico c = new CertificatoMedico(rilascio, scadenza);
        a.getCertificati().add(c);
        return c;
    }

    public Abbonamento rinnovaAbbonamento(Atleta a, TipoAbbonamento tipo, LocalDate inizio, double importo) {
        Abbonamento ab = new Abbonamento(tipo, inizio, importo);
        a.getAbbonamenti().add(ab);
        return ab;
    }

    public Abbonamento getStatoAbbonamento(Atleta a) {
        Abbonamento ultimo = null;
        for (Abbonamento ab : a.getAbbonamenti()) {
            if (ultimo == null || ab.getDataScadenza().isAfter(ultimo.getDataScadenza())) {
                ultimo = ab;
            }
        }
        return ultimo;
    }

    public CertificatoMedico getStatoCertificato(Atleta a) {
        CertificatoMedico ultimo = null;
        for (CertificatoMedico c : a.getCertificati()) {
            if (ultimo == null || c.getDataScadenza().isAfter(ultimo.getDataScadenza())) {
                ultimo = c;
            }
        }
        return ultimo;
    }
}
