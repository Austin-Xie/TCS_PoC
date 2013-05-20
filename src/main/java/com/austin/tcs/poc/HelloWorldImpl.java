
package com.austin.tcs.poc;

import javax.jws.WebService;

@WebService(endpointInterface = "com.austin.tcs.poc.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

