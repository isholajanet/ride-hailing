package service.vehicle;

import database.VehicleDb;
import exceptions.VehicleNotFoundException;
import vehicle.Vehicle;


public class VehicleServiceImpl implements VehicleService{
    private final VehicleDb vehicleDb;

    public VehicleServiceImpl(){
        vehicleDb = new VehicleDb();
    }
    @Override
    public int count() {
        return vehicleDb.count();
    }

    @Override
    public void register(Vehicle vehicle) {
        vehicleDb.save(vehicle);
    }

    @Override
    public Vehicle findByPlateNumber(String plateNumber) throws VehicleNotFoundException {
        return vehicleDb.findByPlateNumber(plateNumber);
    }
}
