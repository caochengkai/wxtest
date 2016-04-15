package com.cck.wxtest.property;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {

	public PropertyConfigurer() {
		Resource resource1 = new ClassPathResource("application.properties");
		setLocations(resource1);
	}
}
