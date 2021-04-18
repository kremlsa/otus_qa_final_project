package finalProject.common;


import org.openqa.selenium.WebElement;

public class TalkCard {
    private String location = "Not defined";
    private String language = "Not defined";
    private String category = "Not defined";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
