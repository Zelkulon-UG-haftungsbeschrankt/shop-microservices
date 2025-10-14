

### How to start
* docker-compose build 
* start database
* start rabbitmq
* start app

```sh
docker network create internal
```

```sh
docker compose up --build
```

### RabbitMQ
The Product-Microservice starts the RabbitMQ Management

First start the Product-Microservice then the others.

* rabbitmq web view: http://localhost:15672
