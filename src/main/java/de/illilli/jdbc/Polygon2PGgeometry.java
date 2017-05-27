package de.illilli.jdbc;

import java.util.ArrayList;
import java.util.List;

public class Polygon2PGgeometry {

	private org.postgis.Geometry geometry;

	public Polygon2PGgeometry(org.geojson.Polygon geojsonPolygon) {

		org.postgis.Polygon[] polygons = null;

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
		geometry = new org.postgis.MultiPolygon(polygons);

	}

	public org.postgis.Geometry getGeometry() {
		return geometry;
	}

}
