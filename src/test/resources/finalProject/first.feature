# language: ru
@Cucumber
Функциональность: Проверяем, что проект стартует

  @Smoke
  Сценарий: Запускаем проект
    Дано  пустой контекст
    Когда читаем переменную в файле properties
    То он равняется 'Hello World!'
