Public class SeatReservation{

    String flightDesignator = "null"
    java.time.LocalDate flightDate = "null"
    String firstName = "null"
    String lastName = "null"
    
    Public Static String getFlightDesignator() {
        return flightDesignator;
    }

    Public Static void setFlightDesignator(String fd) {
        this.flightDesignator = flightDesignator;
    }

    Public Static java.time.LocalDate getFlightDate() {
        return flightDate;
    }

    Public Static void setFlightDate(java.time.LocalDate date) {
        this.flightDate = flightDate;
    }

    Public Static String getFirstName() {
        return firstName;
    }

    Public Static void setFirstName(String fn) {
        this.firstName = firstname;
    }

    Public Static String getLastName() {
        return lastName;
    }

    Public Static void setLastName(String ln) {
        this.lastName = lastName;
    }

    Public String toString() {
        String output = "SeatReservation{flightDesignator=" + flightDesignator
        + ",flightDate=" + flightDate + ",firstName=" + firstName
        + ",lastName=" + lastName + "}";
        return output;
    }

}
