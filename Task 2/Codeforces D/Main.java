import java.util.Scanner;

class Puzzle {

    // Numbers
    long firstNumber;
    long secondNumber;
    long thirdNumber;
    long result;

    // Constructor
    Puzzle(long firstNumber, long secondNumber, long thirdNumber, long result) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
        this.result = result;
    }

    // El anwser
    boolean checkPuzzle() {

        return firstNumber + secondNumber + thirdNumber == result ||
                firstNumber + secondNumber - thirdNumber == result ||
                firstNumber + secondNumber * thirdNumber == result ||

                firstNumber - secondNumber + thirdNumber == result ||
                firstNumber - secondNumber - thirdNumber == result ||
                firstNumber - secondNumber * thirdNumber == result ||

                firstNumber * secondNumber + thirdNumber == result ||
                firstNumber * secondNumber - thirdNumber == result ||
                firstNumber * secondNumber * thirdNumber == result;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        long firstNumber = input.nextLong();
        long secondNumber = input.nextLong();
        long thirdNumber = input.nextLong();
        long result = input.nextLong();

        Puzzle puzzle = new Puzzle(firstNumber, secondNumber, thirdNumber, result);

        if (puzzle.checkPuzzle()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}