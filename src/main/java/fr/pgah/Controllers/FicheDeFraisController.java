package fr.pgah.Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import fr.pgah.AccesBdd.AccesBdd;
import fr.pgah.Model.FicheDeFrais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FicheDeFraisController {
  @FXML
  private TextField nuitees;
  @FXML
  private TextField repas;
  @FXML
  private TextField km;

  private FicheDeFrais fiche;

  @FXML
  void BtnChargementClick(ActionEvent event) {
    fiche = getFicheDeFrais();
    String nombreDeNuitees = fiche.getNuitees();
    String nombreDeRepas = fiche.getRepas();
    String nombreDeKm = fiche.getkm();

    nuitees.setText(nombreDeNuitees);
    repas.setText(nombreDeRepas);
    km.setText(nombreDeKm);
  }

  @FXML
  void BtnValidationClick(ActionEvent event) {

    String nombreDeNuitees = this.nuitees.getText();
    int nuitees = Integer.parseInt(nombreDeNuitees);

    String nombreDeRepas = this.repas.getText();
    int repas = Integer.parseInt(nombreDeRepas);

    String nombreDeKm = this.km.getText();
    int km = Integer.parseInt(nombreDeKm);

    fiche.setNuitees(nuitees);
    fiche.setKm(km);
    fiche.setRepas(repas);

    enregistrerFicheDeFrais(fiche);

  }

  public static FicheDeFrais getFicheDeFrais() {
    Connection conn = AccesBdd.getConnection();
    // On sort immédiatement si la connexion a échoué
    if (conn == null) {
      return null;
    }

    FicheDeFrais fiche = null;

    try {
      String sql = "SELECT * FROM fiche_frais WHERE ff_id = 1";
      Statement requete = conn.createStatement();
      ResultSet res = requete.executeQuery(sql);
      // On parcourt le ResultSet avec un while pour traiter chaque visiteur récupéré
      while (res.next()) {
        // On récupère chaque nuittées pour le visiteur

        String nuiteesStr = res.getString("ff_qte_nuitees");
        int nuitees = Integer.parseInt(nuiteesStr);
        // Date moisStr = res.getDate("ff_mois");

        String repasStr = res.getString("ff_qte_repas");
        int repas = Integer.parseInt(repasStr);

        String kmStr = res.getString("ff_qte_km");
        int km = Integer.parseInt(kmStr);
        // On instancie un objet Visiteur avec ces propriétés
        fiche = new FicheDeFrais(nuitees, repas, km);

      }
    } catch (Exception e) {
      System.out.println("erreur req fiche");
    }
    return fiche;

  }
}
