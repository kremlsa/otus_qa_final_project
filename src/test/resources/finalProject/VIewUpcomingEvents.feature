# language: ru

Функциональность: Работа с элементами раздела Events

  @TestUI
  Сценарий: Просмотр предстоящих мероприятий
    Дано  Пользователь переходит на вкладку events
    Когда Пользователь нажимает на Upcoming Events
    То На странице отображаются карточки предстоящих мероприятий
    И Количество карточек равно счетчику на кнопке Upcoming Events

  @TestUI
  Сценарий: Просмотр карточек мероприятий
    Дано  Пользователь переходит на вкладку events
    Когда Пользователь нажимает на Upcoming Events
    То На странице отображаются карточки предстоящих мероприятий
    И В карточке указана информация о мероприятии:
    * город проведения
    * язык
    * название мероприятия
    * дата мероприятия
    * информация о регистрации
    * список спикеров
    И Важно проверить порядок отображаемых блоков с информацией в карточке мероприятия
