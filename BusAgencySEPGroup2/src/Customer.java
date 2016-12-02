
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
   
   public String getName()
   {
      return name;
   }
   
   public int getPhoneNumber()
   {
      return phoneNumber;
   }

}
