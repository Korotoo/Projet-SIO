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

  /*
   * @FXML
   * private TextField date1;
   * 
   * @FXML
   * private TextField date2;
   * 
   * @FXML
   * private TextField date3;
   * :
   */

  @FXML
  private TextField libellé1;

  @FXML
  private TextField libellé2;

  @FXML
  private TextField libellé3;

  @FXML
  private TextField montant1;

  @FXML
  private TextField montant2;

  @FXML
  private TextField montant3;

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

    String nouveauMontant1 = this.montant1.getText();
    int montant1 = Integer.parseInt(nouveauMontant1);

    String nouveauLibellé1 = this.libellé1.getText();

    fiche.setNuitees(nuitees);
    fiche.setKm(km);
    fiche.setRepas(repas);
    fiche.setMontant1(montant1);
    fiche.setLibellé1(nouveauLibellé1);

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
      String sql = "SELECT * FROM fiche_frais  JOIN  hors_forfait ON fiche_frais.ff_id = hors_forfait.ff_id WHERE fiche_frais.ff_id = 1;";
      Statement requete = conn.createStatement();
      ResultSet res = requete.executeQuery(sql);
      // On parcourt le ResultSet avec un while pour traiter chaque visiteur récupéré
      while (res.next()) {
        // On récupère chaque nuittées pour le visiteur

        int nuitees = res.getInt("ff_qte_nuitees");

        // Date moisStr = res.getDate("ff_mois");

        int repas = res.getInt("ff_qte_repas");
        // int repas = Integer.parseInt(repasStr);

        int km = res.getInt("ff_qte_km");
        // int km = Integer.parseInt(kmStr);

        double montant1 = res.getDouble("hf_montant");
        // int montant1 = Integer.parseInt(montant1Str);

        String libellé1 = res.getString("hf_libelle");

        // On instancie un objet Visiteur avec ces propriétés
        fiche = new FicheDeFrais(nuitees, repas, km, montant1, libellé1);

      }
    } catch (Exception e) {
      System.out.println("erreur req fiche");
    }
    return fiche;

  }

  // Je vais afficher les données les données au lancement de la page

  public void initialisation() {

    fiche = getFicheDeFrais();
    Integer nombreDeNuitees = fiche.getNuitees();
    Integer nombreDeRepas = fiche.getRepas();
    Integer nombreDeKm = fiche.getkm();
    Double montant1Loc = fiche.getMontant1();
    String libellé1Loc = fiche.getLibellé1();

    nuitees.setText(nombreDeNuitees.toString());
    repas.setText(nombreDeRepas.toString());
    km.setText(nombreDeKm.toString());
    montant1.setText(montant1Loc.toString());
    libellé1.setText(libellé1Loc);
  }

  // INSERT INTO `gsb_etudiants`.`hors_forfait` (`hf_date`, `hf_libelle`,
  // `hf_montant`, `ff_id`, `ehf_id`) VALUES ('2020-12-25', 'Location de voiture',
  // '367.98', '1', '1');

}
