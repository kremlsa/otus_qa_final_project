package finalProject.pages;

import org.openqa.selenium.WebElement;

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

    public static Speaker parseSpeaker(WebElement element) {
        Speaker result = new Speaker();

        result.setName(element.getAttribute("data-name"));
        result.setTitle(element.getAttribute("data-job-title"));

        return result;
    }
}