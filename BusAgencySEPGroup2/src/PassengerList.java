import java.util.ArrayList;


public class PassengerList
{
	private ArrayList<Passenger> passengers;
	private ArrayList<Ticket> tickets;
	
	public PassengerList()
	{
		passengers = new ArrayList<Passenger>();
		tickets = new ArrayList<Ticket>();
	}
	
	public void addPassenger(Passenger passenger, double price)
	{
		passengers.add(passenger);
		tickets.add(new Ticket(passenger, price));
	}


	public void deletePassenger(Passenger passenger) {
	   passengers.remove(passenger);
	}
	
	public Passenger getPassenger(Passenger passenger) {
	   for (int i = 0; i < passengers.size(); i++)
      {
         if(passengers.get(i).equals(passenger)) {
            return passengers.get(i);
         }
      }
	   return null;
	}
	
	public Passenger getPassengerByName(String name) {
      for (int i = 0; i < passengers.size(); i++)
      {
         if(passengers.get(i).getName().equals(name)) {
            return passengers.get(i);
         }
      }
      return null;
   }
	
	public ArrayList<Passenger> getAllPassengers() {
	   return passengers;
	}

}
