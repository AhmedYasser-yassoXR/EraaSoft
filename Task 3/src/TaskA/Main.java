package TaskA;



import java.util.Scanner;

class Numbers {

    int limit;

    Numbers(int limit) {
        this.limit = limit;
    }

    void printNumbers() {
        for (int i = 1; i <= limit; i++) {
            System.out.println(i);
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int limit = input.nextInt();

        Numbers numbers = new Numbers(limit);

        numbers.printNumbers();
    }
}