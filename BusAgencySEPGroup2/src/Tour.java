import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


public class Tour extends Travel
{
	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;
	private LocalDateTime returnDate;
	private PassengerList passengerList;
	private ArrayList<Customer> customers;
	
	public Tour(Bus bus, Chauffeur chauffeur, LocalDateTime reservationStartDate, LocalDateTime reservationEndDate)
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
	
	
}
