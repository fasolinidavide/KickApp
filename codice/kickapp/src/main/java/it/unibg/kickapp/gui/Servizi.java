package it.unibg.kickapp.gui;

import it.unibg.kickapp.service.AccountService;
import it.unibg.kickapp.service.AutenticazioneService;
import it.unibg.kickapp.service.LezioneService;
import it.unibg.kickapp.service.PosizioneService;
import it.unibg.kickapp.service.PrenotazioneService;
import it.unibg.kickapp.service.ProgressiService;

public class Servizi {

    public static final AccountService ACCOUNT = new AccountService();
    public static final AutenticazioneService AUTENTICAZIONE = new AutenticazioneService(ACCOUNT);
    public static final LezioneService LEZIONI = new LezioneService();
    public static final PrenotazioneService PRENOTAZIONI = new PrenotazioneService();
    public static final PosizioneService POSIZIONE = new PosizioneService();
    public static final ProgressiService PROGRESSI = new ProgressiService();

    private Servizi() {
    }
}