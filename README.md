# webflux-cqrs-es
Yep. It's in progress. Also.

## run h2 database

```bash
./gradlew h2
`

## run applicatoin

```bash
./gradlew bootRun
```

## login into h2

- open http://127.0.0.1:8082/
- select h2 server
- login:
  - into `jdbc:h2:tcp://127.0.0.1:9092/~/test`
  - as `su` user
  - without password

## add some events

```bash
http :8080/mytest value1=ololo value2=trololo
HTTP/1.1 200 OK
Content-Length: 138
Content-Type: application/json;charset=UTF-8
# output:
```

```json
{
    "createdDate": "2019-03-06T00:59:1",
    "data": {
        "value1": "ololo",
        "value2": "trololo"
    },
    "id": 1,
    "modifiedDate": "2019-03-06T00:59:327",
    "version": 1
}
```

## create inherit domain events

1)

```bash
echo '{
  "dataFieldOfEvent1": "ololo trololo!",
  "data": {
    "val1": "hello!",
    "val2": "hola!",
    "val3": "привед!"
  }
}' | http :8080/event1
HTTP/1.1 200 OK
content-length: 0
```

2)

```bash
http :8080/event3 field1=one field2=two field3=three data:='{"val1":"test!"}'
HTTP/1.1 200 OK
content-length: 0
```

## now let's look what we have....

```bash
http :8080/events
HTTP/1.1 200 OK
Content-Length: 809
Content-Type: application/json;charset=UTF-8
# output:
```

```json
{
    "domainEvents": [
        {
            "createdDate": "2019-03-06T02:35:8",
            "data": {
                "val1": "hello!",
                "val2": "hola!",
                "val3": "привед!"
            },
            "dataFieldOfEvent1": "ololo trololo!",
            "id": 1,
            "modifiedDate": "2019-03-06T02:35:070"
        },
        {
            "createdDate": "2019-03-06T02:36:10",
            "data": {
                "val1": "test!",
                "val2": "default val 2",
                "val3": "and 3..."
            },
            "field1": "one",
            "field2": "two",
            "field3": "three",
            "id": 2,
            "modifiedDate": "2019-03-06T02:36:807"
        }
    ],
    "events1": [
        {
            "createdDate": "2019-03-06T02:35:8",
            "data": {
                "val1": "hello!",
                "val2": "hola!",
                "val3": "привед!"
            },
            "dataFieldOfEvent1": "ololo trololo!",
            "id": 1,
            "modifiedDate": "2019-03-06T02:35:070"
        }
    ],
    "events2": [],
    "events3": [
        {
            "createdDate": "2019-03-06T02:36:10",
            "data": {
                "val1": "test!",
                "val2": "default val 2",
                "val3": "and 3..."
            },
            "field1": "one",
            "field2": "two",
            "field3": "three",
            "id": 2,
            "modifiedDate": "2019-03-06T02:36:807"
        }
    ]
}
```

TODO: kafka messaging and event sourcing and ...everything else

## [download and start](https://raw.githubusercontent.com/daggerok/kafka-java-example/master/download-and-start-kafka.sh) kafka

```bash
wget -qO- https://raw.githubusercontent.com/daggerok/kafka-java-example/master/download-and-start-kafka.sh | bash
```