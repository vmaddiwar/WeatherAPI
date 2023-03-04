package com.vmaddiwar.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snow {

	@JsonProperty("1h")
	private Double _1h;

	@JsonProperty("1h")
	public Double get1h() {
		return _1h;
	}

	@JsonProperty("1h")
	public void set1h(Double _1h) {
	this._1h = _1h;
	}
}