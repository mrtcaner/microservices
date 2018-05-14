# hystrix
* No need for explicit ribbon dependency in pom.xml
* Eventhough eureka dependency seems to be in dependecies still it didn't work so added dependecy explicitly and voila!
* Feign needs to be added explicitly

# build
* run eureka servers
* run client app
* run multiple other-client-app using mvn spring-boot:run

## Future Work
* Move eureka server urls to spring cloud config
* eureka server not replicating
* cannot obtain application's port.(not necessary)
* each other-client-app instance's methods must have different failing strategies to see hystrix working. Currently, if a method fails in one other-client-app instance then it fails for the rest
