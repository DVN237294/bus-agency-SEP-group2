package travelAgencyModel;
import java.io.Serializable;

/**
 * Ticket class which stores a ticket price and the {@link Passenger} owner of the ticket.
 * @author DVN
 *
 */
public class Ticket implements Serializable
{
	private static final long serialVersionUID = 1L;
	private double price;
	private Passenger owner;
	
	/**
	 * Two argument constructor accepting a {@link Passenger} as owner, and a price of the ticket.
	 * @param owner The owner of the {@link Ticket}.
	 * @param price The price of the {@link Ticket}.
	 */
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
