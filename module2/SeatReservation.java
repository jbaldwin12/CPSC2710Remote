public class SeatReservation{

   private String flightDesignator;
   private java.time.LocalDate flightDate;
   private String firstName;
   private String lastName;
   
   public String getFlightDesignator() {
      if (flightDesignator == null)
         return "null";
      return flightDesignator;
   }

   public void setFlightDesignator(String fd) {
      final int minLength = 4;
      final int maxLength = 6;
      this.flightDesignator = fd;
      if (fd == null || fd.length() < minLength || fd.length() > maxLength)
         throw new IllegalArgumentException(
            "Flight Designator should be 4 to 6 digits long");
   }

   public java.time.LocalDate getFlightDate() {
      return flightDate;
   }

   public void setFlightDate(java.time.LocalDate date) {
      this.flightDate = date;
   }

   public String getFirstName() {
      if (firstName == null)
         return "null";
      return firstName;
   }

   public void setFirstName(String fn) {
      this.firstName = fn;
   }

   public  String getLastName() {
      if (lastName == null)
         return "null";
      return lastName;
   }

   public  void setLastName(String ln) {
      this.lastName = ln;
   }

   public String toString() {
      String output = "SeatReservation{flightDesignator=" + flightDesignator
         + ",flightDate=" + flightDate + ",firstName=" + firstName
         + ",lastName=" + lastName + "}";
      return output;
   }

}
