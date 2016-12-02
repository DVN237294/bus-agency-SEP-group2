import java.util.ArrayList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class CustomerList
{
	private ArrayList<Customer> customers;
	private int frequentCustomerThreshhold;
	
	public CustomerList()
	{
		this.customers = new ArrayList<Customer>();
		frequentCustomerThreshhold = Integer.MAX_VALUE;
	}
	
	public void addCustomer(Customer customer)
	{
		customers.add(customer);
	}
	
	public void deleteCustomer(Customer customer)
	{
	   customers.remove(customer);
	}
	
	public boolean isFrequentCustomer(Customer customer)
	{
		return customer.getReservationCount() >= frequentCustomerThreshhold;
	}
	
	public void setFrequentCustomerThreshhold(int reservationCount)
	{
		frequentCustomerThreshhold = reservationCount;
	}
	
	public int getTotalNumberOfCustomers()
	{
	   return customers.size();
	}
}
