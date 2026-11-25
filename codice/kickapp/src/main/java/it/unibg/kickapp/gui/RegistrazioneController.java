package it.unibg.kickapp.gui;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import it.unibg.kickapp.model.Utente;

public class RegistrazioneController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCognome;

    @FXML
    private TextField campoEmail;

    @FXML
    private DatePicker campoDataNascita;

    @FXML
    private PasswordField campoPassword;

    @FXML
    private PasswordField campoConferma;

    @FXML
    private Label erroreLabel;

    @FXML
    private void registrati() {
        String nome = campoNome.getText().trim();
        String cognome = campoCognome.getText().trim();
        String email = campoEmail.getText().trim();
        LocalDate dataNascita = campoDataNascita.getValue();
        String password = campoPassword.getText();

        if (nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || dataNascita == null || password.isEmpty()) {
            erroreLabel.setText("Compila tutti i campi");
            return;
        }
        if (!email.contains("@")) {
            erroreLabel.setText("Email non valida");
            return;
        }
        if (!dataNascita.isBefore(LocalDate.now())) {
            erroreLabel.setText("La data di nascita deve essere nel passato");
            return;
        }
        if (!password.equals(campoConferma.getText())) {
            erroreLabel.setText("Le due password non coincidono");
            return;
        }
        for (Utente u : Servizi.ACCOUNT.getUtenti()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                erroreLabel.setText("Esiste già un account con questa email");
                return;
            }
        }

        Servizi.ACCOUNT.creaAccount(nome, cognome, email, password, dataNascita);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registrazione completata");
        alert.setHeaderText(null);
        alert.setContentText("Account creato. Potrai accedere dopo l'approvazione dell'amministratore.");
        alert.showAndWait();

        Navigazione.vai("login.fxml");
    }

    @FXML
    private void annulla() {
        Navigazione.vai("login.fxml");
    }
}