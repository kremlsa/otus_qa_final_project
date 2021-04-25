package wtf.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseAction {
    public static final Logger logger = LogManager.getLogger();
    // Время ожидания элементов
    public int wait = 10000;
}
