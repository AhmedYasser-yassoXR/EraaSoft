import java.util.Scanner;

class Adder {

    int firstNumber;

    int secondNumber;

    // Constructor
    Adder(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    // nezot ragm
    int addWithoutCarry() {
        return firstNumber ^ secondNumber;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int firstNumber = input.nextInt();
        int secondNumber = input.nextInt();

        Adder adder = new Adder(firstNumber, secondNumber);

        System.out.println(adder.addWithoutCarry());
    }
}