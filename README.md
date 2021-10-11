# Simple Quarkus login

A simple Quarkus login microservice that will return a JWT on successful login. 

I created this as a quick start for when I need a custom login server for projects,
or when I need to spin up a login server quickly for development (School projects, etc.). 

For larger and/or more serious projects I would suggest using [Keycloak](https://www.keycloak.org/)

If you however were to use this project in production, please be sure to enable HTTPS, [generate a new key](https://en.wikibooks.org/wiki/Cryptography/Generate_a_keypair_using_OpenSSL) to replace [testKey.pem](src/main/resources/testKey.pem) and enable some minimal password requirements.


## Running the application in dev mode

You can run your application in dev mode using:
```shell script
./mvnw quarkus:dev
```

An admin account can be registered using Command-line args.
```shell script
./mvnw quarkus:dev -Dquarkus.args=admin:admin
```
This argument follows a `username:password` schema. 

## How to use

To register a user, send a ``` POST ```  request to:

``` HTTP
http://localhost:8080/register 
```  

containing the following JSON:

```JSON
{
	"username":"user",
	"password":"user"
}
```

This should return a `200`.

To log in/ acquire a JWT, send a ``` POST ``` request with an [Authorization request header](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Authorization) included.

<i> Curl example <i>
```shell script
curl -i -X POST -u user:user localhost:8080/login
```

<i> Http header example <i>
```HTTP
Authorization: Basic dXNlcjp1c2Vy
```

## Packaging the application

Before packaging, make sure you edit the production properties in [application.properties](src/main/resources/application.properties).

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

With GraalVM installed you can create a native executable using: 
```shell script
./mvnw package -Pnative
```
Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/simple-login-1.0.0-SNAPSHOT-runner`
