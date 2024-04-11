package org.apache.dubbo.rest.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@RequestMapping("/cors")
@CrossOrigin(origins = "http://example.com", allowedHeaders = {"X-Requested-With", "Origin", "Content-Type", "Accept"}, maxAge = 3600)
public interface CorsDemoService {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String getHello();

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    String postHello();

    @CrossOrigin(origins = {"http://example.com", "http://example.org"})
    @RequestMapping(value = "/multi-origin", method = RequestMethod.GET)
    String getMultiOrigin();

    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST})
    @RequestMapping(value = "/methods", method = {RequestMethod.GET, RequestMethod.POST})
    String getMethods( );

    @CrossOrigin(exposedHeaders = {"X-Custom-Header"})
    @RequestMapping(value = "/exposed-headers", method = RequestMethod.GET)
    String getExposedHeaders();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/wildcard", method = RequestMethod.GET)
    String getWildcard();

    @CrossOrigin(origins = "http://example.com", methods = {RequestMethod.PUT, RequestMethod.DELETE})
    @RequestMapping(value = "/put-delete", method = {RequestMethod.PUT, RequestMethod.DELETE})
    String putDelete( );

    @CrossOrigin(origins = "http://example.com", maxAge = 86400)
    @RequestMapping(value = "/max-age", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    String getMaxAge();

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(value = "/invalidCredential", method = {RequestMethod.GET})
    String inValidCredential();

    @CrossOrigin(origins = "*", allowPrivateNetwork = "true")
    @RequestMapping(value = "/invalidPrivateWork", method = {RequestMethod.GET})
    String inValidPrivateWork();

    @CrossOrigin(origins = "*", allowCredentials = "false")
    @RequestMapping(value = "/validCredential", method = {RequestMethod.GET})
    String validCredential();

    @CrossOrigin(origins = "*", allowPrivateNetwork = "false")
    @RequestMapping(value = "/validPrivateWork", method = {RequestMethod.GET})
    String validPrivateWork();
}