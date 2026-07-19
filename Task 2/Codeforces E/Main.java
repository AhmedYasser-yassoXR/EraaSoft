import java.util.Scanner;

class Interval {

    // Odd Numbers
    int oddNumbers;

    // Even Numbers
    int evenNumbers;

    // Constructor
    Interval(int oddNumbers, int evenNumbers) {
        this.oddNumbers = oddNumbers;
        this.evenNumbers = evenNumbers;
    }

    // Check Interval
    boolean isValidInterval() {
        return oddNumbers == evenNumbers || oddNumbers == evenNumbers + 1;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int oddNumbers = input.nextInt();
        int evenNumbers = input.nextInt();

        Interval interval = new Interval(oddNumbers, evenNumbers);

        if (interval.isValidInterval()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}