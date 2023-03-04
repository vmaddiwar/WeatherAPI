package com.vmaddiwar.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vmaddiwar.weather.service.IWeatherService;
import com.vmaddiwar.weather.service.WeatherServiceImpl;

/**
 * @author vmaddiwar
 *
 */
@Configuration("weatherConfig")
@EnableWebMvc
@ComponentScan( basePackages = { "..." } )
public class WeatherConfig {

	@Bean
	public IWeatherService weatherService() {
		return new WeatherServiceImpl();
	}

	@Bean
	public WebClient webClient() {
		return WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}
	
	@Bean
	public Builder webClientXMlBuilder() {
		return WebClient.builder();
	}

}
