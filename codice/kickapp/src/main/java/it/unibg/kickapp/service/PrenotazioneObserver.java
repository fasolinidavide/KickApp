package it.unibg.kickapp.service;

import it.unibg.kickapp.model.Prenotazione;

public interface PrenotazioneObserver {
    void onPrenotazioneModificata(Prenotazione p);
}
