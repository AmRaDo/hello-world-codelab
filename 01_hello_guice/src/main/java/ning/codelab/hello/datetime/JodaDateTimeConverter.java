package ning.codelab.hello.datetime;

import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import ning.configamajig.v1.Converter;

/**
 * The Converter class which creates the DateTime instance based on input String.
 * Note: The string is expected in the 'HH:mm' format.
 * If input String is null or empty, the current system time in IST zone will be
 * used to create DateTime object.
 */
public class JodaDateTimeConverter implements Converter<DateTime> {

	@Override
	public DateTime convert(String paramString) {
		
		if(paramString == null || paramString.trim().isEmpty())
			return DateTime.now(DateTimeZone.forTimeZone(TimeZone.getTimeZone("IST")));
		return  DateTimeFormat.forPattern("HH:mm").parseDateTime(paramString);
	}

}
