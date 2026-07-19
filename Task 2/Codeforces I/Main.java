import java.util.Scanner;

class LuckyNumber {

    int number;

    // Constructor
    LuckyNumber(int number) {
        this.number = number;
    }

    // Check Lucky Number
    boolean isLucky() {

        int firstDigit = number / 10;
        int secondDigit = number % 10;

        if (secondDigit != 0 && firstDigit % secondDigit == 0) {
            return true;
        }

        if (firstDigit != 0 && secondDigit % firstDigit == 0) {
            return true;
        }

        return false;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int number = input.nextInt();

        LuckyNumber luckyNumber = new LuckyNumber(number);

        if (luckyNumber.isLucky()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}