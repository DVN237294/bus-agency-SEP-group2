import java.time.LocalDateTime;
import java.util.ArrayList;

public class TravelAgency
{
	private double defaultDiscountRate;
	private BusList busList;
	private ChauffeurList chauffeurList;
	//private CustomerList customerList;
	private TravelsList travelsList;
	private DestinationsList destinationsList;
	private int frequentCustomerThreshHold;

	public TravelAgency()
	{
		this.busList = new BusList();
		this.chauffeurList = new ChauffeurList();
		//this.customerList = new CustomerList();
		this.travelsList = new TravelsList();
		this.destinationsList = new DestinationsList();
		this.frequentCustomerThreshHold = Integer.MAX_VALUE;
	}
/*
	public TravelAgency(BusList busList, ChauffeurList chauffeurList, CustomerList customerList, TravelsList travelsList)
	{
		this.busList = busList;
		this.chauffeurList = chauffeurList;
		this.customerList = customerList;
		this.travelsList = travelsList;
	}*/

	public void setFrequentCustomerThreshhold(int reservationCount)
	{
		frequentCustomerThreshHold = reservationCount;
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
	public boolean isFrequentCustomer(Customer customer)
	{
		return travelsList.getCustomerFrequency(customer) >= frequentCustomerThreshHold;
	}
	public void addTourPassenger(Tour tour, Customer payingCustomer, Passenger passenger)
	{
		// this one uses the base price of the tour + default discount rate
		double price = tour.getBasePrice();
		if (isFrequentCustomer(payingCustomer))
		{
			// apply discount
			price *= defaultDiscountRate;
		}
		addTourPassenger(tour, payingCustomer, passenger, price);
	}

	public double getCustomerSuggestedPrice(double basePrice, Customer payingCustomer)
	{
		if (isFrequentCustomer(payingCustomer))
		{
			// apply discount
			basePrice *= defaultDiscountRate;
		}
		return basePrice;
	}

	public double getCustomerSuggestedPrice(Travel travel, Customer payingCustomer)
	{
		double price = travel.getBasePrice();

		if (isFrequentCustomer(payingCustomer))
		{
			// apply discount
			price *= defaultDiscountRate;
		}
		return price;
	}

	public String[] getAllDestinations()
	{
		return destinationsList.getAllDestinations();
	}

	public String getDestination(String destination)
	{
		return destinationsList.getDestination(destination);
	}

	public void addDestination(String destination)
	{
		destinationsList.add(destination);
	}

	public Chauffeur[] getAllChauffeurs()
	{
		return chauffeurList.getAllChauffeurs();
	}

	public Chauffeur getChauffeur(String firstName, String lastName, int chauffeurID)
	{
		return chauffeurList.getChauffeur(firstName, lastName, chauffeurID);
	}

	public String getFirstName(String firstName)
	{
		return chauffeurList.getFirstName(firstName);
	}

	public String[] getAllFirstNames()
	{
		return chauffeurList.getAllFirstNames();
	}

	public String[] getAllLastNames()
	{
		return chauffeurList.getAllLastNames();
	}

	public String[] getAllChauffeurIds()
	{
		return chauffeurList.getAllChauffeurIds();
	}

	public String getLastName(String lastName)
	{
		return chauffeurList.getLastName(lastName);
	}

	public int getChauffeurId(int ID)
	{
		return chauffeurList.getChauffeurId(ID);
	}

	public Bus[] getAllBusses()
	{
		return busList.getAllBusses();
	}

	public Bus getBus(String make, String model, String licensePlate, int maxCapacity)
	{
		return busList.getBus(make, model, licensePlate, maxCapacity);
	}

	public String[] getAllBusMakes()
	{
		return busList.getAllMakes();
	}

	public String[] getAllBusModels()
	{
		return busList.getAllModels();
	}

	public String[] getAllBusLicensePlates()
	{
		return busList.getAllLicensePlates();
	}

	public Integer[] getAllBusMaxCapacities()
	{
		return busList.getAllMaxCapacities();
	}

	public void addChauffeur(Chauffeur chauffeur)
	{
		chauffeurList.addChauffeur(chauffeur);
	}
	
	public void addChauffeur(String firstName, String lastName, int chauffeurID)
	{
		chauffeurList.addChauffeur(new Chauffeur(firstName, lastName, chauffeurID));
	}

	public void deleteChauffeur(String firstName, String lastName, int chauffeurID)
	{
		chauffeurList.deleteChauffeur(new Chauffeur(firstName, lastName, chauffeurID));
	}

	public void deleteChauffeur(Chauffeur chauffeur)
	{
		chauffeurList.deleteChauffeur(chauffeur);
	}

	public void addBus(String make, String model, String licensePlate, int maxCapacity)
	{
		busList.addBus(new Bus(make, model, licensePlate, maxCapacity));
	}

	public void deleteBus(String make, String model, String licensePlate, int maxCapacity)
	{
		busList.deleteBus(new Bus(make, model, licensePlate, maxCapacity));
	}

	public void deleteBus(Bus bus)
	{
		busList.deleteBus(bus);
	}

	public Travel[] searchTravel(String destination, Chauffeur chauffeur, Bus bus)
	{
		return travelsList.searchTravel(destination, chauffeur, bus, false);
	}

	public int[] getBusCapacities()
	{
		return busList.getBusCapacities();
	}
	
	public void addTravel(Travel travel)
	{
		travelsList.addTravel(travel);
	}
	
	public void removeTravel(Travel travel)
	{
		travelsList.deleteTravel(travel);
	}

	/*
	 * public String getReservationStartDate() { return
	 * addTourFrame.getReservationStartDate(); }
	 * 
	 * public String getReservationEndDate() { return
	 * addTourFrame.getReservationEndDate(); }
	 */
}
