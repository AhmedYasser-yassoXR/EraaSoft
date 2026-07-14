import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        long l1 = input.nextLong();
        long r1 = input.nextLong();
        long l2 = input.nextLong();
        long r2 = input.nextLong();

        Interval interval1 = new Interval(l1, r1);
        Interval interval2 = new Interval(l2, r2);

        interval1.intersection(interval2);
    }
}

class Interval {

    long left, right;

    Interval(long left, long right) {
        this.left = left;
        this.right = right;
    }

    void intersection(Interval other) {

        long start = Math.max(this.left, other.left);
        long end = Math.min(this.right, other.right);

        if (start <= end)
            System.out.println(start + " " + end);
        else
            System.out.println(-1);
    }
}