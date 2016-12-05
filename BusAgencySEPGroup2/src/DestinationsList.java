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

   public String[] getAllDestinations()
   {
      String[] temp = new String[destinations.size()];
      destinations.toArray(temp);
      return temp;
   }
}
