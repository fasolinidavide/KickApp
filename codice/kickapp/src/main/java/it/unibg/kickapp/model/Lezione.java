package it.unibg.kickapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import it.unibg.kickapp.model.enums.Stato;
import it.unibg.kickapp.model.enums.StatoPrenotazione;

public class Lezione {
    private int id;
    private LocalDate data;
    private LocalTime oraInizio;
    private int durataMinuti;
    private String disciplina;
    private int capienzaMassima;
    private Stato stato;
    private Insegnante insegnante;
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    public Lezione(Insegnante insegnante, LocalDate data, LocalTime oraInizio, int durataMinuti, String disciplina, int capienzaMassima) {
        this.insegnante = insegnante;
        this.data = data;
        this.oraInizio = oraInizio;
        this.durataMinuti = durataMinuti;
        this.disciplina = disciplina;
        this.capienzaMassima = capienzaMassima;
        this.stato = Stato.PROGRAMMATA;
    }

    public int postiDisponibili() {
        int occupati = 0;
        for (Prenotazione p : prenotazioni) {
            if (p.getStato() == StatoPrenotazione.ATTIVA) {
                occupati++;
            }
        }
        return capienzaMassima - occupati;
    }

    public boolean isPiena() {
        return postiDisponibili() <= 0;
    }

    public boolean isTerminata() {
        LocalDateTime fine = LocalDateTime.of(data, oraInizio).plusMinutes(durataMinuti);
        return fine.isBefore(LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    public int getDurataMinuti() {
        return durataMinuti;
    }

    public void setDurataMinuti(int durataMinuti) {
        this.durataMinuti = durataMinuti;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getCapienzaMassima() {
        return capienzaMassima;
    }

    public void setCapienzaMassima(int capienzaMassima) {
        this.capienzaMassima = capienzaMassima;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Insegnante getInsegnante() {
        return insegnante;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }
}
