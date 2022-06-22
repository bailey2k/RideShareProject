/*
* @author Bailey Jones
* @date 11/27/2021
* This is an abstract superclass, entitled Ride. This works with several subclasses to process information
* about data from a csv file.
*/

// begin class
public abstract class Ride implements Comparable<Ride>
{
   // private attributes
   private String location;
   private double distance;
   
   
   // def constructor
   public Ride()
   {
      location = " ";
      distance = 0.0;
   }
   
   /*
   * overloaded constructor
   * @param location is a given variable for location
   * @param distance is a given var for distance traveled
   */
   public Ride(String location, double distance)
   {
      this.location = location;
      this.distance = distance;
   }
   
   /*
   * copy constructor
   * @param copy is an object to have its data copied from
   *
   */
   public Ride(Ride copy)
   {
      this.location = copy.getLocation();
      this.distance = copy.getDistance();
   }
   
   /*
   * setter for location
   * @param location is the variable given to store in the object's attribute
   */
   public void setLocation(String location)
   {
      this.location = location;
   }
   
   /*
   * setter for distance
   * @param distance is the given value to set distance as in the object
   */
   public void setDistance(double distance)
   {
      this.distance = distance;
   }
   
   /*
   * getter for location
   * @return location returns the stored string val for location
   */
   public String getLocation()
   {
      return location;
   }
   
   /*
   * getter for distance
   * @return distance returns the double val for distance
   */
   public double getDistance()
   {
      return distance;
   }
   
   /*
   * equals method compares the contents of two objects
   * @param test is the test object
   * @return tester is the boolean if the objects are the same or not (t/f)
   */
   
   public boolean equals(Ride test)
   {
      boolean tester = false;
      
      if(this.getLocation().equalsIgnoreCase(test.getLocation()) && this.getDistance() == test.getDistance())
      {
         tester = true;
      }
      
      return tester;
    }
    
   // allows us to use calculateFare in each of the subclasses
   public abstract double calculateFare();
   
   public int compareTo(Ride test)
   {
      if (this.calculateFare() > test.calculateFare())
      {
         return 1;
      }
      else if (this.calculateFare() < test.calculateFare())
      {
         return -1;
      }
      else
      {
         return 0;
      }
   }
   
   
   
   
} // end class