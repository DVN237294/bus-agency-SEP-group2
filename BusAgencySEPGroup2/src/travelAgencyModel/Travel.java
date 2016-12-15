package travelAgencyModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Abstract class Travel generalizes a {@link Tour} and
 * {@link BusAndChauffeurTravel}. Implements {@link Serializable}.
 * 
 * @author DVN
 *
 */
public abstract class Travel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Bus bus;
	private Chauffeur chauffeur;
	private LocalDateTime reservationStartDate;
	private LocalDateTime reservationEndDate;

	private double basePrice;
	private String[] destinations;

	/**
	 * Four parameter constructor accepting a {@link Bus} and a
	 * {@link Chauffeur} to be associated with this {@link Travel} as well as a
	 * time interval for the reservation.
	 * 
	 * @param bus The {@link Bus} to be associated with this {@link Travel}.
	 * @param chauffeur The {@link Chauffeur} to be associated with this {@link Travel}.
	 * @param reservationStartDate The start date of the reservation time interval.
	 * @param reservationEndDate The end date of the reservation time interval.
	 */
	public Travel(Bus bus, Chauffeur chauffeur, LocalDateTime reservationStartDate, LocalDateTime reservationEndDate)
	{
		this.bus = bus;
		this.chauffeur = chauffeur;
		this.reservationStartDate = reservationStartDate;
		this.reservationEndDate = reservationEndDate;

		this.basePrice = 0;
		this.destinations = new String[0];
	}

	public abstract int getCustomerFrequency(Customer customer);

	public void setBus(Bus bus)
	{
		this.bus = bus;
	}

	public void setChauffeur(Chauffeur chauffeur)
	{
		this.chauffeur = chauffeur;
	}

	public double getBasePrice()
	{
		return basePrice;
	}

	public void setBasePrice(double basePrice)
	{
		this.basePrice = basePrice;
	}

	public String[] getDestinations()
	{
		return destinations;
	}

	public void setDestinations(String[] destinations)
	{
		this.destinations = destinations;
	}

	public Bus getBus()
	{
		return bus;
	}

	public Chauffeur getChauffeur()
	{
		return chauffeur;
	}

	public LocalDateTime getReservationStartDate()
	{
		return reservationStartDate;
	}

	public LocalDateTime getReservationEndDate()
	{
		return reservationEndDate;
	}

	/**
	 * Determines if this {@link Travel}s reservation interval overlaps with the specified time interval.
	 * @param startDate The start of the specified time interval.
	 * @param endDate The end of the specified time interval.
	 * @return Returns true if the two time intervals overlap.
	 */
	public boolean reservationOverlaps(LocalDateTime startDate, LocalDateTime endDate)
	{
		return ((Time.unixTime(startDate) < Time.unixTime(reservationEndDate) && Time.unixTime(startDate) > Time.unixTime(reservationStartDate)) || (Time
				.unixTime(endDate) > Time.unixTime(reservationStartDate) && Time.unixTime(endDate) < Time.unixTime(reservationEndDate))) ||
		// or the other way around:
				((Time.unixTime(reservationStartDate) < Time.unixTime(endDate) && Time.unixTime(reservationStartDate) > Time.unixTime(startDate)) || (Time
						.unixTime(reservationEndDate) > Time.unixTime(startDate) && Time.unixTime(reservationEndDate) < Time.unixTime(endDate)));
	}

	/**
	 * Determines if this {@link Travel}s reservation interval overlaps with the specified {@link Travel}s time interval.
	 * @param other The other {@link Travel} to compare against.
	 * @return Returns true if the time intervals overlap.
	 */
	public boolean reservationOverlaps(Travel other)
	{
		return reservationOverlaps(other.getReservationStartDate(), other.getReservationEndDate());
	}

	protected abstract String getReservationType();

	/**
	 * Returns a human readable {@link String} representation of this {@link Travel}. Suitable for a TextArea.
	 * @return Returns the readable {@link String}.
	 */
	public String getText()
	{
		String text = getReservationType() + "\n";
		text += "Reservation from:\t" + Time.readableDate(reservationStartDate);
		text += "\nReservation to:\t\t" + Time.readableDate(reservationEndDate);
		text += "\n\nChauffeur: " + chauffeur.getFirstName() + " " + chauffeur.getLastName() + " (" + chauffeur.getChauffeurID() + ")\n";
		text += "\tContact:\n";
		if (chauffeur.getEmail() != null && !chauffeur.getEmail().equals(""))
			text += "\tMail: " + chauffeur.getEmail() + "\n";
		if (chauffeur.getPhoneNumber() != null && !chauffeur.getPhoneNumber().equals(""))
			text += "\tPhone: " + chauffeur.getPhoneNumber();
		text += "\n\nBus: " + bus.getMake() + ", " + bus.getModel() + " - " + bus.getMaxCapacity() + " seater." + " Licenseplate: " + bus.getLicensePlate();
		text += "\n";
		if (this instanceof Tour)
			text += "Price: " + basePrice + " Per seat";
		else if (this instanceof BusAndChaffeurTravel)
			text += "Reservation price: " + basePrice;

		text += "\n\nDestinations:\n";
		for (int i = 0; i < destinations.length; i++)
		{
			text += "\t" + destinations[i] + "\n";
		}
		return text;
	}
	/**
	 * Returns a {@link String} representation of this {@link Travel}.
	 * @return Returns the {@link String} representation.
	 */
	@Override
	public String toString()
	{
		String toReturn = "";
		if (destinations != null && destinations.length > 0)
			toReturn += destinations[0] + " - ";

		toReturn += "Chauffeur:" + chauffeur.getFirstName();

		return toReturn;
	}

}
