import java.time.LocalDateTime;

public class Passenger
{
	private String name;
	private int phoneNumber;
	private LocalDateTime birthday;
	private String email;
	
	public Passenger(String name, int phoneNumber, LocalDateTime birthday)
	{
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.email = null;
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
	
	public boolean equals(Passenger other)
	{
		return name.equals(other.getName()) && phoneNumber == other.getPhoneNumber();
	}
}
