Profile werden getrennt:
application.yml

-local(JVM,Intellij)
-docker(Container)
-prod(Heroku)

Liquibase wird noch implementiert

### How to start
* docker-compose build
* start database
* start rabbitmq
* start app

### RabbitMQ
* rabbitmq web view: http://localhost:15672

### TestController to send messages
* TestController path: com.example.demo.port.user.controller
    * localhost:8080/product-queue
    * localhost:8080/product-update-queue

```mvn
        <dependency>
            <groupId>com.rabbitmq</groupId>
            <artifactId>amqp-client</artifactId>
            <version>5.16.0</version>
        </dependency>
```

getasucht zu --> 5.18.0 wegen updateProductProducer ERROR

```mvn
        <dependency>
            <groupId>com.rabbitmq</groupId>
            <artifactId>amqp-client</artifactId>
            <version>5.18.0</version>
        </dependency>
```