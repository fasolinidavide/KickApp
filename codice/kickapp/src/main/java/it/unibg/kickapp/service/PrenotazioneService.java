package it.unibg.kickapp.service;

import java.util.ArrayList;
import java.util.List;

import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.Lezione;
import it.unibg.kickapp.model.Prenotazione;
import it.unibg.kickapp.model.enums.Stato;
import it.unibg.kickapp.model.enums.StatoPrenotazione;

public class PrenotazioneService {
    private List<PrenotazioneObserver> osservatori = new ArrayList<>();

    public Prenotazione prenota(Atleta a, Lezione l) {
        if (l.getStato() == Stato.CANCELLATA) {
            throw new IllegalStateException("La lezione e' stata cancellata");
        }
        if (!a.isInRegola(l.getData())) {
            throw new IllegalStateException("Abbonamento o certificato non valido");
        }
        if (l.isPiena()) {
            throw new IllegalStateException("La lezione e' piena");
        }
        for (Prenotazione p : l.getPrenotazioni()) {
            if (p.getAtleta() == a && p.getStato() == StatoPrenotazione.ATTIVA) {
                throw new IllegalStateException("Sei gia' prenotato a questa lezione");
            }
        }
        Prenotazione p = new Prenotazione(a, l);
        a.getPrenotazioni().add(p);
        l.getPrenotazioni().add(p);
        notifica(p);
        return p;
    }

    public void disdici(Prenotazione p) {
        p.setStato(StatoPrenotazione.DISDETTA);
        notifica(p);
    }

    public void registraPresenze(Lezione l, List<Atleta> presenti) {
        if (!l.isTerminata()) {
            throw new IllegalStateException("La lezione non e' ancora terminata");
        }
        for (Prenotazione p : l.getPrenotazioni()) {
            if (p.getStato() == StatoPrenotazione.ATTIVA) {
                p.setPresente(presenti.contains(p.getAtleta()));
                notifica(p);
            }
        }
    }

    public void addObserver(PrenotazioneObserver o) {
        osservatori.add(o);
    }

    public void removeObserver(PrenotazioneObserver o) {
        osservatori.remove(o);
    }

    private void notifica(Prenotazione p) {
        for (PrenotazioneObserver o : osservatori) {
            o.onPrenotazioneModificata(p);
        }
    }
}
