package fr.pgah.Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.Date;

import fr.pgah.App;
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
  public void initialize() {
    System.out.println("Lancée");
    initialisation();

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

    enregistrerFicheDeFrais(nuitees, repas, km);

  }

  @FXML
  void id_return(ActionEvent event) throws IOException {
    App.setRoot("page_accueil");

  }

  public static String enregistrerFicheDeFrais(int nuitees, int repas, int km) {
    Connection conn = AccesBdd.getConnection();
    // On sort immédiatement si la connexion a échoué
    if (conn == null) {
      return null;
    }

    try {
      // Préparation de la requête SQL pour mettre à jour la fiche de frais
      String sql = "UPDATE fiche_frais SET ff_qte_repas = ?, ff_qte_nuitees = ? ,ff_qte_km = ? WHERE ff_id = 1";
      PreparedStatement pstmt = conn.prepareStatement(sql);

      // Définition des paramètres pour la requête
      pstmt.setInt(1, repas);
      pstmt.setInt(2, nuitees);
      pstmt.setInt(3, km);

      // Exécution de la requête de mise à jour
      int rowsAffected = pstmt.executeUpdate();
      if (rowsAffected > 0) {
        return "Mise à jour réussie.";
      } else {
        return "Aucune mise à jour effectuée. Vérifiez l'ID de la fiche de frais.";
      }

    } catch (Exception e) {
      e.printStackTrace();
      return "Erreur lors de la mise à jour de la fiche de frais: " + e.getMessage();
    } finally {
      // Assurez-vous de fermer la connexion à la base de données ici, si nécessaire
      // Exemple: if (conn != null) try { conn.close(); } catch (SQLException ignore)
      // {}
    }

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

  // Je vais afficher les données les données au lancement de la page

  public void initialisation() {

    fiche = getFicheDeFrais();
    String nombreDeNuitees = fiche.getNuitees();
    String nombreDeRepas = fiche.getRepas();
    String nombreDeKm = fiche.getkm();

    nuitees.setText(nombreDeNuitees);
    repas.setText(nombreDeRepas);
    km.setText(nombreDeKm);
  }

}
