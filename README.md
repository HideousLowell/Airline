# Airline
Приложение позволяющее администраторам добавлять авиарейсы, пассажирам - осуществлять поиск билетов, приобретать их
_____

## Стек
- Java 8
- Maven
- Spring Boot
- Spring Security
- Hibernate
- Postgres
- Thymeleaf
_____

Существуют две роли: пользователь и администратор.
Для авторизации необходимо войти в свой аккаунт, либо создать его.
При авторизации пользователь перенаправляется на страницу покупки билетов, администратор на служебную страницу.
Администратор имеет доступ ко всем рейсам, может загружать новые рейсы из файла в формате csv.
При загрузке информации о рейсах, в которых указаны дни недели, автоматически генерируются даты рейсов на ближайший месяц.
Пользователь может осуществлять поиск рейсов по параметрам, бронировать билеты.
При заполнении мест рейс перестает отображаться для поиска.
