import java.time.LocalDateTime;
import java.util.Date;


public class BusAndChaffeurTravel extends Travel
{
	private int personCount;
	public BusAndChaffeurTravel(Customer customer, Bus bus, Chauffeur chauffeur, int personCount, LocalDateTime reservationStartDate, LocalDateTime reservationEndDate)
	{
		super(bus, chauffeur, reservationStartDate, reservationEndDate);
		this.personCount = personCount;
	}
	
	public int getPersonCount()
	{
		return personCount;
	}
}
