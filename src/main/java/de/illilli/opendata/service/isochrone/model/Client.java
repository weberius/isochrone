package de.illilli.opendata.service.isochrone.model;

public class Client {

	private String name;
	private double x;
	private double y;
	private String link;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", x=" + x + ", y=" + y + ", link=" + link + "]";
	}

}
