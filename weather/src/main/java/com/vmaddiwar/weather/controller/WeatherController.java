package com.vmaddiwar.weather.controller;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vmaddiwar.weather.dto.WeatherByZipResponse;
import com.vmaddiwar.weather.service.IWeatherService;

/**
 * @author vmaddiwar
 *
 */
@Controller
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private IWeatherService weatherService;

	private static final Logger logger = LogManager.getLogger(WeatherController.class);

	/**
	 * @param cacheKey
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/getWeatherByZip")
	public String getWeatherByZip(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "zipCode", required = false) String zipCode, Model model,
			RedirectAttributes redirectAttrs) throws IOException {
		logger.debug("Inside Weather controller" + zipCode);
		if(zipCode==null || zipCode.equals("")){
			model.addAttribute("invalidZipCode", "TRUE");
			return "weatherPage";
		}
		if(zipCode!=null && !zipCode.equals("")){
			String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(zipCode);
			if(!matcher.matches()){
				model.addAttribute("invalidZipCode", "TRUE");
				return "weatherPage";
			}
		}
		WeatherByZipResponse weatherByZipResponse = weatherService.getWeatherByZip(zipCode);
		redirectAttrs.addFlashAttribute("weatherByZipResponse", weatherByZipResponse);
		redirectAttrs.addFlashAttribute("zipCode", zipCode);
		return "redirect:/weather/weatherHome";
	}

	@GetMapping(path = "/weatherHome")
	public String getWelcomePage(@ModelAttribute("weatherByZipResponse") WeatherByZipResponse weatherByZipResponse,
			@ModelAttribute("zipCode") String zipCode, Model model) throws IOException {
		logger.debug("Inside getWelcomePage");
		if (weatherByZipResponse != null && weatherByZipResponse.getMain() != null) {
			model.addAttribute("temp", weatherByZipResponse.getMain().getTemp());
			model.addAttribute("feels", weatherByZipResponse.getMain().getFeels_like());
			model.addAttribute("humidity", weatherByZipResponse.getMain().getHumidity());
			model.addAttribute("cityName", weatherByZipResponse.getName());
		}
		model.addAttribute("zipCode", zipCode);

		return "weatherPage";

	}

}
