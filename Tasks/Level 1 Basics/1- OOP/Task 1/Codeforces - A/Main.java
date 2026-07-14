import java.util.Scanner;

class Greeting {

    String name;

    Greeting(String name) {

        this.name = name;
    }

    void sayHello() {

        System.out.println("Hello, " + name);
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String s = input.nextLine();

        Greeting person = new Greeting(s);

        person.sayHello();
    }
}