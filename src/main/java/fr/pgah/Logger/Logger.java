package fr.pgah.Logger;

public class Logger {

  /**
   * Affiche un message dans la console avec la date et l'heure actuels. Utilisé pour logger
   * (journaliser) les événements importants de l'application.
   *
   * @param message le message à afficher
   */
  public static void log(String message) {
    String dateEtHeure = java.time.LocalDateTime.now().toString().substring(0, 19);
    System.out.println(dateEtHeure + " - " + message);
  }
}
