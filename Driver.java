/*
* @author Bailey Jones
* @date 11/30/2021
* This is a driver for Ride which takes data from a csv and stores it in an arraylist
* to perform calculations on it.
*/

// long list of import statements
import java.text.DecimalFormat;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;

// class begins 
public class Driver 
{
   // main method that throws IO exception
   public static void main(String[] args) throws IOException
   {
      Scanner scan = new Scanner(System.in);
      ArrayList<Ride> dataArray = new ArrayList<Ride>();
      
      readData(dataArray);
      mainController(scan, dataArray);
    }
    
    /*
    * readData is a method that takes all of the data from a csv file and puts it into objects within an arraylist
    * @param dataArray is the arraylist shared by every method in this driver
    * @return dataArray returns the filled dataArray
    */
    public static ArrayList<Ride> readData(ArrayList<Ride> dataArray) throws IOException
    {
        // create file
        File rideData = new File("rideInfo.csv");
        Scanner reader = new Scanner(rideData);
        
        // erase first line of csv
        reader.nextLine();
        
        // loop that runs until EOF
        while(reader.hasNext())
        {
            String line = reader.nextLine();
            StringTokenizer toker = new StringTokenizer(line, ",");
            
            String location = toker.nextToken();
            double distance = Double.parseDouble(toker.nextToken());
            
            // makes two objects per line, 2 per city
            dataArray.add(new UberRide(location, distance));
            dataArray.add(new LyftRide(location, distance));
        }
        
        return dataArray;
     }
     
     /*
     * mainController is an integer-driven menu that has several method calls
     * @param scan is the scanner for user input
     * @param dataArray same arraylist
     */
     public static void mainController(Scanner scan, ArrayList<Ride> dataArray)
     {
         // big text wall for user
         System.out.println("Welcome to the Lyft/Uber cost calculator.");
         System.out.println("Please enter an integer to traverse this menu.");
         System.out.println("0: Display all rides.");
         System.out.println("1: Search by city.");
         System.out.println("2: Search by minimum cost and chosen app.");
         
         // grabs integer from user
         int choice = scan.nextInt();
         
         switch(choice)
         {
            case 0: displayAll(dataArray, scan);
            break;
         
            case 1: searchByCity(dataArray, scan);
            break;
            
            case 2: searchByMin(dataArray, scan);
            break;
            
            // if the user enters something that is not valid, the program terminates
            default: System.out.println("Invalid integer. Exiting program......");
                  System.exit(0);
         }
                       
     }
     
     /*
     * displayAll just displays everything in the dataArray
     * @param dataArray is the same arraylist
     * @param scan is scanner for user input
     */
     public static void displayAll(ArrayList<Ride> dataArray, Scanner scan)
     {
         // header before loop to make it better
         System.out.println(String.format("      "+"%-10s%-14s%-12s%-12s","App Name","Location","Distance","Total Cost"));
         
         for (int index = 0; index < dataArray.size(); index++)
            {
               // get variables in memory to make code easier to read
               String location = dataArray.get(index).getLocation();
               double distance = dataArray.get(index).getDistance();
               String app = " ";
               
               // a simple way to tell if its uber or lyft without storing it in the object itself
               if (dataArray.get(index) instanceof UberRide)
               {  
                  app = "Uber";
               }
               else
               {
                  app = "Lyft";
               }
         
         // each row displays app name, city, distance traveled and total cost in USD
         System.out.println(String.format("Info: "+"%2s%14s%14s%13.2f", app, location, distance, dataArray.get(index).calculateFare()));
 
         }
           
          // exit prompt
          System.out.println("Return to main menu? No: 0, Yes: 1");
          
          int userch = scan.nextInt();
          
          if (userch == 1)
          {
            mainController(scan, dataArray);
          }
          else
          {
            System.exit(0);
          }
 
       }
       
