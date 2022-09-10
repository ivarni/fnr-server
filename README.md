# Fødselsnummer-generator

## Server
Skrevet i Kotlin.

### Lokal utvikling
Kjør opp `Application.kt`

### Heroku deploy
```
heroku auth:login
heroku container:login
heroku create
heroku container:push web
heroku container:release web
```
