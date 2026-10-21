package VehicleRegistration.model;

public abstract class Vehicle {

    private String plateNumber;
    private String ownerName;
    private String vehicleType;
    private int registrationYear;
    private String status;

    public Vehicle(String plateNumber, String ownerName,
                   String vehicleType, int registrationYear,
                   String status) {
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.registrationYear = registrationYear;
        this.status = status;
    }
//--------------------Getter--------------------
    public String getPlateNumber() {
        return plateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public String getStatus() {
        return status;
    }
//--------------------Setter--------------------
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract String getRegistrationLabel();

    @Override
    public String toString() {
        return "Vehicle{" +
                "plateNumber='" + plateNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", registrationYear=" + registrationYear +
                ", status='" + status + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vehicle vehicle = (Vehicle) o;

        return plateNumber.equalsIgnoreCase(vehicle.plateNumber);
    }

    @Override
    public int hashCode() {
        return plateNumber.toLowerCase().hashCode();
    }

}
