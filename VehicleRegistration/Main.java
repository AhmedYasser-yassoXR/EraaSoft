package VehicleRegistration;

import VehicleRegistration.exception.DuplicatePlateException;
import VehicleRegistration.exception.VehicleNotFoundException;
import VehicleRegistration.model.Car;
import VehicleRegistration.model.Truck;
import VehicleRegistration.model.Motorcycle;
import VehicleRegistration.model.Vehicle;
import VehicleRegistration.service.RegistrationService;
import VehicleRegistration.validation.InputValidator;
import VehicleRegistration.exception.InvalidInputException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RegistrationService service = new RegistrationService();

        while (true) {
            System.out.println("\n===== Vehicle Registration System =====");
            System.out.println("1. Register New Vehicle");
            System.out.println("2. Search Vehicle by Plate");
            System.out.println("3. Update Owner Name");
            System.out.println("4. Delete Vehicle");
            System.out.println("5. View All Vehicles");
            System.out.println("6. Filter by Vehicle Type");
            System.out.println("7. Show Owner History");
            System.out.println("8. Show Expired Registrations");
            System.out.println("9. Statistics Report");
            System.out.println("0. Exit");
            System.out.println("Select an option: ");
            int choice;
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle Plate: ");
                    String plate = sc.nextLine().trim();

                    System.out.print("Enter Owner Name: ");
                    String owner = sc.nextLine().trim();

                    System.out.print("Enter Vehicle Type (Car/Truck/Motorcycle): ");
                    String type = sc.nextLine().trim();

                    System.out.print("Enter Registration Year: ");
                    int year;

                    try {
                        year = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Input Error: Please enter a valid year.");
                        break;
                    }

                    try {
                        // Validate common fields before creating the vehicle
                        InputValidator.validatePlateNumber(plate);
                        InputValidator.validateOwnerName(owner);
                        InputValidator.validateVehicleType(type);
                        InputValidator.validateRegistrationYear(year);

                        Vehicle vehicle = null;

                        switch (type.toLowerCase()) {
                            case "car":
                                System.out.print("Enter number of doors: ");
                                int doors = Integer.parseInt(sc.nextLine());

                                vehicle = new Car(plate, owner, year, "ACTIVE", doors);
                                break;

                            case "truck":
                                System.out.print("Enter cargo capacity in tons: ");
                                double capacity = Double.parseDouble(sc.nextLine());

                                vehicle = new Truck(plate, owner, year, "ACTIVE", capacity);
                                break;

                            case "motorcycle":
                                System.out.print("Enter Engine Type (Sport/Cruiser/Off-Road): ");
                                String engineType = sc.nextLine().trim();

                                vehicle = new Motorcycle(plate, owner, year, "ACTIVE", engineType);
                                break;
                        }

                        if (vehicle != null) {
                            service.registerVehicle(vehicle);
                            System.out.println("Vehicle registered successfully.");
                        }

                    } catch (InvalidInputException e) {
                        System.out.println("Input Error: " + e.getMessage());

                    } catch (NumberFormatException e) {
                        System.out.println("Input Error: Please enter a valid numeric value.");

                    } catch (DuplicatePlateException e) {
                        System.out.println(e.getMessage());
                    }

                    break;


                case 2:
                    System.out.print("Enter Plate Number: ");
                    String plateNumber = sc.nextLine().trim();
                    Vehicle found = service.findByPlate(plateNumber);
                    System.out.println("===== Vehicle Details =====");
                    System.out.println(found);
                    System.out.println(found.getRegistrationLabel());
                    break;

                case 3:
                    System.out.print("Enter Vehicle Plate Number: ");
                    String updatePlate = sc.nextLine().trim();
                    System.out.print("Enter New Owner Name: ");
                    String newOwner = sc.nextLine().trim();
                    service.updateOwner(updatePlate, newOwner);
                    System.out.println("Owner update successful.");
                    break;

                case 4:
                    System.out.print("Enter Vehicle Plate Number: ");
                    String deletePlateNumber = sc.nextLine().trim();
                    service.deleteVehicle(deletePlateNumber);
                    System.out.println("Vehicle deleted successfully.");
                    break;
                case 5:
                    List<Vehicle> vehicles= service.getAllVehicles();
                    System.out.println(vehicles);
                    if (vehicles.isEmpty()) {
                        System.out.println("No Vehicles Found");
                    } else  {
                        for (Vehicle v : vehicles) {
                            System.out.println(v);
                            System.out.println(v.getRegistrationLabel());
                        }
                    }
                    break;
                case 6:
                    System.out.print("Enter Vehicle Type (Car/Truck/Motorcycle): ");
                    String filterType = sc.nextLine().trim();

                    List<Vehicle> filteredVehicles = service.getAllVehicles()
                            .stream()
                            .filter(v -> v.getVehicleType()
                                    .equalsIgnoreCase(filterType))
                            .collect(java.util.stream.Collectors.toList());

                    if (filteredVehicles.isEmpty()) {
                        System.out.println("No Vehicles Found for This Type");
                    } else {
                        for (Vehicle v : filteredVehicles) {
                            System.out.println(v);
                            System.out.println(v.getRegistrationLabel());
                        }
                    }
                    break;
                case 7:
                    System.out.print("Enter Vehicle Plate Number: ");
                    String historyPlate = sc.nextLine().trim();

                    List<String> history = service.getOwnerHistory(historyPlate);

                    System.out.println("===== Owner History =====");

                    for (int i = 0; i < history.size(); i++) {
                        System.out.println((i + 1) + ". " + history.get(i));
                    }
                    break;
                case 8:
                    int currentYear = java.time.Year.now().getValue();

                    List<Vehicle> expiredVehicles =
                            service.getExpiredRegistrations(currentYear);

                    if (expiredVehicles.isEmpty()) {
                        System.out.println("No Expired Registrations Found");
                    } else {
                        System.out.println("===== Expired Registrations =====");

                        for (Vehicle v : expiredVehicles) {
                            System.out.println(v);
                        }
                    }
                    break;
                case 9:
                    List<Vehicle> allVehicles = service.getAllVehicles();

                    long cars = allVehicles.stream()
                            .filter(v -> v.getVehicleType()
                                    .equalsIgnoreCase("Car"))
                            .count();

                    long trucks = allVehicles.stream()
                            .filter(v -> v.getVehicleType()
                                    .equalsIgnoreCase("Truck"))
                            .count();

                    long motorcycles = allVehicles.stream()
                            .filter(v -> v.getVehicleType()
                                    .equalsIgnoreCase("Motorcycle"))
                            .count();

                    long active = allVehicles.stream()
                            .filter(v -> v.getStatus()
                                    .equalsIgnoreCase("ACTIVE"))
                            .count();

                    long expired = service.getExpiredRegistrations(
                            java.time.Year.now().getValue()
                    ).size();
                    service.printStatistics();
                    System.out.println("===== Statistics Report =====");
                    System.out.println("Total Vehicles: " + allVehicles.size());
                    System.out.println("Cars: " + cars);
                    System.out.println("Trucks: " + trucks);
                    System.out.println("Motorcycles: " + motorcycles);
                    System.out.println("Active Status: " + active);
                    System.out.println("Expired by Registration Year: " + expired);
                    break;
                case 0:
                    System.out.println("Exiting Vehicle Registration System...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid Option");

            }
            try {
                switch (choice) {
                    // كل الـ cases من 0 إلى 9 هنا
                }
            } catch (DuplicatePlateException | VehicleNotFoundException e) {
                System.out.println(e.getMessage());
            }
            }

    }
    }

