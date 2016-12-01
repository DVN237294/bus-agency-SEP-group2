import java.util.Date;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public abstract class Travel
{
	private Bus bus;
	private Chauffeur chauffeur;
	private Date reservationStartDate;
	private Date reservationEndDate;
	
	private double basePrice;
	private String[] destinations;
	public Travel(Bus bus, Chauffeur chauffeur, Date reservationStartDate, Date reservationEndDate)
	{
		this.bus = bus;
		this.chauffeur = chauffeur;
		this.reservationStartDate = reservationStartDate;
		this.reservationEndDate = reservationEndDate;
		
		this.basePrice = 0;
		this.destinations = null;
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


	public Date getReservationStartDate()
	{
		return reservationStartDate;
	}


	public Date getReservationEndDate()
	{
		return reservationEndDate;
	}


	public boolean reservationOverlaps(Date startDate, Date endDate)
	{
		throw new NotImplementedException();
	}
}
