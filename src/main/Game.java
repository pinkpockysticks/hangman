package main;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    private Player player1;
    private Player player2;
    private Scanner input;
    private HashMap<Character, Character> state = new HashMap<>();
    private List<Character> mistakes = new ArrayList<>();
    private int mistakesCounter = 0;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.input = new Scanner(System.in);
    }

    public void playGame() {
        Phrase phrase = getPhrase();
        setState(phrase);

        if (phrase != null) {
            displayGame(phrase);

            while (true) {
                System.out.println("Category: " + phrase.getHint());
                System.out.println("List of available commands: [quit]");
                if (!enterLetter(phrase, true)) {
                    break;
                }
                displayGame(phrase);
            }
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

    public void setState(Phrase phrase) {
        for (char c : phrase.getCharacters()) {
            state.put(c, '_');
        }
    }

    private void displayGame(Phrase phrase) {
        List<Character> characters = phrase.getCharacters();
        System.out.println();
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            if (Objects.equals(character, ' ')) {
                System.out.print("    ");
            } else if (state.get(character).equals("_")){
                System.out.print("_ ");
            } else {
                System.out.print(state.get(character) + " ");
            }
        }
        System.out.println();
    }

    private boolean enterLetter(Phrase phrase, boolean quitReturnsToMainMenu) {
        System.out.println("Enter letter or command: ");
        String s = input.nextLine().trim().toLowerCase();
        if (s.length() != 1) {
            if (s.equals("quit")) {
                return false; //will implement saving and quitting later
            } else {
                System.out.print("That's not a valid letter or command! Try again: ");
                return true;
            }
        } else {
            Pattern pattern = Pattern.compile("^[a-zA-Z]$");
            Matcher matcher = pattern.matcher(s);
            boolean matchFound = matcher.find();
            if (!matchFound) {
                System.out.print("That's not a valid letter or command! Try again: ");
            } else {
                char l = s.charAt(0);
                if (state.containsKey(l) && state.get(l) != l) { //correct guess
                    state.put(l, l);
                    //update stats
                    return true;
                } else if (state.containsKey(l) && state.get(l) == l) {
                    System.out.print("You've already guessed this letter! Try again: ");
                    return true; // if entered info is the same as the game state do nothing
                } else if (!state.containsKey(l) && !mistakes.contains(l)) { // incorrect guess
                    System.out.print("Wrong letter! Try again: ");
                    mistakes.add(l);
                    mistakesCounter++;
                    //update stats
                    return true;
                } else { // already guessed incorrect letter
                    System.out.print("You've already guessed this letter! Try again: ");
                    return true;
                }

            }
        }
        return true;
    }

}
