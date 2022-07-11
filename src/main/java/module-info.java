module ec.edu.espol.proyecto1p_edd_grupo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyecto1p_edd_grupo3 to javafx.fxml;
    exports ec.edu.espol.proyecto1p_edd_grupo3;
    opens ec.edu.espol.controllers to javafx.fxml;
    exports ec.edu.espol.controllers;
    opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
}
