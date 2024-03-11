package fr.pgah;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Cette classe (qui étend la classe Application de JavaFX) est le point d'entrée principal dans
 * l'application JavaFX.
 */
public class App extends Application {

  /**
   * La scène (contenu de la fenêtre) principale de l'application. Cette scène va changer en
   * fonction de l'écran que l'on veut afficher. On passera par la méthode setRoot() pour changer la
   * scène.
   */
  private static Scene scene;

  /**
   * Cette méthode est appelée au démarrage de l'application.
   *
   * @param stage le stage (fenêtre) principal de l'application
   * @throws IOException si le chargement du FXML échoue
   */
  @Override
  public void start(Stage stage) throws IOException {
    // On définit la taille minimale de la fenêtre
    stage.setMinWidth(1024);
    stage.setMinHeight(768);
    // L'écran à afficher au démarrage de l'application
    scene = new Scene(loadFXML("liste_visiteurs"), 1024, 768);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Change la scène affichée.
   *
   * @param fxml le nom du fichier FXML à charger
   * @throws IOException si le chargement du FXML échoue
   */
  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  /**
   * Charge et renvoie un fichier FXML.
   *
   * @param fxml le nom du fichier FXML à charger
   * @return le parent racine du fichier FXML chargé
   * @throws IOException si le chargement du FXML échoue
   */
  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  /**
   * Point d'entrée principal de l'application.
   *
   * @param args les arguments éventuels de la ligne de commande
   */
  public static void main(String[] args) {
    // Cette méthode de JavaFX appelle ensuite la méthode start()
    launch();
  }
}
