package com.vmaddiwar.weather;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author vmaddiwar
 *
 */
@SpringBootApplication
public class WeatherApplication {

	private static final Logger logger = LogManager.getLogger(WeatherApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
		logger.debug("Inside WeatherApplication Main Method");
	}

}
