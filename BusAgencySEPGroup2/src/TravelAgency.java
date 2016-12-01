import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TravelAgency
{

   private BusList busList;
   private ChauffeurList chauffeurList;
   private CustomerList customerList;
   private TravelsList travelsList;

   public TravelAgency(BusList busList, ChauffeurList chauffeurList,
         CustomerList customerList, TravelsList travelsList)
   {
      this.busList = busList;
      this.chauffeurList = chauffeurList;
      this.customerList = customerList;
      this.travelsList = travelsList;
   }

   public Bus[] listAvailableBusses(LocalDateTime startDate, LocalDateTime endDate, int minCapacity)
   {
	   ArrayList<Bus> temp = new ArrayList<Bus>();
	   for(Bus bus : busList.getBusses(minCapacity))
	   {
		   if(!travelsList.hasReservationFor(bus, startDate, endDate))
			   temp.add(bus);
	   }
	   Bus[] returnTemp = new Bus[temp.size()];
	   temp.toArray(returnTemp);
	   return returnTemp;
   }

   public Chauffeur[] listAvailableChauffeurs(Date startDate, Date endDate)
   {
      throw new NotImplementedException();
   }
}
