package test.ning.codelab.hello;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ning.codelab.hello.HelloResource;
import ning.codelab.hello.HelloServerModule;
import ning.codelab.hello.datetime.DateTimeModule;

/**
 * HelloResource TestNG module.
 */
public class HelloResourceTest {
	
	private static final String XN_CURRENT_TIME_PROPERTY_KEY = "xn.current.time";
	private static final String XN_HELLO_MESSAGE_PROPERTY_KEY = "xn.hello.message";

	private HelloResource useGuiceToInstantiateTheHelloResource() {
		Injector injector = Guice.createInjector(new HelloServerModule(),
				new DateTimeModule());
		HelloResource theHello = injector.getInstance(HelloResource.class);
		return theHello;
	}
	
    @BeforeMethod
    public void setUp()
    {
        System.clearProperty(XN_HELLO_MESSAGE_PROPERTY_KEY);
        System.clearProperty(XN_CURRENT_TIME_PROPERTY_KEY);
    }

	@Test
	public void testHelloDefault() {
		HelloResource theHello = useGuiceToInstantiateTheHelloResource();
		String message = theHello.getMessage();
		Assert.assertNotNull(message);
		assert message.startsWith("hello, world");
	}

	@Test
	public void testHelloSystemProperty() {
		String helloPropertyName = XN_HELLO_MESSAGE_PROPERTY_KEY; // see MyConfig class
		String emergencyBroadcastSystem = "this is only a test";
		System.setProperty(helloPropertyName, emergencyBroadcastSystem);

		HelloResource theHello = useGuiceToInstantiateTheHelloResource();

		String message = theHello.getMessage();
		Assert.assertNotNull(message);
		assert message.startsWith(emergencyBroadcastSystem);
	}

	@Test
	public void testHelloMessageInMorning() {
		String helloPropertyName = XN_HELLO_MESSAGE_PROPERTY_KEY;
		String helloMessage = "Hello world";
		System.setProperty(helloPropertyName, helloMessage);

		String timePropertyName = XN_CURRENT_TIME_PROPERTY_KEY;
		String currentTime = "08:00";
		System.setProperty(timePropertyName, currentTime);

		HelloResource theHello = useGuiceToInstantiateTheHelloResource();

		String message = theHello.getMessage();
		Assert.assertNotNull(message);
		assert "Hello world good morning @ 08:00".equals(message);
	}

	@Test
	public void testHelloMessageAtNoon() {
		String helloPropertyName = XN_HELLO_MESSAGE_PROPERTY_KEY;
		String helloMessage = "Hello world";
		System.setProperty(helloPropertyName, helloMessage);

		String timePropertyName = XN_CURRENT_TIME_PROPERTY_KEY;
		String currentTime = "12:00";
		System.setProperty(timePropertyName, currentTime);

		HelloResource theHello = useGuiceToInstantiateTheHelloResource();

		String message = theHello.getMessage();
		Assert.assertNotNull(message);
		assert "Hello world good afternoon @ 12:00".equals(message);
	}

	@Test
	public void testHelloMessageInAfterNoon() {
		String helloPropertyName = XN_HELLO_MESSAGE_PROPERTY_KEY;
		String helloMessage = "Hello world";
		System.setProperty(helloPropertyName, helloMessage);

		String timePropertyName = XN_CURRENT_TIME_PROPERTY_KEY;
		String currentTime = "15:00";
		System.setProperty(timePropertyName, currentTime);

		HelloResource theHello = useGuiceToInstantiateTheHelloResource();

		String message = theHello.getMessage();
		Assert.assertNotNull(message);
		assert "Hello world good afternoon @ 15:00".equals(message);
	}

	@Test
	public void testHelloMessageInEvening() {
		String helloPropertyName = XN_HELLO_MESSAGE_PROPERTY_KEY;
		String helloMessage = "Hello world";
		System.setProperty(helloPropertyName, helloMessage);

		String timePropertyName = XN_CURRENT_TIME_PROPERTY_KEY;
		String currentTime = "19:00";
		System.setProperty(timePropertyName, currentTime);

		HelloResource theHello = useGuiceToInstantiateTheHelloResource();

		String message = theHello.getMessage();
		Assert.assertNotNull(message);
		assert "Hello world good evening @ 19:00".equals(message);
	}

	@Test
	public void testHelloMessageAtNight() {
		String helloPropertyName = XN_HELLO_MESSAGE_PROPERTY_KEY;
		String helloMessage = "Hello world";
		System.setProperty(helloPropertyName, helloMessage);

		String timePropertyName = XN_CURRENT_TIME_PROPERTY_KEY;
		String currentTime = "22:00";
		System.setProperty(timePropertyName, currentTime);

		HelloResource theHello = useGuiceToInstantiateTheHelloResource();

		String message = theHello.getMessage();
		Assert.assertNotNull(message);
		assert "Hello world good night @ 22:00".equals(message);
	}

}
