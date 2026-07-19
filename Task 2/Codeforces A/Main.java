import java.util.Scanner;

class TShirt {

    // EL Discount
    double discountPercentage;

    // Price after discounte
    double discountedPrice;

    // Constructor
    TShirt(double discountPercentage, double discountedPrice) {
        this.discountPercentage = discountPercentage;
        this.discountedPrice = discountedPrice;
    }

    // Price before discont
    double getOriginalPrice() {
        return (discountedPrice * 100) / (100 - discountPercentage);
    }

    void printInfo() {
        System.out.println("Discount: " + discountPercentage + "%");
        System.out.println("Price After Discount: " + discountedPrice);
        System.out.printf("Original Price: %.2f%n", getOriginalPrice());
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Discount");
        double discountPercentage = input.nextDouble();
        System.out.println("Price");
        double discountedPrice = input.nextDouble();

        TShirt shirt = new TShirt(discountPercentage, discountedPrice);

        shirt.printInfo();
    }
}