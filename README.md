# weezevent-sdk-spring-rest-template

This project is an extension for weezevent-sdk in order to use Spring RestTemplate instead of Apache HttpClient for a better integration with Spring Framework.

This project uses :
* Weezevent SDK
* Jackson 2.5.3
* Spring RestTemplate 4.1.6.RELEASE

## compile

Compile the project with Maven (weezevent-sdk must be already installed in your local Maven repository) :

```shell
$ mvn install
```

## dependency

Add Maven dependencies in your pom.xml file :

```xml
<dependency>
	<groupId>com.morsca.weezevent</groupId>
	<artifactId>weezevent-sdk-spring-rest-template</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

## usage with Weezevent SDK

For default Spring RestTemplate, simply create a new WeezeventRestTemplateClient instance :

```java
WeezeventClient client = WeezeventClient.getWeezeventClient();
client.setWeezeventHttpClient(new WeezeventRestTemplateClient());
//do some stuff...
```

Otherwise, you can use an existing instance of RestTemplate :

```java
@Autowired
private RestTemplate restTemplate;
...
WeezeventClient client = WeezeventClient.getWeezeventClient();
client.setWeezeventHttpClient(new WeezeventRestTemplateClient(restTemplate));
//do some stuff...
```

However, used mapper must be configured as following :

```java
objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
objectMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
```