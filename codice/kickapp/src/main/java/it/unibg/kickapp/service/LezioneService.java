package it.unibg.kickapp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.Insegnante;
import it.unibg.kickapp.model.Lezione;
import it.unibg.kickapp.model.Prenotazione;
import it.unibg.kickapp.model.enums.Stato;
import it.unibg.kickapp.model.enums.StatoPrenotazione;

public class LezioneService {
    private List<Lezione> lezioni = new ArrayList<>();

    public Lezione creaLezione(Insegnante i, LocalDate data, LocalTime ora, int durata, String disciplina, int capienza) {
        if (capienza <= 0 || durata <= 0) {
            throw new IllegalArgumentException("Durata e capienza devono essere maggiori di zero");
        }
        Lezione l = new Lezione(i, data, ora, durata, disciplina, capienza);
        i.getLezioni().add(l);
        lezioni.add(l);
        return l;
    }

    public void cancellaLezione(Lezione l) {
        l.setStato(Stato.CANCELLATA);
        for (Prenotazione p : l.getPrenotazioni()) {
            p.setStato(StatoPrenotazione.DISDETTA);
        }
    }

    public List<Lezione> getCalendario(LocalDate da, LocalDate a) {
        List<Lezione> risultato = new ArrayList<>();
        for (Lezione l : lezioni) {
            if (!l.getData().isBefore(da) && !l.getData().isAfter(a)) {
                risultato.add(l);
            }
        }
        return risultato;
    }

    public List<Atleta> getIscritti(Lezione l) {
        List<Atleta> iscritti = new ArrayList<>();
        for (Prenotazione p : l.getPrenotazioni()) {
            if (p.getStato() == StatoPrenotazione.ATTIVA) {
                iscritti.add(p.getAtleta());
            }
        }
        return iscritti;
    }
}
