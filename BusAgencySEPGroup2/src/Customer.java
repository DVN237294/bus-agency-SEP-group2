import java.util.ArrayList;


public class Customer
{
   private String name;
   private int phoneNumber;
   private PassengerList passengerList;

   public Customer(String name, int phoneNumber)
   {
      this.name = name;
      this.phoneNumber = phoneNumber;
   }
   
   public void addPassenger(Passenger passenger, double price) {
      passengerList.addPassenger(passenger, price);
   }
   
   public void deletePassenger(Passenger passenger) {
      passengerList.deletePassenger(passenger);
   }
   
   public String getName()
   {
      return name;
   }
   
   public int getPhoneNumber()
   {
      return phoneNumber;
   }
   
   public ArrayList<Passenger> getAllPassengers() {
      return passengerList.getAllPassengers();
   }

}
