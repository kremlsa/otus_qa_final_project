package finalProject.pages;

import lombok.*;
import org.openqa.selenium.WebElement;

/**
 * Класс для представления списка докладчиков в карточках
 * в качестве объекта
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Speaker {

    private String name = "Not defined";
    private String title = "Not defined";

    /**
     * метод для парсинга докладчиков из элемента DOM модели сайта
     *
     * @param element элемент для парсинга WebElement
     * @return объект Speaker
     */
    public static Speaker parseSpeaker(WebElement element) {

         return Speaker
                .builder()
                .name(element.getAttribute("data-name"))
                .title(element.getAttribute("data-job-title"))
                .build();
    }

    @Override
    public String toString() {
        return this.name + " - " + this.title + ". ";
    }
}