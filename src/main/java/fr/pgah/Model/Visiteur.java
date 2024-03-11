package fr.pgah.Model;

public class Visiteur {

  private String matricule;
  private String nom;
  private String prenom;

  public Visiteur(String matricule, String nom, String prenom) {
    this.matricule = matricule;
    this.nom = nom;
    this.prenom = prenom;
  }

  // Les getters ne sont pas appelés explicitement dans le code donné,
  // mais sont nécessaires pour que JavaFX puisse accéder aux propriétés de l'objet
  // (pour afficher automatiquement les propriétés du visiteur dans un TableView)

  public String getMatricule() {
    return matricule;
  }

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  /**
   * Retourne une représentation textuelle de l'objet (utile pour le débogage) (utilisé aussi dans
   * ListeVisiteursController pour peupler une ListView)
   */
  @Override
  public String toString() {
    return "matricule = " + matricule + ", nom = " + nom + ", prénom = " + prenom;
  }
}
