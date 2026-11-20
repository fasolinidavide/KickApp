package it.unibg.kickapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.Insegnante;
import it.unibg.kickapp.model.Utente;
import it.unibg.kickapp.model.enums.StatoAccount;

public class AccountService {
    private List<Utente> utenti = new ArrayList<>();

    public Atleta creaAccount(String nome, String cognome, String email, String password, LocalDate dataNascita) {
        Atleta a = new Atleta(nome, cognome, email, password, dataNascita);
        utenti.add(a);
        return a;
    }

    public void accettaAtleta(Atleta a) {
        a.setStato(StatoAccount.ATTIVO);
    }

    public void rifiutaAtleta(Atleta a) {
        a.setStato(StatoAccount.RIFIUTATO);
    }

    public List<Atleta> cercaAtleta(String testo) {
        List<Atleta> risultato = new ArrayList<>();
        String t = testo.toLowerCase();
        for (Utente u : utenti) {
            if (u instanceof Atleta a) {
                if (a.getNome().toLowerCase().contains(t) || a.getCognome().toLowerCase().contains(t) || a.getEmail().toLowerCase().contains(t)) {
                    risultato.add(a);
                }
            }
        }
        return risultato;
    }

    public void modificaAtleta(Atleta a, String nome, String cognome, String email) {
        a.setNome(nome);
        a.setCognome(cognome);
        a.setEmail(email);
    }

    public void rimuoviAtleta(Atleta a) {
        utenti.remove(a);
    }

    public Insegnante aggiungiInsegnante(String nome, String cognome, String email, String password) {
        Insegnante i = new Insegnante(nome, cognome, email, password);
        utenti.add(i);
        return i;
    }

    public void rimuoviInsegnante(Insegnante i) {
        utenti.remove(i);
    }

    public List<Utente> getUtenti() {
        return utenti;
    }
}
