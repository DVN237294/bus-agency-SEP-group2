package travelAgencyModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 * {@link ArrayList} backed list containing instances of {@link Bus}. Implements
 * {@link Serializable}
 * 
 * @author DVN, Afonso, Carlos
 */
public class BusList implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Bus> buses;

	/**
	 * Default constructor, which initializes the underlying collection.
	 */
	public BusList()
	{
		buses = new ArrayList<Bus>();
	}

	public void addBus(Bus bus)
	{
		buses.add(bus);
	}

	public void deleteBus(Bus bus)
	{
		buses.remove(bus);
	}

	/**
	 * Returns an {@link ArrayList} of {@link Bus}es which has a capacity
	 * greater than or equal to {@link minCapacity}.
	 * 
	 * @param minCapacity
	 *            The minimum passenger capacity that any returned {@link Bus}
	 *            must have.
	 * @return An {@link ArrayList} of {@link Bus}es.
	 */
	public ArrayList<Bus> getBusses(int minCapacity)
	{
		ArrayList<Bus> temp = new ArrayList<>();
		for (int i = 0; i < buses.size(); i++)
		{
			if (buses.get(i).getMaxCapacity() >= minCapacity)
			{
				temp.add(buses.get(i));
			}
		}

		return temp;
	}

	/**
	 * Returns all of the stored {@link Bus}es as a shallow copy.
	 * 
	 * @return An array of {@link Bus}es.
	 */
	public Bus[] getAllBusses()
	{
		Bus[] temp = new Bus[buses.size()];
		buses.toArray(temp);
		return temp;
	}

	/**
	 * Returns the makes of every {@link Bus} in the underlying collection. May
	 * contain duplicates.
	 * 
	 * @return An array of {@link String}s.
	 */
	public String[] getAllMakes()
	{
		String[] temp = new String[buses.size()];
		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = buses.get(i).getMake();
		}
		return temp;
	}

	/**
	 * Returns the model of every {@link Bus} in the underlying collection. May
	 * contain duplicates.
	 * 
	 * @return An array of {@link String}s.
	 */
	public String[] getAllModels()
	{
		String[] temp = new String[buses.size()];
		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = buses.get(i).getModel();
		}
		return temp;
	}

	/**
	 * Returns the license plate of every {@link Bus} in the underlying
	 * collection. May contain duplicates.
	 * 
	 * @return An array of {@link String}s.
	 */
	public String[] getAllLicensePlates()
	{
		String[] temp = new String[buses.size()];
		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = buses.get(i).getLicensePlate();
		}
		return temp;
	}

	/**
	 * Returns the maximum passenger capacity of every {@link Bus} in the
	 * underlying collection. May contain duplicates.
	 * 
	 * @return An array of {@link Integer}s.
	 */
	public Integer[] getAllMaxCapacities()
	{
		Integer[] temp = new Integer[buses.size()];
		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = buses.get(i).getMaxCapacity();
		}
		return temp;
	}

	/**
	 * Returns the first {@link Bus} from the underlying collection which
	 * matches the input parameters. If any of the passed reference types are
	 * null, they are ignored.
	 * 
	 * @param make
	 *            The {@link Bus}s manufacturer.
	 * @param model
	 *            The {@link Bus} model.
	 * @param licensePlate
	 *            The vehicle license plate.
	 * @param maxCapacity
	 *            The maximum capacity of the {@link Bus}.
	 * @return Returns the first instance of {@link Bus} from the underlying
	 *         collection which matches one of the input parameters.
	 */
	public Bus getBus(String make, String model, String licensePlate, int maxCapacity)
	{
		for (int i = 0; i < buses.size(); i++)
		{
			if (buses.get(i).getMake().equals(make) || buses.get(i).getModel().equals(model) || buses.get(i).getLicensePlate().equals(licensePlate)
					|| buses.get(i).getMaxCapacity() == maxCapacity)
			{
				return buses.get(i);
			}
		}
		return null;
	}

	/**
	 * Gets all unique maximum capacities of the {@link Bus}es stored in the
	 * underlying collection. Sorted in ascending order.
	 * 
	 * @return Sorted collection of integers.
	 */
	public int[] getBusCapacities()
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();

		for (Bus bus : buses)
		{
			if (!temp.contains(bus.getMaxCapacity()))
				temp.add(bus.getMaxCapacity());
		}

		int[] returnArray = new int[temp.size()];
		for (int i = 0; i < returnArray.length; i++)
		{
			returnArray[i] = temp.get(i);
		}
		Arrays.sort(returnArray);
		return returnArray;
	}

	/**
	 * Searches for {@link Bus}es that satisfies the given input parameters.
	 * Does exclusive search by default.If any of the passed parameters are
	 * {@link null}, they are ignored, in which case they do not affect the
	 * search result.
	 * 
	 * @param make The manufacturer to search for.
	 * @param model The vehicle model to search for.
	 * @param licensePlate The vehicle license plate to search for.
	 * @param maxCapacity The vehicle maximum passenger capacity to search for.
	 * @return Returns an array of {@link Bus}es that satisfies the given parameters.
	 */
	public Bus[] search(String make, String model, String licensePlate, int maxCapacity)
	{
		ArrayList<Bus> temp = new ArrayList<Bus>();
		Bus[] tempArray = new Bus[0];

		boolean mustHaveMake = make != null;
		boolean mustHaveModel = model != null;
		boolean mustHaveLicensePlate = licensePlate != null;
		boolean mustHaveMaxCapacity = maxCapacity != 0;

		for (Bus bus : buses)
		{
			boolean hasMake = mustHaveMake && bus.getMake().equals(make);
			boolean hasModel = mustHaveModel && bus.getModel().equals(model);
			boolean hasLicensePlate = mustHaveLicensePlate && bus.getLicensePlate().equals(licensePlate);
			boolean hasMaxCapacity = mustHaveMaxCapacity && bus.getMaxCapacity() == maxCapacity;

			if (mustHaveMake == hasMake && mustHaveModel == hasModel && mustHaveLicensePlate == hasLicensePlate && mustHaveMaxCapacity == hasMaxCapacity)
			{
				temp.add(bus);
			}

		}

		return temp.toArray(tempArray);
	}
}
