package de.illilli.opendata.service.isochrone.model;

import java.util.Arrays;

public class Isochron {

	private int number;
	private int id;
	private String client;
	private int value;
	private double area;
	private double reachfactor;
	private double[] center;
	private String geojson;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getReachfactor() {
		return reachfactor;
	}

	public void setReachfactor(double reachfactor) {
		this.reachfactor = reachfactor;
	}

	public double[] getCenter() {
		return center;
	}

	public void setCenter(double[] center) {
		this.center = center;
	}

	public String getGeojson() {
		return geojson;
	}

	public void setGeojson(String geojson) {
		this.geojson = geojson;
	}

	@Override
	public String toString() {
		return "Isochron [number=" + number + ", id=" + id + ", client=" + client + ", value=" + value + ", area="
				+ area + ", reachfactor=" + reachfactor + ", center=" + Arrays.toString(center) + ", geojson=" + geojson
				+ "]";
	}

}
