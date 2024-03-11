package fr.pgah.Controllers;

import java.io.IOException;
import fr.pgah.App;
import javafx.fxml.FXML;

public class AutreEcranController {

  @FXML
  private void btnRetour() throws IOException {
    App.setRoot("liste_visiteurs");
  }
}
