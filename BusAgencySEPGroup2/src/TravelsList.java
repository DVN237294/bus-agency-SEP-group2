import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TravelsList implements Serializable
{
	private ArrayList<Travel> travels;
	

	public TravelsList()
	{
		travels = new ArrayList<Travel>();
	}

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
	
	public int getCustomerFrequency(Customer customer)
	{
		int sum = 0;
		for(Travel travel : travels)
		{
			sum += travel.getCustomerFrequency(customer);
		}
		return sum;
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

	public Travel[] searchTravel(String destination, Chauffeur chauffeur, Bus bus, Boolean inclusive)
	{
		if (inclusive)
			return searchTravel(destination, chauffeur, bus);

		ArrayList<Travel> temp = new ArrayList<Travel>();
		Travel[] tempArray = new Travel[0];

		boolean mustHaveDestination = destination != null;
		boolean mustHaveChauffeur = chauffeur != null;
		boolean mustHaveBus = bus != null;

		for (Travel travel : travels)
		{
			boolean hasDestination = false;
			boolean hasChauffeur = mustHaveChauffeur && travel.getChauffeur().equals(chauffeur);
			boolean hasBus = mustHaveBus && travel.getBus().equals(bus);
			if (mustHaveDestination)
			{
				for (String dst : travel.getDestinations())
					if (dst.equals(destination))
					{
						hasDestination = true;
						break;
					}
			}
			if(mustHaveDestination == hasDestination && mustHaveChauffeur == hasChauffeur && mustHaveBus == hasBus)
			{
				temp.add(travel);
			}
			
		}

		return temp.toArray(tempArray);
	}

	public Travel[] searchTravel(String destination, Chauffeur chauffeur, Bus bus)
	{
		ArrayList<Travel> temp = new ArrayList<Travel>();
		Travel[] tempArray = new Travel[0];
		for (Travel travel : travels)
		{
			if (destination != null)
			{
				boolean hasDestination = false;
				for (String dst : travel.getDestinations())
					if (dst.equals(destination))
					{
						hasDestination = true;
						break;
					}
				if (hasDestination)
				{
					temp.add(travel);
					continue;
				}
			}
			if (chauffeur != null && travel.getChauffeur().equals(chauffeur))
			{
				temp.add(travel);
				continue;
			}
			if (bus != null && travel.getBus().equals(bus))
			{
				temp.add(travel);
				continue;
			}
		}

		return temp.toArray(tempArray);
	}

	public Travel[] getAllTravels()
	{
		Travel[] temp = new Travel[travels.size()];
		travels.toArray(temp);
		return temp;
	}
}
