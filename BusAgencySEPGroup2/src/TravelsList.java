import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TravelsList
{
	private ArrayList<Travel> travels;

	public TravelsList()
	{
		travels = new ArrayList<Travel>();
	}

	// public addTourPassenger() pointless

	public void addTravel(Travel travel)
	{
		travels.add(travel);
	}

	public void deleteTravel(Travel travel)
	{
		travels.remove(travel);
	}

	public boolean hasReservationFor(Bus what, LocalDateTime startDate, LocalDateTime endDate)
	{
		for (Travel travel : travels)
		{
			if (travel.getBus().equals(what) && travel.reservationOverlaps(startDate, endDate))
				return true;
		}
		return false;
	}

	public boolean hasReservationFor(Chauffeur what, LocalDateTime startDate, LocalDateTime endDate)
	{
		for (Travel travel : travels)
		{
			if (travel.getChauffeur().equals(what) && travel.reservationOverlaps(startDate, endDate))
				return true;
		}
		return false;
	}

	public int getNumberOftravels()
	{
		return travels.size();
	}
}
