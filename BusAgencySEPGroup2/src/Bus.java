public class Bus
{
   private String make;
   private String model;
   private String licensePlate;
   private String color;
   private String[] specialFeatures;
   private int maxCapacity;

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

   public boolean equals(Bus other)
   {
      return other.getLicensePlate().equals(this.licensePlate);
   }
   
   @Override
   public String toString()
   {
	   if(model == null && licensePlate == null && maxCapacity == Integer.MIN_VALUE)
		   return make;
	   
      return make + ", " + model + ", " + licensePlate + ", " + maxCapacity;
   }

}
