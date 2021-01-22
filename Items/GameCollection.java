package Exercise4.Items;

import java.io.Serializable;
import java.util.*;

public class GameCollection implements Serializable {
    private HashMap<String, Game> allGames;
    private HashMap<String, Console> allConsoles;
    private HashMap<String, Genre> allGenres;

    public GameCollection() {
        this.allGames = new HashMap<>();
        this.allConsoles = new HashMap<>();
        this.allGenres = new HashMap<>();
    }

    public void initiateAllConsoles() {
        allConsoles.put("Nintendo Switch", new Console("Nintendo Switch", "2017-03-03"));
        allConsoles.put("Playstation 1", new Console("Playstation 1", "1994-12-03"));
        allConsoles.put("Super Nintendo", new Console("Super Nintendo", "1990-11-21"));
        allConsoles.put("Xbox 360", new Console("Xbox 360", "2005-11-22"));
    }

    public void initiateAllGenres() {
        allGenres.put("Platformer", new Genre("Platformer"));
        allGenres.put("Open World", new Genre("Open World"));
        allGenres.put("Action", new Genre("Action"));
        allGenres.put("RPG", new Genre("RPG"));
        allGenres.put("Simulation", new Genre("Simulation"));
        allGenres.put("Shooter", new Genre("Shooter"));
    }

    public void initiateGameObjects() {
        getConsoleByName("Nintendo Switch").addToGamesHash(new Game("Super Mario Odyssey", getGenreByName("Platformer"), getConsoleByName("Nintendo Switch")));
        getConsoleByName("Nintendo Switch").addToGamesHash(new Game("Yoshi's Crafted World", getGenreByName("Platformer"), getConsoleByName("Nintendo Switch")));
        getConsoleByName("Nintendo Switch").addToGamesHash(new Game("Zelda: Breath of the Wild", getGenreByName("Open World"), getConsoleByName("Nintendo Switch")));

        getConsoleByName("Playstation 1").addToGamesHash(new Game("Crash Bandicoot 1", getGenreByName("Platformer"), getConsoleByName("Playstation 1")));
        getConsoleByName("Playstation 1").addToGamesHash(new Game("Crash Bandicoot 3", getGenreByName("Platformer"), getConsoleByName("Playstation 1")));
        getConsoleByName("Playstation 1").addToGamesHash(new Game("Metal Gear Solid", getGenreByName("Action"), getConsoleByName("Playstation 1")));

        getConsoleByName("Super Nintendo").addToGamesHash(new Game("Secret of Mana", getGenreByName("RPG"), getConsoleByName("Super Nintendo")));
        getConsoleByName("Super Nintendo").addToGamesHash(new Game("EarthBound", getGenreByName("RPG"), getConsoleByName("Super Nintendo")));
        getConsoleByName("Super Nintendo").addToGamesHash(new Game("Super Mario World", getGenreByName("Platformer"), getConsoleByName("Super Nintendo")));

        getConsoleByName("Xbox 360").addToGamesHash(new Game("Viva Pinata", getGenreByName("Simulation"), getConsoleByName("Xbox 360")));
        getConsoleByName("Xbox 360").addToGamesHash(new Game("Halo 4", getGenreByName("Shooter"), getConsoleByName("Xbox 360")));
    }

    public void initiateGameListsInGenres() {
        List<Game> gameList = new ArrayList<>(allGames.values());
        for (Game g : gameList) {
            g.getGenre().addGame(g);
        }
    }

    public void removeGame() {
        System.out.println("\nVilket spel vill du ta bort? Skriv in siffra förljt av Enter.");
        List<Game> gameList = new ArrayList<>(allGames.values());
        HashMap<Integer, String> gameHash = new HashMap<>();
        int counter = 1;
        for (Game g : gameList) {
            System.out.println(counter + ". " + g.getName());
            gameHash.put(counter, g.getName());
            counter++;
        }
        Scanner scan = new Scanner(System.in);
        int userInput = scan.nextInt();

        String gameName = gameHash.get(userInput);
        Game gameToRemove = getGameByName(gameName);

        gameToRemove.getConsole().removeGameFromHash(gameToRemove);
        gameToRemove.getGenre().removeGame(gameToRemove);
        allGames.remove(gameToRemove.getName());
        System.out.println("\n" + gameToRemove.getName() + " har tagits bort ur samlingen!");
    }

