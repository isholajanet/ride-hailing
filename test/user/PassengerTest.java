package user;

import database.PassengerDb;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.passenger.PassengerServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {
    Passenger passenger1;
    Passenger passenger2;
    PassengerServiceImpl passengerServiceImpl;
    PassengerDb passengerDb;

    @BeforeEach
    void setUp() {
        passenger1 = new Passenger("1", "janet", "ishola", "jane@gmail.com","08128215330","1234");
        passenger2 = new Passenger("2", "timi", "osinuga", "timi@gmail.com","0815893030","0000");
        passengerServiceImpl = new PassengerServiceImpl();

    }

    @AfterEach
    void tearDown() {
    }
    @Test
    @DisplayName("Create Passenger")
    void testThatUserCanBeCreated(){
        //given
      Passenger passenger3 = new Passenger("2", "timi", "osinuga", "timi@gmail.com","0815893030","0000");

        try {
            passengerServiceImpl.createPassenger(passenger1);
            passengerServiceImpl.createPassenger(passenger2);
            passengerServiceImpl.createPassenger(passenger3);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }

        assertEquals(2, passengerServiceImpl.count());
    }
    @Test
    @DisplayName("Passenger already exist test")
    void testThatPassengerCannotBeCreatedIfItAlreadyExist(){
        Passenger passenger3 = new Passenger("2", "timi", "osinuga", "timi@gmail.com","0815893030","0000");

        try {
            passengerServiceImpl.createPassenger(passenger1);
            passengerServiceImpl.createPassenger(passenger2);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }

        assertEquals(2, passengerServiceImpl.count());

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                ()-> passengerServiceImpl.createPassenger(passenger3));

        assertEquals("Passenger already exist", exception.getLocalizedMessage());
    }
    @Test
    @DisplayName("Find passenger test")
    void testToFindPassenger(){
        try {
            passengerServiceImpl.createPassenger(passenger1);
            passengerServiceImpl.createPassenger(passenger2);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        Passenger foundPassenger = null;
        try {
            foundPassenger = passengerServiceImpl.findPassenger(passenger1);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull(foundPassenger);
        assertEquals(foundPassenger, passenger1);
    }
    @Test
    @DisplayName("Passenger not found test")
    void testToThrowAnExceptionIfTheUserIsNotFound(){
       UserNotFoundException exception = assertThrows(UserNotFoundException.class,
               ()-> passengerServiceImpl.findPassenger(passenger1));
       assertEquals("Passenger not found",exception.getLocalizedMessage());

    }

    @DisplayName("Find passenger By Id")
    @Test
    void testToFindPassengerByPassengerId(){
        try {
            passengerServiceImpl.createPassenger(passenger1);
            passengerServiceImpl.createPassenger(passenger2);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }

        Passenger foundPassenger = null;
        try {
            foundPassenger = passengerServiceImpl.findPassengerById("1");
        } catch (UserNotFoundException e) {
            System.err.printf("%s: ", e.getLocalizedMessage());
        }
        assertEquals("ishola", foundPassenger.getLastName());
    }

    @DisplayName("Find Passenger By Name")
    @Test
    void testToFindPassengerByFirstNameOrLastName() throws UserNotFoundException {
        //create users
        Passenger passenger3 = new Passenger("3", "timi", "olayemi", "timiolayemi@gmail.com","0815893031","9999");
        try {
            passengerServiceImpl.createPassenger(passenger1);
            passengerServiceImpl.createPassenger(passenger2);
            passengerServiceImpl.createPassenger(passenger3);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
       List<Passenger> foundPassengers =  passengerServiceImpl.findPassengerByName("timi");
        assertEquals(2, foundPassengers.size());
    }
    @DisplayName("Throw User Not Found Exception When User Does Not Exist")
    @Test
    void testToThrowExceptionWhenUserDoesNotExist() throws UserNotFoundException {
        Passenger passenger3 = new Passenger("3", "timi", "olayemi", "timiolayemi@gmail.com","0815893031","9999");
        try {
            passengerServiceImpl.createPassenger(passenger1);
            passengerServiceImpl.createPassenger(passenger2);
            passengerServiceImpl.createPassenger(passenger3);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        UserNotFoundException exception =  assertThrows(UserNotFoundException.class, ()-> passengerServiceImpl.findPassengerByName("tope"));
        assertEquals("User not found", exception.getLocalizedMessage());
    }
    @DisplayName("Update User")
    @Test
    void testToUpdateUsersInformation(){
        Passenger passenger3 = new Passenger("3", "timi", "olayemi", "timiolayemi@gmail.com","0815893031","9999");
        try {
            passengerServiceImpl.createPassenger(passenger1);
            passengerServiceImpl.createPassenger(passenger2);
            passengerServiceImpl.createPassenger(passenger3);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        //update user details

        PassengerUpdateForm form = new PassengerUpdateForm("yinka", null, null, null);
        try {
            passengerServiceImpl.updatePassenger("1", form);
        } catch (UserNotFoundException e) {
            e.getLocalizedMessage();
        }
        //Search for user with id
        Passenger foundPassenger = null;
        try {
            foundPassenger = passengerServiceImpl.findPassengerById("1");
        } catch (UserNotFoundException e) {
            System.err.printf("%s: ", e.getLocalizedMessage());
        }
        assertEquals("yinka", foundPassenger.getFirstName());
        assertEquals("ishola", foundPassenger.getLastName());

    }

    @DisplayName("Delete passenger")
    @Test
    void testThatSystemCanDeletePassenger(){
        //register passenger
        Passenger passenger3 = new Passenger("3", "timi", "olayemi", "timiolayemi@gmail.com","0815893031","9999");
        try {
            passengerServiceImpl.createPassenger(passenger1);
            passengerServiceImpl.createPassenger(passenger2);
            passengerServiceImpl.createPassenger(passenger3);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        //Delete passenger
            passengerServiceImpl.deletePassenger(passenger3);
            assertEquals(2, passengerServiceImpl.count());


    }


}