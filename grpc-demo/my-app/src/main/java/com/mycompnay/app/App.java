package com.mycompnay.app;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger logger = LogManager.getLogger("HelloWorld");

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        logger.info("Hello, world!");

        // good
        logger.debug("Logging user {} with bir {}", "hello", "world");
    }
}
