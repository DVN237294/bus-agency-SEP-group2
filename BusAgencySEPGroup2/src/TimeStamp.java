import java.time.LocalDateTime;
import java.time.ZoneId;


public abstract class TimeStamp
{
	public static long unixTime(LocalDateTime time)
	{
		return time.atZone(ZoneId.systemDefault()).toEpochSecond();
	}
}
