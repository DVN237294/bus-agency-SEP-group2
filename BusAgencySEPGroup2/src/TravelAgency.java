import java.time.LocalDateTime;
import java.util.ArrayList;

import sun.nio.cs.ext.ISCII91;

public class TravelAgency
{
	private double defaultDiscountRate;
	private BusList busList;
	private ChauffeurList chauffeurList;
	private CustomerList customerList;
	private TravelsList travelsList;
	private DestinationsList destinationsList;

	public TravelAgency()
	{
		this.busList = new BusList();
		this.chauffeurList = new ChauffeurList();
		this.customerList = new CustomerList();
		this.travelsList = new TravelsList();
		this.destinationsList = new DestinationsList();
	}
	public TravelAgency(BusList busList, ChauffeurList chauffeurList, CustomerList customerList, TravelsList travelsList)
	{
		this.busList = busList;
		this.chauffeurList = chauffeurList;
		this.customerList = customerList;
		this.travelsList = travelsList;
	}

	public Bus[] listAvailableBusses(LocalDateTime startDate, LocalDateTime endDate, int minCapacity)
	{
		ArrayList<Bus> temp = new ArrayList<Bus>();
		for (Bus bus : busList.getBusses(minCapacity))
		{
			if (!travelsList.hasReservationFor(bus, startDate, endDate))
				temp.add(bus);
		}
		Bus[] returnTemp = new Bus[temp.size()];
		temp.toArray(returnTemp);
		return returnTemp;
	}

	public Chauffeur[] listAvailableChauffeurs(LocalDateTime startDate, LocalDateTime endDate)
	{
		ArrayList<Chauffeur> temp = new ArrayList<Chauffeur>();
		for (Chauffeur chauffeur : chauffeurList.getAllChauffeurs())
		{
			if (!travelsList.hasReservationFor(chauffeur, startDate, endDate))
				temp.add(chauffeur);
		}
		Chauffeur[] returnTemp = new Chauffeur[temp.size()];
		temp.toArray(returnTemp);
		return returnTemp;
	}

	public void setDefaultDiscountRate(double rate)
	{
		this.defaultDiscountRate = rate;
	}

	public void addTourPassenger(Tour tour, Customer payingCustomer, Passenger passenger, double price)
	{
		if (!tour.hasCustomer(payingCustomer))
			tour.addCustomer(payingCustomer);

		if (!payingCustomer.hasAssociatedPassenger(passenger))
			payingCustomer.addPassenger(passenger, price);
	}

	public void addTourPassenger(Tour tour, Customer payingCustomer, Passenger passenger)
	{
		// this one uses the base price of the tour + default discount rate
		double price = tour.getBasePrice();
		if(customerList.isFrequentCustomer(payingCustomer))
		{
			//apply discount
			price *= defaultDiscountRate;
		}
		addTourPassenger(tour, payingCustomer, passenger, price);
	}
	public double getCustomerSuggestedPrice(Travel travel, Customer payingCustomer)
	{
		double price = travel.getBasePrice();
		
		if(customerList.isFrequentCustomer(payingCustomer))
		{
			//apply discount
			price *= defaultDiscountRate;
		}
		return price;
	}
	public String[] getAllDestinations()
	{
		return destinationsList.getAllDestinations();
	}
}
