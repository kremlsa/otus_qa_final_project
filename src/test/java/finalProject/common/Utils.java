package finalProject.common;

import finalProject.pages.Speaker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;


/**
 * Класс для вспомогательных методов
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Utils {

    /**
     * Метод для парсинга даты конца периода
     *
     * @param date дата в текстовом представлении String
     * @return дата LocalDate
     */
    public static LocalDate parseEndDate(String date) {
        String[] parseDate = date.split(" ");
        int year = Integer.parseInt(parseDate[parseDate.length - 1]);
        int month = Utils.parseMonth(parseDate[parseDate.length - 2]);
        int day = Integer.parseInt(parseDate[parseDate.length - 3]);
        return LocalDate.of(year, month, day);
    }

    /**
     * Метод для парсинга даты начала периода
     *
     * @param date дата в текстовом представлении String
     * @return дата LocalDate
     */
    public static LocalDate parseStartDate(String date) {
        String[] parseDate = date.split(" ");
        int year = Integer.parseInt(parseDate[parseDate.length - 1]);
        int month = Utils.parseMonth(parseDate[1]);
        int day = Integer.parseInt(parseDate[0]);
        return LocalDate.of(year, month, day);
    }

    /**
     * метод для проверки вхождения диапазона дат в текущий период или после него
     *
     * @param date диапазон дат в текстовом представлении String
     * @return результат проверки boolean
     */
    public static boolean dateInRangeOrBefore(String date) {
        LocalDate startDate = Utils.parseStartDate(date);
        LocalDate endDate = Utils.parseEndDate(date);
        LocalDate now = LocalDate.now();
        //Проверяем что дата в карточке в пределах диапазона или позже
        if (now.isBefore(startDate) || now.isAfter(endDate)) {
            return now.isBefore(endDate) && now.isBefore(startDate);
        }
        return  true;
    }

    /**
     * метод для сопоставления месяца из текстового вида в число
     *
     * @param mon месяц в текстовом представлении String
     * @return цифровое представление месяца int
     */
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

    /**
     * Метод возвращающий текстовое значение элемента
     * с указанным локатором в элементе или Not defined
     *
     * @param element элемент WebElement
     * @param locator локатор элемента By
     * @return текст элемента String
     */
    public static String textOrNotDefined(WebElement element, By locator) {
        if ($(locator).exists()) {
            return element.findElement(locator).getText();
        } else {
            return "Not defined";
        }
    }

    /**
     * Метод возвращающий текстовое значение элементов
     * из списка с указанным локатором в элементе или Not defined
     *
     * @param element элемент WebElement
     * @param locator локатор элемента By
     * @return текст элемента String
     */
    public static List<Speaker> speakersOrNotDefined(WebElement element, By locator) {
        List<Speaker> results = new ArrayList<>();
        if ($(locator).exists()) {
            List<WebElement> elements = element.findElements(locator);
            for (WebElement el : elements) {
                results.add(Speaker.parseSpeaker(el));
            }

        }
        return results;
    }
}
