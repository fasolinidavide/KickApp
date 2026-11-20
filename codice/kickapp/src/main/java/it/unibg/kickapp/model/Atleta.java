package it.unibg.kickapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unibg.kickapp.model.enums.Cintura;
import it.unibg.kickapp.model.enums.StatoAccount;

public class Atleta extends Utente {
    private LocalDate dataNascita;
    private StatoAccount stato;
    private Cintura cinturaAttuale;
    private List<Prenotazione> prenotazioni = new ArrayList<>();
    private List<Abbonamento> abbonamenti = new ArrayList<>();
    private List<CertificatoMedico> certificati = new ArrayList<>();
    private List<PassaggioCintura> passaggiCintura = new ArrayList<>();

    public Atleta(String nome, String cognome, String email, String password, LocalDate dataNascita) {
        super(nome, cognome, email, password);
        this.dataNascita = dataNascita;
        this.stato = StatoAccount.IN_ATTESA;
        this.cinturaAttuale = Cintura.BIANCA;
    }

    public boolean isInRegola(LocalDate data) {
        if (stato != StatoAccount.ATTIVO) {
            return false;
        }
        boolean abbonamentoValido = false;
        for (Abbonamento a : abbonamenti) {
            if (a.isValido(data)) {
                abbonamentoValido = true;
            }
        }
        boolean certificatoValido = false;
        for (CertificatoMedico c : certificati) {
            if (c.isValido(data)) {
                certificatoValido = true;
            }
        }
        return abbonamentoValido && certificatoValido;
    }

    public int contaPresenza() {
        int totale = 0;
        for (Prenotazione p : prenotazioni) {
            if (p.isPresente()) {
                totale++;
            }
        }
        return totale;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public StatoAccount getStato() {
        return stato;
    }

    public void setStato(StatoAccount stato) {
        this.stato = stato;
    }

    public Cintura getCinturaAttuale() {
        return cinturaAttuale;
    }

    public void setCinturaAttuale(Cintura cinturaAttuale) {
        this.cinturaAttuale = cinturaAttuale;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public List<Abbonamento> getAbbonamenti() {
        return abbonamenti;
    }

    public List<CertificatoMedico> getCertificati() {
        return certificati;
    }

    public List<PassaggioCintura> getPassaggiCintura() {
        return passaggiCintura;
    }
}
