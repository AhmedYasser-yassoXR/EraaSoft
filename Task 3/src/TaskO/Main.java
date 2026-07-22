package TaskO;

import java.util.Scanner;

class Triangle {

    int rows;

    Triangle(int rows) {
        this.rows = rows;
    }

    // function Print Triangle
    void printTriangle() {

        for (int i = 1; i <= rows; i++) {

            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

    }
}

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int rows = input.nextInt();

        Triangle triangle = new Triangle(rows);

        triangle.printTriangle();
    }
}