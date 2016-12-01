import java.util.ArrayList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BusList
{
   private ArrayList<Bus> buses;

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

   public Bus[] getBusses(int minCapacity)
   {
      throw new NotImplementedException();
   }
}
