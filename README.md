# cxf-jaxrs-native
Experimenting Apache CXF JAX-RS services with GraalVM.

Based on

* https://github.com/apache/cxf/tree/main/distribution/src/main/release/samples/jax_rs/graalvm_basic
* https://github.com/apache/cxf/tree/main/distribution/src/main/release/samples/jax_rs/spring_boot

### Server

```
mvn -Pnative,server clean native:compile
```

This goal will produce `bin/jaxrs-demo-server` executable (platform-dependent) which could be run right away: 

  * On Windows: `bin\jaxrs-demo-server.exe`
  * On Linux: `./bin/jaxrs-demo-server`

At this point there is a web service listening on `localhost:9000`.

### Client

```
mvn -Pnative,client clean native:compile
```

This goal will produce `bin/jaxrs-demo-client` executable (platform-dependent) which could be run right away: 

  * On Windows: `bin\jaxrs-demo-client.exe`
  * On Linux: `./bin/jaxrs-demo-client`

The command should produce the following output (assuming the server is up and running):

```
  Sent HTTP GET request to query customer info
  <?xml version="1.0" encoding="UTF-8" standalone="yes"?><Customer><id>123</id><name>Mary</name></Customer>


  Sent HTTP GET request to query sub resource product info
  <?xml version="1.0" encoding="UTF-8" standalone="yes"?><Product><description>product 323</description><id>323</id></Product>


  Sent HTTP PUT request to update customer info
  Response status code: 200
  Response body: 



  Sent HTTP POST request to add customer
  Response status code: 200
  Response body: 
  <?xml version="1.0" encoding="UTF-8" standalone="yes"?><Customer><id>128</id><name>Jack</name></Customer>
```
