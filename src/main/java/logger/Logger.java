package logger;

/**
 * Created by Kuba on 2018-01-28.
 */
public class Logger {

    private Logger() {
    }

    public static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(Logger.class);
    public static void debug (String message) {
        LOGGER.debug(message);
    }

    public static void info (String message) {
        LOGGER.info(message);
    }

    public static void warning (String message) {
        LOGGER.warn(message);
    }

    public static void error (String message) {
        LOGGER.error(message);
    }

    public static void fatal (String message) {
        LOGGER.fatal(message);
    }
}
