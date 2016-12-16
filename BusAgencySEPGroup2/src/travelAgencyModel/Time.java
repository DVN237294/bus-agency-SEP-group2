package travelAgencyModel;
import java.time.LocalDateTime;
import java.time.ZoneId;


/**
 * Abstract time class that proves some helper methods
 * @author DVN
 *
 */
public abstract class Time
{
	/**
	 * Computes the UNIX time (or Epoch time) of the specified {@link LocalDateTime}.
	 * Computes the number of seconds that has elapsed since Thursday 1. of January 1970 at 00:00:00 UTC.
	 * @param time The {@link LocalDateTime} to compute unix time from.
	 * @return Returns the amount of seconds since 01/01/1970 00:00:00 UTC.
	 */
	public static long unixTime(LocalDateTime time)
	{
		return time.atZone(ZoneId.systemDefault()).toEpochSecond();
	}
	/**
	 * Returns a more human readable string representation of the passed {@link LocalDateTime}.
	 * @param time The {@link LocalDateTime} from which to construct the {@link String}.
	 * @return Returns a human readable date and time {@link String}.
	 */
	public static String readableDate(LocalDateTime time)
	{
		return time.getDayOfMonth() + ". " + " of " + time.getMonth().name().toLowerCase() + " " + time.getYear() + " at " + doubleDigits(time.getHour()) + ":" + doubleDigits(time.getMinute());
		
	}

	/**
	 * Converts an integer between 0 and 9 to double digit {@link String} representation. I.e. 5 -> "05".
	 * Any number passed outside of this range is returned as a {@link String} representation.
	 * @param number The number to convert.
	 * @return Returns a double digit {@link String} representation of the passed integer.
	 */
	public static String doubleDigits(int number)
	{
		if(number < 10 && number >= 0)
			return "0" + Integer.toString(number);
		
		return Integer.toString(number);
	}
}
