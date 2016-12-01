
public class Ticket
{
	private double price;
	private Passenger owner;
	
	public Ticket(Passenger owner, double price)
	{
		this.price = price;
		this.owner = owner;
	}
	
	public double getPice() {
	   return price;
	}
	
	public Passenger getOwner() {
	   return owner;
	}
	
}