    public void addToAllGames() {
        List<Console> consoleList = new ArrayList<>(allConsoles.values());
        List<Game> gameList = new ArrayList<>();
        for (Console c : consoleList) {
            gameList.addAll(c.getGamesHashAsList());
        }
        for (Game g : gameList) {
            allGames.put(g.getName(), g);
        }
    }

    public void addGame() {
        System.out.println("\nVad heter spelet du vill lägga till?");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();
        HashMap<Integer, String> tempHash = printGenreMenu();
        Genre tempGenre = readGenreFromMenu(tempHash);

        tempHash = printConsoleMenu();
        int userInput = scan.nextInt();
        String consoleName = tempHash.get(userInput);
        Console tempConsole = getConsoleByName(consoleName);

        Game newGame = new Game(title, tempGenre, tempConsole);

        tempConsole.addToGamesHash(newGame);
        allGames.put(newGame.getName(), newGame);
        tempGenre.addGame(newGame);
        System.out.println("\n" + newGame.getName() + " har lagts till i samlingen!");
    }

    public void showGamesByGenre() {
        HashMap<Integer, String> tempHash = printGenreMenu();
        Genre tempGenre = readGenreFromMenu(tempHash);
        List<Game> tempList = tempGenre.getGameList();
        if (tempList.size() > 0) {
            System.out.println("Du har följande spel i genren " + tempGenre.getName() + ":");
            for (Game g : tempList) {
                System.out.println(g.getName());
            }
        } else {
            System.out.println("Du har inga " + tempGenre.getName() + "-spel.");
        }
    }

    private HashMap<Integer, String> printGenreMenu() {
        Set<String> sourceSet = allGenres.keySet();
        List<String> genreNames = new ArrayList<>(sourceSet);
        HashMap<Integer, String> menuGenres = new HashMap<>();
        int counter = 1;
        for (String name : genreNames) {
            menuGenres.put(counter, name);
            counter++;
        }
        System.out.println("Välj genre och tryck Enter:");
        menuGenres.forEach((k, v) -> System.out.println(k + ". " + v));
        return menuGenres;
    }

    private Genre readGenreFromMenu(HashMap<Integer, String> menuGenres) {
        Scanner scan = new Scanner(System.in);
        int userInput = scan.nextInt();
        String genreName = menuGenres.get(userInput);
        return getGenreByName(genreName);
    }

    private HashMap<Integer, String> printConsoleMenu() {
        Set<String> sourceSet = allConsoles.keySet();
        List<String> consoleNames = new ArrayList<>(sourceSet);
        HashMap<Integer, String> menuConsoles = new HashMap<>();
        int counter = 1;
        for (String name : consoleNames) {
            menuConsoles.put(counter, name);
            counter++;
        }
        System.out.println("\nVälj konsoll och tryck Enter:");
        menuConsoles.forEach((k, v) -> System.out.println(k + ". " + v));
        return menuConsoles;
    }

    public void showGamesByConsole() {
        HashMap<Integer, String> consoleHash = printConsoleMenu();
        Scanner scan = new Scanner(System.in);
        int userInput = scan.nextInt();
        String consoleName = consoleHash.get(userInput);
        Console tempConsole = getConsoleByName(consoleName);
        List<Game> tempList = tempConsole.getGamesHashAsList();

        if (tempList.size() > 0) {
            System.out.println("Till " + consoleName + " har du följande spel:");
            for (Game g : tempList) {
                System.out.println(g.toString());
            }
        } else {
            System.out.println("Du har inga spel till " + consoleName);
        }
    }

    public void searchForGame() {
        System.out.println("Skriv in spelet du letar efter: ");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();

        Game tempGame = getGameByName(userInput);
        System.out.println("Här är spelet du letade efter:");
        System.out.println(tempGame.toString());
    }

    public Console getConsoleByName(String key) {
        return allConsoles.get(key);
    }

    public Game getGameByName(String key) {
        return allGames.get(key);
    }

    public Genre getGenreByName(String key) {
        return allGenres.get(key);
    }

    public void showCollection() {
        System.out.println("ALL CONSOLES");
        allConsoles.forEach((k, v) -> System.out.println(v.toString()));
        System.out.println("ALL GAMES");
        allGames.forEach((k, v) -> System.out.println(v.toString()));
    }
}
