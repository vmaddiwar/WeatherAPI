package com.vmaddiwar.weather.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmaddiwar.weather.dto.WeatherByZipResponse;

/**
 * @author vmaddiwar
 *
 */
@Service
public class WeatherServiceImpl implements IWeatherService {
	
	private static final Logger logger = LogManager.getLogger(WeatherServiceImpl.class);

	@Autowired
	private WebClient.Builder webClientXMlBuilder;

	private WebClient webClient;

	@Override
	public WeatherByZipResponse getWeatherByZip(String zipCode) {
		webClient = webClientXMlBuilder.baseUrl(
				"https://api.openweathermap.org/data/2.5/weather?APPID=1b4127cb6979519b4ba01da789ae42e7&units=imperial&q="
						+ zipCode).build();
		ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {
		};
		String response = webClient.post().headers(httpHeaders -> {
		}).retrieve().bodyToMono(returnType).block();

		ObjectMapper mapper = new ObjectMapper();
		WeatherByZipResponse weatherByZipResponse = null;
		try {
			weatherByZipResponse = mapper.readValue(response, WeatherByZipResponse.class);
		} catch (JsonMappingException e) {
			logger.error("Error during Json Mapping" +e.getMessage());
		} catch (JsonProcessingException e) {
			logger.error("Error during Json Processing" +e.getMessage());
		}

		return weatherByZipResponse;

	}

}
