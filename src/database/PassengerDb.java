package database;

import exceptions.UserNotFoundException;
import user.Passenger;

import java.util.*;

public class PassengerDb {
    private final Map<String, Passenger> passengerMap;

    public PassengerDb(){
        passengerMap = new HashMap<>();
    }
    public long count(){
        return passengerMap.size();
    }

    public boolean contains(Passenger passenger) {
        return passengerMap.containsValue(passenger);
    }

    public void save(Passenger passenger) {
        passengerMap.put(passenger.getPassengerId(), passenger);
    }


    public Passenger find(Passenger passenger) throws UserNotFoundException {
        Passenger foundPassenger = null;
        for(Passenger p : passengerMap.values()){
            if(p.equals(passenger)){
                foundPassenger = p;
            }
        }
        if(foundPassenger == null){
            throw new UserNotFoundException("Passenger not found");
        }
        return foundPassenger;
    }

    public Passenger findById(String passengerId) throws UserNotFoundException {
       String errorMessage = String.format("Passenger with id %s does not exist", passengerId);
       if(!passengerMap.containsKey(passengerId)){
           throw new UserNotFoundException(errorMessage);
       }
       return passengerMap.get(passengerId);
    }

    public List<Passenger> findAll() {
        List<Passenger> passengers = new ArrayList<>(passengerMap.values());
        return passengers;
    }
}
