package travelAgencyModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * {@link ArrayList} backed list containing instances of {@link Customer}.
 * Implements {@link Serializable}
 * 
 * @author DVN
 *
 */
public class CustomerList implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Customer> customers;

	/**
	 * Default constructor initializes the underlying collection.
	 */
	public CustomerList()
	{
		this.customers = new ArrayList<Customer>();
	}

	/**
	 * Add a {@link Customer} to the underlying collection by passing a name and
	 * phone number.
	 * 
	 * @param name
	 *            The name of the {@link Customer}.
	 * @param phoneNumber
	 *            The phone number of the {@link Customer}.
	 * @return Returns a reference to the added {@link Customer}.
	 */
	public Customer addCustomer(String name, int phoneNumber)
	{
		Customer temp = new Customer(name, phoneNumber);
		addCustomer(temp);
		return temp;
	}

	/**
	 * Computes the aggregate sum of appearances of the provided
	 * {@link Customer} in the underlying collection of {@link Customer} s.
	 * 
	 * @param customer
	 *            The {@link Customer} to search for.
	 * @return Returns the number of times the {@link Customer} is seen in the
	 *         collection.
	 */
	public int getCustomerFrequency(Customer customer)
	{
		int sum = 0;
		for (Customer listCustomer : customers)
			if (listCustomer.equals(customer))
				sum++;

		return sum;
	}

	/**
	 * Computes the aggregate sum of {@link Passenger}s in the underlying
	 * collections
	 * 
	 * @return Returns the amount of {@link Passenger}s in the underlying
	 *         collections.
	 */
	public int getPassengerCount()
	{
		int sum = 0;
		for (Customer customer : customers)
			sum += customer.getPassengerCount();

		return sum;
	}

	public void addCustomer(Customer customer)
	{
		customers.add(customer);
	}

	/**
	 * Finds the first {@link Customer} for which the name and phone number are
	 * equal to the passed arguments.
	 * 
	 * @param name The name of the {@link Customer}.
	 * @param phoneNumber The phone number of the {@link Customer}.
	 * @return Returns the first {@link Customer} in the collection for which the name and phone number are equal.
	 */
	public Customer findCustomer(String name, int phoneNumber)
	{
		for (Customer collectionCustomer : customers)
			if (collectionCustomer.getName().equals(name) && phoneNumber == collectionCustomer.getPhoneNumber())
				return collectionCustomer;

		return null;
	}

	public void removeCustomer(String name, int phoneNumber)
	{
		removeCustomer(findCustomer(name, phoneNumber));
	}

	public void removeCustomer(Customer customer)
	{
		customers.remove(customer);
	}

	public int getNumberOfCustomers()
	{
		return customers.size();
	}

	/**
	 * Returns the first {@link Customer} from the underlying collection for which the Customer.equals() method returns true.
	 * @param customer The {@link Customer} to search for.
	 * @return Returns the first {@link Customer}.
	 */
	public Customer getCustomer(Customer customer)
	{
		for (int i = 0; i < customers.size(); i++)
		{
			if (customers.get(i).equals(customer))
			{
				return customers.get(i);
			}
		}
		return null;
	}

	public ArrayList<Customer> getAllCustomers()
	{
		return customers;
	}
}
