package com.example.restservice;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	/**
	 * raw json
	 * @param params
	 * @return
	 */
	@GetMapping("/greeting2")
	public Greeting greeting(@RequestBody Map<String,Object> params){
		String productId = params.get("productId").toString();
		return new Greeting(counter.incrementAndGet(), String.format(template, productId));
	}

	/**
	 * form-data / x-www-form-urlencoded
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	@RequestMapping(value = "/greeting3",method = RequestMethod.POST)
	public Greeting greeting(String firstName,String lastName){
		return new Greeting(counter.incrementAndGet(),String.format(template,firstName + lastName));
	}
}
