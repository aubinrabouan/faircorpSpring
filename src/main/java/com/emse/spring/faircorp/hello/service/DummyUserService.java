package com.emse.spring.faircorp.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService{

    @Autowired
    private GreetingService greetingService;

    public void setGreetingService(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    @Override
    public void greetAll() {
        String a = "Elodie";
        String b = "Charles";
        greetingService.greet(a);
        greetingService.greet(b);
    }

}
