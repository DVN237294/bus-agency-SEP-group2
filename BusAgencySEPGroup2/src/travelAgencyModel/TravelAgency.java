package travelAgencyModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Gateway class for the underlying model. Implements the {@link Serializable}
 * pattern for file persistence.
 * 
 * @author DVN, Carlos, Afonso
 *
 */
public class TravelAgency implements Serializable
{
	private String filePersistenceLocationBusList = "C:\\SEP_GROUP2\\TRAVELAGENCY_BUSLIST.o";
	private String filePersistenceLocationChaffeurList = "C:\\SEP_GROUP2\\TRAVELAGENCY_CHAUFFEURLIST.o";
	private String filePersistenceLocationTravelsList = "C:\\SEP_GROUP2\\TRAVELAGENCY_TRAVELSLIST.o";
	private String filePersistenceLocationDestinationsList = "C:\\SEP_GROUP2\\TRAVELAGENCY_DESTINATIONSLIST.o";
	private double defaultDiscountRate;
	private BusList busList;
	private ChauffeurList chauffeurList;
	// private CustomerList customerList;
	private TravelsList travelsList;
	private DestinationsList destinationsList;
	private int frequentCustomerThreshHold;

	/**
	 * Default constructor, initializes the model and loads data from files.
	 */
	public TravelAgency()
	{
		this.busList = new BusList();
		this.chauffeurList = new ChauffeurList();
		// this.customerList = new CustomerList();
		this.travelsList = new TravelsList();
		this.destinationsList = new DestinationsList();
		this.frequentCustomerThreshHold = Integer.MAX_VALUE;

		if (!loadFilePersistence())
		{
			// No files exists, must be the first time the program is run.. load
			// in some dummy data:
			loadDummyData();
		}
	}

	private void loadDummyData()
	{
		Bus bus1 = new Bus("International Harvester", "S-Series", "AB 555422", 54);
		Bus bus2 = new Bus("Nissan Diesel", "Space Arrow", "QS 153422", 47);
		Bus bus3 = new Bus("Volkswagen", "Volksbus", "PA 923152", 49);
		bus1.setColor("Yellow");
		bus2.setColor("Blue");
		bus3.setColor("Green");

		Address chauffeurAddress1 = new Address("Allegade", 47, 8700);
		Address chauffeurAddress2 = new Address("Høegh Guldbergs Gade", 99, 8700);
		Address chauffeurAddress3 = new Address("Grusdalsvej", 26, 8700);
		Chauffeur chauffeur1 = new Chauffeur("Martin", "Christensen", 8452);
		Chauffeur chauffeur2 = new Chauffeur("Jens", "Petersen", 2932);
		Chauffeur chauffeur3 = new Chauffeur("Michael", "Pedersen", 9271);
		chauffeur1.setEmail("MC@viabus.dk");
		chauffeur2.setEmail("JP@viabus.dk");
		chauffeur3.setEmail("MP@viabus.dk");
		chauffeur1.setAddress(chauffeurAddress1);
		chauffeur2.setAddress(chauffeurAddress2);
		chauffeur3.setAddress(chauffeurAddress3);
		chauffeur1.setPhoneNumber("23759316");
		chauffeur2.setPhoneNumber("23661991");
		chauffeur3.setPhoneNumber("23439901");

		String destination1 = "Legoland, Billund";
		String destination2 = "Djurs Sommerland";
		String destination3 = "Grænsen, Tyskland";

		Customer customer1 = new Customer("Jørgen Møller", 88245691);
		Customer customer2 = new Customer("Søren", 33265542);

		Travel travel1 = new Tour(bus1, chauffeur1, LocalDateTime.of(2016, 11, 10, 9, 0), LocalDateTime.of(2016, 11, 10, 17, 59));
		travel1.setBasePrice(99.75);
		travel1.setDestinations(new String[] { destination1 });
		((Tour) travel1).addCustomer(customer1);
		customer1.addPassenger(new Passenger("Anders", 53432591, LocalDateTime.of(1987, 4, 20, 0, 0)), 99.75);
		customer1.addPassenger(new Passenger("Thomas", 23112591, LocalDateTime.of(1987, 9, 11, 0, 0)), 99.75);
		customer1.addPassenger(new Passenger("Nicklas", 73342511, LocalDateTime.of(1988, 1, 19, 0, 0)), 99.75);

		Travel travel2 = new BusAndChaffeurTravel(customer2, bus2, chauffeur2, 30, LocalDateTime.of(2017, 1, 11, 9, 0), LocalDateTime.of(2017, 1, 11, 20, 0));
		travel2.setDestinations(new String[] { destination3 });

		this.addBus(bus1);
		this.addBus(bus2);
		this.addBus(bus3);
		this.addChauffeur(chauffeur1);
		this.addChauffeur(chauffeur2);
		this.addChauffeur(chauffeur3);
		this.addDestinations(new String[] { destination1, destination2, destination3 });
		this.addTravel(travel1);
		this.addTravel(travel2);
	}

