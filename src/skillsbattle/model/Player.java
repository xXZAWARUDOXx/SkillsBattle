package skillsbattle.model;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "getPlayerByName", query = "SELECT p FROM Player p WHERE p.playerName =:name"),
        @NamedQuery(name = "searchForPassword", query = "SELECT p FROM Player p WHERE p.password =:password")
})
@Table(name = "player")
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "playerName", length = 64)
    private String playerName;

    @Column(name = "password", length = 18)
    private String password;

    public Player(String playerName, String password) {
        this.playerName = playerName;
        this.password = password;
    }

    public Player() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}