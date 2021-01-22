package Exercise4.Items;

import java.io.Serializable;

public class Game implements Serializable {
    private String name;
    private Genre genre;
    private Console console;

    public Game(){};

    public Game(String name, Genre genre, Console console) {
        this.name = name;
        this.genre = genre;
        this.console = console;
    }

    public String getName() {
        return this.name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Console getConsole() { return console; }

    @Override
    public String toString() {
        return "Namn: " + name +
                ", Genre: " + genre.toString();
    }
}
