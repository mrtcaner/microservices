# hystrix
* No need for explicit ribbon dependency in pom.xml
* Eventhough eureka dependency seems to be in dependecies still it didn't work so added dependecy explicitly and voila!
* Feign needs to be added explicitly
* each other-client-app has 5 execute strategy methods. Each client's executestrategy method runs a random implementation of IExecutionStrategy
* for each other-client-app, method-executestrategy pairs are printed to console at startup
* One of these 5 strategies is a successful executor(which doesn't fail and returns a string).
* So each client has one of the successful executor for exactly one method. That method may be same with another instance of other-client-app or not!
* What we are trying to achieve is we want some methods to fail depending on their execution strategies. Finally at one instance there will be a successful executor implementation running so that all other methods will be 
circuit breaked. We should see lots of failed request data on hystrix dashboard

# build
* run eureka servers
* run client app
* run multiple other-client-app using mvn spring-boot:run


## Future Work
* Move eureka server urls to spring cloud config
* eureka server not replicating
* cannot obtain application's port.(not necessary)
* cannot get hystrix.stream. chrome and firefox console outputs an error message saying "Failed opening connection to /hystrix.stream : 404 : HTTP/1.1 404" whereas edge keeps loading stream forever
