package com.cck.wxtest.config;

import java.io.File;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class PropertyConfigurer extends PropertySourcesPlaceholderConfigurer {
	private static final String WXTEST_CONFIG = "wxtest.config";
	private static final String FILE_NAME = "config.properties";

	public PropertyConfigurer() {
		this.localOverride = true;
		this.ignoreUnresolvablePlaceholders = true;
		String configPath = System.getProperty(WXTEST_CONFIG);
		if (configPath == null) {
			throw new IllegalArgumentException("Please use -D" + WXTEST_CONFIG + "=<your config path> to set config.properties");
		}
		FileSystemResource configResource = new FileSystemResource(configPath + File.separator + FILE_NAME);
		setLocations(configResource);
	}
}
