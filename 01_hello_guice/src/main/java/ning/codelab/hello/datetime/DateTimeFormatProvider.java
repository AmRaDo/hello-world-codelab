package ning.codelab.hello.datetime;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.inject.Provider;

public class DateTimeFormatProvider implements Provider<DateTimeFormatter> {

	@Override
	public DateTimeFormatter get() {
		return DateTimeFormat.forPattern("HH:mm");
	}

}
