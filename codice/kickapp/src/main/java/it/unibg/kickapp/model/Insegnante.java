package it.unibg.kickapp.model;

import java.util.ArrayList;
import java.util.List;

public class Insegnante extends Utente {
    private List<Lezione> lezioni = new ArrayList<>();
    private List<PassaggioCintura> passaggiCintura = new ArrayList<>();

    public Insegnante(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password);
    }

    public List<Lezione> getLezioni() {
        return lezioni;
    }

    public List<PassaggioCintura> getPassaggiCintura() {
        return passaggiCintura;
    }
}
