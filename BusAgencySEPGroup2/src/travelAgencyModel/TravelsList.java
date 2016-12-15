package travelAgencyModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * {@link ArrayList} backed list containing instances of classes extending
 * {@link Travel}
 * 
 * @author DVN, Afonso, Carlos
 */
public class TravelsList implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Travel> travels;

	/**
	 * Default constructor initializes the backing {@link ArrayList}.
	 */
	public TravelsList()
	{
		travels = new ArrayList<Travel>();
	}

	/**
	 * Adds the specified {@link Travel} to the collection.
	 * 
	 * @param travel
	 *            the {@link Travel} to add to the collection.
	 */
	public void addTravel(Travel travel)
	{
		travels.add(travel);
	}

	/**
	 * Removes the {@link Travel} from the collection which satisfies the
	 * travel.equals() method, if any.
	 * 
	 * @param travel
	 *            The {@link Travel} to remove.
	 */
	public void deleteTravel(Travel travel)
	{
		travels.remove(travel);
	}

	/**
	 * Determines if this {@link TravelsList} has a {@link Travel} which has a
	 * reservation for the specified {@link Bus} within the specified time
	 * period.
	 * 
	 * @param what
	 *            The {@link Bus} that is checked for a preexisting reservation.
	 * @param startDate
	 *            The {@link LocalDateTime} which specifies the start of the
	 *            time interval.
	 * @param endDate
	 *            The {@link LocalDateTime} which specifies the end of the time
	 *            interval.
	 * @return Returns true if the specified {@link Bus} already has a
	 *         reservation within the specified time interval
	 */
	public boolean hasReservationFor(Bus what, LocalDateTime startDate, LocalDateTime endDate)
	{
		for (Travel travel : travels)
		{
			if (travel.getBus().equals(what) && travel.reservationOverlaps(startDate, endDate))
				return true;
		}
		return false;
	}

	/**
	 * Determines how many times a given {@link Customer} has been registered in
	 * a {@link Travel}.
	 * 
	 * @param customer
	 *            The {@link Customer} to check the reservation frequency of.
	 * @return The sum of all registrations for the given {@link Customer}.
	 */
	public int getCustomerFrequency(Customer customer)
	{
		int sum = 0;
		for (Travel travel : travels)
		{
			sum += travel.getCustomerFrequency(customer);
		}
		return sum;
	}

	/**
	 * Determines if this {@link TravelsList} has a {@link Travel} which has a
	 * reservation for the specified {@link Chauffeur} within the specified time
	 * period.
	 * 
	 * @param what
	 *            The {@link Chauffeur} that is checked for a preexisting
	 *            reservation.
	 * @param startDate
	 *            The {@link LocalDateTime} which specifies the start of the
	 *            time interval.
	 * @param endDate
	 *            The {@link LocalDateTime} which specifies the end of the time
	 *            interval.
	 * @return Returns true if the specified {@link Chauffeur} already has a
	 *         reservation within the specified time interval.
	 */
	public boolean hasReservationFor(Chauffeur what, LocalDateTime startDate, LocalDateTime endDate)
	{
		for (Travel travel : travels)
		{
			if (travel.getChauffeur().equals(what) && travel.reservationOverlaps(startDate, endDate))
				return true;
		}
		return false;
	}

	/**
	 * Determines the number of {@link Travel}s stored in the collection.
	 * 
	 * @return The number of {@link Travel}s stored in the collection.
	 */
	public int getNumberOftravels()
	{
		return travels.size();
	}

	/**
	 * Searches for {@link Travel}s that satisfies the given input parameters.
	 * If {@link inclusive} is passed as true, all of the {@link Travel}s that
	 * matches any of the input parameters is returned. If {@link inclusive} is
	 * false, all of the {@link Travel}s that matches all of the specified
	 * parameters are returned. If any of the passed parameters are {@link null}
	 * , they are ignored, in which case they do not affect the search result.
	 * 
	 * @param destination
	 *            A destination that is searched for in the {@link Travel}s
	 *            destinations collection.
	 * @param chauffeur
	 *            A {@link Chauffeur} that is searched for.
	 * @param bus
	 *            A {@link Bus} that is searched {@link Bus}.
	 * @param inclusive
	 *            Specifies whether the search should be done inclusive or
	 *            exclusive.
	 *
	 * @return A collection of {@link Travel}s that matches the search
	 *         parameters.
	 */
	public Travel[] search(String destination, Chauffeur chauffeur, Bus bus, Boolean inclusive)
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
			if (mustHaveDestination == hasDestination && mustHaveChauffeur == hasChauffeur && mustHaveBus == hasBus)
			{
				temp.add(travel);
			}

		}

		return temp.toArray(tempArray);
	}

	/**
	 * Searches for {@link Travel}s that satisfies the given input parameters.
	 * The search is inclusive and will therefore return all {@link Travel}s
	 * that matches any of the given parameters. If any of the passed parameters
	 * are {@link null} , they are ignored, in which case they do not affect the
	 * search result.
	 * 
	 * @param destination
	 *            A destination that is searched for in the {@link Travel}s
	 *            destinations collection.
	 * @param chauffeur
	 *            A {@link Chauffeur} that is searched for.
	 * @param bus
	 *            A {@link Bus} that is searched {@link Bus}.
	 *
	 * @return A collection of {@link Travel}s that matches the search
	 *         parameters.
	 */
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

	/**
	 * Returns every {@link Travel} in the underlying collection as a shallow
	 * copy.
	 * 
	 * @return A collection of {@link Travel}s.
	 */
	public Travel[] getAllTravels()
	{
		Travel[] temp = new Travel[travels.size()];
		travels.toArray(temp);
		return temp;
	}

	/**
	 * Returns the first {@link Customer} in the underlying collection whose
	 * name and phoneNumber is equal to the provided arguments.
	 * 
	 * @param name
	 *            The name to look for.
	 * @param phoneNumber
	 *            The phone number to look for
	 * @return Returns the first instance of {@link Customer} that satisfies the
	 *         condition if any, otherwise returns {@link null}.
	 */
	public Customer getCustomer(String name, int phoneNumber)
	{
		for (Travel travel : travels)
		{
			if (travel instanceof Tour)
			{
				for (Customer customer : ((Tour) travel).getCustomers())
					if (customer.getName().equals(name) && customer.getPhoneNumber() == phoneNumber)
						return customer;
			} else if (travel instanceof BusAndChaffeurTravel)
			{
				Customer customer = ((BusAndChaffeurTravel) travel).getCustomer();
				if (customer.getName().equals(name) && customer.getPhoneNumber() == phoneNumber)
					return customer;
			}
		}
		return null;
	}
}
