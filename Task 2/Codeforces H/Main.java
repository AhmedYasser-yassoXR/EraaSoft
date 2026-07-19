import java.util.Scanner;

class DataType {

    long firstNumber;

    long secondNumber;

    long thirdNumber;

    // Constructor
    DataType(long firstNumber, long secondNumber, long thirdNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
    }

    // Check Data Type
    String getDataType() {

        long multiplication = firstNumber * secondNumber;

        if (multiplication % thirdNumber != 0) {
            return "double";
        }

        long result = multiplication / thirdNumber;

        if (result >= Integer.MIN_VALUE && result <= Integer.MAX_VALUE) {
            return "int";
        }

        return "long long";
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        long firstNumber = input.nextLong();
        long secondNumber = input.nextLong();
        long thirdNumber = input.nextLong();

        DataType dataType = new DataType(firstNumber, secondNumber, thirdNumber);

        System.out.println(dataType.getDataType());
    }
}