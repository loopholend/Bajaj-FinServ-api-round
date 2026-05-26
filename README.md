# Bajaj Finserv Health - REST API Round

Spring Boot REST API for the Bajaj Finserv Health qualifier.

## How to run locally

```
mvn spring-boot:run
```

API will be available at `http://localhost:8080/bfhl`

## API

**POST /bfhl** - processes the input data array and returns categorized results

Example request:
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

**GET /bfhl** - returns operation code

## Deploy

Push to Railway/Render and it will auto-detect Maven + the Procfile.

## Author
Pranjal Pal - 0827CS231185
