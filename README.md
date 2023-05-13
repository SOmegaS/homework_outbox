Домашнее задание "Transactional outbox"

После выполнения данного запроса в базу данных PostgreSQL будет добавлен новый пользователь, а не более,
чем через 5 секунд, сообщение будет отправлено в RabbitMQ.

```
###
POST http://localhost:8080/registerUser
Content-Type: application/json

{
"login": "login",
"password": "password"
}
```