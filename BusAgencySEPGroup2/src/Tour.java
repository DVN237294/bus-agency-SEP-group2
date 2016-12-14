import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tour extends Travel
{
	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;
	private LocalDateTime returnDate;
	private CustomerList customers;

	public Tour(Bus bus, Chauffeur chauffeur, LocalDateTime reservationStartDate, LocalDateTime reservationEndDate)
	{
		super(bus, chauffeur, reservationStartDate, reservationEndDate);
		departureDate = null;
		arrivalDate = null;
		returnDate = null;
		customers = new CustomerList();
	}

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

	@Override
	public String getText()
	{
		String text = "";
		text += "Customers:\n";
		for (Customer customer : customers.getAllCustomers())
		{
			text += "\t" + customer.getName() + " - Phone: " + customer.getPhoneNumber() + " - Passengers: " + customer.getPassengerCount() + "\n\tTotal amount: "
					+ customer.getTicketTotalAmount() + "\n\tAll-time reservations by customer: " + customer.getReservationCount() + "\n";
			for (Passenger passenger : customer.getAllPassengers())
			{
				text += "\t\t" + passenger.getName() + " - Phone: " + passenger.getPhoneNumber() + " - Email: " + passenger.getEmail() + "\n"; 
			}
			text += "\n\n";
		}

		return super.getText() + text;
	}

	@Override
	public String toString()
	{
		return "\t\tTour: " + super.toString() + " - " + "Customers: " + customers.getTotalNumberOfCustomers() + ", Passengers: "
				+ customers.getPassengerCount();
	}

	@Override
	public int getCustomerFrequency(Customer customer)
	{
		return customers.getCustomerFrequency(customer);

	}

	@Override
	protected String getReservationType()
	{
		return "Tour Reservation";
	}
}
