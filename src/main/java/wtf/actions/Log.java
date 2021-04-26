package wtf.actions;

public class Log extends Base {
    /**
     * Метод для записи сообщения с уровнем Info
     *
     */
    public static  void logInfo(String message) {
        logger.info(message);
    }

    /**
     * Метод для записи сообщения с уровнем Warn
     *
     */
    public static void logWarn(String message) {
        logger.warn(message);
    }

    /**
     * Метод для записи сообщения с уровнем Error
     *
     */
    public static  void logError(String message) {
        logger.error(message);
    }
}
