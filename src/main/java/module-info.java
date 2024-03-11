module fr.pgah {
  requires transitive javafx.controls;
  requires javafx.fxml;
  requires java.sql;

  opens fr.pgah to javafx.fxml;
  opens fr.pgah.Controllers to javafx.fxml;
  opens fr.pgah.Model to javafx.base;

  exports fr.pgah;
}
