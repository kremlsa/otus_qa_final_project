package finalProject.stepdefs;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;


public class DummySteps {

    @Value("${dummy}")
    private String dummyValue;


    @Дано("пустой контекст")
    public void dummyFirst(){
        System.out.println("1");
    }

    @Когда("читаем переменную в файле properties")
    public void dummySecond() {
        System.out.println("1");
    }

    @То("он равняется {string}")
    public void dummyThird(String value) {
        Assert.assertEquals(value, dummyValue);
    }

    @Дано("Selenide: Пользователь переходит на вкладку Talks Library")
    public DummySteps openTalksLibrary() {
        Selenide.open("https://events.epam.com");
        $("a.nav-link[href*='/video']").click();
        return this;
    }

    @Когда("Selenide: Пользователь вводит ключевое слово QA в поле поиска")
    public void selenideSearchQA() {
        $("input.evnt-search[type=text]").sendKeys("QA", Keys.ENTER);
    }

    @То("Selenide: На странице отображаются доклады, содержащие в названии ключевое слово поиска")
    public void selenideНаСтраницеОтображаютсяДокладыСодержащиеВНазванииКлючевоеСловоПоиска() {
    }
}
