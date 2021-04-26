package wtf.actions;

/**
 * Класс для действий связанных с логированием событий
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
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