       /*
       * searchByCity uses a chunk of the code from displayAll to display out, but
       * this searches by city and displays the trips on both apps
       * @param dataArray is same arraylist
       *
       */
       public static void searchByCity(ArrayList<Ride> dataArray, Scanner scan)
       {
         String city = " ";
         scan.nextLine();
         System.out.println("Enter a city, or part of it's name.");
         city = scan.nextLine();
         
         System.out.println(String.format("      "+"%-10s%-14s%-12s%-12s","App Name","Location","Distance","Total Cost"));
         
         // same structure from displayAll, but with the city search statement
         for (int index = 0; index < dataArray.size(); index++)
         {
            // looks for objects with the city name portion that user input in
            if (dataArray.get(index).getLocation().toLowerCase().contains(city.toLowerCase()))
            {
               String location = dataArray.get(index).getLocation();
               double distance = dataArray.get(index).getDistance();
               double cost = dataArray.get(index).calculateFare();
               String app = " ";
               
               // same app checker from before
               if (dataArray.get(index) instanceof UberRide)
               {  
                  app = "Uber";
               }
               else
               {
                  app = "Lyft";
               }
               
               // row display
               System.out.println(String.format("Info: "+"%2s%14s%14s%13.2f", app, location, distance, cost));
             }
          }
          
          // exit prompt
          System.out.println("Return to main menu? No: 0, Yes: 1");
          int userch = scan.nextInt();
          
          if (userch == 1)
          {
            mainController(scan, dataArray);
          }
          else
          {
            System.exit(0);
          }
        }
       
       /*
       * searchByMin shows the lowest priced trip based on the city the user puts in,
       * this method also uses a Comparable interface method
       * @param dataArray the same arraylist 
       * @param scan is the scanner for user input
       */
       public static void searchByMin(ArrayList<Ride> dataArray, Scanner scan)
       {
         // city prompt used before 
         String city = " ";
         scan.nextLine();
         System.out.println("Enter a city, or part of it's name.");
         city = scan.nextLine();  
         
         // header
         System.out.println(String.format("      "+"%-10s%-14s%-12s%-12s","App Name","Location","Distance","Total Cost"));  
         
         // this for statement goes with how I organized the arraylist, in uber lyft uber lyft order
         for (int index = 0; index < dataArray.size(); index +=2)
         {
            // city search if statement
            if (dataArray.get(index).getLocation().toLowerCase().contains(city.toLowerCase()))
               {
               // if the uber ride is cheaper, this block runs
                if (dataArray.get(index).compareTo(dataArray.get(index + 1)) == -1)
                {   
                    // same displayAll type code used before
                    String location = dataArray.get(index).getLocation();
                    double distance = dataArray.get(index).getDistance();
                    double cost = dataArray.get(index).calculateFare();
                    String app = " ";
               
                    if (dataArray.get(index) instanceof UberRide)
                    {  
                         app = "Uber";
                    }
                     else
                     {
                         app = "Lyft";
                     }
                     
                     System.out.println(String.format("Info: "+"%2s%14s%14s%13.2f", app, location, distance, cost));
                  }
                  else 
                  {
                    // i make an integer that is one above the current index to view the lyft objects
                    int lyftCode = index++;
                    String location = dataArray.get(lyftCode).getLocation();
                    double distance = dataArray.get(lyftCode).getDistance();
                    double cost = dataArray.get(lyftCode).calculateFare();
                    String app = " ";
               
                    if (dataArray.get(lyftCode) instanceof UberRide)
                    {  
                         app = "Uber";
                    }
                     else
                     {
                         app = "Lyft";
                     }
                     
                     System.out.println(String.format("Info: "+"%2s%14s%14s%13.2f", app, location, distance, cost));
                   }
                }
           
           
          }   
          // exit prompt
          System.out.println("Return to main menu? No: 0, Yes: 1");
          int userch = scan.nextInt();
          
          if (userch == 1)
          {
            mainController(scan, dataArray);
          }
          else
          {
            System.exit(0);
          }

        
     }
       
       
       
       
       
       
       
       
}     