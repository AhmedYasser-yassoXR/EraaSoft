import java.util.Scanner;

class Alphabet {

    // Current Character
    char currentCharacter;

    // Constructor
    Alphabet(char currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    // Next Character
    char getNextCharacter() {
        return (char) (currentCharacter + 1);
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        char currentCharacter = input.next().charAt(0);

        Alphabet alphabet = new Alphabet(currentCharacter);

        System.out.println(alphabet.getNextCharacter());
    }
}