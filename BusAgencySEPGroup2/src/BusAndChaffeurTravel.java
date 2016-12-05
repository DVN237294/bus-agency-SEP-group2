import java.time.LocalDateTime;

public class BusAndChaffeurTravel extends Travel
{
   private int personCount;
   private Customer customer;

   public BusAndChaffeurTravel(Customer customer, Bus bus, Chauffeur chauffeur,
         int personCount, LocalDateTime reservationStartDate,
         LocalDateTime reservationEndDate)
   {
      super(bus, chauffeur, reservationStartDate, reservationEndDate);
      this.personCount = personCount;
   }

   public int getPersonCount()
   {
      return personCount;
   }
   
   public void setCustomer(Customer customer) {
      this.customer = customer;
   }
   
   public Customer getCustomer() {
      return customer;
   }
}
