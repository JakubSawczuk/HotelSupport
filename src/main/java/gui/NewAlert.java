package gui;

import javafx.scene.control.Alert;

public class NewAlert {

    public NewAlert(String alertType, String title, String header) {
        Alert alert;
        if (alertType.equals("Error")) alert = new Alert(Alert.AlertType.ERROR);
        else if (alertType.equals("Information")) alert = new Alert(Alert.AlertType.INFORMATION);
        else alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
