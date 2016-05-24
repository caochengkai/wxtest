package com.cck.wxtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication  
@ServletComponentScan //scan the servlet or filter or listener for web
public class SampleController extends SpringBootServletInitializer {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String home() {
		return "Hello World!";
	}

	//让项目在servlet容器中启动（类似于web.xml文件配置的方式来启动Spring应用上下文）
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleController.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleController.class, args);
	}
}