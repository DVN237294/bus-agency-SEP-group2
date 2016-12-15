package travelAgencyModel;

import java.io.Serializable;

/**
 * Chauffeur class containing basic information about a chauffeur, such as name,
 * an ID, phone number, email and address. Implements {@link Serializable}
 * 
 * @author DVN
 *
 */
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

	/**
	 * Three parameter constructor
	 * 
	 * @param firstName
	 *            The first name (given name) of the chauffeur.
	 * @param lastName
	 *            The last name (surname) of the chauffeur
	 * @param chauffeurID
	 *            The unique ID of this chauffeur.
	 */
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

	/**
	 * Determines if this {@link Chauffeur} has a preference for driving the
	 * specified {@link Bus}
	 * 
	 * @param bus The {@link Bus} which the {@link Chauffeur} might have a preference for.
	 * @return Returns true if the {@link Chauffeur} has a preference for this {@link Bus}.
	 */
	public boolean hasBusPreference(Bus bus)
	{
		for (Bus busPref : this.busPreferences)
			if (busPref.equals(bus))
				return true;

		return false;
	}
	/**
	 * Determines if this {@link Chauffeur} has a preference for driving to
	 * specified destination.
	 * 
	 * @param destination The destination which the {@link Chauffeur} might have a preference for.
	 * @return Returns true if the {@link Chauffeur} has a preference for this destination.
	 */
	public boolean hasDestinationPreference(String destination)
	{
		for (String destPref : this.destinationPreferences)
			if (destPref.equals(destination))
				return true;

		return false;
	}

	/**
	 * Returns a string representation of this {@link Chauffeur}.
	 *
	 */
	@Override
	public String toString()
	{
		if (lastName == null && chauffeurID == Integer.MIN_VALUE)
			return firstName;

		return lastName + ", " + firstName + " (" + chauffeurID + ")";
	}

}
