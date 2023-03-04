package com.vmaddiwar.weather;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.vmaddiwar.weather.config.WeatherConfig;
import com.vmaddiwar.weather.security.WeatherSecurityConfiguration;

/**
 * @author vmaddiwar
 *
 */

@SpringBootTest
@ContextConfiguration(classes = { WeatherConfig.class })
@AutoConfigureMockMvc
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WeatherSecurityConfiguration.class) })
public class WeatherZipCodeTest {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void weatherByZip() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/weather/getWeatherByZip").param("zipCode", "02148")
				.accept(MediaType.APPLICATION_XHTML_XML)).andDo(print()).andExpect(status().is3xxRedirection());
	}
}
