package travelAgencyModel;
import java.time.LocalDateTime;

public class BusAndChaffeurTravel extends Travel
{
	private int personCount;
	private Customer customer;

	public BusAndChaffeurTravel(Customer customer, Bus bus, Chauffeur chauffeur, int personCount, LocalDateTime reservationStartDate,
			LocalDateTime reservationEndDate)
	{
		super(bus, chauffeur, reservationStartDate, reservationEndDate);
		this.personCount = personCount;
		this.customer = customer;
		customer.incrementReservations();
	}

	public int getPersonCount()
	{
		return personCount;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	@Override
	public String toString()
	{
		return "Bus & Chauffeur: " + super.toString() + " - Customer: " + customer.getName();
	}

	@Override
	public int getCustomerFrequency(Customer customer)
	{
		if (customer.equals(customer))
			return 1;

		return 0;
	}

	@Override
	protected String getReservationType()
	{
		return "Bus and Chauffeur Reservation";
	}
}
