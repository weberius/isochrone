package de.illilli.opendata.service.isochrone.jdbc;

public class ClientDTO {

	private String client;
	private double x;
	private double y;
	private double geojson;

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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

	public double getGeojson() {
		return geojson;
	}

	public void setGeojson(double geojson) {
		this.geojson = geojson;
	}

	@Override
	public String toString() {
		return "ClientDTO [client=" + client + ", x=" + x + ", y=" + y + ", geojson=" + geojson + "]";
	}

}
