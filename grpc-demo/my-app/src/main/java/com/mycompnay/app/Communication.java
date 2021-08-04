package com.mycompnay.app;

import com.google.inject.AbstractModule;
import org.graalvm.compiler.serviceprovider.ServiceProvider;
import com.google.inject.Provides;

import javax.inject.Inject;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

public class Communication {
    @Inject
    private Logger logger;

    @Inject
    private Comunicator communicator;
}

class GuiceDemo {
    @Qualifier
    @Retention(RUNTIME)
    @interface Message {}

    @Qualifier
    @Retention(RUNTIME)
    @interface Count {}

    static class DemoModule extends AbstractModule {
        @Provides
        @Count
        static Integer provideCount() {
            return 3;
        }

        @Provides
        @Message
        static String provideMessage() {
            return "hello world";
        }
    }
}