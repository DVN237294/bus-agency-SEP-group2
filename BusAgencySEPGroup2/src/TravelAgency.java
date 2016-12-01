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

   public Bus[] listAvailableBusses(Date startDate, Date endDate,
         int minCapacity)
   {
      throw new NotImplementedException();
   }

   public Chauffeur[] listAvailableChauffeurs(Date startDate, Date endDate)
   {
      throw new NotImplementedException();
   }
}
