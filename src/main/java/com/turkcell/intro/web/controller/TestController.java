package com.turkcell.intro.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping({"/hello", "/merhaba"}) //birden fazla url ile gidebilmesi icin {} kullaniyoruz.
    public String sayHello(){
        return "Merhaba Dunya";
    }

    @GetMapping("/hi")
    public String sayHi(@RequestParam(defaultValue = "kişi") String name,
                        @RequestParam() int age){ //Default olarak query string atar.
        return "Merhaba "+ name + ", yasiniz: "+ age;
    }
}
