import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class InventoryLogger {

    private static final Logger logger =
            Logger.getLogger(InventoryLogger.class.getName());

    static {

        try {
            // Creates inventory.log in the project directory
            FileHandler fileHandler = new FileHandler("inventory.log", true);

            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);

            logger.setUseParentHandlers(false);

        } catch (IOException e) {
            System.out.println("Logger initialization failed.");
            e.printStackTrace();
        }
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarning(String message) {
        logger.warning(message);
    }

    public static void logError(String message) {
        logger.severe(message);
    }
}