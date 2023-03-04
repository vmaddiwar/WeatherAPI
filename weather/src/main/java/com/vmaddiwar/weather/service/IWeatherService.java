package com.vmaddiwar.weather.service;

import com.vmaddiwar.weather.dto.WeatherByZipResponse;

/**
 * @author vmaddiwar
 *
 */
public interface IWeatherService {
	public WeatherByZipResponse getWeatherByZip(String zipCode);
}
	