import java.util.Scanner;

class Katryoshka {

    // Eyes
    long eyes;

    // Mouths
    long mouths;

    // Bodies
    long bodies;

    // Constructor
    Katryoshka(long eyes, long mouths, long bodies) {
        this.eyes = eyes;
        this.mouths = mouths;
        this.bodies = bodies;
    }

    // Maximum Katryoshkas
    long getMaximumKatryoshkas() {

        long firstType = Math.min(Math.min(eyes, mouths), bodies);

        eyes -= firstType;
        bodies -= firstType;

        long secondType = Math.min(eyes / 2, bodies);

        return firstType + secondType;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        long eyes = input.nextLong();
        long mouths = input.nextLong();
        long bodies = input.nextLong();

        Katryoshka doll = new Katryoshka(eyes, mouths, bodies);

        System.out.println(doll.getMaximumKatryoshkas());
    }
}