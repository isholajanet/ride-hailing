package vehicle;

import java.time.LocalDate;

public class Vehicle {
    private VehicleType vehicleType;
    private String brandName;
    private String modelName;
    private int modelYear;
    private String color;
    private boolean isActive;
    private String plateNumber;
    private LocalDate dateCreated;


    public Vehicle(VehicleType vehicleType, String brandName, String modelName, int modelYear, String color, String plateNumber) {
        this.vehicleType = vehicleType;
        this.brandName = brandName;
        this.modelName = modelName;
        this.modelYear = modelYear;
        this.color = color;
        this.plateNumber = plateNumber;
        dateCreated = LocalDate.now();
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getBrandName() {
        return brandName;
    }
}
