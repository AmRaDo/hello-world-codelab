package ning.codelab.hello;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import com.google.inject.Inject;

/**
 * A small class that implements a "message service." For simplification, the
 * service delegates to the message configured by the configuration object.
 * In the future, more complex logic may be enabled.
 */
public class HelloResource
{
    private final MyConfig config;
    private final DateTimeFormatter fmt;


    @Inject
    public HelloResource(MyConfig config, DateTimeFormatter fmt)
    {
        this.config = config;
        this.fmt = fmt;
    }

    /**
     * The "message service" implementation.
     */
    public String getMessage()
    {
    	DateTime currentTime = config.getCurrentTime();
    	
		String dateFormat = fmt.print(currentTime);
    	
        return config.getMessage() + getWishMessage(currentTime.getHourOfDay()) + " @ " + dateFormat ;
    }
    
    private String getWishMessage(int hourOfDay){
    	if(hourOfDay < 12)
    		return " good morning";
    	if(hourOfDay < 16)
    		return " good afternoon";
    	if(hourOfDay < 20)
    		return " good evening";
    	return " good night";
    }
}
