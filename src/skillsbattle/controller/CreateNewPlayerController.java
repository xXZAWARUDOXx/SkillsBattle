package skillsbattle.controller;

import ch.nahro.test.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import skillsbattle.dao.GenericDAO;
import skillsbattle.model.Player;

import java.util.List;

public class CreateNewPlayerController {
    private Stage stage;
    private GenericDAO playerDAO;

    @FXML
    private PasswordField playerPassword;
    @FXML
    private PasswordField playerPasswordConfirm;

    public CreateNewPlayerController() {

    }

    public CreateNewPlayerController(Stage stage) {
        this.stage = stage;
    }

    public void init() {
        this.playerDAO = new GenericDAO(Player.class);
    }

    @FXML
    public void back(ActionEvent actionEvent) {
        init();
        this.stage.close();
        NameSearchController nameSearchController = new NameSearchController(stage);
        nameSearchController.showStage();
    }

    @FXML
    public void login() {
        init();
        List<Player> players = playerDAO.namedQuery(playerPassword.getText(), "searchForPassword");
        if (Helper.isValid(playerPassword.getText(), playerPasswordConfirm.getText())); {
            if (players.get(0).getPassword().equals(playerPassword.getText())) {
                // alles gut
                playerDAO.insert(new Player("Player A", playerPassword.getText()));
                this.stage.close();
                SecondPlayerController secondPlayerController = new SecondPlayerController();
                secondPlayerController.showStageSecondPlayer();
            } else {
                // Spieler muss erstellt werden
                this.stage.close();
                Helper.feedback(Alert.AlertType.ERROR, "Error Login", "Falsche Daten", "Bitte Daten richtig eingeben");
                playerPassword.setStyle("-fx-text-box-border: #FF0000; -fx-focus-color: #FF0000;");
                playerPasswordConfirm.setStyle("-fx-text-box-border: #FF0000; -fx-focus-color: #FF0000;");
            }
        }
    }

    public void showStageCreateNewPlayer() {
        this.stage = Helper.getStage(this.getClass().getClassLoader().getResource("createNewPlayer.fxml"), "5 in a row", this);
        this.stage.show();
    }
}
