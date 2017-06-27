**Prereq**

Ensure that ../configserver README.md instructions are done, and server is running.


**Start Client**

```
mvn clean install
mvn spring-boot:run
```

Verify client is able to fetch config from server:

http://localhost:8080/message
