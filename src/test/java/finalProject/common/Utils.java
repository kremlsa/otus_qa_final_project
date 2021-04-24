package finalProject.common;

import java.time.LocalDate;

/**
 * Класс для вспомогательных методов
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Utils {

    //Метод для парсинга даты конца периода
    public static LocalDate parseEndDate(String date) {
        String[] parseDate = date.split(" ");
        int year = Integer.parseInt(parseDate[parseDate.length - 1]);
        int month = Utils.parseMonth(parseDate[parseDate.length - 2]);
        int day = Integer.parseInt(parseDate[parseDate.length - 3]);
        return LocalDate.of(year, month, day);
    }

    //Метод для парсинга даты начала периода
    public static LocalDate parseStartDate(String date) {
        String[] parseDate = date.split(" ");
        int year = Integer.parseInt(parseDate[parseDate.length - 1]);
        int month = Utils.parseMonth(parseDate[1]);
        int day = Integer.parseInt(parseDate[0]);
        return LocalDate.of(year, month, day);
    }

    //Вспомогательный метод для сопоставления месяца из текстового вида в число
    public static int parseMonth(String mon) {
        switch (mon.toLowerCase()) {
            case "jan":
                return 1;
            case "feb":
                return 2;
            case "mar":
                return 3;
            case "apr":
                return 4;
            case "may":
                return 5;
            case "jun":
                return 6;
            case "jul":
                return 7;
            case "aug":
                return 8;
            case "sep":
                return 9;
            case "oct":
                return 10;
            case "nov":
                return 11;
            case "dec":
                return 12;
        }
        return 0;
    }
}
