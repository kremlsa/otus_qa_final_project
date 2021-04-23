package finalProject.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Класс конфигурации SpringBoot
 *
 * @author Alexander Kremlev
 * @version 1.0
 */
@Configuration
@ComponentScan("finalProject.pages")
@ComponentScan("finalProject.common")
@ComponentScan("finalProject.stepdefs")
@PropertySource("classpath:config.properties")
@PropertySource("classpath:mainPage.properties")
@PropertySource("classpath:eventsPage.properties")
@PropertySource("classpath:talkPage.properties")
@PropertySource("classpath:talkCardPage.properties")
@PropertySource("classpath:eventCard.properties")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)

public class Cfg {

}
