package skillsbattle.controller;

import ch.nahro.test.Helper;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import skillsbattle.dao.GenericDAO;
import skillsbattle.model.Player;

public class MainGameController {
    private Stage stage;
    private GenericDAO playerDAO;
    private String arg;
    private boolean secondPlaysFirst;

    public MainGameController() {
    }

    public MainGameController(Stage stage) {
        this.stage = stage;
    }

    public MainGameController(Stage stage, String arg, boolean secondPlaysFirst) {
        this.stage = stage;
        this.arg = arg;
        this.secondPlaysFirst = secondPlaysFirst;
    }

    public void init() {
        this.playerDAO = new GenericDAO(Player.class);
    }

    @FXML
    public void restartGame() {
        // einfach gleiche Seite nochmals neu laden
        this.stage = Helper.getStage(this.getClass().getClassLoader().getResource("mainGame.fxml"), "5 in a row", this);
        this.stage.show();
    }

    @FXML
    public void logout() {
        this.stage.close();
        NameSearchController nameSearchController = new NameSearchController();
        nameSearchController.showStage();
    }

    public void showMainGameController() {
        this.stage = Helper.getStage(this.getClass().getClassLoader().getResource("mainGame.fxml"), "5 in a row", this);
        this.stage.show();
    }
}
