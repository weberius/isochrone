package de.illilli.opendata.service.isochrone.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.geojson.Feature;
import org.postgis.PGgeometry;

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

		setId(NumberUtils.toInt(feature.getId()));
		setClient(client);
		setValue((Integer) feature.getProperty("value"));
		setArea((Double) feature.getProperty("area"));
		setReachfactor((Double) feature.getProperty("reachfactor"));
		// setCenter
		List<Double> center = feature.getProperty("center");
		org.postgis.Geometry pgPoint = new org.postgis.Point(center.get(0), center.get(1));
		pgPoint.setSrid(SRID);
		setCenter(new PGgeometry(pgPoint));
		// setGeom
		org.postgis.Polygon[] polygons = null;

		org.geojson.Polygon geojsonPolygon = (org.geojson.Polygon) feature.getGeometry();
		List<List<org.geojson.LngLatAlt>> coordinates = geojsonPolygon.getCoordinates();
		for (List<org.geojson.LngLatAlt> lngLatAltList : coordinates) {

			List<org.postgis.Point> pointsList = new ArrayList<org.postgis.Point>();
			for (org.geojson.LngLatAlt lngLatAlt : lngLatAltList) {
				org.postgis.Point point = new org.postgis.Point(lngLatAlt.getLongitude(), lngLatAlt.getLatitude());
				pointsList.add(point);
			}
			org.postgis.Point[] points = new org.postgis.Point[pointsList.size()];
			points = pointsList.toArray(points);
			org.postgis.LinearRing ring = new org.postgis.LinearRing(points);
			org.postgis.LinearRing[] rings = new org.postgis.LinearRing[] { ring };
			org.postgis.Polygon polygon = new org.postgis.Polygon(rings);
			polygons = new org.postgis.Polygon[] { polygon };
		}
		org.postgis.Geometry pgMultiPolygon = new org.postgis.MultiPolygon(polygons);

		pgMultiPolygon.setSrid(SRID);
		setGeom(new PGgeometry(pgMultiPolygon));
		logger.info(this.toString());
	}

}
