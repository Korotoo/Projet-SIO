package fr.pgah.Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import fr.pgah.AccesBdd.AccesBdd;
import fr.pgah.Model.FicheDeFrais;

public class FicheDeFraisController {

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
        // On récupère chaque propriété du visiteur dans des variables

        String nuitees = res.getString("ff_qte_nuitees");
        // String mois = res.getString("ff_mois");
        // String repas = res.getString("ff_qte_repas");
        // String km = res.getString("ff_qte_km");
        // On instancie un objet Visiteur avec ces propriétés
        fiche = new FicheDeFrais(nuitees);

      }
    } catch (Exception e) {
      System.out.println("erreur req fiche");
    }
    return fiche;

  }
}
