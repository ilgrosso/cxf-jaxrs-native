/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package demo.jaxrs.client;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientApp {

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:9000");
    }

    @Bean
    public CommandLineRunner initWebClientRunner(final WebClient webClient) {
        return new CommandLineRunner() {

            @Override
            public void run(final String... runArgs) throws Exception {
                // Sent HTTP GET request to query customer info
                System.out.println("Sent HTTP GET request to query customer info");

                String response = webClient.path("/customerservice/customers/123").get(String.class);
                System.out.println(response);

                // Sent HTTP GET request to query sub resource product info
                System.out.println("\n");
                System.out.println("Sent HTTP GET request to query sub resource product info");

                webClient.reset();
                response = webClient.path("/customerservice/orders/223/products/323").get(String.class);
                System.out.println(response);

                // Sent HTTP PUT request to update customer info
                System.out.println("\n");
                System.out.println("Sent HTTP PUT request to update customer info");

                webClient.reset();
                try (Response r = webClient.
                        path("/customerservice/customers").
                        put(Entity.xml(getClass().getResourceAsStream("/update_customer.xml")))) {

                    System.out.println("Response status code: " + r.getStatus());
                    System.out.println("Response body: ");
                    System.out.println(r.readEntity(String.class));
                }

                // Sent HTTP POST request to add customer
                System.out.println("\n");
                System.out.println("Sent HTTP POST request to add customer");

                webClient.reset();
                try (Response r = webClient.
                        path("/customerservice/customers").
                        post(Entity.xml(getClass().getResourceAsStream("/add_customer.xml")))) {

                    System.out.println("Response status code: " + r.getStatus());
                    System.out.println("Response body: ");
                    System.out.println(r.readEntity(String.class));
                }

                System.out.println("\n");
            }
        };
    }

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(ClientApp.class).web(WebApplicationType.NONE).run(args);
    }
}
