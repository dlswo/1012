package org.store.domain;

import lombok.Data;

@Data
public class StoreVO {
	
	private String name;
	private double lat;
	private double lng;
	private String desc;
	public StoreVO(String name, double lat, double lng, String desc) {
		super();
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.desc = desc;
	}
	
	public double calc(double lat, double lng) {
		
		return Math.sqrt(
				Math.pow(this.lat - lat, 2) +
				Math.pow(this.lng - lng, 2));
		
	}
	
}
