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
package demo.jaxrs.server;

import java.util.HashMap;
import java.util.Map;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Order")
public class Order {

    private long id;

    private String description;

    private final Map<Long, Product> products = new HashMap<>();

    public Order() {
        init();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        this.description = d;
    }

    @GET
    @Path("products/{productId}/")
    public Product getProduct(@PathParam("productId") int productId) {
        System.out.println("----invoking getProduct with id: " + productId);
        return products.get(Long.valueOf(productId));
    }

    final void init() {
        Product p = new Product();
        p.setId(323);
        p.setDescription("product 323");
        products.put(p.getId(), p);
    }
}
