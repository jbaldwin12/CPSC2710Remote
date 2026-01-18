/*
 *Project: Module 2
 * Author: Jordan Baldwin
 * Email: jtb0185@auburn.edu
 * Date: 1/17/2026
 * Description: Flight reservation app
 */


package edu.au.cpsc.module2;

public class SeatReservation {

   private String flightDesignator;
   private java.time.LocalDate flightDate;
   private String firstName;
   private String lastName;
   private int numberOfBags;
   private boolean flyingWithInfant;
   private boolean flyingWithTravelInsurance;
   
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
      final int minLength = 2;
      final int maxLength = 15;
      this.firstName = fn;
      if (fn == null || fn.length() < minLength || fn.length() > maxLength)
         throw new IllegalArgumentException(
                 "First name should be 2 to 15 digits long");
   }

   public  String getLastName() {
      if (lastName == null)
         return "null";
      return lastName;
   }

   public  void setLastName(String ln) {
      final int minLength = 2;
      final int maxLength = 15;
      this.lastName = ln;
      if (ln == null || ln.length() < minLength || ln.length() > maxLength)
         throw new IllegalArgumentException(
                 "Last name should be 2 to 15 digits long");
   }

   public int getNumberOfBags() {
      return numberOfBags;
   }

   public void setNumberOfBags(int nob) {
      this.numberOfBags = nob;
   }

   public boolean isFlyingWithInfant() {
      return flyingWithInfant;
   }

   public void makeFlyingWithInfant() {
      this.flyingWithInfant = true;
   }

   public void makeNotFlyingWithInfant() {
      this.flyingWithInfant = false;
   }

   public boolean hasTravelInsurance() {
      return flyingWithTravelInsurance;
   }

   public void makeFlyingWithTravelInsurance() {
      this.flyingWithTravelInsurance = true;
   }

   public void makeNotFlyingWithTravelInsurance() {
      this.flyingWithTravelInsurance = false;
   }

   public String toString() {
      String output = "SeatReservation{flightDesignator=" + flightDesignator
         + ",flightDate=" + flightDate + ",firstName=" + firstName
         + ",lastName=" + lastName + ", numberOfBags=" + numberOfBags
         + ", flyingWithInfant=" + flyingWithInfant
         + ", flyingWithTravelInsurance=" + flyingWithTravelInsurance + "}";
      return output;
   }
}
