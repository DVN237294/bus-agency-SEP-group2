package travelAgencyModel;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Passenger class containing basic information about a passenger, such as name, phone number and birthday.
 * Implements {@link Serializable}.
 * @author DVN
 *
 */
public class Passenger implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Address address;
	private String name;
	private int phoneNumber;
	private LocalDateTime birthday;
	private String email;

	/**
	 * Three parameter constructor which accepts a name, phone number and the birthday of the passenger 
	 * @param name The name of the passenger.
	 * @param phoneNumber The phone number of the passenger.
	 * @param birthday The birthday of the passenger.
	 */
	public Passenger(String name, int phoneNumber, LocalDateTime birthday)
	{
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.email = null;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public Address getAddress()
	{
		return this.address;
	}

	public String getName()
	{
		return name;
	}

	public int getPhoneNumber()
	{
		return phoneNumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public LocalDateTime getBirthday()
	{
		return birthday;
	}

	/**
	 * Determines if this instances of {@link Passenger} is equal to the provided instance.
	 * Tests for equality by comparing name and phone number.
	 * @param other The instance of {@link Passenger} to compare for equality against.
	 * @return Returns true if the two instances of {@link Passenger} share the same name and phone number.
	 */
	public boolean equals(Passenger other)
	{
		return name.equals(other.getName()) && phoneNumber == other.getPhoneNumber();
	}
}
