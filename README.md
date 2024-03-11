# AP2 GSB - Projet de démarrage

Ce dépôt contient :

- un _dump_ d'une base de données _gsb_etudiants_ (fichier `gsb_etudiants.sql`)
- un projet JavaFX qui propose quelques fonctionnalités de base :
  - écran d'affichage de la liste des visiteurs (avec ListView et avec TableView)
  - classe de base pour l'accès en base de données avec JDBC (`AccesBdd.java`)
    - méthode de connexion à la BDD
    - méthode pour récupérer la liste des visiteurs
  - classe simple de logging (`Logger.java`) : permet de loguer des messages correspondants à des événements dans l'application sur la sortie standard (console)
  - classe `Visiteur`

Vous pouvez importer la BDD dans votre serveur MySQL et vous servir du projet JavaFX comme code de démarrage. Bien entendu, il faudra adapter le code de connexion à la base de données pour qu'il corresponde à votre configuration.
