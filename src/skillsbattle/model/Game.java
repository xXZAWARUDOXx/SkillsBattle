package skillsbattle.model;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "game", indexes = {
        @Index(name = "fk_Game_Player_idx", columnList = "firstPlayerID"),
        @Index(name = "fk_Game_Player1_idx", columnList = "secondPlayerID")
})
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "firstPlayerID")
    private Player firstPlayerID;

    @ManyToOne
    @JoinColumn(name = "secondPlayerID")
    private Player secondPlayerID;

    @Column(name = "result")
    private Integer result;

    @Column(name = "moves")
    private Integer moves;

    @Column(name = "gameDate")
    private Instant gameDate;

    public Instant getGameDate() {
        return gameDate;
    }

    public void setGameDate(Instant gameDate) {
        this.gameDate = gameDate;
    }

    public Integer getMoves() {
        return moves;
    }

    public void setMoves(Integer moves) {
        this.moves = moves;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Player getSecondPlayerID() {
        return secondPlayerID;
    }

    public void setSecondPlayerID(Player secondPlayerID) {
        this.secondPlayerID = secondPlayerID;
    }

    public Player getFirstPlayerID() {
        return firstPlayerID;
    }

    public void setFirstPlayerID(Player firstPlayerID) {
        this.firstPlayerID = firstPlayerID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}