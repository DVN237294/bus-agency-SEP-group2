import java.util.ArrayList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class CustomerList
{
	private ArrayList<Customer> customers;
	
	public CustomerList()
	{
		this.customers = new ArrayList<Customer>();
	}
	
	public void addCustomer(Customer customer)
	{
		customers.add(customer);
	}
	
	public boolean isFrequentCustomer(Customer customer)
	{
		throw new NotImplementedException(); //also, this might need to be rephrased / reworked
	}
	
	public void setFrequentCustomerThreshhold(int reservationCount)
	{
		throw new NotImplementedException(); //also, this might need to be rephrased / reworked
	}
}
