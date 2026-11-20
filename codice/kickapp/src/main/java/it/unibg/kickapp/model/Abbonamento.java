package it.unibg.kickapp.model;

import java.time.LocalDate;

import it.unibg.kickapp.model.enums.TipoAbbonamento;

public class Abbonamento {
    private int id;
    private TipoAbbonamento tipo;
    private LocalDate dataInizio;
    private LocalDate dataScadenza;
    private double importo;

    public Abbonamento(TipoAbbonamento tipo, LocalDate dataInizio, double importo) {
        this.tipo = tipo;
        this.dataInizio = dataInizio;
        this.importo = importo;
        if (tipo == TipoAbbonamento.MENSILE) {
            this.dataScadenza = dataInizio.plusMonths(1);
        } else if (tipo == TipoAbbonamento.TRIMESTRALE) {
            this.dataScadenza = dataInizio.plusMonths(3);
        } else {
            this.dataScadenza = dataInizio.plusYears(1);
        }
    }

    public boolean isValido(LocalDate data) {
        return !data.isBefore(dataInizio) && !data.isAfter(dataScadenza);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoAbbonamento getTipo() {
        return tipo;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public double getImporto() {
        return importo;
    }
}
