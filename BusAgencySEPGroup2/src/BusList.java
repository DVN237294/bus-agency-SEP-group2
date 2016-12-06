import java.util.ArrayList;

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

   public ArrayList<Bus> getBusses(int minCapacity)
   {
      ArrayList<Bus> temp = new ArrayList<>();
      for (int i = 0; i < buses.size(); i++)
      {
         if(buses.get(i).getMaxCapacity() > minCapacity) {
            temp.add(buses.get(i));
         }
      }
      
      return temp;
   }
   
   public ArrayList<Bus> getAllBusses() {
      return buses;
   }
}
