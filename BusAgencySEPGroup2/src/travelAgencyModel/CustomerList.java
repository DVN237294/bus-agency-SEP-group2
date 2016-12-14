package travelAgencyModel;
import java.io.Serializable;
import java.util.ArrayList;

public class CustomerList implements Serializable
{
	private ArrayList<Customer> customers;

	public CustomerList()
	{
		this.customers = new ArrayList<Customer>();
	}

	public Customer addCustomer(String name, int phoneNumber)
	{
		Customer temp = new Customer(name, phoneNumber);
		addCustomer(temp);
		return temp;
	}

	public int getCustomerFrequency(Customer customer)
	{
		int sum = 0;
		for (Customer listCustomer : customers)
			if (listCustomer.equals(customer))
				sum++;

		return sum;
	}

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

	public Customer findCustomer(String name, int phoneNumber)
	{
		for (Customer collectionCustomer : customers)
			if (collectionCustomer.getName().equals(name) && phoneNumber == collectionCustomer.getPhoneNumber())
				return collectionCustomer;

		return null;
	}

	public void deleteCustomer(String name, int phoneNumber)
	{
		deleteCustomer(findCustomer(name, phoneNumber));
	}

	public void deleteCustomer(Customer customer)
	{
		customers.remove(customer);
	}


	public int getTotalNumberOfCustomers()
	{
		return customers.size();
	}

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

	public Customer getCustomerByName(String name)
	{
		for (int i = 0; i < customers.size(); i++)
		{
			if (customers.get(i).getName().equals(name))
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
