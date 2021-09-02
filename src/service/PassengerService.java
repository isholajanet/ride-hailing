package service;

import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import user.Passenger;

import java.util.List;

public interface PassengerService {
    //register a passenger
    void createPassenger(Passenger passenger) throws UserAlreadyExistsException;
    Passenger findPassenger(Passenger passenger) throws UserNotFoundException;
    Passenger findPassengerById(String passengerId) throws UserNotFoundException;
    List<Passenger> findPassengerByName(String firstName) throws UserNotFoundException;
    void updatePassenger(Passenger passenger);
    void deletePassenger(Passenger passenger);




}
