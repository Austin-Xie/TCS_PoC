package com.austin.tcs.poc;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    String sayHi(String text);
}

