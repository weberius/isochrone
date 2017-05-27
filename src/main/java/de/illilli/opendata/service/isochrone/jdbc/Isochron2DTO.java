package de.illilli.opendata.service.isochrone.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.geojson.Feature;
import org.postgis.PGgeometry;

import de.illilli.jdbc.Polygon2PGgeometry;

/**
 * Diese Klasse erweitert IsochronDTO und konvertiert eine geojson Feature in
 * ein IsochronDTO. Dieses kann dann ohne weiteres in die Datenbank geschrieben
 * werden.
 *
 */
public class Isochron2DTO extends IsochronDTO {

	public static final int SRID = 4326;

	private static final Logger logger = Logger.getLogger(Isochron2DTO.class);

	public Isochron2DTO(String client, Feature feature) throws SQLException {

		// setId
		setId((Integer) feature.getProperty("id"));
		// setClient
		setClient(client);
		// setValue
		setValue((Integer) feature.getProperty("value"));
		// setArea
		setArea((Double) feature.getProperty("area"));
		// setReachfactor
		setReachfactor((Double) feature.getProperty("reachfactor"));
		// setCenter
		List<Double> center = feature.getProperty("center");
		org.postgis.Geometry pgPoint = new org.postgis.Point(center.get(0), center.get(1));
		pgPoint.setSrid(SRID);
		setCenter(new PGgeometry(pgPoint));
		// setGeom
		org.geojson.Polygon geojsonPolygon = (org.geojson.Polygon) feature.getGeometry();
		org.postgis.Geometry pgMultiPolygon = new Polygon2PGgeometry(geojsonPolygon).getGeometry();
		pgMultiPolygon.setSrid(SRID);
		setGeom(new PGgeometry(pgMultiPolygon));

		logger.info(this.toString());
	}

}
