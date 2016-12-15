package travelAgencyModel;

import java.io.Serializable;

/**
 * Class containing the most basic information about a bus, such as make, model,
 * license plate and passenger capacity.
 * Implements {@link Serializable}
 * @author Afonso
 *
 */
public class Bus implements Serializable
{
	private String make;
	private String model;
	private String licensePlate;
	private String color;
	private String[] specialFeatures;
	private int maxCapacity;

	/**
	 * Four argument constructor which accepts the make, model, license plate
	 * and passenger capacity of the bus.
	 * 
	 * @param make
	 *            The manufacturer of the bus.
	 * @param model
	 *            The model name.
	 * @param licensePlate
	 *            The vehicle license plate.
	 * @param maxCapacity
	 *            The passenger capacity of the bus.
	 */
	public Bus(String make, String model, String licensePlate, int maxCapacity)
	{
		this.make = make;
		this.model = model;
		this.licensePlate = licensePlate;
		this.maxCapacity = maxCapacity;
		this.color = null;
		this.specialFeatures = null;
	}

	public int getMaxCapacity()
	{
		return maxCapacity;
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

	/**
	 * Equals method that determines if the two instances of {@link Bus} are
	 * perceived to be equal.
	 * 
	 * @param other
	 *            The other {@link Bus} to compare against.
	 * @return Returns true if both of the buses {@link licensePlate} are not
	 *         {@link null} and their {@link licensePlate} are equal. (equals
	 *         method returns true)
	 */
	public boolean equals(Bus other)
	{
		return other.getLicensePlate() != null && this.licensePlate != null && other.getLicensePlate().equals(this.licensePlate);
	}

	/**
	 * ToString method that returns a concatenation of {@link make},
	 * {@link model}, {@link licensePlate} and {@link maxCapacity}. In the
	 * special condition that {@link model}, {@link licensePlate} and
	 * {@link maxCapacity} are null, {@link make} is returned.
	 */
	public String toString()
	{
		if (model == null && licensePlate == null && maxCapacity == Integer.MIN_VALUE)
			return make;

		return make + ", " + model + ", " + licensePlate + ", " + maxCapacity;
	}

}
