package de.illilli.opendata.service.isochrone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.geojson.Feature;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.isochrone.jdbc.ClientDTO;
import de.illilli.opendata.service.isochrone.model.Client2GeoJson;

public class ClientsGeojsonFacade extends ClientsFacade implements Facade {

	private FeatureCollection featureCollection = new FeatureCollection();
	private List<Feature> featureList = new ArrayList<Feature>();

	public ClientsGeojsonFacade() throws SQLException, NamingException, IOException {
		super();
		for (ClientDTO dto : dtoList) {
			featureList.add(new Client2GeoJson(dto).getFeature());
		}
		featureCollection.addAll(featureList);
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
