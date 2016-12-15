package travelAgencyModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * {@link ArrayList} backed list containing instances of {@link Passenger} and
 * {@link Ticket}s. Implements {@link Serializable}
 * 
 * @author DVN
 *
 */
public class PassengerList implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Passenger> passengers;
	private ArrayList<Ticket> tickets;

	/**
	 * Default constructor initializes the underlying collections.
	 */
	public PassengerList()
	{
		passengers = new ArrayList<Passenger>();
		tickets = new ArrayList<Ticket>();
	}

	/**
	 * Add a {@link Passenger} to the underlying collection, with the associated
	 * {@link Ticket} price.
	 * 
	 * @param passenger
	 *            The passenger to add to the collection.
	 * @param price
	 *            The price of the passengers {@link Ticket}.
	 */
	public void addPassenger(Passenger passenger, double price)
	{
		passengers.add(passenger);
		tickets.add(new Ticket(passenger, price));
	}

	/**
	 * Gets the {@link Ticket} price paid for the passed {@link Passenger}.
	 * 
	 * @param passenger
	 *            The {@link Passenger} for which to find the {@link Ticket}
	 *            price.
	 * @return Returns the price of the passengers associated {@link Ticket}.
	 */
	public double getPassengerTicketPrice(Passenger passenger)
	{
		for (Ticket collectionTicket : tickets)
			if (collectionTicket.getOwner().equals(passenger))
				return collectionTicket.getPrice();

		return 0;
	}

	/**
	 * Gets the count of {@link Passenger}s in the underlying collection.
	 * 
	 * @return
	 */
	public int getSize()
	{
		return passengers.size();
	}

	/**
	 * Gets the aggregate sum of all {@link Ticket} prices associated with the
	 * {@link Passenger}s in this list.
	 * 
	 * @return Returns the aggregate sum of {@link Ticket} prices.
	 */
	public double getPassengerTicketPriceSum()
	{
		double acc = 0;
		for (Ticket collectionTicket : tickets)
			acc += collectionTicket.getPrice();

		return acc;
	}

	public void removePassenger(Passenger passenger)
	{
		passengers.remove(passenger);
	}

	public ArrayList<Passenger> getAllPassengers()
	{
		return passengers;
	}

	/**
	 * Determines if the underlying collection has a {@link Passenger} which is
	 * considered equal to the passed {@link Passenger}.
	 * Utilizes the Passenger.equals() method to test for equality.
	 * @param passenger The {@link Passenger} to look for.
	 * @return Returns true if the underlying collection has a {@link Passenger} equal to the argument.
	 */
	public boolean hasPassenger(Passenger passenger)
	{
		for (Passenger collectionPassenger : passengers)
			if (collectionPassenger.equals(passenger))
				return true;

		return false;
	}
}
