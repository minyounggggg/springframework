package com.spring.javaclassS.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
@RequestMapping("/restapi")
public class RestapiController {
	
	@ResponseBody
	@RequestMapping(value = "/restapiTest2/{message}", method = RequestMethod.GET)
	public String restapiTest2GET(@PathVariable String message) {
		System.out.println("message : " + message);
		return "message : " + message;
	}
	
	@RequestMapping(value = "/restapiTest3/{message}", method = RequestMethod.GET)
	public String restapiTest3GET(@PathVariable String message) {
		System.out.println("message : " + message);
		return "message : " + message;
	}
	
}