	private boolean loadFilePersistence()
	{
		File buslist = new File(filePersistenceLocationBusList);
		File chaffeurlist = new File(filePersistenceLocationChaffeurList);
		File travelslist = new File(filePersistenceLocationTravelsList);
		File destinationslist = new File(filePersistenceLocationDestinationsList);

		if (buslist.exists())
		{
			try
			{
				FileInputStream fileIn = new FileInputStream(buslist);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				this.busList = (BusList) in.readObject();
				in.close();
				fileIn.close();
			} catch (Exception ex)
			{
				// just continue
			}
		}
		if (chaffeurlist.exists())
		{
			try
			{
				FileInputStream fileIn = new FileInputStream(chaffeurlist);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				this.chauffeurList = (ChauffeurList) in.readObject();
				in.close();
				fileIn.close();
			} catch (Exception ex)
			{
				// just continue
			}
		}
		if (travelslist.exists())
		{
			try
			{
				FileInputStream fileIn = new FileInputStream(travelslist);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				this.travelsList = (TravelsList) in.readObject();
				in.close();
				fileIn.close();
			} catch (Exception ex)
			{
				// just continue
			}
		}
		if (destinationslist.exists())
		{
			try
			{
				FileInputStream fileIn = new FileInputStream(destinationslist);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				this.destinationsList = (DestinationsList) in.readObject();
				in.close();
				fileIn.close();
			} catch (Exception ex)
			{
				// just continue
			}
		}
		return buslist.exists() && chaffeurlist.exists() && travelslist.exists() && destinationslist.exists();
	}

	private void saveObjectInFile(String file, Object obj)
	{
		File theFile = new File(file);
		File parentDirectory = new File(theFile.getParent());
		if (!parentDirectory.exists())
			parentDirectory.mkdirs();
		try
		{
			FileOutputStream fileOutStream = new FileOutputStream(theFile);
			ObjectOutputStream outStream = new ObjectOutputStream(fileOutStream);
			outStream.writeObject(obj);
			outStream.close();
			fileOutStream.close();
		} catch (IOException i)
		{
			i.printStackTrace();
		}
	}

	private void saveFileBusList()
	{
		saveObjectInFile(filePersistenceLocationBusList, busList);
	}

	private void saveFileChauffeurList()
	{
		saveObjectInFile(filePersistenceLocationChaffeurList, chauffeurList);
	}

	private void saveFileTravelsList()
	{
		saveObjectInFile(filePersistenceLocationTravelsList, travelsList);
	}

	private void saveFileDestinationsList()
	{
		saveObjectInFile(filePersistenceLocationDestinationsList, destinationsList);
	}

	/**
	 * Sets the frequent customer threshhold, which determines when a customer
	 * is applicable to receive a discount specified by the
	 * {@link defaultDiscountRate}.
	 * 
	 * @param reservationCount
	 *            The amount of reservations needed to be applicable for a
	 *            automatic discount
	 */
	public void setFrequentCustomerThreshhold(int reservationCount)
	{
		frequentCustomerThreshHold = reservationCount;
	}

	/**
	 * Lists the {@link Bus}es that are available within the specified time
	 * period, which has a capacity equal to or exceeding {@link minCapacity}.
	 * 
	 * @param startDate
	 *            The start of the time interval.
	 * @param endDate
	 *            The end of the time interval.
	 * @param minCapacity
	 *            The minimum {@link Passenger} capacity that the returned buses
	 *            must have.
	 * @return A collection of {@link Bus}es that satisfies the conditions.
	 */
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

	/**
	 * Lists the {@link Chauffeur}s that are available within the specified time
	 * period.
	 * 
	 * @param startDate
	 *            The start of the time interval.
	 * @param endDate
	 *            The end of the time interval.
	 * @return A collection of {@link Chauffeur}s that satisfies the conditions.
	 */
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

	/**
	 * Sets the default discount rate that will be applied whenever a customer
	 * exceeds the {@link frequentCustomerThreshHold}.
	 * 
	 * @param rate
	 *            The discount rate to be applied.
	 */
	public void setDefaultDiscountRate(double rate)
	{
		this.defaultDiscountRate = rate;
	}

	/*
	 * public void addTourPassenger(Tour tour, Customer payingCustomer,
	 * Passenger passenger, double price) { if
	 * (!tour.hasCustomer(payingCustomer)) tour.addCustomer(payingCustomer);
	 * 
	 * if (!payingCustomer.hasAssociatedPassenger(passenger))
	 * payingCustomer.addPassenger(passenger, price); }
	 */

	/**
	 * Determines whether the specified {@link Customer}s all-time reservation
	 * count exceeds the {@link frequentCustomerThreshHold}.
	 * 
	 * @param customer
	 *            The {@link Customer} to check.
	 * @return Returns true if the {@link Customer} exceeds the
	 *         {@link frequentCustomerThreshHold}.
	 */
	public boolean isFrequentCustomer(Customer customer)
	{
		return travelsList.getCustomerFrequency(customer) >= frequentCustomerThreshHold;
	}

