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
      this.flightDesignator = fd;
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
