package finalProject.pages;

import org.openqa.selenium.WebElement;

/**
 * Класс для представления списка докладчиков в карточках
 * в качестве объекта
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Speaker {

    private String name = "Not defined";
    private String title = "Not defined";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //метод для парсинга докладчиков из элемента DOM модели сайта
    public static Speaker parseSpeaker(WebElement element) {
        Speaker result = new Speaker();
        result.setName(element.getAttribute("data-name"));
        result.setTitle(element.getAttribute("data-job-title"));
        return result;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.title + ". ";
    }
}