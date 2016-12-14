import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

	public TravelAgency()
	{
		this.busList = new BusList();
		this.chauffeurList = new ChauffeurList();
		// this.customerList = new CustomerList();
		this.travelsList = new TravelsList();
		this.destinationsList = new DestinationsList();
		this.frequentCustomerThreshHold = Integer.MAX_VALUE;
		
		loadFilePersistence();
	}

	public void loadFilePersistence()
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
	}

	private void saveObjectInFile(String file, Object obj)
	{
		File theFile = new File(file);
		File parentDirectory = new File(theFile.getParent());
		if(!parentDirectory.exists())
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
	
	public void saveFileBusList()
	{
		saveObjectInFile(filePersistenceLocationBusList, busList);
	}
	
	public void saveFileChauffeurList()
	{
		saveObjectInFile(filePersistenceLocationChaffeurList, chauffeurList);
	}
	
	public void saveFileTravelsList()
	{
		saveObjectInFile(filePersistenceLocationTravelsList, travelsList);
	}
	
	public void saveFileDestinationsList()
	{
		saveObjectInFile(filePersistenceLocationDestinationsList, destinationsList);
	}

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

	/*public void addTourPassenger(Tour tour, Customer payingCustomer, Passenger passenger, double price)
	{
		if (!tour.hasCustomer(payingCustomer))
			tour.addCustomer(payingCustomer);

		if (!payingCustomer.hasAssociatedPassenger(passenger))
			payingCustomer.addPassenger(passenger, price);
	}*/

	public boolean isFrequentCustomer(Customer customer)
	{
		return travelsList.getCustomerFrequency(customer) >= frequentCustomerThreshHold;
	}

	/*public void addTourPassenger(Tour tour, Customer payingCustomer, Passenger passenger)
	{
		// this one uses the base price of the tour + default discount rate
		double price = tour.getBasePrice();
		if (isFrequentCustomer(payingCustomer))
		{
			// apply discount
			price *= defaultDiscountRate;
		}
		addTourPassenger(tour, payingCustomer, passenger, price);
	}*/

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

	/*public String getDestination(String destination)
	{
		return destinationsList.getDestination(destination);
	}*/

	public void addDestinations(String[] destinations)
	{
		for(String destination : destinations)
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

	/*public String getFirstName(String firstName)
	{
		return chauffeurList.getFirstName(firstName);
	}*/

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

	/*public String getLastName(String lastName)
	{
		return chauffeurList.getLastName(lastName);
	}*/

	/*public int getChauffeurId(int ID)
	{
		return chauffeurList.getChauffeurId(ID);
	}*/

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
		chauffeurList.deleteChauffeur(chauffeur);
		saveFileChauffeurList();
	}

	public void addBus(String make, String model, String licensePlate, int maxCapacity)
	{
		busList.addBus(new Bus(make, model, licensePlate, maxCapacity));
		saveFileBusList();
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
