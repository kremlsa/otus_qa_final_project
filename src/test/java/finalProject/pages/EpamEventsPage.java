package finalProject.pages;

import org.openqa.selenium.By;
import wtf.uniloc.UniLoc;
import finalProject.common.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    private String eventTitle = "//div[@class='evnt-event-name']/h1/span";
    private List<EventCard> eventCards = new ArrayList<>();
    private List<String> cardErrors = new ArrayList<>();

    /**
     * Метод для открытия раздела upcoming events
     *
     */
    public void openUpcomingEvents() {
        //Запоминаем текст текущего элемента из списка тем и значение
        String elementText = find.locText(By.xpath(eventTitle));
        //Если раздел не активен, то перейти в него
        if (!find.isAtrContains(By.xpath(upcomingEvents + "/.."),"class", "active")) {
            //Кликнуть элемент
            click.xpathLocator(upcomingEvents + "/..")
                    .log("Переход в раздел Upcoming Events");
            //ждём пока прогрузится новый список тем
            wait.disappearText(By.xpath(eventTitle), elementText, 5000);
        }
    }

    /**
     * Метод для проверки появления карточек
     *
     * @return результат boolean
     */
    public boolean isCardApperance() {
        getAllCards();
        return find.isElementExists(By.cssSelector(cardBody));
    }

    /**
     * Метод для проверки счётчика карточек
     * @param buttonName имя кнопка для локатора String
     *
     * @return результат boolean
     */
    public boolean isCounterCorrect(String buttonName) {
        //Карточек найдено
        int cardsFind = find.listLoc(By.cssSelector(cardBody)).size();
        //Карточек в счётчике
        int cardsInCounter = Integer.parseInt(find.locText(UniLoc.xpathLocator(UniLoc.EVENTCOUNTER, buttonName)));
        //Сравниваем карточки найденные и счётчик
        if (cardsFind == cardsInCounter) {
            logInfo("Найдено карточек - " + cardsFind + ", карточек в счётчике " + cardsInCounter);
            return true;
        } else {
            logWarn("Найдено карточек - " + cardsFind + ", карточек в счётчике " + cardsInCounter);
            return false;
        }
    }

    /**
     * Метод для составления списка объектов - карточки мероприятий
     *
     */
    public void getAllCards() {
        eventCards = find.listLoc(By.cssSelector(cardBody))
                .stream()
                .map(EventCard::parse)
                .collect(Collectors.toList());
        logInfo("Найдено " + eventCards.size() + " карточек мероприятий");
    }

    /**
     * Метод для отображения языка карточек мероприятий
     *
     */
    public void checkLang() {
        eventCards.forEach(card -> {
                    if (card.getLang().equals("Not defined")) {
                        logWarn("Язык для карточки " + card.getLink() + " - " + card.getLang());
                    } else {
                        logInfo("Язык для карточки  " + card.getLink() + " - " + card.getLang());
                    }
                });
    }

    /**
     * Метод для отображения наименования событий карточек мероприятий
     *
     */
    public void checkEvent() {
        eventCards.forEach(card -> {
                    if (card.getEventName().equals("Not defined")) {
                        logWarn("Мероприятие для карточки " + card.getLink() + " - " + card.getEventName());
                    } else {
                        logInfo("Мероприятие для карточки  " + card.getLink() + " - " + card.getEventName());
                    }
                });
    }

    /**
     * Метод для отображения даты карточек мероприятий
     *
     */
    public void checkDate() {
        eventCards.forEach(card -> {
                    if (card.getDate().equals("Not defined")) {
                        logWarn("Дата для карточки " + card.getLink() + " - " + card.getDate());
                    } else {
                        logInfo("Дата для карточки  " + card.getLink() + " - " + card.getDate());
                    }
                });
    }

    /**
     * Метод для отображения регистрации карточек мероприятий
     *
     */
    public void checkReg() {
        eventCards.forEach(card -> {
            if (card.getRegistration().equals("Not defined")) {
                logWarn("Регистрация для карточки " + card.getLink() + " - " + card.getRegistration());
            } else {
                logInfo("Регистрация для карточки  " + card.getLink() + " - " + card.getRegistration());
            }
        });
    }

    /**
     * Метод для отображения докладчиков карточек мероприятий
     *
     */
    public void checkSpeakers() {
        eventCards.forEach(card -> {
            if (card.getSpeakers().equals("Not defined")) {
                logWarn("Докладчики для карточки " + card.getLink() + " - " + card.getSpeakers());
            } else {
                logInfo("Докладчики для карточки  " + card.getLink() + " - " + card.getSpeakers());
            }
        });
    }

    /**
     * Метод для открытия раздела past events
     *
     */
    public void openPastEvents() {
        //Запоминаем текст текущего элемента из списка тем и значение
        String elementText = find.locText(By.xpath(eventTitle));
        //Если раздел не активен, то перейти в него
        if (!find.isAtrContains(By.xpath(pastEvents + "/.."),"class", "active")) {
            //Кликнуть элемент
            click.xpathLocator(pastEvents + "/..")
                    .log("Переход в раздел Upcoming Events");
            //ждём пока прогрузится новый список тем
            wait.disappearText(By.xpath(eventTitle), elementText, 5000);
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
        String elementText = find.locText(By.xpath(eventTitle));
        //Настраиваем элементы фильтра
        click.xpathLocator(UniLoc.xpathString(UniLoc.SPAN, filter))
                .xpathLocator(UniLoc.xpathString(UniLoc.LABELDATA, value))
                .log("Настроен фильтр " + filter + " со значением " + value);
        //ждём пока прогрузится новый список тем
        wait.disappearText(By.xpath(eventTitle), elementText, 5000);
    }

    /**
     * Метод для проверки даты проведения мероприятий,
     * дата должна быть раньше текущей.
     *
     * @return дата раньше текущей? boolean
     */
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

    /**
     * Метод для проверки полноты заполнения карточек мероприятий
     *
     * @return карточки заполнены полностью? boolean
     */
    public boolean isFieldFill() {
        for (EventCard card : eventCards) {
            if (card.getSpeakers().equals("Not defined") || card.getRegistration().equals("Not defined")
                    || card.getDate().equals("Not defined") || card.getLang().equals("Not defined")
                    || card.getEventName().equals("Not defined")) {
                logWarn("Карточки заполнены с ошибками");
                return false;
            }
        }
        logInfo(eventCards.size() + " карточек заполнены корректно");
        return true;
    }

    /**
     * Метод для проверки даты проведения мероприятий,
     * больше или равны текущей дате или текущая дата находится в диапазоне дат проведения.
     *
     * @return дата карточки в заданных пределах? boolean
     */
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
