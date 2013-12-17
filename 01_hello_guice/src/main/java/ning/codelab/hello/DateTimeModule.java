package ning.codelab.hello;

import org.joda.time.format.DateTimeFormatter;

import com.google.inject.Binder;
import com.google.inject.Module;

public class DateTimeModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(DateTimeFormatter.class).toProvider(DateTimeFormatProvider.class).asEagerSingleton();
	}

}
