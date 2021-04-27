package finalProject.pages;

import org.openqa.selenium.By;

import org.testng.Assert;
import wtf.pom.BasePage;
import wtf.uniloc.UniLoc;
import finalProject.common.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для описания страницы - Events
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class EpamEventsPage extends BasePage {

    //Локаторы
    private final String UPCOMING_EVENTS = "//span[contains(text(),'Upcoming events')]";
    private final String PAST_EVENTS = "//span[contains(text(),'Past Events')]";
    private final String CARD_BODY = "div.evnt-event-card";
    private final String EVENT_TITLE = "//div[@class='evnt-event-name']/h1/span";
    private List<EventCard> eventCards = new ArrayList<>();

    /**
     * Метод для открытия раздела upcoming events
     *
     */
    public void openUpcomingEvents() {
        //Запоминаем текст текущего элемента из списка тем и значение
        String elementText = find.locText(By.xpath(EVENT_TITLE));
        //Если раздел не активен, то перейти в него
        if (!find.isAtrContains(By.xpath(UPCOMING_EVENTS + "/.."),"class", "active")) {
            //Кликнуть элемент
            click.xpathLocator(UPCOMING_EVENTS + "/..")
                    .log("Переход в раздел Upcoming Events");
            //ждём пока прогрузится новый список тем
            wait.disappearText(By.xpath(EVENT_TITLE), elementText, 5000);
        }
    }

    /**
     * Метод для проверки появления карточек
     *
     * @return результат boolean
     */
    public boolean isCardApperance() {
        getAllCards();
        return find.isElementExists(By.cssSelector(CARD_BODY));
    }

    /**
     * Метод для получения значения счётчика карточек
     * @param buttonName имя кнопки для локатора String
     *
     * @return количество карточек в счётчике int
     */
    public int cardInCounter(String buttonName) {
        return Integer.parseInt(find.locText(UniLoc.xpathLocator(UniLoc.EVENTCOUNTER, buttonName)));
    }

    /**
     * Метод для возврата количества карточек в модели
     *
     * @return количество карточек в DOM int
     */
    public int cardExist() {
        return find.listLoc(By.cssSelector(CARD_BODY)).size();
    }


    /**
     * Метод для составления списка объектов - карточки мероприятий
     *
     */
    public void getAllCards() {
        eventCards = find.listLoc(By.cssSelector(CARD_BODY))
                .stream()
                .map(EventCard::parse)
                .collect(Collectors.toList());
        logger.info("Найдено {} карточек мероприятий", eventCards.size());
    }

    /**
     * Метод для отображения языка карточек мероприятий
     *
     */
    public void checkLang() {
        eventCards.forEach(card -> 
                logger.info("Язык для карточки {} - {}", card.getLink(), card.getLang()));
    }

    /**
     * Метод для отображения наименования событий карточек мероприятий
     *
     */
    public void checkEvent() {
        eventCards.forEach(card -> 
                logger.info("Мероприятие для карточки {} - {}", card.getLink(), card.getEventName()));
    }

    /**
     * Метод для отображения даты карточек мероприятий
     *
     */
    public void checkDate() {
        eventCards.forEach(card ->
                logger.info("Дата для карточки {} - {}", card.getLink(), card.getDate()));
    }

    /**
     * Метод для отображения регистрации карточек мероприятий
     *
     */
    public void checkReg() {
        eventCards.forEach(card ->
                logger.info("Регистрация для карточки {} - {}", card.getLink(), card.getRegistration()));
    }

    /**
     * Метод для отображения докладчиков карточек мероприятий
     *
     */
    public void checkSpeakers() {
        eventCards.forEach(card ->
                logger.info("Докладчики для карточки {} - {}", card.getLink(), card.getSpeakers()));
    }

    /**
     * Метод для открытия раздела past events
     *
     */
    public void openPastEvents() {
        //Запоминаем текст текущего элемента из списка тем и значение
        String elementText = find.locText(By.xpath(EVENT_TITLE));
        //Если раздел не активен, то перейти в него
        if (!find.isAtrContains(By.xpath(PAST_EVENTS + "/.."),"class", "active")) {
            //Кликнуть элемент
            click.xpathLocator(PAST_EVENTS + "/..")
                    .log("Переход в раздел Upcoming Events");
            //ждём пока прогрузится новый список тем
            wait.disappearText(By.xpath(EVENT_TITLE), elementText, 5000);
        }
    }

    /**
     * Метод для выбора значений фильтра
     *
     * @param filter название фильтра String
     * @param value название элемента фильтра String
     */
    public void selectFilterValue(String filter, String value) {
        //Запоминаем текущий элемент из списка тем и значение
        String elementText = find.locText(By.xpath(EVENT_TITLE));
        //Настраиваем элементы фильтра
        click.xpathLocator(UniLoc.xpathString(UniLoc.SPAN, filter))
                .xpathLocator(UniLoc.xpathString(UniLoc.LABELDATA, value))
                .log("Настроен фильтр " + filter + " со значением " + value);
        //ждём пока прогрузится новый список тем
        wait.disappearText(By.xpath(EVENT_TITLE), elementText, 5000);
    }

    /**
     * Метод для проверки даты проведения мероприятий,
     * дата должна быть раньше текущей.
     *
     * @return дата раньше текущей? boolean
     */
    public boolean isDateInCardLessCurrentDate() {
        eventCards.stream()
                .collect(Collectors.toList())
                .forEach(x -> Assert.assertTrue(Utils.parseEndDate(x.getDate()).isBefore(LocalDate.now()),
                        "Дата в карточке " + x.getLink() + " позже текущей"));
        return true;
    }

    public List<String> getDate() {
        return eventCards.stream().map(EventCard::getDate).collect(Collectors.toList());
    }

    /**
     * Метод для проверки полноты заполнения карточек мероприятий
     *
     * @return карточки заполнены полностью? boolean
     */
    public boolean isFieldFill() {
        eventCards.stream()
                .collect(Collectors.toList())
                .forEach(x -> Assert.assertTrue(x.isFieldsFill(),"Карточка " + x.getLink() + " не заполнена"));
        logger.info("{} карточек заполнены корректно", eventCards.size());
        return true;
    }

    /**
     * Метод для проверки даты проведения мероприятий,
     * больше или равны текущей дате или текущая дата находится в диапазоне дат проведения.
     *
     * @return дата карточки в заданных пределах? boolean
     */
    public boolean checkDateRange() {
        eventCards.stream()
                .collect(Collectors.toList())
                .forEach(x -> Assert.assertTrue(Utils.dateInRangeOrBefore(x.getDate()),
                        "Дата в карточке " + x.getLink() + " вне пределов текущей даты"));
        return true;
    }
}
