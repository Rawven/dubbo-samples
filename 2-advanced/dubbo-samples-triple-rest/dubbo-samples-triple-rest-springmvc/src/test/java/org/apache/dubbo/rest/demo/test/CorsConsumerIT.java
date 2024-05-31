/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.rest.demo.test;

import java.util.logging.Logger;
import org.apache.dubbo.rpc.protocol.tri.rest.cors.CorsHeaderFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@SpringBootTest(classes = CorsConsumerIT.class)
@RunWith(SpringRunner.class)
public class CorsConsumerIT {

    private static final String providerAddress = System.getProperty("dubbo.address", "localhost");

    private static final Logger logger = Logger.getLogger(CorsConsumerIT.class.getName());
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CorsConsumerIT.class);

    @Test
    public void testGlobalConfigAndClassConfig() {
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.get()
                .uri("http://" + providerAddress + ":50052/cors/hello")
                .header("Content-type", "application/json")
                .header("Origin", "http://example.com")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            defaultClient.post()
                    .uri("http://" + providerAddress + ":50052/cors/hello")
                    .header("Content-type", "application/json")
                    .header("Origin", "http://not-example.com")
                    .retrieve()
                    .toEntity(String.class);
        });

        result = defaultClient.options()
                .uri("http://" + providerAddress + ":50052/cors/hello")
                .header("Content-type", "application/json")
                .header("Origin", "http://example.com")
                .header(CorsHeaderFilter.ACCESS_CONTROL_REQUEST_METHOD, "GET")
                 .header(CorsHeaderFilter.ACCESS_CONTROL_REQUEST_HEADERS, "X-Requested-With","triple")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals(204, result.getStatusCode().value());

    }

    @Test
    public void testCorsMultiOrigin() {
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.get()
                .uri("http://" + providerAddress + ":50052/cors/multi-origin")
                .header("Content-type", "application/json")
                .header("Origin", "http://example.com")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());

        result = defaultClient.get()
                .uri("http://" + providerAddress + ":50052/cors/multi-origin")
                .header("Content-type", "application/json")
                .header("Origin", "http://example.org")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());

        result = defaultClient.get()
                .uri("http://" + providerAddress + ":50052/cors/multi-origin")
                .header("Content-type", "application/json")
                .header("Origin", "http://dubbo.com")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());


    }


    @Test
    public void testCorsMethods() {
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.get()
                .uri("http://" + providerAddress + ":50052/cors/methods")
                .header("Content-type", "application/json")
                .header("Origin", "http://example.com")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());


        result = defaultClient.post()
                .uri("http://" + providerAddress + ":50052/cors/methods")
                .header("Content-type", "application/json")
                .header("Origin", "http://example.com")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            defaultClient.put()
                    .uri("http://" + providerAddress + ":50052/cors/methods")
                    .header("Content-type", "application/json")
                    .header("Origin", "http://example.com")
                    .retrieve()
                    .toEntity(String.class);
        });

    }

    @Test
    public void testCorsExposedHeaders() {
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.get()
                .uri("http://" + providerAddress + ":50052/cors/exposed-headers")
                .header("Content-type", "application/json")
                .header("Origin", "http://example.com")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());
    }

    @Test
    public void testCorsWildcard() {
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.get()
                .uri("http://" + providerAddress + ":50052/cors/wildcard")
                .header("Content-type", "application/json")
                .header("Origin", "http://any-origin.com")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());
    }

    @Test
    public void testCorsPutDelete() {
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.put()
                .uri("http://" + providerAddress + ":50052/cors/put-delete")
                .header("Content-type", "application/json")
                .header("Origin", "http://example.com")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());

        result = defaultClient.delete()
                .uri("http://" + providerAddress + ":50052/cors/put-delete")
                .header("Content-type", "application/json")
                .header("Origin", "http://example.com")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());
    }

    @Test
    public void testCorsMaxAge() {
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.get()
                .uri("http://" + providerAddress + ":50052/cors/max-age")
                .header("Content-type", "application/json")
                .header("Origin", "http://example.com")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());
    }

    @Test
    public void testCorsMaxAgeOption() {
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.options()
                .uri("http://" + providerAddress + ":50052/cors/max-age")
                .header("Content-type", "application/json")
                .header(CorsHeaderFilter.ACCESS_CONTROL_REQUEST_METHOD, "GET")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());
    }

}
