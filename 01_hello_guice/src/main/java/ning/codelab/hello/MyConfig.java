package ning.codelab.hello;

import org.joda.time.DateTime;

import ning.configamajig.v1.Property;

/**
 * Configamajig configuration class. The method bodies are ignored at runtime.
 * We make it abstract to help folks not accidentally instantiate it. See
 * {@link HelloServerModule#configure} body for using Configamajig
 * to instantiate this.
 */
public abstract class MyConfig
{
    @Property(value = "xn.hello.message", missing = "hello, world")
    public abstract String getMessage();
    
    @Property(value= "xn.current.time", missing = "" )
    public abstract DateTime getCurrentTime();
}
