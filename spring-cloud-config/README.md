# spring cloud config

## build
- download and run zookeper https://medium.com/@shaaslam/installing-apache-zookeeper-on-windows-45eda303e835 
- download and run kafka server (kafka_2.11-1.1.0\bin\windows>kafka-server-start.bat ../../config/server.properties) 
- btw kafka_2.11-1.1.0 may have zookeper too!
- run config-server application
- run client-config application(make sure you have vm parameter -Dspring.profiles.active=dev)
- a configuration repository must be supplied to server properties and also a webhook must be configured to test autorefresh 
- to make webhook to access your local app, do not forget to make sure your server app can be accessed from internet. Consider port forwarding!

## Future Work
* add security and encryption http://www.baeldung.com/spring-cloud-configuration
* Autorefresh doesn't work on spring boot 2.0.1-RELEASE. Downgrading to 1.5.12 causes file delete problems on windows but must be working on linux(not tested)
* Add embedded zookeper and kafka server https://gist.github.com/vmarcinko/e4e58910bcb77dac16e9