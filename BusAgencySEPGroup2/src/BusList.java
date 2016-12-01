import java.util.ArrayList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class BusList
{
	private ArrayList<Bus> busses;
	
	public BusList()
	{
		busses = new ArrayList<Bus>();
	}
	public void addBus(Bus bus)
	{
		busses.add(bus);
	}
	public Bus[] getBusses(int minCapacity)
	{
		throw new NotImplementedException();
	}
}
