package fr.pgah.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import fr.pgah.App;
import fr.pgah.AccesBdd.AccesBdd;
import fr.pgah.Model.Visiteur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Classe ListeVisiteursController qui gère l'affichage des visiteurs. Cette classe est responsable
 * de la gestion de la liste des visiteurs dans l'interface utilisateur.
 */
public class ListeVisiteursController implements Initializable {

  private ObservableList<Visiteur> visiteurs = FXCollections.observableArrayList();

  @FXML
  private ListView<String> lvVisiteurs;

  @FXML
  private TableView<Visiteur> tvVisiteurs;

  /**
   * Méthode appelée au démarrage de l'application JavaFX. Elle initialise la ListView et la
   * TableView des visiteurs (deux fois la même fonctionnalité, mais avec des composants graphiques
   * différents). Ici les paramètres ne sont pas utilisés, mais on est obligé des les indiquer car
   * JavaFX nous y oblige (la méthode est spécifiée ainsi dans le framework).
   *
   * @param url l'URL de la racine de l'objet de contenu
   * @param rb les ressources utilisées pour localiser l'objet de contenu
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Récupération de la liste des visiteurs depuis la base de données
    visiteurs = AccesBdd.getVisiteurs();

    // Initialisation des composants graphiques et peuplement avec les visiteurs
    peuplerListView();
    initialiserTableView();
    peuplerTableView();
  }

  /**
   * Peuple la ListView avec les visiteurs. La ListView est plus simple à utiliser, mais on ne peut
   * pas décomposer en colonnes.
   */
  private void peuplerListView() {
    // On construit la liste de strings à partir de la liste de visiteurs
    // L'expression est complexe, retenez simplement qu'on appelle toString()
    // sur chaque visiteur
    ObservableList<String> visiteursString =
        FXCollections.observableArrayList(visiteurs.stream().map(v -> v.toString()).toList());

    // On ajoute la liste de strings à la ListView
    lvVisiteurs.setItems(visiteursString);
  }

  /**
   * Initialise trois colonnes pour la TableView. La TableView est plus complexe à utiliser, mais
   * est plus puissante que la ListView. On peut décomposer en colonnes, ajouter des boutons pour
   * chaque élément, etc.
   */
  private void initialiserTableView() {
    // Définit les colonnes souhaitées, avec leur titre affiché
    TableColumn<Visiteur, String> colMatricule = new TableColumn<>("Matricule");
    TableColumn<Visiteur, String> colNom = new TableColumn<>("Nom");
    TableColumn<Visiteur, String> colPrenom = new TableColumn<>("Prénom");

    // Associe chaque colonne à un attribut de la classe Visiteur
    colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
    colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

    // Ajoute finalement les colonnes à la TableView
    tvVisiteurs.getColumns().add(colMatricule);
    tvVisiteurs.getColumns().add(colPrenom);
    tvVisiteurs.getColumns().add(colNom);
  }

  /**
   * Peuple la TableView avec les visiteurs.
   */
  private void peuplerTableView() {
    tvVisiteurs.setItems(visiteurs);
  }

  /**
   * Appelée lorsqu'on clique sur le bouton "Vers autre écran". Elle change le contenu de la fenêtre
   * pour afficher un autre écran.
   *
   * @param event l'événement qui a déclenché l'appel de cette méthode
   * @throws IOException si le chargement du FXML échoue
   */
  @FXML
  private void btnVersAutreEcran(ActionEvent event) throws IOException {
    App.setRoot("autre_ecran");
  }
}
