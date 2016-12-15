package travelAgencyModel;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Extension of {@link Travel} that adds departure, arrival and return dates as
 * well as a {@link CustomerList} to keep track of the {@link Customer}s
 * associated with this {@link Tour}. Implements {@link Serialization} by
 * extension.
 * 
 * @author DVN
 *
 */
public class Tour extends Travel
{
	private static final long serialVersionUID = 1L;
	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;
	private LocalDateTime returnDate;
	private CustomerList customers;

	/**
	 * Four parameter constructor that accepts a {@link Bus}, a
	 * {@link Chauffeur} and a reservation interval.
	 * 
	 * @param bus
	 *            The {@link Bus} to be associated with this {@link Tour}.
	 * @param chauffeur
	 *            The {@link Chauffeur} to be associated with this {@link Tour}.
	 * @param reservationStartDate
	 *            The start of the reservation interval.
	 * @param reservationEndDate
	 *            The end of the reservation interval.
	 */
	public Tour(Bus bus, Chauffeur chauffeur, LocalDateTime reservationStartDate, LocalDateTime reservationEndDate)
	{
		super(bus, chauffeur, reservationStartDate, reservationEndDate);
		departureDate = null;
		arrivalDate = null;
		returnDate = null;
		customers = new CustomerList();
	}

	/**
	 * Add a {@link Customer} to this {@link Tour} as well as incrementing the
	 * total amount of reservations seen by that {@link Customer}.
	 * 
	 * @param customer The {@link Customer} to add.
	 */
	public void addCustomer(Customer customer)
	{
		customers.addCustomer(customer);
		customer.incrementReservations();
	}

	public LocalDateTime getDepartureDate()
	{
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate)
	{
		this.departureDate = departureDate;
	}

	public LocalDateTime getArrivalDate()
	{
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate)
	{
		this.arrivalDate = arrivalDate;
	}

	public LocalDateTime getReturnDate()
	{
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate)
	{
		this.returnDate = returnDate;
	}

	public boolean hasCustomer(Customer customer)
	{
		return customers.getCustomer(customer) != null;
	}

	/**
	 * Returns a human readable {@link String} representation of this {@link Tour}. Suitable for a TextArea.
	 * @return Returns the readable {@link String}.
	 */
	@Override
	public String getText()
	{
		String text = "";
		text += "Customers:\n";
		for (Customer customer : customers.getAllCustomers())
		{
			text += "\t" + customer.getName() + " - Phone: " + customer.getPhoneNumber() + " - Passengers: " + customer.getPassengerCount()
					+ "\n\tTotal amount: " + customer.getTicketTotalAmount() + "\n\tAll-time reservations by customer: " + customer.getReservationCount()
					+ "\n";
			for (Passenger passenger : customer.getAllPassengers())
			{
				text += "\t\t" + passenger.getName();
				if(passenger.getPhoneNumber() != 0)
					text += " - Phone: " + passenger.getPhoneNumber();
				if(passenger.getEmail() != null && !passenger.getEmail().equals(""))
					text += " - Email: " + passenger.getEmail() + "\n";
				else 
					text += "\n";
			}
			text += "\n\n";
		}

		return super.getText() + text;
	}

	/**
	 * Returns a {@link String} representation of this {@link Tour}.
	 * @return Returns the {@link String} representation.
	 */
	@Override
	public String toString()
	{
		return "\t\tTour: " + super.toString() + " - " + "Customers: " + customers.getNumberOfCustomers() + ", Passengers: " + customers.getPassengerCount();
	}

	/**
	 * Gets the all-time aggregate sum of reservations seen for this {@link Customer}.
	 * @return Returns the aggregate sum.
	 */
	@Override
	public int getCustomerFrequency(Customer customer)
	{
		return customers.getCustomerFrequency(customer);

	}

	/**
	 * Gets the type of reservation as a {@link String} representation. 
	 * @return Returns "Tour Reservation".
	 */
	@Override
	protected String getReservationType()
	{
		return "Tour Reservation";
	}
}
