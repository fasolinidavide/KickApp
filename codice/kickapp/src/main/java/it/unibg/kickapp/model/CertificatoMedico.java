package it.unibg.kickapp.model;

import java.time.LocalDate;

public class CertificatoMedico {
    private int id;
    private LocalDate dataRilascio;
    private LocalDate dataScadenza;

    public CertificatoMedico(LocalDate dataRilascio, LocalDate dataScadenza) {
        this.dataRilascio = dataRilascio;
        this.dataScadenza = dataScadenza;
    }

    public boolean isValido(LocalDate data) {
        return !data.isBefore(dataRilascio) && !data.isAfter(dataScadenza);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataRilascio() {
        return dataRilascio;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }
}
