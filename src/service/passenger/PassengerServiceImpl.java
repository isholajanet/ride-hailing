package service.passenger;

import database.PassengerDb;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import service.passenger.PassengerService;
import user.Passenger;
import user.PassengerUpdateForm;

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
    public void updatePassenger(String id, PassengerUpdateForm form) throws UserNotFoundException {
        Passenger passenger = findPassengerById(id);
        if(form.firstName() != null){
            passenger.setFirstName(form.firstName());
        }
        if(form.lastName() != null){
            passenger.setLastName(form.lastName());
        }
        if(form.phoneNumber() != null){
            passenger.setPhoneNumber(form.phoneNumber());
        }
        if(form.email() != null){
            passenger.setEmailAddress(form.email());
        }
        passengerDb.save(passenger);

    }

    @Override
    public void deletePassenger(Passenger passenger) {
        if(passengerDb.contains(passenger)){
            passengerDb.delete(passenger);
        }

    }
    public long count(){
        return passengerDb.count();
    }

}
