package TaskV;
import java.util.Scanner;

class PumGame {

    int lines;

    PumGame(int lines) {
        this.lines = lines;
    }

    // Print PUM Game
    void printGame() {

        int number = 1;

        for (int i = 1; i <= lines; i++) {

            System.out.println(number + " " + (number + 1) + " " + (number + 2) + " PUM");

            number += 4;
        }

    }
}

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int lines = input.nextInt();

        PumGame game = new PumGame(lines);

        game.printGame();
    }
}