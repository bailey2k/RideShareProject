/*
* @author Bailey Jones
* @date 11/29/2021
* The LyftRide class is essentially the same class as the UberRide class, but Lyft has a mandatory 
* service fee, which my class takes into consideration.
*/

//class begins with no import statement(s)

public class LyftRide extends Ride
{
   // the class' private constants
   private final double baseFare = 1.56;
   private final double perMile = 0.83;
   private final double minFare = 5.30;
   private final double servFee = 3.25;
   
   // def constructor that uses the superclass def constructor
   public LyftRide()
   {
      super();
   }
   
   /*
   * overload constructor that uses the superclass overloaded constructor to operate
   * @param location is given string for location
   * @param distance is given double for distance 
   */
   public LyftRide(String location, double distance)
   {
      super(location, distance);
   }
   
   /*
   * simple copy constructor that uses superclass methods to copy from given param object
   * @param copy is the copied object
   */
   public LyftRide(LyftRide copy)
   {
      this.setLocation(copy.getLocation());
      this.setDistance(copy.getDistance());
   }
   
   /*
   * equals method compares the contents of two objects
   * @param test is the test object
   * @return tester is the boolean if the objects are the same or not (t/f)
   */
   public boolean equals(LyftRide test)
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
      
      fare = baseFare + (perMile * super.getDistance());
      
      if (fare < 5.30)
      {
         fare = minFare + servFee;
      }
      else
      {
         fare += servFee;
      }
      
      return fare;
   }
   
   
}