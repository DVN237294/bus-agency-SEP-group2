import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class BusList implements Serializable
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
      System.out.println();
   }

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

   public Bus[] getAllBusses()
   {
      Bus[] temp = new Bus[buses.size()];
      buses.toArray(temp);
      return temp;
   }
   
   public String[] getAllMakes()
   {
      String[] temp = new String[buses.size()];
      for (int i = 0; i < temp.length; i++)
      {
            temp[i] = buses.get(i).getMake();
      }
      return temp;
   }
   
   public String[] getAllModels()
   {
      String[] temp = new String[buses.size()];
      for (int i = 0; i < temp.length; i++)
      {
            temp[i] = buses.get(i).getModel();
      }
      return temp;
   }
   
   public String[] getAllLicensePlates()
   {
      String[] temp = new String[buses.size()];
      for (int i = 0; i < temp.length; i++)
      {
            temp[i] = buses.get(i).getLicensePlate();
      }
      return temp;
   }
   
   public Integer[] getAllMaxCapacities()
   {
      Integer[] temp = new Integer[buses.size()];
      for (int i = 0; i < temp.length; i++)
      {
            temp[i] = buses.get(i).getMaxCapacity();
      }
      return temp;
   }

   public Bus getBus(String make, String model, String licensePlate, int maxCapacity)
   {
      for (int i = 0; i < buses.size(); i++)
      {
         if (buses.get(i).getMake().equals(make)
               || buses.get(i).getModel().equals(model)
               || buses.get(i).getLicensePlate().equals(licensePlate)
               || buses.get(i).getMaxCapacity() == maxCapacity)
         {
            return buses.get(i);
         }
      }
      return null;
   }
   
   public int[] getBusCapacities()
   {
	   ArrayList<Integer> temp = new ArrayList<Integer>();
	   
	   for(Bus bus : buses)
	   {
		   if(!temp.contains(bus.getMaxCapacity()))
			   temp.add(bus.getMaxCapacity());
	   }
	   
	   int[] returnArray = new int[temp.size()];
	   for(int i = 0; i < returnArray.length; i++)
	   {
		   returnArray[i] = temp.get(i);
	   }
	   Arrays.sort(returnArray); 
	   return returnArray;
   }
}
