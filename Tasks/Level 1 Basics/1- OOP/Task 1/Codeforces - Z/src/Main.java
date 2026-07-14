import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double A = input.nextDouble();
        double B = input.nextDouble();
        double C = input.nextDouble();
        double D = input.nextDouble();

        PowerComparison comparison = new PowerComparison(A, B, C, D);
        comparison.compare();
    }
}

class PowerComparison {

    double A, B, C, D;

    PowerComparison(double A, double B, double C, double D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    void compare() {

        double left = B * Math.log(A);
        double right = D * Math.log(C);

        if (left > right)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}