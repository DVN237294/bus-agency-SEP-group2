import java.io.Serializable;


public class Chauffeur implements Serializable
{
	private String firstName;
	private String lastName;
	private int chauffeurID;
	private String phoneNumber;
	private String email;
	private String[] destinationPreferences;
	private Bus[] busPreferences;
	private Address address;
	
	public Chauffeur(String firstName, String lastName, int chauffeurID)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.chauffeurID = chauffeurID;
		
		this.phoneNumber = null;
		this.email = null;
		this.destinationPreferences = null;
		this.busPreferences = null;
	}
	public Address getAddress()
	{
		return this.address;
	}
	public void setAddress(Address address)
	{
		this.address = address;
	}
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String[] getDestinationPreferences()
	{
		return destinationPreferences;
	}

	public void setDestinationPreferences(String[] destinationPreferences)
	{
		this.destinationPreferences = destinationPreferences;
	}

	public Bus[] getBusPreferences()
	{
		return busPreferences;
	}

	public void setBusPreferences(Bus[] busPreferences)
	{
		this.busPreferences = busPreferences;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public int getChauffeurID()
	{
		return chauffeurID;
	}
	
	public boolean equals(Chauffeur other)
	{
		return other.getChauffeurID() == this.chauffeurID;
	}
	
	public boolean hasBusPreference(Bus bus)
	{
		for(Bus busPref : this.busPreferences)
			if(busPref.equals(bus))
				return true;
		
		return false;
	}
	
	public boolean hasDestinationPreference(String destination)
	{
		for(String destPref : this.destinationPreferences)
			if(destPref.equals(destination))
				return true;
		
		return false;
	}
	@Override
	public String toString()
	{
		if(lastName == null && chauffeurID == Integer.MIN_VALUE)
			return firstName;
		
		return lastName +", " + firstName + " (" + chauffeurID + ")";
	}
	
}
