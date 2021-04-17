package finalProject.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;

@Configuration
@ComponentScan("finalProject.pages")
@ComponentScan("finalProject.common")
@ComponentScan("finalProject.stepdefs")
@PropertySource("classpath:config.properties")
@PropertySource("classpath:mainPage.properties")
@PropertySource("classpath:eventsPage.properties")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)

public class Cfg {

}
