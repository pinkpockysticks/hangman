package main;

import java.util.HashMap;
import java.util.Scanner;

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

    public void playGame() {}
}
