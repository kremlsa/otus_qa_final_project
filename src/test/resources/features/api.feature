# language: ru

Функциональность: Работа с api

  Сценарий: Проверка разрешений на доступ к регистрации на сайте из страны - Россия
    И Запускаем сценарий "Проверка разрешений на доступ к регистрации на сайте из страны - Россия"
    Дано Ресурс "https://access.epam.com"
    И Адрес api "/auth/realms/plusx/locations/countries"
    И Тип содержимого "Json"
    И Устанавливается контекст
    Когда Отправляется запрос Get
    То Получен ответ
    И В ответе присутствует объект Json с ключом "name" и значением "Russia"
    И В объекте есть ключ "type" со значением "Country"
    И В объекте есть ключ "active" со значением "true"

  Сценарий: Проверяем информацию о прошедших событиях
    И Запускаем сценарий "Проверяем информацию о прошедших событиях"
    Дано Ресурс "https://events.epam.com"
    И Адрес api "/api/v2/calendar"
    И Тип содержимого "Json"
    И Устанавливается контекст
    И параметр Get запроса "start_date" со значением "10/05/2020"
    И параметр Get запроса "end_date" со значением "10/12/2020"
    Когда Отправляется запрос Get с параметрами
    То Получен ответ
    И В ответе присутствует объект Json с ключом "is_past" и значением "true"