# Проектная работа от EPAM

## Задача для проекта: Необходимо построить фреймворк для автоматизации Е2Е тестирования сайта с обязательным тестовым покрытием.

## Инструкция по запуску


1. Скачать проект с https://github.com/kremlsa/otus_qa_final_project
   
2. Запустить проект через - maven clean test   
   
3. Параметры для запуска через maven
   (по умолчанию заданы в классе "src/main/java/cfg/Cfg")

   разрешение браузера:
   -DbrowserSize
   
   удалённый вебдрайвер:
   -DremoteURL

   выбор браузера:
   -Dbrowser
   
   Пример для запуска:

   mvn clean test -DbrowserSize='1920x1080' -DremoteURL='http://selenoid:4444/wd/hub' -Dbrowser='chrome'

4. Для CI/CD возможно настроить задачу в Jenkins через использование JenkinsFile

5. Отчёты Allure размещены в target\allure-results
   для просмотра Allure отчётов использовать команду - mvn allure:serve