package Exercise4.Items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Genre implements Serializable {
    private String name;
    private List<Game> gameList;

    public Genre(){};

    public Genre(String name) {
        this.name = name;
        this.gameList = new ArrayList<>();
    }

    public void addGame(Game game){
        this.gameList.add(game);
    }

    public void removeGame(Game game){
        this.gameList.remove(game);
    }

    public List<Game> getGameList(){
        return gameList;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
