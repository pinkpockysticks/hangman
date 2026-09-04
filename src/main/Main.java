package main;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main() {

        printTitle();
        menu();
    }

    public static void printTitle() {
        System.out.println("""
                  ____         ____      _         _      __   __U _____ u   ____          _   _      _      _   _     ____    __  __      _      _   _    \s
                  |___"\\      U|  _"\\ u  |"|    U  /"\\  u  \\ \\ / /\\| ___"|/U |  _"\\ u      |'| |'| U  /"\\  u | \\ |"| U /"___|uU|' \\/ '|uU  /"\\  u | \\ |"|   \s
                  U __) |     \\| |_) |/U | | u   \\/ _ \\/    \\ V /  |  _|"   \\| |_) |/     /| |_| |\\ \\/ _ \\/ <|  \\| |>\\| |  _ /\\| |\\/| |/ \\/ _ \\/ <|  \\| |>  \s
                  \\/ __/ \\     |  __/   \\| |/__  / ___ \\   U_|"|_u | |___    |  _ <       U|  _  |u / ___ \\ U| |\\  |u | |_| |  | |  | |  / ___ \\ U| |\\  |u  \s
                  |_____|u     |_|       |_____|/_/   \\_\\    |_|   |_____|   |_| \\_\\       |_| |_| /_/   \\_\\ |_| \\_|   \\____|  |_|  |_| /_/   \\_\\ |_| \\_|   \s
                  <<  //       ||>>_     //  \\\\  \\\\    >>.-,//|(_  <<   >>   //   \\\\_      //   \\\\  \\\\    >> ||   \\\\,-._)(|_  <<,-,,-.   \\\\    >> ||   \\\\,-.\s
                 (__)(__)     (__)__)   (_")("_)(__)  (__)\\_) (__)(__) (__) (__)  (__)    (_") ("_)(__)  (__)(_")  (_/(__)__)  (./  \\.) (__)  (__)(_")  (_/ \s
                """);
    }

    public static void menu() {

        while (true) {
            System.out.println("Start new game [s], continue saved game [c] or quit [q] ?");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            s = s.trim();

            if (s.equalsIgnoreCase("s")) {
                signUp();
                break;
            } else if (s.equalsIgnoreCase("c")) {
                File file = new File("saves.txt");
                if (!file.exists()) {
                    System.out.println("No saved game found.");
                } else {
                    break;
                }
            } else if (s.equalsIgnoreCase("q")) {
                System.exit(0);
            }
        }
    }

    public static void signUp() {

        Player player1 = getName(1);
        Player player2 = getName(2);

    }

    public static Player getName(int playerNo) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter name of player " + playerNo);
            String name = scanner.nextLine();
            name = name.trim();
            boolean valid = validatePlayerName(name);
            while (!valid) {
                System.out.println("Entered name not valid. Name must only contain letters. Please try again:");
                name = scanner.nextLine();
                valid = validatePlayerName(name);
            }
            Player player = new Player(name);
            return player;
        }
    }

    public static boolean validatePlayerName(String name) {

        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(name);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        }
        return false;
    }

}