package it.unibg.kickapp.service;

import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.Utente;
import it.unibg.kickapp.model.enums.StatoAccount;

public class AutenticazioneService {
    private AccountService accountService;
    private Utente utenteCorrente;

    public AutenticazioneService(AccountService accountService) {
        this.accountService = accountService;
    }

    public Utente login(String email, String password) {
        for (Utente u : accountService.getUtenti()) {
            if (u.getEmail().equalsIgnoreCase(email) && u.verificaPassword(password)) {
                if (u instanceof Atleta a && a.getStato() != StatoAccount.ATTIVO) {
                    return null;
                }
                utenteCorrente = u;
                return u;
            }
        }
        return null;
    }

    public void logout() {
        utenteCorrente = null;
    }

    public Utente getUtenteCorrente() {
        return utenteCorrente;
    }
}
