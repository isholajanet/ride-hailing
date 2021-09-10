package database;

import exceptions.VehicleNotFoundException;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleDb {
    private List<Vehicle> vehicleList = new ArrayList<>();

    public int count() {
        return vehicleList.size();
    }

    public void save(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    public Vehicle findByPlateNumber(String plateNumber) throws VehicleNotFoundException {
        for(Vehicle vehicle: vehicleList){
            if(vehicle.getPlateNumber().equals(plateNumber)){
                return vehicle;
            }
        }
        throw new VehicleNotFoundException("Vehicle with plate number " +plateNumber + "not registered");
    }
}
