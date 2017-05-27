package de.illilli.opendata.service.isochrone;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class LoadFacadeTest {

	private static final Logger logger = Logger.getLogger(LoadFacadeTest.class);

	public static void main(String[] args) throws SQLException, IOException, NamingException {
		ConnectionEnvironment.setUpConnectionForJndi();
		String client = "test";
		Facade facade = new LoadFacade(client);
		logger.info(facade.getJson());
	}

}
