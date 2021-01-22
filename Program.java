package Exercise4;

import Exercise4.Helpers.FileUtils;
import Exercise4.Items.GameCollection;

import java.io.File;
import java.util.Scanner;

public class Program {
    private GameCollection gameCollection;

    public Program() {
        gameCollection = new GameCollection();
    }

    public void start() {
        //Testa att läsa spelsamling från fil
        File tempFile = new File("src/Exercise4/gameCollection.ser");
        if (tempFile.exists()) {
            gameCollection = (GameCollection) FileUtils.readObject("src/Exercise4/gameCollection.ser");
        }
        else {
            System.out.println("Initerar spelsamling");
            gameCollection.initiateAllConsoles();
            gameCollection.initiateAllGenres();
            gameCollection.initiateGameObjects();
            gameCollection.addToAllGames();
            gameCollection.initiateGameListsInGenres();
        }
        runMenu();
        FileUtils.writeObject("src/Exercise4/gameCollection.ser", gameCollection);
        System.out.println("\nSamlingen sparad, hejdå!");
    }

    private void runMenu() {
        Scanner scan = new Scanner(System.in);
        int userInput;
        do {
            printMenu();
            userInput = scan.nextInt();
            menuSwitch(userInput);
        } while (userInput != 7);
    }

    private void printMenu() {
        System.out.println("\nVälj ett av följande:\n" +
                "1. Visa spelsamling\n" +
                "2. Lägg till ett spel\n" +
                "3. Ta bort ett spel\n" +
                "4. Visa spel efter konsoll\n" +
                "5. Visa spel efter genre\n" +
                "6. Sök efter spel\n" +
                "7. Avsluta\n" +
                "Skriv ditt val följt av Enter.");
    }

    private void menuSwitch(int userInput) {
        switch (userInput) {
            case 1 -> gameCollection.showCollection();
            case 2 -> gameCollection.addGame();
            case 3 -> gameCollection.removeGame();
            case 4 -> gameCollection.showGamesByConsole();
            case 5 -> gameCollection.showGamesByGenre();
            case 6 -> gameCollection.searchForGame();
        }
    }


}
