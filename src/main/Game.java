package main;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    private Player player1;
    private Player player2;
    private Scanner input;
    private HashMap<Character, Character> state = new HashMap<>();

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.input = new Scanner(System.in);
    }

    public void playGame() {
        Phrase phrase = getPhrase();

        if (phrase != null) {
            displayGame(phrase);
        }
    }

    public Phrase getPhrase() {
        String p = validatePhrase();
        String h = getHint();

        return new Phrase(p, h);
    }

    public String validatePhrase() {
        System.out.print("Enter a word or phrase for the other player to guess: ");
        String p = input.nextLine().trim();

        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");

        Matcher matcher = pattern.matcher(p);
        boolean matchFound = matcher.find();

        while (!matchFound || p.length() > 200) {
            System.out.print("Your word or phrase can only contain letters and spaces and must be 200 characters or less. Try again: ");
            p = input.nextLine().trim();
            matcher = pattern.matcher(p);
            matchFound = matcher.find();
        }
        p = p.toLowerCase().replaceAll(" +", " ");
        return p;
    }

    public String getHint() {
        System.out.println("What category is your word or phrase?");
        System.out.println("Suggestions: film, tv, music, book, sport, animal, person, brand, none");
        System.out.println("Enter a category:");
        String h = input.nextLine().trim();

        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");

        Matcher matcher = pattern.matcher(h);
        boolean matchFound = matcher.find();

        while (!matchFound || h.length() > 20) {
            System.out.print("Your category can only contain letters and spaces and must be 20 characters or less. Try again: ");
            h = input.nextLine().trim();
            matcher = pattern.matcher(h);
            matchFound = matcher.find();
        }

        h = h.toLowerCase().replaceAll(" +", " ");
        return h;
    }

    private void displayGame(Phrase phrase) {
        List<Character> characters = phrase.getCharacters();

        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            if (Objects.equals(character, ' ')) {
                System.out.print("    ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

}
