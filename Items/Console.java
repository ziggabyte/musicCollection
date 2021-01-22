package Exercise4.Items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Console implements Serializable {
    private String name;
    private String date;
    private HashMap<String, Game> gamesHash;

    public Console() {
    }

    public Console(String name, String date) {
        this.name = name;
        this.date = date;
        gamesHash = new HashMap<>();
    }

    public void addToGamesHash(Game game) {
        gamesHash.put(game.getName(), game);
    }

    public void removeGameFromHash(Game game){
        gamesHash.remove(game.getName());
    }

    public List<Game> getGamesHashAsList() {
        return new ArrayList<>(gamesHash.values());
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
