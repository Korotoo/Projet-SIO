package fr.pgah.Controllers;

import fr.pgah.AccesBdd.AccesBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PageConnexionController {

    @FXML
    private Button btn_connexion;

    @FXML
    private TextField id_login;

    @FXML
    private TextField id_password;

    @FXML
    private Text messageErreur;

    @FXML
    void btnConnexion(ActionEvent event) {

        String identifiant = id_login.getText();
        String motDePasse = id_password.getText();

        // Utilisation de la méthode de vérification des identifiants de la classe
        // AccesBdd
        boolean identifiantsValides = AccesBdd.verifierIdentifiants(identifiant, motDePasse);

        if (identifiantsValides) {
            // Rediriger l'utilisateur vers la page suivante
            messageErreur.setText("Connexion réussie !");
            // Code pour changer de scène ou effectuer d'autres actions après la connexion
            // réussie
        } else {
            messageErreur.setText("Identifiant ou mot de passe incorrect !");
        }
    }
}
