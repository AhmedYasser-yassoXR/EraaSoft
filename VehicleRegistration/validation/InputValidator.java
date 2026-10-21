package VehicleRegistration.validation;

import VehicleRegistration.exception.InvalidInputException;

import java.time.Year;

public class InputValidator {

    private InputValidator() {
        // Utility class
    }

    public static void validatePlateNumber(String plateNumber) {
        if (plateNumber == null ||
                !plateNumber.trim().toUpperCase()
                        .matches("[A-Z0-9-]{3,10}")) {

            throw new InvalidInputException(
                    "Plate number must be 3-10 alphanumeric characters"
            );
        }
    }

    public static void validateOwnerName(String ownerName) {
        if (ownerName == null ||
                ownerName.trim().length() < 3 ||
                !ownerName.trim().matches("[a-zA-Z ]+")) {

            throw new InvalidInputException(
                    "Owner name must contain only letters"
            );
        }
    }

    public static void validateVehicleType(String vehicleType) {
        if (vehicleType == null ||
                !(vehicleType.trim().equalsIgnoreCase("Car") ||
                        vehicleType.trim().equalsIgnoreCase("Truck") ||
                        vehicleType.trim().equalsIgnoreCase("Motorcycle"))) {

            throw new InvalidInputException(
                    "Invalid type. Use: Car, Truck, Motorcycle"
            );
        }
    }

    public static void validateRegistrationYear(int registrationYear) {
        int currentYear = Year.now().getValue();

        if (registrationYear < 1990 ||
                registrationYear > currentYear) {

            throw new InvalidInputException(
                    "Year must be between 1990 and " + currentYear
            );
        }
    }
}
