import java.util.ArrayList;
import java.util.Date;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TravelsList
{
   private ArrayList<Travel> travels;

   public TravelsList()
   {
      travels = new ArrayList<Travel>();
   }

   // public addTourPassenger() pointless

   public boolean hasReservation(Bus what, Date startDate, Date endDate)
   {
      throw new NotImplementedException(); // hmm, what did i want to do here..?
   }

   public boolean hasReservation(Chauffeur what, Date startDate, Date endDate)
   {
      throw new NotImplementedException(); // hmm, what did i want to do here..?
   }

   public int getNumberOftravels()
   {
      return travels.size();
   }
}
