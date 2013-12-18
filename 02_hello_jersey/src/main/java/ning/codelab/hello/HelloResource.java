package ning.codelab.hello;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import ning.codelab.hello.xml.HelloXMLMessage;

import com.google.inject.Inject;

/**
 * Initially, a small class that implements a "message service." For
 * simplification, the service delegates to a message configured by the
 * configuration object. In the future, more complex logic may be enabled.
 * 
 * Now, we extend the HelloResource class to be a Jersey endpoint.
 * This is a jax-rs resource class. The @Path annotation says it
 * matches / (in the sense of http://localhost:9999/ )
 *
 * Different methods are annotated to handle different methods (only GET here),
 * and different Accept header media type thingamabobs.
 *
 * See http://jersey.dev.java.net/ for Jersey documentation
 */
@Path("/")
public class HelloResource
{
    private final MyConfig config;
    @Context
    private HttpHeaders headers;

    public HttpHeaders getHeaders() {
		return headers;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	@Inject
    public HelloResource(MyConfig config)
    {
        this.config = config;
    }

    @GET
    @Path("plaintext")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPlainText() {
        return this.getMessage();
    }
    
    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloMessage getJson() {
        /**
         * an inner class is used here to put an envelope around the
         * message for JSON formatting, if we just returned the message the
         * response would be:
         * 
         * "hello, world"
         *
         * with the object envelope it becomes:
         *
         * {"message":"hello, world"}
         *
         * which may or may not matter, but we want something like that often
         * enough that an example is reasonable to provide.
         */
        return new HelloMessage() {
            public String getMessage() {
                return HelloResource.this.getMessage();
            }
        };
    }
    
    @GET
    @Path("mime")
    @Produces(MediaType.APPLICATION_XML)
    public HelloXMLMessage getMIME() {
    	HelloXMLMessage helloXML = new HelloXMLMessage();
    	helloXML.setMessage(getMessage());
    	return helloXML;
    }
    
    /**
     * The original "message service" implementation.
     * @param hh 
     */
    public String getMessage()
    {
    	if(headers != null){
    		List<Locale> acceptableLanguages = headers.getAcceptableLanguages();
    		if(acceptableLanguages != null){
    			return Arrays.toString(acceptableLanguages.toArray());
    		}
    	}
    	return config.getMessage();
    }
    
    /**
     * An inner class "response object" so that we can test more easily.
     */
    public abstract static class HelloMessage {
        public abstract String getMessage();
    }
    
}
