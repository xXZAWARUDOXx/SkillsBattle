package skillsbattle.controller;

import ch.nahro.test.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import skillsbattle.dao.GenericDAO;
import skillsbattle.model.Player;

import java.util.List;

public class NameSearchController {
    private Stage stage;
    private GenericDAO playerDAO;

    @FXML
    private TextField playerName;

    public NameSearchController() {
    }

    public NameSearchController(Stage stage) {
        this.stage = stage;
    }

    public void init() {
        this.playerDAO = new GenericDAO(Player.class);
    }

    @FXML
    private void cont(ActionEvent actionEvent) {
        init();
        List<Player> players = playerDAO.namedQuery(playerName.getText(), "getPlayerByName");
        if (!playerName.getText().equals("")) {
            if (!(players.size() == 0)) {
                if (players.get(0).getPlayerName().equals(playerName.getText())) {
                    // alles gut
                    this.stage.close();
                    PasswordCheckController passwordCheckController = new PasswordCheckController(stage, players.get(0));
                    passwordCheckController.showStagePasswordCheck();
                } else {
                    // error
                    Helper.feedback(Alert.AlertType.ERROR, "Error Login", "Falsche Daten", "Bitte Daten richtig eingeben");
                    playerName.setStyle("-fx-text-box-border: #FF0000; -fx-focus-color: #FF0000;");
                }
            } else {
                // Spieler erstellen
                CreateNewPlayerController createNewPlayerController = new CreateNewPlayerController();
                createNewPlayerController.showStageCreateNewPlayer();
            }
        } else {
            // error
            Helper.feedback(Alert.AlertType.ERROR, "Error Login", "Falsche Daten", "Bitte Daten richtig eingeben");
            playerName.setStyle("-fx-text-box-border: #FF0000; -fx-focus-color: #FF0000;");
        }
        /*this.stage.close();
        PasswordCheckController passwordCheckController = new PasswordCheckController(stage, players.get(0));
        passwordCheckController.showStagePasswordCheck();*/
    }

    public void showStage() {
        this.stage = Helper.getStage(this.getClass().getClassLoader().getResource("nameSearch.fxml"), "5 in a row", this);
        this.stage.show();
    }
}
