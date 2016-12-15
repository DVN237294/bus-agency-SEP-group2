package travelAgencyModel;

import java.time.LocalDateTime;

/**
 * Extension of {@link Travel} that forces a single {@link Chauffeur} and a
 * personCount to be included.
 * Implements {@link Serialization} by extension.
 * @author DVN
 *
 */
public class BusAndChaffeurTravel extends Travel
{
	private int personCount;
	private Customer customer;

	/**
	 * Six parameter constructor. 
	 * @param customer The {@link Customer} making the reservation.
	 * @param bus The {@link Bus} assigned the reservation.
	 * @param chauffeur The {@link Chauffeur} assigned the reservation.
	 * @param personCount The expected or maximum amount of passengers this reservation covers.
	 * @param reservationStartDate The start date of the reservation.
	 * @param reservationEndDate The end date of the reservation.
	 */
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
