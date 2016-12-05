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
	public Customer addCustomer(String name, int phoneNumber)
	{
		Customer temp = new Customer(name, phoneNumber);
		addCustomer(temp);
		return temp;		
	}
	public void addCustomer(Customer customer)
	{
		customers.add(customer);
	}
	public Customer findCustomer(String name, int phoneNumber)
	{
		for(Customer collectionCustomer : customers)
			if(collectionCustomer.getName().equals(name) && phoneNumber == collectionCustomer.getPhoneNumber())
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
	
	public Customer getCustomer(Customer customer) {
	   for (int i = 0; i < customers.size(); i++)
      {
         if(customers.get(i).equals(customer)) {
            return customers.get(i);
         }
      }
	   return null;
	}
	
	public Customer getCustomerByName(String name) {
	   for (int i = 0; i < customers.size(); i++)
      {
         if(customers.get(i).getName().equals(name)) {
            return customers.get(i);
         }
      }
	   return null;
	}
	
	public ArrayList<Customer> getAllCustomers() {
	   return customers;
	}
}
