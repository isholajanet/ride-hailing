package service.vehicle;

import exceptions.VehicleNotFoundException;
import vehicle.Vehicle;

public interface VehicleService {
    int count();
    void register(Vehicle vehicle);
    Vehicle findByPlateNumber(String plateNumber) throws VehicleNotFoundException;
}
