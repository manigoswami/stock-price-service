# Manig Stock Price API
REST based APIs to fetch Close Price and Moving Averages

## Requirements
 * Java 1.8
 * Tested on Mac OSX, CentOS 7.X 
 * Maven (if you want to build from the source code)
 
### Build
 ```
 mvn clean package

```
To build without invoking test cases

```

mvn clean package -Dmaven.test.skip=true

```
### Deployment

Above command generates an executable jar under target directory. It can be run with following command

```
java -jar target/stockPriceService-1.0.0-SNAPSHOT.jar

```
### Configurations
* By default it starts an embedded Tomcat at port 8080. You can
modify it using the application.yml under resource directory. 

* The log can be configured under applications.yml. By default it will writes
to /log. You may have to grant permission and create directory based on
your box.

* The Quandl API details are configured under directive named serviceURL in applications.yml. 
The default usage is 50 per day from an IP. If you plan to use more, modify the URL to add
 your API_KEY to the serviceURL directive

### Getting Started with the API
After you have build the project, go to generated-docs/ directory under source root and refer
to the html API documentation to get started. It covers both success and error cases in detail.

### Design Notes

 Following are the tech stack used:
 
* Java 1.8
* Spring Boot 4.0
* Caffeine Caching Engine 2.6
* Lombok 1.16
* Restdocs
* Spring Runner
* Cobertura



