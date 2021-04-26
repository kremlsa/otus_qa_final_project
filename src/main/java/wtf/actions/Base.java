package wtf.actions;

import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Base {
    public static final Logger logger = LogManager.getLogger();
    // Время ожидания элементов
    public int wait = 10000;

    /**
     * Метод для записи в Лог титула текущей страницы
     *
     */
    public void logTitle() {
        logger.info("Открываем раздел - " + Selenide.title());
    }

    public void log(String message) {
        logger.info(message);
    }
}
