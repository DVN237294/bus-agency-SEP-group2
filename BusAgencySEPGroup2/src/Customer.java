import java.util.ArrayList;

public class Customer
{
   private String name;
   private int phoneNumber;
   private ArrayList<PassengerList> passengerLists;

   public Customer(String name, int phoneNumber)
   {
      this.name = name;
      this.phoneNumber = phoneNumber;
   }
   
   public void addPassengerList(PassengerList passengerList)
   {
      passengerLists.add(passengerList);
   }
   
   public void deletePassengerList(PassengerList passengerList)
   {
      passengerLists.remove(passengerList);
   }
   
   public String getName()
   {
      return name;
   }
   
   public int getPhoneNumber()
   {
      return phoneNumber;
   }

}
