package de.illilli.opendata.service.isochrone.jdbc;

import java.sql.Timestamp;

import org.postgis.PGgeometry;

/**
 * <p>
 * Dies ist die Data Transfer Klasse für ein Isochron.
 * </p>
 * <p>
 * Achtung: der Identifier im System ist number. id wird bereits an anderer
 * Stelle verwendet.
 * </p>
 * 
 * <pre>
 *  CREATE TABLE ISOCHRONE (
      number      SERIAL PRIMARY KEY, 
      id          integer,
      client      varchar(128),
      value       integer, 
      area        double precision,
      reachfactor double precision,
      modtime     timestamp DEFAULT current_timestamp
    );
    SELECT AddGeometryColumn ('public','isochrone','center',4326,'POINT',2);
    SELECT AddGeometryColumn ('public','isochrone','geom',4326,'MULTIPOLYGON',2);
 * 
 * </pre>
 */
public class IsochronDTO {

	private int number;
	private int id;
	private String client;
	private int value;
	private double area;
	private double reachfactor;
	private double x;
	private double y;
	private String geojson;
	private PGgeometry center;
	private PGgeometry geom;
	private Timestamp modtime;

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

	public PGgeometry getCenter() {
		return center;
	}

	public void setCenter(PGgeometry center) {
		this.center = center;
	}

	public PGgeometry getGeom() {
		return geom;
	}

	public void setGeom(PGgeometry geom) {
		this.geom = geom;
	}

	public Timestamp getModtime() {
		return modtime;
	}

	public void setModtime(Timestamp modtime) {
		this.modtime = modtime;
	}

	@Override
	public String toString() {
		return "IsochronDTO [number=" + number + ", id=" + id + ", client=" + client + ", value=" + value + ", area="
				+ area + ", reachfactor=" + reachfactor + ", x=" + x + ", y=" + y + ", geojson=" + geojson + ", center="
				+ center + ", geom=" + geom + ", modtime=" + modtime + "]";
	}

}
