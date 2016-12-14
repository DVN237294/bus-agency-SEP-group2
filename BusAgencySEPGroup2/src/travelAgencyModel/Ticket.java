package travelAgencyModel;
import java.io.Serializable;


public class Ticket implements Serializable
{
	private double price;
	private Passenger owner;
	
	public Ticket(Passenger owner, double price)
	{
		this.price = price;
		this.owner = owner;
	}
	
	public double getPrice() {
	   return price;
	}
	
	public Passenger getOwner() {
	   return owner;
	}
	
}
