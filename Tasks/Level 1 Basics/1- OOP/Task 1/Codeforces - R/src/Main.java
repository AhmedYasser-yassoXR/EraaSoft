import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        Age age = new Age(n);

        System.out.println(age.getYears() + " years");
        System.out.println(age.getMonths() + " months");
        System.out.println(age.getDays() + " days");
    }
}

class Age {

    int days;

    Age(int days) {
        this.days = days;
    }

    int getYears() {
        return days / 365;
    }

    int getMonths() {
        return (days % 365) / 30;
    }

    int getDays() {
        return (days % 365) % 30;
    }
}
