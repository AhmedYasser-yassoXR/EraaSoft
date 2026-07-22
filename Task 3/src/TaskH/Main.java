package TaskH;

import java.util.Scanner;

class primeNumbers {

    int n;

    primeNumbers(int n) {
        this.n= n;
    }

        boolean isPrime() {

            if (n <= 1) {
                return false;
            }

            for (int i = 2; i * i <= n; i++) {

                if (n % i == 0) {
                    return false;
                }

            }

            return true;
        }
    }

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        primeNumbers PrimeNumbers =new primeNumbers(n);

        if(PrimeNumbers.isPrime()) {
            System.out.println("YES");
        }
        else System.out.println("NO");{

        }


    }
}