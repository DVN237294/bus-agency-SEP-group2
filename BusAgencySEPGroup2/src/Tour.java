import java.util.ArrayList;
import java.util.Date;


public class Tour extends Travel
{
	private Date departureDate;
	private Date arrivalDate;
	private Date returnDate;
	private PassengerList passengerList;
	private ArrayList<Customer> customers;
	
	public Tour(Bus bus, Chauffeur chauffeur, Date reservationStartDate, Date reservationEndDate)
	{
		super(bus, chauffeur, reservationStartDate, reservationEndDate);
		departureDate = null;
		arrivalDate = null;
		returnDate = null;
		passengerList = new PassengerList();
		customers = new ArrayList<Customer>();
	}
	
	public void addPassenger(Passenger passenger, double price)
	{
		passengerList.addPassenger(passenger, price);
	}

	public Date getDepartureDate()
	{
		return departureDate;
	}

	public void setDepartureDate(Date departureDate)
	{
		this.departureDate = departureDate;
	}

	public Date getArrivalDate()
	{
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate)
	{
		this.arrivalDate = arrivalDate;
	}

	public Date getReturnDate()
	{
		return returnDate;
	}

	public void setReturnDate(Date returnDate)
	{
		this.returnDate = returnDate;
	}
	
	
}
