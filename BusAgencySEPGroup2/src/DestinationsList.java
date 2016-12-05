import java.util.ArrayList;

public class DestinationsList
{
   private ArrayList<String> destinations;

   public DestinationsList()
   {
      destinations = new ArrayList<String>();
   }

   public void add(String destination)
   {
      destinations.add(destination);
   }
   
   public void delete(String destination) {
      destinations.remove(destination);
   }

   public String getDestination(String destination) {
      for (int i = 0; i < destinations.size(); i++)
      {
         if(destinations.get(i).equals(destination)) {
            return destinations.get(i);
         }
      }
      return null;
   }
   
   public String[] getAllDestinations()
   {
      String[] temp = new String[destinations.size()];
      destinations.toArray(temp);
      return temp;
   }
}
