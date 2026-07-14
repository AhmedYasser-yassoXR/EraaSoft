import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int a = input.nextInt();
        char s = input.next().charAt(0);
        int b = input.nextInt();

        Calculator calculator = new Calculator(a, s, b);
        calculator.calculate();
    }
}

class Calculator {

    int a, b;
    char op;

    Calculator(int a, char op, int b) {
        this.a = a;
        this.op = op;
        this.b = b;
    }

    void calculate() {
        switch (op) {
            case '+':
                System.out.println(a + b);
                break;

            case '-':
                System.out.println(a - b);
                break;

            case '*':
                System.out.println(a * b);
                break;

            case '/':
                System.out.println(a / b);
                break;
        }
    }
}