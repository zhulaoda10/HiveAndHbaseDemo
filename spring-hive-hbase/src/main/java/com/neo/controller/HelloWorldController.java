package com.neo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController{
	
    @RequestMapping("/hello")
    public String index() {
        return "Hello zhuyafei";
    }

//   @CrossOrigin(origins = "*", maxAge = 3600) 
    @RequestMapping(value="/cors")
    public String hello(){
    	return "hello,aobama@!!aaaa!";
    }
}