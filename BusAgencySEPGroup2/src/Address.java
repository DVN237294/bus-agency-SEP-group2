
public class Address
{
	private int zipCode;
	private String streetName;
	private int streetHouseNumber;
	private int doorNumber;
	private int floorNumber;
	
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
		return zipCode;
	}

	public void setDoorNumber(int doorNumber)
	{
		if(this.doorNumber == Integer.MIN_VALUE)
			throw new IllegalStateException("Invalid or unset door number");
		this.doorNumber = doorNumber;
	}

	public void setFloorNumber(int floorNumber)
	{
		if(this.floorNumber == Integer.MIN_VALUE)
			throw new IllegalStateException("Invalid or unset floor number");
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
