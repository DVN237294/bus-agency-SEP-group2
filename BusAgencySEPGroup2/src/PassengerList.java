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

	public double getPassengerTicketPrice(Passenger passenger)
	{
		for (Ticket collectionTicket : tickets)
			if (collectionTicket.getOwner().equals(passenger))
				return collectionTicket.getPrice();

		return 0;
	}

	public void deletePassenger(Passenger passenger)
	{
		passengers.remove(passenger);
	}

	public ArrayList<Passenger> getAllPassengers()
	{
		return passengers;
	}

	public boolean hasPassenger(Passenger passenger)
	{
		for (Passenger collectionPassenger : passengers)
			if (collectionPassenger.equals(passenger))
				return true;

		return false;
	}
}
