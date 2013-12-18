package ning.codelab.hello.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HelloMessage")
public class HelloXMLMessage {
	String message;

	@XmlElement
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage(){
		return this.message;
	}
}