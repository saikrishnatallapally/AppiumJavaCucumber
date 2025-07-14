package com.challengeapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropertyReader {

	private static PropertyReader instance;
	private final Properties properties;
	private static final Logger logger = LogManager.getLogger(PropertyReader.class);
	
	private PropertyReader() {
		properties = new Properties();

		String filePath = System.getProperty("user.dir") + "/src/test/resources/propertiesfiles/config.properties";

		try (FileInputStream input = new FileInputStream(filePath)) {
			properties.load(input);
			logger.info("Loaded properties from: "+ filePath);
		} catch (IOException e) {
			logger.error("Error while loading the properties file: " + e.getMessage());
			throw new RuntimeException("Failed to load property file: " + filePath, e);

		}
	}

	public static synchronized PropertyReader getInstance() {
		if (instance == null) {
			instance = new PropertyReader();
		}
		return instance;
	}

	public String getProperty(String key) {
		String value = System.getProperty(key);
		if (value != null) {
			return value;
		}
		return properties.getProperty(key);
	}

}