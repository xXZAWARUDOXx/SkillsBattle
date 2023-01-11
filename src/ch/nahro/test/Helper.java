package ch.nahro.test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

public class Helper {
    public static FXMLLoader loader;
    public static String PATTERN = "/^[a-zA-Z]+$/g";

    public static Stage getStage(URL url, String title, Object controller) {
        Stage stage = new Stage();
        loader = new FXMLLoader(url);
        loader.setController(controller);
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle(title);
        return stage;
    }

    public static void feedback(Alert.AlertType type, String title, String header, String context) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }

    public static boolean isValid(String password, String confirm) {
        Pattern specialCharPattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern upperCasePattern = Pattern.compile("[A-Z ]");
        Pattern lowerCasePattern = Pattern.compile("[a-z ]");
        Pattern digitCasePattern = Pattern.compile("[0-9 ]");
        boolean flag = true;
        if (!password.equals(confirm)) {
            flag = false;
        }
        if (password.length() < 6) {
            flag = false;
        }
        if (!specialCharPattern.matcher(password).find()) {
            flag = false;
        }
        if (!upperCasePattern.matcher(password).find()) {
            flag = false;
        }
        if (!lowerCasePattern.matcher(password).find()) {
            flag = false;
        }
        if (!digitCasePattern.matcher(password).find()) {
            flag = false;
        }
        return flag;
    }
}
