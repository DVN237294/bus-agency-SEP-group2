package travelAgencyModel;
import java.io.Serializable;


/** 
 * Simple implementation of an address
 * Implements {@link Serializable}
 * @author DVN
 *
 */
public class Address implements Serializable
{
	private int zipCode;
	private String streetName;
	private int streetHouseNumber;
	private int doorNumber;
	private int floorNumber;
	
	/**
	 * Three parameter contructor which accepts a street name, a street house number and a zipcode
	 * @param streetName The name of the street.
	 * @param streetHouseNumber The house number on the specified street.
	 * @param zipCode The zipcode or postal code.
	 */
	public Address(String streetName, int streetHouseNumber, int zipCode)
	{
		this.streetName = streetName;
		this.streetHouseNumber = streetHouseNumber;
		this.zipCode = zipCode;
		this.doorNumber = Integer.MIN_VALUE;
		this.floorNumber = Integer.MIN_VALUE;
	}

	public int getZipCode()
	{
		return zipCode ;
	}

	public void setDoorNumber(int doorNumber)
	{
		this.doorNumber = doorNumber;
	}

	public void setFloorNumber(int floorNumber)
	{
		this.floorNumber = floorNumber;
	}

	public String getStreetName()
	{
		return streetName;
	}

	public int getStreetHouseNumber()
	{
		return streetHouseNumber;
	}

	public int getDoorNumber()
	{
		return doorNumber;
	}

	public int getFloorNumber()
	{
		return floorNumber;
	}
}
