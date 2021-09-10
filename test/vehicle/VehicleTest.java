package vehicle;

import exceptions.VehicleNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.vehicle.VehicleServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {
    VehicleServiceImpl vehicleServiceImpl;
    Vehicle vehicle1;
    Vehicle vehicle2;

    @BeforeEach
    void setUp() {
        vehicleServiceImpl = new VehicleServiceImpl();
    }
    private void createVehicle(){
        vehicle1 = new Vehicle(VehicleType.TWO_SEATER,
                "Toyota",
                "Corolla",
                2012,
                "Black",
                "XYZ-12BD");
        vehicle2 = new Vehicle(VehicleType.FOUR_SEATER,
                "Honda",
                "Civic",
                2016,
                "Wine",
                "MAZ-24UH");
    }

    @AfterEach
    void tearDown() {
    }
    @DisplayName("Register vehicle")
    @Test
    void testThatVehicleCanBeRegistered(){
        createVehicle();
        assertEquals("2021-09-04", vehicle1.getDateCreated().toString());
        vehicleServiceImpl.register(vehicle1);
        vehicleServiceImpl.register(vehicle2);

        assertEquals(2, vehicleServiceImpl.count());
    }

    @DisplayName("Find vehicle by plate number")
    @Test
    void testToFindVehicleByPlateNumber() throws VehicleNotFoundException {
        createVehicle();
        vehicleServiceImpl.register(vehicle1);
        vehicleServiceImpl.register(vehicle2);

        Vehicle foundVehicle = vehicleServiceImpl.findByPlateNumber("XYZ-12BD");
        assertEquals("Toyota", foundVehicle.getBrandName());

    }
}