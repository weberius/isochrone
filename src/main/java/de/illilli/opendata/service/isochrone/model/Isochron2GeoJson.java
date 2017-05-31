package de.illilli.opendata.service.isochrone.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.GeoJsonObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.isochrone.jdbc.IsochronDTO;

public class Isochron2GeoJson {

	private Feature feature = new Feature();

	public Isochron2GeoJson(IsochronDTO dto) throws JsonParseException, JsonMappingException, IOException {
		feature.setId(dto.getNumber() + "." + dto.getId());
		Map<String, Object> properties = new HashMap<>();
		properties.put("name", dto.getClient());
		properties.put("id", dto.getId());
		properties.put("area", dto.getArea());
		double[] center = new double[] { dto.getX(), dto.getY() };
		properties.put("center", center);
		properties.put("reachfactor", dto.getReachfactor());
		properties.put("value", dto.getValue());
		feature.setProperties(properties);

		GeoJsonObject geomertry = new ObjectMapper().readValue(dto.getGeojson(), GeoJsonObject.class);
		feature.setGeometry(geomertry);
	}

	public Feature getFeature() {
		return feature;
	}

}
