package frameworkUtils;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc

/**
 * The Class Log.
 * @author PIPULPANT
 */
public class Log {

    /** The Log. */
    //Initialize Log4j instance
    private static Logger Log = Logger.getLogger(Log.class.getName());

    /**
     * Start log.
     *
     * @param testClassName the test class name
     */
    //We can use it when starting tests
    public static void startLog (String testClassName){
        Log.info("Test is Starting...");
    }

    /**
     * End log function.
     *
     * @param testClassName the test class name
     */
    //We can use it when ending tests
    public static void endLog (String testClassName){
        Log.info("Test is Ending...");
    }

    /**
     * Info Log function.
     *
     * @param message the message
     */
    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }

}
