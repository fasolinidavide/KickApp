package it.unibg.kickapp.model;

import java.time.LocalDateTime;

import it.unibg.kickapp.model.enums.StatoPrenotazione;

public class Prenotazione {
    private int id;
    private LocalDateTime data;
    private boolean presente;
    private StatoPrenotazione stato;
    private Atleta atleta;
    private Lezione lezione;

    public Prenotazione(Atleta atleta, Lezione lezione) {
        this.atleta = atleta;
        this.lezione = lezione;
        this.data = LocalDateTime.now();
        this.presente = false;
        this.stato = StatoPrenotazione.ATTIVA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public StatoPrenotazione getStato() {
        return stato;
    }

    public void setStato(StatoPrenotazione stato) {
        this.stato = stato;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public Lezione getLezione() {
        return lezione;
    }
}