	/*
	 * public void addTourPassenger(Tour tour, Customer payingCustomer,
	 * Passenger passenger) { // this one uses the base price of the tour +
	 * default discount rate double price = tour.getBasePrice(); if
	 * (isFrequentCustomer(payingCustomer)) { // apply discount price *=
	 * defaultDiscountRate; } addTourPassenger(tour, payingCustomer, passenger,
	 * price); }
	 */

	/**
	 * Returns the suggested price for a given {@link Customer} given a specific
	 * base price. The given base price is returned as-is if the
	 * {@link Customer } is not applicable to a discount.
	 * 
	 * @param basePrice
	 *            The base price.
	 * @param payingCustomer
	 *            The {@link Customer} to be assessed for a discount.
	 * @return Returns the given {@link basePrice} if the given {@link Customer }
	 *         is not applicable for a discount. Otherwise returns a new price
	 *         with a discount deducted.
	 */
	public double getCustomerSuggestedPrice(double basePrice, Customer payingCustomer)
	{
		if (isFrequentCustomer(payingCustomer))
		{
			// apply discount
			basePrice *= defaultDiscountRate;
		}
		return basePrice;
	}

	/**
	 * Returns the suggested price for a given {@link Customer} given a specific
	 * {@link Travel}. The base price of the {@link Travel} is returned as-is if
	 * the {@link Customer } is not applicable to a discount.
	 * 
	 * @param travel
	 *            The {@link Travel} from which the base price is determined.
	 * @param payingCustomer
	 *            The {@link Customer} to be assessed for a discount.
	 * @return Returns the base price of the given {@link Travel} if the given
	 *         {@link Customer } is not applicable for a discount. Otherwise
	 *         returns a new price with a discount deducted.
	 */
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

	/*
	 * public String getDestination(String destination) { return
	 * destinationsList.getDestination(destination); }
	 */

	public void addDestinations(String[] destinations)
	{
		for (String destination : destinations)
			destinationsList.add(destination);

		saveFileDestinationsList();
	}

	public Chauffeur[] getAllChauffeurs()
	{
		return chauffeurList.getAllChauffeurs();
	}

	public Chauffeur getChauffeur(String firstName, String lastName, int chauffeurID)
	{
		return chauffeurList.getChauffeur(firstName, lastName, chauffeurID);
	}

	/*
	 * public String getFirstName(String firstName) { return
	 * chauffeurList.getFirstName(firstName); }
	 */

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

	/*
	 * public String getLastName(String lastName) { return
	 * chauffeurList.getLastName(lastName); }
	 */

	/*
	 * public int getChauffeurId(int ID) { return
	 * chauffeurList.getChauffeurId(ID); }
	 */

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
		saveFileChauffeurList();
	}

	public void addChauffeur(String firstName, String lastName, int chauffeurID)
	{
		addChauffeur(new Chauffeur(firstName, lastName, chauffeurID));
	}

	public void deleteChauffeur(String firstName, String lastName, int chauffeurID)
	{
		deleteChauffeur(new Chauffeur(firstName, lastName, chauffeurID));
	}

	public void deleteChauffeur(Chauffeur chauffeur)
	{
		chauffeurList.removeChauffeur(chauffeur);
		saveFileChauffeurList();
	}

	/**
	 * Adds the specified bus to the underlying collection, and saves the
	 * collection to the hard drive.
	 * 
	 * @param bus
	 *            The bus to add.
	 */
	public void addBus(Bus bus)
	{
		busList.addBus(bus);
		saveFileBusList();
	}

	/**
	 * Adds a new bus with the specified parameters to the underlying
	 * collection, and saves the collection to the hard drive.
	 * 
	 * @param make
	 *            The manufacturer of the bus.
	 * @param model
	 *            The model name of the bus.
	 * @param licensePlate
	 *            The vehicle license plate.
	 * @param maxCapacity
	 *            The maximum passenger capacity of this bus.
	 */
	public void addBus(String make, String model, String licensePlate, int maxCapacity)
	{
		addBus(new Bus(make, model, licensePlate, maxCapacity));
	}

	public void deleteBus(String make, String model, String licensePlate, int maxCapacity)
	{
		deleteBus(new Bus(make, model, licensePlate, maxCapacity));
	}

	public void deleteBus(Bus bus)
	{
		busList.deleteBus(bus);
		saveFileBusList();
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
		saveFileTravelsList();
	}

	public void removeTravel(Travel travel)
	{
		travelsList.deleteTravel(travel);
		saveFileTravelsList();
	}

	/*
	 * public String getReservationStartDate() { return
	 * addTourFrame.getReservationStartDate(); }
	 * 
	 * public String getReservationEndDate() { return
	 * addTourFrame.getReservationEndDate(); }
	 */
}
