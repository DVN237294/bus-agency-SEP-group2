import java.util.Date;


public class Passenger extends Customer
{
	private Date birthday;
	private String email;
	
	public Passenger(String name, int phoneNumber, Date birthday)
	{
		super(name,  phoneNumber);
		this.birthday = birthday;
		this.email = null;
	}
	

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Date getBirthday()
	{
		return birthday;
	}

}
