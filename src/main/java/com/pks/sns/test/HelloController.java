package com.pks.sns.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	
	@GetMapping("/snshello")
	@ResponseBody
	public String hello() {
		return "SNS Hello World";
	}
	
}
