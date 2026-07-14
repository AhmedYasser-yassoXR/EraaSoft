import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();

        Numbers numbers = new Numbers(a, b, c);

        System.out.println(numbers.getMin() + " " + numbers.getMax());
    }
}

class Numbers {
    int a, b, c;

    Numbers(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    int getMin() {
        return Math.min(a, Math.min(b, c));
    }

    int getMax() {
        return Math.max(a, Math.max(b, c));
    }
}