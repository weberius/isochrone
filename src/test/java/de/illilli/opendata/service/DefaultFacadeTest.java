package de.illilli.opendata.service;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Dieser Test prüft, ob die DefaultFacade bei übergabe eines levels und einer
 * msg die richtige Antwort zurückliefert. Die zu erwartende Rückgabe wird aus
 * <code>/src/text/resources/DefaultFacade.test.json</code> ausgelesen.
 */
public class DefaultFacadeTest {

	@Test
	public void test() throws IOException {
		String level = DefaultFacade.INFO;
		String msg = "test";
		Facade facade = new DefaultFacade(level, msg);
		String expected = IOUtils.toString(this.getClass().getResourceAsStream("/DefaultFacade.test.json"));
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

}
