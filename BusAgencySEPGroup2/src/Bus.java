
public class Bus
{
	private String make;
	private String model;
	private String licensePlate;
	private String color;
	private String[] specialFeatures;
	private int maxCapacity;
	
	public Bus(String make, String model, String licensePlate)
	{
		this.make = make;
		this.model = model;
		this.licensePlate = licensePlate;
		this.color = null;
		this.specialFeatures = null;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String[] getSpecialFeatures()
	{
		return specialFeatures;
	}

	public void setSpecialFeatures(String[] specialFeatures)
	{
		this.specialFeatures = specialFeatures;
	}

	public String getMake()
	{
		return make;
	}

	public String getModel()
	{
		return model;
	}

	public String getLicensePlate()
	{
		return licensePlate;
	}
	
	public boolean equals(Bus other)
	{
		return other.getLicensePlate().equals(this.licensePlate);
	}
	
}
