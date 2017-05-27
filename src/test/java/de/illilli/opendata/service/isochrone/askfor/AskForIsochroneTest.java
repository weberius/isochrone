package de.illilli.opendata.service.isochrone.askfor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.geojson.Feature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.illilli.opendata.service.AskFor;

public class AskForIsochroneTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetNumberOfPolygonsData() throws JsonParseException, JsonMappingException, IOException {
		InputStream input = this.getClass().getResourceAsStream("/test.0.geojson");
		AskFor<List<Feature>> askfor = new AskForIsochrone(input);
		List<Feature> features = askfor.getData();
		int expected = 6;
		int actual = features.size();
		Assert.assertEquals(expected, actual);
	}

}
