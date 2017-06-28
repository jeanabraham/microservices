This client exposes a URI http://localhost:8020/sentence, which when invoked, discovers word clients using the Eureka server to build a sentence to respond.

**Prereq**

Eureka server and other clients are running:

netflix-eureka-server
netflix-eureka-client   (subject word client)
netflix-eureka-client-2 (verb word client)
netflix-eureka-client-3 (object word client)

**Start sentence client**

```
cd netflix-eureka-client-main/
mvn spring-boot:run
```

