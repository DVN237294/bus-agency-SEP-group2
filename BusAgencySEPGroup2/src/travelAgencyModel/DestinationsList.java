package travelAgencyModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * {@link ArrayList} backed list containing instances of {@link String}.
 * Implements {@link Serializable}
 * 
 * @author DVN
 *
 */
public class DestinationsList implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<String> destinations;

	/**
	 * Default constructor which initializes the underlying collection.
	 */
	public DestinationsList()
	{
		destinations = new ArrayList<String>();
	}

	/**
	 * Adds the specified destination to the collection, if it does not already
	 * contain an equal destination.
	 * 
	 * @param destination
	 *            The destination to add.
	 */
	public void add(String destination)
	{
		if (!destinations.contains(destination))
			destinations.add(destination);
	}

	public void remove(String destination)
	{
		destinations.remove(destination);
	}

	/**
	 * Gets every destination from the underlying collection as a shallow copy.
	 * @return An array of {@link String}s
	 */
	public String[] getAllDestinations()
	{
		String[] temp = new String[destinations.size()];
		destinations.toArray(temp);
		return temp;
	}
}
