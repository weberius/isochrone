package de.illilli.opendata.service.isochrone.jdbc;

public class ClientDTO {

	private String client;
	private int id;
	private int number;
	private double x;
	private double y;
	private String geojson;

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getGeojson() {
		return geojson;
	}

	public void setGeojson(String geojson) {
		this.geojson = geojson;
	}

	@Override
	public String toString() {
		return "ClientDTO [client=" + client + ", id=" + id + ", number=" + number + ", x=" + x + ", y=" + y
				+ ", geojson=" + geojson + "]";
	}

}
