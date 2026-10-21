package VehicleRegistration.service;

import VehicleRegistration.exception.DuplicatePlateException;
import VehicleRegistration.exception.VehicleNotFoundException;
import VehicleRegistration.model.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class RegistrationService {

    private List<Vehicle> vehiclesList = new ArrayList<>();
    private Map<String, Vehicle> plateIndex = new HashMap<>();
    private Set<String> registeredPlates = new HashSet<>();
    private Map<String, List<String>> ownerHistory = new HashMap<>();

    public void registerVehicle(Vehicle v) {

        String plate = v.getPlateNumber().toUpperCase();

        if (registeredPlates.contains(plate)) {
            throw new DuplicatePlateException(plate);
        }

        vehiclesList.add(v);
        plateIndex.put(plate, v);
        registeredPlates.add(plate);

        ownerHistory.put(
                plate,
                new ArrayList<>(Collections.singletonList(v.getOwnerName()))
        );
    }

    public Vehicle findByPlate(String plate) {

        String normalizedPlate = plate.toUpperCase();

        if (!plateIndex.containsKey(normalizedPlate)) {
            throw new VehicleNotFoundException(normalizedPlate);
        }

        return plateIndex.get(normalizedPlate);
    }

    public void updateOwner(String plate, String newOwner) {

        Vehicle vehicle = findByPlate(plate);
        vehicle.setOwnerName(newOwner);

        String normalizedPlate = plate.toUpperCase();
        ownerHistory.get(normalizedPlate).add(newOwner);
    }

    public void deleteVehicle(String plate) {

        Vehicle vehicle = findByPlate(plate);
        String normalizedPlate = vehicle.getPlateNumber().toUpperCase();

        vehiclesList.remove(vehicle);
        plateIndex.remove(normalizedPlate);
        registeredPlates.remove(normalizedPlate);
        ownerHistory.remove(normalizedPlate);
    }

    public List<Vehicle> getAllVehicles() {
        return Collections.unmodifiableList(vehiclesList);
    }

    public List<Vehicle> filterByType(String type) {

        return vehiclesList.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase(type))
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Vehicle> getVehiclesByOwner(String ownerName) {

        return vehiclesList.stream()
                .filter(v -> v.getOwnerName().toLowerCase()
                        .contains(ownerName.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Vehicle> getExpiredRegistrations(int currentYear) {

        return vehiclesList.stream()
                .filter(v -> currentYear - v.getRegistrationYear() > 5)
                .sorted(Comparator.comparingInt(
                        Vehicle::getRegistrationYear))
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Vehicle> getSortedByYear(boolean ascending) {

        Comparator<Vehicle> comparator =
                Comparator.comparingInt(Vehicle::getRegistrationYear);

        if (!ascending) {
            comparator = comparator.reversed();
        }

        return vehiclesList.stream()
                .sorted(comparator)
                .collect(java.util.stream.Collectors.toList());
    }

    // Get owner history
    public List<String> getOwnerHistory(String plate) {

        findByPlate(plate);

        String normalizedPlate = plate.toUpperCase();

        return Collections.unmodifiableList(
                ownerHistory.get(normalizedPlate)
        );
    }

    // 1. Get count, average, minimum and maximum registration year

    public void printStatistics() {
     IntSummaryStatistics stats = vehiclesList.stream().mapToInt(Vehicle::getRegistrationYear).summaryStatistics();

    // 2.Count vehicles by type
     Map<String, Long> byType = vehiclesList.stream()
             .collect(Collectors.groupingBy(Vehicle::getVehicleType, Collectors.counting()));

        // 3. Partition vehicles into ACTIVE and EXPIRED
     Map<Boolean, Long> byStatus = vehiclesList.stream()
             .collect(Collectors.partitioningBy(
                     v -> v.getStatus().equalsIgnoreCase("ACTIVE"),
                     Collectors.counting()));
        System.out.println("========== REGISTRATION STATISTICS ==========");
        System.out.printf("Total Vehicles : %d%n", stats.getCount());

        if (stats.getCount() > 0) {

            // %.0f%n average el year 3l4an display as a number
            // %d%n  عشان تحدد شكل طباعة البيانات
            System.out.printf("Average Year : %.0f%n", stats.getAverage());
            System.out.printf("Newest Vehicle : %d%n", stats.getMax());
            System.out.printf("Oldest Vehicle : %d%n", stats.getMin());
        } else {
            System.out.println("Average Year : N/A");
            System.out.println("Newest Vehicle : N/A");
            System.out.println("Oldest Vehicle : N/A");
        }

        System.out.println("----------------------------------------------");
        System.out.println("Vehicles by Type:");

        System.out.printf(" Car : %d%n",
                byType.getOrDefault("Car", 0L));

        System.out.printf(" Truck : %d%n",
                byType.getOrDefault("Truck", 0L));

        System.out.printf(" Motorcycle : %d%n",
                byType.getOrDefault("Motorcycle", 0L));

        System.out.println("----------------------------------------------");

        System.out.printf("ACTIVE vehicles : %d%n",
                byStatus.get(true));

        System.out.printf("EXPIRED vehicles: %d%n",
                byStatus.get(false));

        System.out.println("==============================================");
    }


}

