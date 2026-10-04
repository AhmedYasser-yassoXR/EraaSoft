package EWallet.Global;

public class Validation {

    public static boolean isNull(String value) {
        return value == null;
    }

    public static boolean isEmpty(String value) {
        return value != null && value.isEmpty();
    }

    public static boolean isBlank(String value) {
        return value != null && value.isBlank();
    }

    public static boolean isValidInput(String value) {

        return value != null
                && !value.isEmpty()
                && !value.isBlank();
    }
}