package test.ning.codelab.hello.mock;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

public class MockHttpHeaders implements HttpHeaders {

	private List<Locale> acceptablelanguages;
	@Override
	public List<Locale> getAcceptableLanguages() {
		return acceptablelanguages;
	}
	
	public void setAcceptableLanguages(Locale... langs){
		acceptablelanguages = Arrays.asList(langs);
	}

	@Override
	public List<MediaType> getAcceptableMediaTypes() {
		return null;
	}

	@Override
	public Map<String, Cookie> getCookies() {
		return null;
	}

	@Override
	public Locale getLanguage() {
		return null;
	}

	@Override
	public MediaType getMediaType() {
		return null;
	}

	@Override
	public List<String> getRequestHeader(String arg0) {
		return null;
	}

	@Override
	public MultivaluedMap<String, String> getRequestHeaders() {
		return null;
	}

}
