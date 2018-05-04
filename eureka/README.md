# eureka
* Nothing fancy!
* Multiple server and client projects are presented
* Both clients have single rest controller classes and are able to call other client's methods
* Servers run on ports 8011 and 8012, clients run on ports 7001 and 7002


## Future Work
* Move eureka server urls to spring cloud config
* A single client app is sufficient. It can be started with different params many times(mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=test")
	- server.port should be assigned random (server.port=${PORT:${SERVER_PORT:0}})
	- these properties can be read within rest controller using @Value if neccessary
