package com.cck.wxtest;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

//让项目在servlet容器中启动（类似于web.xml文件配置的方式来启动Spring应用上下文）
public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleController.class);
	}
}
