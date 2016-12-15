package travelAgencyModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Customer class containing basic information about a customer such as name and
 * phone number. The Customer class implements {@link PassengerList} to keep
 * track of any {@link Passenger}s that this {@link Customer} might have paid
 * for. Implements {@link Serializable}.
 * 
 * @author DVN
 *
 */
public class Customer implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String name;
	private int phoneNumber;
	private PassengerList associatedPassengers;
	private int reservationCount;
	private Address address;
	private LocalDateTime birthday;

	/**
	 * Two argument constructor accepting a customer name and customer phone
	 * number.
	 * 
	 * @param name
	 *            The name of the customer.
	 * @param phoneNumber
	 *            The customers phone number.
	 */
	public Customer(String name, int phoneNumber)
	{
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.reservationCount = 0;
		this.associatedPassengers = new PassengerList();
	}

	/**
	 * Set the birthday of the {@link Customer} as a {@link LocalDateTime}
	 * 
	 * @param birthday
	 *            The birthday of the customer.
	 */
	public void setBirthday(LocalDateTime birthday)
	{
		this.birthday = birthday;
	}

	/**
	 * Gets the count of {@link Passenger}s that this {@link Customer} has
	 * associated with it (paid for).
	 * 
	 * @return An integer specifying the count of {@link Passenger}s paid for.
	 */
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

	/**
	 * Add a passenger to the underlying collection of {@link Passenger}s.
	 * 
	 * @param passenger
	 *            The {@link Passenger} to add.
	 * @param price
	 *            The amount that this {@link Customer} has to pay for the
	 *            {@link Passenger}s {@link Ticket}.
	 */
	public void addPassenger(Passenger passenger, double price)
	{
		associatedPassengers.addPassenger(passenger, price);
	}

	/**
	 * Remove the {@link Passenger} from the underlying collection.
	 * 
	 * @param passenger
	 */
	public void removePassenger(Passenger passenger)
	{
		associatedPassengers.removePassenger(passenger);
	}

	public String getName()
	{
		return name;
	}

	public int getPhoneNumber()
	{
		return phoneNumber;
	}

	public ArrayList<Passenger> getAllPassengers()
	{
		return associatedPassengers.getAllPassengers();
	}

	public int getReservationCount()
	{
		return reservationCount;
	}

	/**
	 * Increment the reservations count seen for this {@link Customer}. Used in
	 * relation to automatic discounts.
	 * 
	 */
	public void incrementReservations()
	{
		reservationCount++;
	}

	/**
	 * Determines if this instances of {@link Customer} is equal to the provided instance.
	 * Tests for equality by comparing name and phone number.
	 * @param other The instance of {@link Customer} to compare for equality against.
	 * @return Returns true if the two instances of {@link Customer} share the same name and phone number.
	 */
	public boolean equals(Customer other)
	{
		return name.equals(other.getName()) && phoneNumber == other.getPhoneNumber();
	}

	/**
	 * Determines if this {@link Customer} has the provided {@link Passenger} associated with it.
	 * @param passenger The {@link Passenger} to test for.
	 * @return Returns true if this {@link Customer} has the provided {@link Passenger} in its collection.
	 */
	public boolean hasAssociatedPassenger(Passenger passenger)
	{
		return associatedPassengers.hasPassenger(passenger);
	}

	/**
	 * Returns the aggregate sum of {@link Ticket} prices paid by this {@link Customer} for all of its {@link Passengers}.
	 * @return Returns the aggregate sum.
	 */
	public double getTicketTotalAmount()
	{
		return associatedPassengers.getPassengerTicketPriceSum();
	}

	/**
	 * Returns the {@link Ticket} price for the specified {@link Passenger}.
	 * @param passenger The {@link Passenger} for which to find the {@link Ticket} price.
	 * @return The {@link Ticket} price of the given {@link Passenger}.
	 */
	public double getPassengerTicketPrice(Passenger passenger)
	{
		return associatedPassengers.getPassengerTicketPrice(passenger);
	}

	/**
	 * Returns a {@link String} representation of this {@link Customer}.
	 */
	@Override
	public String toString()
	{
		return name + " - " + associatedPassengers.getSize() + " Passengers. Amount: " + associatedPassengers.getPassengerTicketPriceSum();
	}
}
