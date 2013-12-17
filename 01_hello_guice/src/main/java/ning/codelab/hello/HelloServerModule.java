package ning.codelab.hello;

import ning.codelab.hello.datetime.JodaDateTimeConverter;
import ning.configamajig.v1.ConfigModule;

import com.google.inject.AbstractModule;

/**
 * This is the root module for the Hello server. It will install other modules
 * and configure what is needed for the hello core. See
 * <a href=http://code.google.com/p/google-guice/>http://code.google.com/p/google-guice/</a>
 * for more information on Google Guice.
 */
public class HelloServerModule extends AbstractModule
{
    protected void configure()
    {
        /*
         * Install the Configamajig module which populates objects using
         * System properties (or defaults provided in the annotations).
         */
        install(new ConfigModule().addConverter(JodaDateTimeConverter.class));

        /*
         * The config class works with the ConfigModule to convert system
         * properties in java objects via Configamajig
         * (http://git.source.ninginc.com/ning-libs-configamajig.git).
         * The "EagerSingleton" just tells Guice "do this NOW" (as opposed to
         * later in the request cycle).
         */
        bind(MyConfig.class).toProvider(ConfigModule.provide(MyConfig.class)).asEagerSingleton();

        /*
         * Bind a HelloResource (to an instance that is created by Guice).
         */
        bind(HelloResource.class).asEagerSingleton();
    }
}
