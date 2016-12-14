import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Customer implements Serializable
{
   private String name;
   private int phoneNumber;
   private PassengerList associatedPassengers;
   private int reservationCount;
   private Address address;
   private LocalDateTime birthday;

   public Customer(String name, int phoneNumber)
   {
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.reservationCount = 0;
      this.associatedPassengers = new PassengerList();
   }
   
   public void setBirthday(LocalDateTime birthday)
   {
	   this.birthday = birthday;
   }
   
   public int getPassengerCount()
   {
	   return associatedPassengers.getSize();
   }
   
   public LocalDateTime getBirthday()
   {
	   return this.birthday;
   }
   
   public void setAddress(Address address)
   {
	   this.address = address;
   }
   
   public Address getAddress()
   {
	   return this.address;
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
   
   public double getPassengerTicketPrice(Passenger passenger)
   {
	   return associatedPassengers.getPassengerTicketPrice(passenger);
   }
   
   
   
   @Override
   public String toString()
   {
	   return name + " - " + associatedPassengers.getSize() + " Passengers. Amount: " + associatedPassengers.getPassengerTicketPriceSum();
   }
}
