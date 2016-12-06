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
         if (buses.get(i).getMaxCapacity() > minCapacity)
         {
            temp.add(buses.get(i));
         }
      }

      return temp;
   }

   public Bus[] getAllBusses()
   {
      Bus[] temp = new Bus[buses.size()];
      buses.toArray(temp);
      return temp;
   }

   public Bus getBus(String make, String model, String licensePlate)
   {
      for (int i = 0; i < buses.size(); i++)
      {
         if (buses.get(i).getMake().equals(make)
               || buses.get(i).getModel().equals(model)
               || buses.get(i).getLicensePlate().equals(licensePlate))
         {
            return buses.get(i);
         }
      }
      return null;
   }
}
