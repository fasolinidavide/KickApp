package it.unibg.kickapp.service;

import it.unibg.kickapp.model.Prenotazione;

/**
 * observer usato solo nei test
 */
class ObserverTest implements PrenotazioneObserver {

    int numeroNotifiche = 0;
    Prenotazione ultimaPrenotazione = null;

    @Override
    public void onPrenotazioneModificata(Prenotazione p) {
        numeroNotifiche = numeroNotifiche + 1;
        ultimaPrenotazione = p;
    }
}