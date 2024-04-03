package logger;

public class App {
    /*
     * Let's use this for testing
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("App.main()");
        logger.log("some");
        logger.log("message");
        logger.log("should");
        logger.log("appear");
        logger.log("in");
        logger.log("the");
        logger.log("console");
        logger.flush();
    }
}
