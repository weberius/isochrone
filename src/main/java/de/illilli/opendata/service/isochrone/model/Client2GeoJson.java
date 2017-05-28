package de.illilli.opendata.service.isochrone.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.GeoJsonObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.isochrone.jdbc.ClientDTO;

/**
 * Diese Klasse mappt ein ClientDTO in ein geojson-Feature Objekt.
 */
public class Client2GeoJson {

	private Feature feature = new Feature();

	public Client2GeoJson(ClientDTO dto) throws JsonParseException, JsonMappingException, IOException {
		feature.setId(dto.getNumber() + dto.getId() + "");
		Map<String, Object> properties = new HashMap<>();
		properties.put("name", dto.getClient());
		properties.put("id", dto.getId());
		feature.setProperties(properties);

		GeoJsonObject geomertry = new ObjectMapper().readValue(dto.getGeojson(), GeoJsonObject.class);
		feature.setGeometry(geomertry);
	}

	public Feature getFeature() {
		return feature;
	}

}
