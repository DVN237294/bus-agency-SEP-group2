import java.time.LocalDateTime;

public abstract class Travel
{
	private Bus bus;
	private Chauffeur chauffeur;
	private LocalDateTime reservationStartDate;
	private LocalDateTime reservationEndDate;

	private double basePrice;
	private String[] destinations;

	public Travel(Bus bus, Chauffeur chauffeur, LocalDateTime reservationStartDate, LocalDateTime reservationEndDate)
	{
		this.bus = bus;
		this.chauffeur = chauffeur;
		this.reservationStartDate = reservationStartDate;
		this.reservationEndDate = reservationEndDate;

		this.basePrice = 0;
		this.destinations = null;
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

	public boolean reservationOverlaps(LocalDateTime startDate, LocalDateTime endDate)
	{
		return ((TimeStamp.unixTime(startDate) < TimeStamp.unixTime(reservationEndDate) && TimeStamp.unixTime(startDate) > TimeStamp
				.unixTime(reservationStartDate)) || (TimeStamp.unixTime(endDate) > TimeStamp.unixTime(reservationStartDate) && TimeStamp.unixTime(endDate) < TimeStamp
				.unixTime(reservationEndDate)))
				||
				// or the other way around:
				((TimeStamp.unixTime(reservationStartDate) < TimeStamp.unixTime(endDate) && TimeStamp.unixTime(reservationStartDate) > TimeStamp
						.unixTime(startDate)) || (TimeStamp.unixTime(reservationEndDate) > TimeStamp.unixTime(startDate) && TimeStamp
						.unixTime(reservationEndDate) < TimeStamp.unixTime(endDate)));
	}

	public boolean reservationOverlaps(Travel other)
	{
		return reservationOverlaps(other.getReservationStartDate(), other.getReservationEndDate());
	}

	@Override
	public String toString()
	{
		if (destinations != null && destinations.length > 0)
			return destinations[0];

		return "";
	}

}
