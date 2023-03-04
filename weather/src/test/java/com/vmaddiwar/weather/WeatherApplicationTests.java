package com.vmaddiwar.weather;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vmaddiwar.weather.config.WeatherConfig;

@ContextConfiguration(classes = {WeatherConfig.class})
@TestPropertySource(locations="classpath:application.properties")
@WebAppConfiguration
class WeatherApplicationTests {

	@Test
	void contextLoads() {
	}

}
