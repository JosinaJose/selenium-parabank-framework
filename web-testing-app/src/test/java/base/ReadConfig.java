package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import base.ReadConfig;

public class ReadConfig {

	Properties properties;

	public ReadConfig() {
		try {
			FileInputStream fis = new FileInputStream(
					"D:\\Josina\\Josina_Job_Hunting\\Java_Selenium_Project\\JavaSelenium-Project\\Regression\\web-testing-app\\Configuration\\config.properties");
			properties = new Properties();
			properties.load(fis);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public String getUsername() {
		return properties.getProperty("username");
	}

	public String getPassword() {
		return properties.getProperty("password");
	}
}
