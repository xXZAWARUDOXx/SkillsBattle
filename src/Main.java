import javafx.application.Application;
import javafx.stage.Stage;
import skillsbattle.controller.NameSearchController;
import skillsbattle.dao.Connector;
import skillsbattle.dao.GenericDAO;
import skillsbattle.model.Game;
import skillsbattle.model.Player;

import java.time.Instant;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Connector.getInstance().setConfiguration();

        Player person = new Player();
        person.setPlayerName("Nahro");
        Game game = new Game();
        game.setGameDate(Instant.now());
        GenericDAO<Player> playerDAO = new GenericDAO<>(Player.class);
        playerDAO.insert(person);
        game.setFirstPlayerID(person);
        GenericDAO<Game> gameDAO = new GenericDAO<>(Game.class);
        gameDAO.insert(game);

        NameSearchController nameSearchController = new NameSearchController();
        nameSearchController.showStage();
    }
}
