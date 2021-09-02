package user;

import database.PassengerDb;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import service.PassengerService;

import java.util.ArrayList;
import java.util.List;

public class PassengerServiceImpl implements PassengerService {
    private final PassengerDb passengerDb;
    public PassengerServiceImpl(){
        passengerDb = new PassengerDb();
    }
    @Override
    public void createPassenger(Passenger passenger) throws UserAlreadyExistsException {
        if(passengerDb.contains(passenger)){
            throw new UserAlreadyExistsException("Passenger already exist");
        }
        passengerDb.save(passenger);

    }

    @Override
    public Passenger findPassenger(Passenger passenger) throws UserNotFoundException {
        Passenger foundPassenger = passengerDb.find(passenger);
        return foundPassenger;
    }

    @Override
    public Passenger findPassengerById(String passengerId) throws UserNotFoundException {
        Passenger foundPassenger = passengerDb.findById(passengerId);
        return foundPassenger;
    }

    @Override
    public List<Passenger> findPassengerByName(String name) throws UserNotFoundException {
        List<Passenger> passengers = new ArrayList<>();
        for (Passenger passenger: passengerDb.findAll()) {
            if(passenger.getFirstName().compareToIgnoreCase(name) == 0 || passenger.getLastName().compareToIgnoreCase(name) == 0){
                passengers.add(passenger);
            }
        }
        if(passengers.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        return passengers;
    }

    @Override
    public void updatePassenger(Passenger passenger) {

    }

    @Override
    public void deletePassenger(Passenger passenger) {

    }
    public long count(){
        return passengerDb.count();
    }

}
