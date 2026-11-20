package it.unibg.kickapp.model;

import java.time.LocalDate;

import it.unibg.kickapp.model.enums.Cintura;

public class PassaggioCintura {
    private int id;
    private LocalDate data;
    private Cintura nuovaCintura;
    private Insegnante insegnante;

    public PassaggioCintura(LocalDate data, Cintura nuovaCintura, Insegnante insegnante) {
        this.data = data;
        this.nuovaCintura = nuovaCintura;
        this.insegnante = insegnante;
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

    public Cintura getNuovaCintura() {
        return nuovaCintura;
    }

    public Insegnante getInsegnante() {
        return insegnante;
    }
}
