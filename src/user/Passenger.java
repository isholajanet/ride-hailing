package user;

import java.util.Objects;

public class Passenger {
    private String passengerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String password;

    public Passenger(String userId, String firstName, String lastName, String emailAddress, String phoneNumber, String password) {
        this.passengerId = userId;
        this.firstName = firstName;

        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getUserId() {
        return passengerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(getUserId(), passenger.getUserId()) || Objects.equals(getEmailAddress(), passenger.getEmailAddress()) || Objects.equals(getPhoneNumber(), passenger.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getEmailAddress(), getPhoneNumber());
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
