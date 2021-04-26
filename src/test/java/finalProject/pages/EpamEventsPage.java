package finalProject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import wtf.uniloc.UniLoc;
import finalProject.common.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static wtf.actions.Log.logInfo;
import static wtf.actions.Log.logWarn;

/**
 * Класс для описания страницы - Events
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class EpamEventsPage extends BasePage{

    //Локаторы
    private String upcomingEvents = "//span[contains(text(),'Upcoming events')]";
    private String pastEvents = "//span[contains(text(),'Past Events')]";
    private String cardBody = "div.evnt-event-card";
    private String upcomingEventsCount = "//span[contains(text(),'Upcoming events')]/../span[3]";
    private String cardPlace = "//*[@class='online' or @class='location']/span";
    private String cardLang = "//*[@class='evnt-details-cell language-cell']/p/span";
    private String cardEvent = "//*[@class='evnt-event-name']/h1/span";
    private String cardDate = "//*[@class='evnt-dates-cell dates']/p/span";
    private String cardReg = "//*[@class='evnt-dates-cell dates']/p/span[contains(@class, 'status')]";
    private String cardSpeakers = "//*[@class='evnt-people-cell speakers']/div/div[@class='evnt-speaker']";
    private String eventTitle = "//div[@class='evnt-event-name']/h1/span";

    private List<SelenideElement> cards;
    private List<EventCard> eventCards = new ArrayList<>();
    private List<String> cardErrors = new ArrayList<>();

    public void openUpcomingEvents() {
        //Запоминаем текущий элемент из списка тем и значение
        SelenideElement element = $x(eventTitle);
        String elementText = element.getText();
        //Если раздел не активен, то перейти в него
        if (!$x(upcomingEvents + "/..")
                .getAttribute("class")
                .contains("active")) {
            $x(upcomingEvents + "/..").click();
            //ждём пока прогрузится новый список тем
            element.waitUntil(Condition.not(Condition.matchesText(elementText)), 5000);
        }
        //Логируем
        logInfo("Переход в раздел Upcoming Events");
    }

    public boolean isCardApperance() {
        getAllCards();
        return $(cardBody).exists();
    }

    //Сделать регистронезависимый поиск для локатора
    public boolean isCounterCorrect(String buttonName) {
        //Карточек найдено
        int cardsFind = $$(cardBody).size();
        //Карточек в счётчике
        int cardsInCounter = Integer.parseInt($x(UniLoc.xpathString(UniLoc.EVENTCOUNTER, buttonName))
                .getText().trim());
        //Сравниваем карточки найденные и счётчик
        if (cardsFind == cardsInCounter) {
            logInfo("Найдено карточек - " + cardsFind + ", карточек в счётчике " + cardsInCounter);
            return true;
        } else {
            logWarn("Найдено карточек - " + cardsFind + ", карточек в счётчике " + cardsInCounter);
            return false;
        }
    }

    public void getAllCards() {
        cards = $$(cardBody);
        logInfo("Найдено " + cards.size() + " карточек мероприятий");
        //Чистим карточки от предыдущих тестов
        eventCards = new ArrayList<>();
        //Парсим карточки событий
        for (SelenideElement card : cards) {
            EventCard eventCard = new EventCard();
            eventCard.parse(card.toWebElement());
            eventCards.add(eventCard);
        }
    }

    public void checkLang() {
        for(EventCard card : eventCards) {
            if (card.getLang().equals("Not defined")) {
                logInfo("Язык для карточки " + card.getCardLink()
                        + " - " + card.getLang());
            } else {
                logInfo("Язык для карточки  " + card.getCardLink()
                        + " - " + card.getLang());
            }
        }
    }

    public void checkEvent() {
        for(EventCard card : eventCards) {
            if (card.getEventName().equals("Not defined")) {
                logInfo("event for card " + card.getCardLink()
                        + " - " + card.getEventName());
            } else {
                logInfo("event for card " + card.getCardLink()
                        + " - " + card.getEventName());
            }
        }
    }

    public void checkDate() {
        for(EventCard card : eventCards) {
            if (card.getDate().equals("Not defined")) {
                logInfo("Дата для карточки " + card.getCardLink()
                        + " - " + card.getDate());
            } else {
                logInfo("Дата для карточки  " + card.getCardLink()
                        + " - " + card.getDate());
            }
        }
    }

    public void checkReg() {
        for(EventCard card : eventCards) {
            if (card.getRegistration().equals("Not defined")) {
                cardErrors.add(card.getCardLink());
                logInfo("регистрация для карточки " + card.getCardLink()
                        + " - " + card.getRegistration());
            } else {
                logInfo("регистрация для карточки  " + card.getCardLink()
                        + " - " + card.getRegistration());
            }
        }
    }

    public void checkSpeakers() {
        for(EventCard card : eventCards) {
            if (card.getSpeakers().equals("Not defined")) {
                logInfo("Спикеры для карточки " + card.getCardLink()
                        + " - " + card.getSpeakers());
            } else {
                logInfo("Спикеры для карточки  " + card.getCardLink()
                        + " - " + card.getSpeakers());
            }
        }
    }

    public void openPastEvents() {
        //Запоминаем текущий элемент из списка тем и значение
        SelenideElement element = $x(eventTitle);
        String elementText = element.getText();
        //Если раздел не активен, то перейти в него
        if (!$x(pastEvents + "/..")
                .getAttribute("class")
                .contains("active")) {
            $x(pastEvents + "/..").click();
            //ждём пока прогрузится новый список тем
            element.waitUntil(Condition.not(Condition.matchesText(elementText)), 5000);
        }
        //Логируем
        logInfo("Переход в раздел Past Events");


    }

    public void selectFilterValue(String filter, String value) {
        //Запоминаем текущий элемент из списка тем и значение
        SelenideElement element = $x(eventTitle);
        String elementText = element.getText();
        //Настраиваем элементы фильтра
        $x(UniLoc.xpathString(UniLoc.SPAN, filter)).click();
        $x(UniLoc.xpathString(UniLoc.LABELDATA, value)).click();
        //Логируем
        logInfo("Настроен фильтр " + filter + " со значением " + value);
        //ждём пока прогрузится новый список тем
        element.waitUntil(Condition.not(Condition.matchesText(elementText)), 5000);
    }

    public boolean isDateInCardLessCurrentDate() {
        //Просматриваем все карточки
        for (EventCard card : eventCards) {
            //Парсим дату
            LocalDate date = Utils.parseEndDate(card.getDate());
            //Проверяем что дата в карточке до текущей
            if (date.isAfter(LocalDate.now())) {
                //Логируем
                logWarn("Карточка " + card.getEventName() + " с датой "
                        + date + " после текущей " + LocalDate.now());
                return false;
            } else {
                //Логируем
                logInfo("Карточка " + card.getEventName() + " с датой " + date
                + " до текущей " + LocalDate.now());
            }
        }
        return true;
    }

    public void openAnyCard() {
        SelenideElement element = $(cardBody);
        String elementText = element.$("h1").getText();
        element.click();
        logInfo("Открываем карточку " + elementText);
    }

    public boolean isFieldFill() {
        if (cardErrors.size() > 0) {
            logInfo("Карточки заполнены с ошибками");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkDateRange() {
        for (EventCard card : eventCards) {
            //Парсим дату
            LocalDate startDate = Utils.parseStartDate(card.getDate());
            LocalDate endDate = Utils.parseEndDate(card.getDate());
            LocalDate now = LocalDate.now();
            //Проверяем что дата в карточке в пределах диапазона или позже
            if (now.isBefore(startDate) || now.isAfter(endDate)) {
                if (!now.isBefore(endDate) || !now.isBefore(startDate)) {
                    //Логируем
                    logWarn("Карточка " + card.getEventName() + " с датой "
                            + card.getDate() + " находится вне пределах текущей даты " + LocalDate.now());
                    return false;
                }
            }
            //Логируем
            logInfo("Карточка " + card.getEventName() + " с датой "
                    + card.getDate() + " в пределах от текущей даты " + LocalDate.now());
        }
        return true;
    }
}
