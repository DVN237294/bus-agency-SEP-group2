import java.time.LocalDateTime;
import java.time.ZoneId;


public abstract class Time
{
	public static long unixTime(LocalDateTime time)
	{
		return time.atZone(ZoneId.systemDefault()).toEpochSecond();
	}
	public static String readableDate(LocalDateTime time)
	{
		return time.getDayOfMonth() + ". " + " of " + time.getMonth().name().toLowerCase() + " " + time.getYear() + " at " + doubleDigits(time.getHour()) + ":" + doubleDigits(time.getMinute());
		
	}
	private static String doubleDigits(int number)
	{
		if(number < 9 && number > 0)
			return "0" + Integer.toString(number);
		
		return Integer.toString(number);
	}
}
