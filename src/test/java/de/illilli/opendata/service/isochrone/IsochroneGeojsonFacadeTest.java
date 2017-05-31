package de.illilli.opendata.service.isochrone;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class IsochroneGeojsonFacadeTest {

	private static final Logger logger = Logger.getLogger(IsochroneGeojsonFacadeTest.class);

	public static void main(String[] args) throws SQLException, NamingException, IOException {
		ConnectionEnvironment.setUpConnectionForJndi();
		Facade facade = new IsochroneGeojsonFacade("test");
		logger.info(facade.getJson());
	}

}
