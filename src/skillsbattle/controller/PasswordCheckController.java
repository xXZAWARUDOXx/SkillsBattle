package skillsbattle.controller;

import ch.nahro.test.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import skillsbattle.dao.GenericDAO;
import skillsbattle.model.Player;

import java.util.List;

public class PasswordCheckController {
    private Stage stage;
    private Player player;
    private GenericDAO<Player> playerDAO;

    @FXML
    private PasswordField playerPassword;

    public PasswordCheckController(Stage stage, Player player) {
        this.stage = stage;
        this.player = player;
    }

    public void init() {
        this.playerDAO = new GenericDAO(Player.class);
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        init();
        List<Player> players = playerDAO.namedQuery(playerPassword.getText(), "searchForPassword");
        if (Helper.isValid(playerPassword.getText(), playerPassword.getText())); {
            if (players.get(0).getPassword().equals(playerPassword.getText())) {
                // alles gut
                this.stage.close();
                SecondPlayerController secondPlayerController = new SecondPlayerController();
                secondPlayerController.showStageSecondPlayer();
            } else {
                // Spieler muss erstellt werden
                this.stage.close();
                CreateNewPlayerController createNewPlayerController = new CreateNewPlayerController();
                createNewPlayerController.showStageCreateNewPlayer();
            }
        }
    }

    @FXML
    public void back(ActionEvent actionEvent) {
        init();
        this.stage.close();
        NameSearchController nameSearchController = new NameSearchController(stage);
        nameSearchController.showStage();
    }

    public void showStagePasswordCheck(){
        this.stage = Helper.getStage(this.getClass().getClassLoader().getResource("passwordCheck.fxml"), "5 in a row", this);
        this.stage.show();
        loadData();
    }

    public void loadData() {
        init();
        List<Player> players = playerDAO.getAll();
    }
}