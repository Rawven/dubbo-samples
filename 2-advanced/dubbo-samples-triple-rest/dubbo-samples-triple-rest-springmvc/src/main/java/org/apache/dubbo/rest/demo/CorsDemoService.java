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
package org.apache.dubbo.rest.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/cors")
@CrossOrigin(origins = "http://example.com", allowedHeaders = {"X-Requested-With", "Origin", "Content-Type", "Accept"}, maxAge = 3600L)
public interface CorsDemoService {

    @RequestMapping(value = "/hello", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.OPTIONS})
    @CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS})
    String hello();


    @CrossOrigin(origins = {"http://example.com", "http://example.org"})
    @RequestMapping(value = "/multi-origin", method = RequestMethod.GET)
    String getMultiOrigin();

    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST})
    @RequestMapping(value = "/methods", method = {RequestMethod.GET, RequestMethod.POST})
    String getMethods();

    @CrossOrigin(exposedHeaders = {"X-Custom-Header"})
    @RequestMapping(value = "/exposed-headers", method = RequestMethod.GET)
    String getExposedHeaders();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/wildcard", method = RequestMethod.GET)
    String getWildcard();

    @CrossOrigin(origins = "http://example.com", methods = {RequestMethod.PUT, RequestMethod.DELETE})
    @RequestMapping(value = "/put-delete", method = {RequestMethod.PUT, RequestMethod.DELETE})
    String putDelete();

    @CrossOrigin(origins = "http://example.com", maxAge = 86400)
    @RequestMapping(value = "/max-age", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    String getMaxAge();
}
