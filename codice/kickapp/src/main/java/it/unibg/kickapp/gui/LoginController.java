package it.unibg.kickapp.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import it.unibg.kickapp.model.Atleta;
import it.unibg.kickapp.model.Insegnante;
import it.unibg.kickapp.model.Utente;

public class LoginController {

    @FXML
    private TextField campoEmail;

    @FXML
    private PasswordField campoPassword;

    @FXML
    private Label erroreLabel;

    @FXML
    private void accedi() {
        String email = campoEmail.getText().trim();
        String password = campoPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            erroreLabel.setText("Inserisci email e password");
            return;
        }

        Utente u = Servizi.AUTENTICAZIONE.login(email, password);
        if (u == null) {
            erroreLabel.setText("Email o password errate, oppure account non ancora approvato");
            return;
        }

        if (u instanceof Atleta) {
            Navigazione.vai("atleta.fxml");
        } else if (u instanceof Insegnante) {
            Navigazione.vai("insegnante.fxml");
        } else {
            Navigazione.vai("admin.fxml");
        }
    }

    @FXML
    private void apriRegistrazione() {
        Navigazione.vai("registrazione.fxml");
    }
}