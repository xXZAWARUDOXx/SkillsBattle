package skillsbattle.controller;

import ch.nahro.test.Helper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import skillsbattle.dao.GenericDAO;
import skillsbattle.model.Player;

public class SecondPlayerController {
    private Stage stage;
    private GenericDAO playerDAO;
    private ImageView imageViewPlayer = new ImageView("mensch.PNG");
    private ImageView imageViewComputer = new ImageView("pc.PNG");
    private String arg = "";
    boolean secondPlaysFirst = false;

    @FXML
    private CheckBox checkBox;
    @FXML
    private ImageView player;
    @FXML
    private ImageView computer;
    @FXML
    private Button buttonPlayer;
    @FXML
    private Button buttonComputer;

    public void init() {
        this.playerDAO = new GenericDAO(Player.class);
        this.buttonPlayer = new Button();
        this.buttonComputer = new Button();
    }

    @FXML
    public void gameStart() {
        init();
        this.stage.close();
        MainGameController mainGameController = new MainGameController(stage, arg, secondPlaysFirst);
        mainGameController.showMainGameController();
    }

    @FXML
    public void player() {
        init();
        buttonPlayer.setGraphic(imageViewPlayer);
        buttonPlayer.setVisible(false);
        buttonPlayer.setDisable(true);
        buttonComputer.setDisable(false);
        System.out.println("player");
        arg = "player";
    }

    @FXML
    public void computer() {
        init();
        buttonComputer.setGraphic(imageViewComputer);
        buttonComputer.setVisible(false);
        buttonPlayer.setDisable(false);
        buttonComputer.setDisable(true);
        System.out.println("computer");
        arg = "computer";
    }

    @FXML
    public boolean fistPlayer() {
        if (checkBox.isSelected()) {
            secondPlaysFirst = true;
        }

        return secondPlaysFirst;
    }

    public void showStageSecondPlayer() {
        this.stage = Helper.getStage(this.getClass().getClassLoader().getResource("secondPlayer.fxml"), "5 in a row", this);
        this.stage.show();
    }
}
