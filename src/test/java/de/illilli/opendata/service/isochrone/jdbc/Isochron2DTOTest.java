package de.illilli.opendata.service.isochrone.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.geojson.Feature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.isochrone.askfor.AskForIsochrone;

public class Isochron2DTOTest {

	private IsochronDTO dto;

	@Before
	public void setUp() throws Exception {
		String client = "test";
		InputStream input = this.getClass().getResourceAsStream("/test.0.geojson");
		AskFor<List<Feature>> askfor = new AskForIsochrone(input);
		List<Feature> features = askfor.getData();

		dto = new Isochron2DTO(client, features.get(0));
	}

	@Test
	public void testArea() throws JsonParseException, JsonMappingException, IOException, SQLException {

		double expected = 4694457.82;
		double actual = dto.getArea();
		double delta = 0.0;

		Assert.assertEquals(expected, actual, delta);
	}

	@Test
	public void testValue() throws JsonParseException, JsonMappingException, IOException, SQLException {

		int expected = 600;
		int actual = dto.getValue();

		Assert.assertEquals(expected, actual);
	}

}
