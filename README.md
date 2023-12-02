# Weather Project

### Описание программы

Необходимо разработать анализатор погоды.
Нужно использовать сторонние API (например https://rapidapi.com/weatherapi/api/weatherapi-com).
Приложение будет запрашивать погоду с заданной в настройках периодичностью по определенному городу.

- [Сохранение погоды по расписанию](src%2Fmain%2Fjava%2Forg%2Fsenla%2Fwether%2Fscheduler%2FScheduler.java)
- [Dump файл БД](src%2Fmain%2Fresources%2FDump_weather.sql)

### Пример запросов и ответов для тестирования через REST API:
- http://localhost:8080/api/currentWeather/averageTemp?startDate=02.12.2023&endDate=04.12.2023

![currentWeather.png](src%2Fmain%2Fresources%2Fimg%2F%D1%ED%E8%EC%EE%EA%20%FD%EA%F0%E0%ED%E0%202023-12-03%20012300.png)

-http://localhost:8080/api/currentWeather

![averageTemp.png](src%2Fmain%2Fresources%2Fimg%2F%D1%ED%E8%EC%EE%EA%20%FD%EA%F0%E0%ED%E0%202023-12-03%20012341.png)
