# WTF - Web Testing Framework
## автоматизация тестирования веб приложений

## Инструкция

1. Скачать проект с https://github.com/kremlsa/otus_qa_final_project
2. Скопировать package - "wtf" в "src/main/java/*" проекта
3. Создать класс Hooks для выполнения шагов до и после сценариев, тестов
   пример для Cucumber:
***   
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import org.apache.logging.log4j.Logger;
import wtf.actions.Base;


import static wtf.cfg.Cfg.setBrowserConfiguration;

  public class Hooks {

  public Logger logger = Base.logger;

  @Before
  public void setUp() {
  setBrowserConfiguration();
  logger.info("Открыт браузер");
  }

  @AfterStep(value="@Selenide")
  private void setDownTest() {
  Selenide.clearBrowserLocalStorage();
  logger.info("Чистим браузер");
  Selenide.closeWebDriver();
  logger.info("Закрыт браузер");
  }

  @After
  public void closeSelenide() {
  Selenide.closeWebDriver();
  logger.info("Закрыт браузер");
  }

}
***
4. Разместить тесты, POM, features, steps и прочее в "src/test/*" проекта
5. В случае POM наследовать страницы от суперкласса в  "src/main/java/pom/BasePage"
6. Создать при необходимости класс фикстуру по аналогии с п.5, где инициализировать
   основные классы фреймворка
7. Из корня фреймворка скопировать pom.xml в проект (объединить dependencies)
   и выполнить импорт классов при необходимости
8. Параметры для запуска через maven (по умолчанию заданы в классе "src/main/java/cfg/Cfg")

  разрешение браузера:
  -DbrowserSize
  удалённый вебдрайвер:
  -DremoteURL
  выбор браузера:
  -Dbrowser





Для CI/CD использовать JenkinsFile
