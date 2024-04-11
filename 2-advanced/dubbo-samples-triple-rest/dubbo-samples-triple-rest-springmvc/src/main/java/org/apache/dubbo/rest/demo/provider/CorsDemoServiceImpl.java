package org.apache.dubbo.rest.demo.provider;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rest.demo.CorsDemoService;

import org.springframework.web.bind.annotation.RequestMethod;

@DubboService
public class CorsDemoServiceImpl implements CorsDemoService {
    @Override
    public String getHello() {
        return "hello";
    }

    @Override
    public String postHello() {
        return "hello";
    }

    @Override
    public String getMultiOrigin() {
        return "hello";
    }

    @Override
    public String getMethods( ) {
        return "hello";
    }

    @Override
    public String getExposedHeaders() {
        return "hello";
    }

    @Override
    public String getWildcard() {
        return "hello";
    }

    @Override
    public String putDelete( ) {
        return "hello";
    }

    @Override
    public String getMaxAge() {
        return "hello";
    }

    @Override
    public String inValidCredential() {
        return "hello";
    }

    @Override
    public String inValidPrivateWork() {
        return "hello";
    }

    @Override
    public String validCredential() {
        return "hello";
    }

    @Override
    public String validPrivateWork() {
        return "hello";
    }
}
