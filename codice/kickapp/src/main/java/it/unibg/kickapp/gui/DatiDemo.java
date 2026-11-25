package it.unibg.kickapp.gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import it.unibg.kickapp.model.Amministratore;
import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.Insegnante;
import it.unibg.kickapp.model.Lezione;
import it.unibg.kickapp.model.enums.Cintura;
import it.unibg.kickapp.model.enums.TipoAbbonamento;

public class DatiDemo {

    private DatiDemo() {
    }

    public static void carica() {
        LocalDate oggi = LocalDate.now();

        Servizi.ACCOUNT.getUtenti().add(new Amministratore("Admin", "KickApp", "admin@kickapp.it", "admin"));

        Insegnante marco = Servizi.ACCOUNT.aggiungiInsegnante("Marco", "Ferri", "marco.ferri@kickapp.it", "marco");
        Insegnante elena = Servizi.ACCOUNT.aggiungiInsegnante("Elena", "Galli", "elena.galli@kickapp.it", "elena");

        Atleta mario = Servizi.ACCOUNT.creaAccount("Mario", "Rossi", "mario.rossi@mail.it", "mario", LocalDate.of(1999, 5, 12));
        Atleta luca = Servizi.ACCOUNT.creaAccount("Luca", "Bianchi", "luca.bianchi@mail.it", "luca", LocalDate.of(2002, 1, 30));
        Atleta anna = Servizi.ACCOUNT.creaAccount("Anna", "Conti", "anna.conti@mail.it", "anna", LocalDate.of(2000, 9, 3));
        Atleta sara = Servizi.ACCOUNT.creaAccount("Sara", "Neri", "sara.neri@mail.it", "sara", LocalDate.of(2003, 11, 21));
        Servizi.ACCOUNT.creaAccount("Giulia", "Verdi", "giulia.verdi@mail.it", "giulia", LocalDate.of(2001, 3, 14));
        Servizi.ACCOUNT.creaAccount("Tommaso", "Greco", "tommaso.greco@mail.it", "tommaso", LocalDate.of(1998, 7, 2));

        for (Atleta a : List.of(mario, luca, anna, sara)) {
            Servizi.ACCOUNT.accettaAtleta(a);
            Servizi.POSIZIONE.rinnovaAbbonamento(a, TipoAbbonamento.MENSILE, oggi.minusDays(10), 40.0);
        }
        Servizi.POSIZIONE.registraCertificato(mario, oggi.minusDays(70), oggi.plusDays(300));
        Servizi.POSIZIONE.registraCertificato(luca, oggi.minusDays(30), oggi.plusDays(335));
        Servizi.POSIZIONE.registraCertificato(anna, oggi.minusDays(100), oggi.plusDays(265));
        Servizi.POSIZIONE.registraCertificato(sara, oggi.minusDays(400), oggi.minusDays(35));

        Lezione settimanaScorsa = Servizi.LEZIONI.creaLezione(marco, oggi.minusDays(7), LocalTime.of(18, 0), 90, "Kickboxing base", 12);
        Lezione ieri = Servizi.LEZIONI.creaLezione(marco, oggi.minusDays(1), LocalTime.of(18, 0), 90, "Kickboxing base", 12);
        Lezione domani = Servizi.LEZIONI.creaLezione(marco, oggi.plusDays(1), LocalTime.of(18, 0), 90, "Kickboxing base", 12);
        Lezione sparring = Servizi.LEZIONI.creaLezione(elena, oggi.plusDays(1), LocalTime.of(19, 30), 60, "Sparring", 1);
        Servizi.LEZIONI.creaLezione(marco, oggi.plusDays(3), LocalTime.of(10, 0), 90, "Kickboxing avanzato", 10);
        Servizi.LEZIONI.creaLezione(marco, oggi.plusDays(5), LocalTime.of(18, 0), 90, "Kickboxing base", 12);
        Servizi.LEZIONI.creaLezione(elena, oggi.plusDays(7), LocalTime.of(19, 0), 60, "K1", 10);

        Servizi.PRENOTAZIONI.prenota(mario, settimanaScorsa);
        Servizi.PRENOTAZIONI.prenota(luca, settimanaScorsa);
        Servizi.PRENOTAZIONI.registraPresenze(settimanaScorsa, List.of(mario));

        Servizi.PRENOTAZIONI.prenota(mario, ieri);
        Servizi.PRENOTAZIONI.prenota(luca, ieri);
        Servizi.PRENOTAZIONI.prenota(anna, ieri);

        Servizi.PRENOTAZIONI.prenota(mario, domani);
        Servizi.PRENOTAZIONI.prenota(luca, domani);
        Servizi.PRENOTAZIONI.prenota(anna, sparring);

        Servizi.PROGRESSI.registraPassaggioCintura(mario, Cintura.GIALLA, marco);
    }
}