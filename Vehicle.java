package entity;

public class Vehicle {
    private int vehicleId;
    private String model;
    private String type;
    private String registrationNumber;
    private double rentalPricePerDay;

    public Vehicle(int vehicleId, String model, String type, String registrationNumber, double rentalPricePerDay) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public Vehicle() {}

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public double getRentalPricePerDay() { return rentalPricePerDay; }
    public void setRentalPricePerDay(double rentalPricePerDay) { this.rentalPricePerDay = rentalPricePerDay; }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", rentalPricePerDay=" + rentalPricePerDay +
                '}';
    }
}
