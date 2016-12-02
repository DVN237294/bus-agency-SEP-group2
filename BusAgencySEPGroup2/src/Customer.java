import java.util.ArrayList;


public class Customer
{
   private String name;
   private int phoneNumber;
   private PassengerList associatedPassengers;
   private int reservationCount;

   public Customer(String name, int phoneNumber)
   {
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.reservationCount = 0;
   }
   
   public void addPassenger(Passenger passenger, double price) {
	   associatedPassengers.addPassenger(passenger, price);
   }
   
   public void deletePassenger(Passenger passenger) {
	   associatedPassengers.deletePassenger(passenger);
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
      return associatedPassengers.getAllPassengers();
   }
   
   public int getReservationCount()
   {
	   return reservationCount;
   }
   
   public void incrementReservations()
   {
	   reservationCount++;
   }

   public boolean equals(Customer other)
   {
	   return name.equals(other.getName()) && phoneNumber == other.getPhoneNumber();
   }
   
   public boolean hasAssociatedPassenger(Passenger passenger)
   {
	   return associatedPassengers.hasPassenger(passenger);
   }
}
