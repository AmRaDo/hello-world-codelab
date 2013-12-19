package test.ning.codelab.hello;

import java.util.Arrays;
import java.util.Locale;

import javax.ws.rs.core.HttpHeaders;

import ning.codelab.hello.HelloResource;
import ning.codelab.hello.HelloResource.HelloMessage;
import ning.codelab.hello.HelloServerModule;
import ning.codelab.hello.xml.HelloXMLMessage;

import org.easymock.EasyMock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * HelloResource TestNG module.
 */
public class HelloResourceJerseyTest
{
    /** see MyConfig class */
    private static final String XN_HELLO_MESSAGE_PROPERTY_KEY = "xn.hello.message";

    @BeforeMethod
    public void setUp()
    {
        System.clearProperty(XN_HELLO_MESSAGE_PROPERTY_KEY);
    }

    private HelloResource useGuiceToInstantiateTheHelloResource()
    {
        Injector injector = Guice.createInjector(new HelloServerModule());
        HelloResource theHello = injector.getInstance(HelloResource.class);

        return theHello;
    }

    @Test
    public void testHelloDefaultPlaintext()
    {
        HelloResource theHello = useGuiceToInstantiateTheHelloResource();
        assert "hello, world".equals(theHello.getPlainText());
    }

    @Test
    public void testHelloDefaultJson()
    {
        HelloResource theHello = useGuiceToInstantiateTheHelloResource();

        HelloMessage helloResult = theHello.getJson();
        assert "hello, world".equals(helloResult.getMessage());
    }
    
    @Test
    public void testHelloDefaultMime()
    {
    	 HelloResource theHello = useGuiceToInstantiateTheHelloResource();
    	 HelloXMLMessage mime = theHello.getMIME();
    	 assert "hello, world".equals(mime.getMessage());
    }
    
    @Test
    public void testHelloWithENJALocale()
    {
    	HelloResource theHello = useGuiceToInstantiateTheHelloResource();
    	HttpHeaders mockHeaders = EasyMock.createMock(HttpHeaders.class);
    	EasyMock.expect(mockHeaders.getAcceptableLanguages()).andReturn(Arrays.asList(Locale.ENGLISH, Locale.JAPANESE));
    	EasyMock.replay(mockHeaders);
/*    	MockHttpHeaders headers = new MockHttpHeaders();
    	headers.setAcceptableLanguages(Locale.ENGLISH, Locale.JAPANESE);*/
    	theHello.setHeaders(mockHeaders);
    	assert "[en, ja]".equals(theHello.getMessage());
    }
}
