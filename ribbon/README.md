# eureka
* Each client app has "/name" service. 
* client app has /otherclientsname service which retrieves other-client-app's predefined message + a unique id.
* /otherclientsname service makes calls using a restTemplate that is ribbon aware. So each call is load balanced
* Servers run on ports 8011 and 8012, clients run on random ports
* client-app has ribbon dependecy added whereas other-client-app not. So only client-app's some methods are load balanced

# built - run
* run eureka servers
* run client app
* run multiple other-client-app using mvn spring-boot:run
* localhost:<client-app-port>/otherclientsname will return a message with unique id for each other-client-app.

## Future Work
* Move eureka server urls to spring cloud config
* eureka server not replicating
* cannot obtain application's port.(not necessary)
* override some ribbon configs through configuration classes
