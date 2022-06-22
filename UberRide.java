/*
* @author Bailey Jones
* @date 11/29/2021
* The UberRide class is basically the same as the LyftRide class aside from the fact that
* ride fare is calculated differently.
*/

//class begins with no import statement(s)

public class UberRide extends Ride
{
   // the class' private constants
   private final double baseFare = 2.20;
   private final double perMile = 1.29;
   private final double minFare = 7.65;
   
   // def constructor that uses the superclass def constructor
   public UberRide()
   {
      super();
   }
   
   /*
   * overload constructor that uses the superclass overloaded constructor to operate
   * @param location is given string for location
   * @param distance is given double for distance 
   */
   public UberRide(String location, double distance)
   {
      super(location, distance);
   }
   
   /*
   * simple copy constructor that uses superclass methods to copy from given param object
   * @param copy is the copied object
   */
   public UberRide(UberRide copy)
   {
      this.setLocation(copy.getLocation());
      this.setDistance(copy.getDistance());
   }
   
   /*
   * equals method compares the contents of two objects
   * @param test is the test object
   * @return tester is the boolean if the objects are the same or not (t/f)
   */
   public boolean equals(UberRide test)
   {
      boolean tester = false;
      
      if (this.getLocation().equalsIgnoreCase(test.getLocation()) && this.getDistance() == test.getDistance())
      {
         tester = true;
      }
      
      return tester;
   }
   
   /*
   * calculateFare calculates the fare of an uber ride based on the class' constants
   * @return fare returns the final fare calculation based on distance
   *
   */
   public double calculateFare()
   {
      double fare = 0.0;
      
      fare = 2.20 + (1.29 * super.getDistance());
      
      if (fare < 7.65)
      {
         fare = 7.65;
      }
      
      return fare;
   }
   
   
}