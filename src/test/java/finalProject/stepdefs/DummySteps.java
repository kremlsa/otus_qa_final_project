package finalProject.stepdefs;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Value;


public class DummySteps extends BaseStep{

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
        Assertions.assertEquals(value, dummyValue);
    }
}
