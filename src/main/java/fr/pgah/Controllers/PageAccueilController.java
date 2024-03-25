package fr.pgah.Controllers;

import java.io.IOException;

import fr.pgah.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PageAccueilController {

    @FXML
    private Button fichesDeFrais;

    @FXML
    private Button listeVisiteur;

    @FXML
    void accesFichesDeFrais(ActionEvent event) throws IOException {
        App.setRoot("fiche");

    }

    @FXML
    void accesListeVisiteurs(ActionEvent event) throws IOException {
        App.setRoot("liste_visiteurs");

    }

}
