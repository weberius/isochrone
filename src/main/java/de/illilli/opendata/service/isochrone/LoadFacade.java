package de.illilli.opendata.service.isochrone;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.geojson.Feature;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.InsertDao;
import de.illilli.jdbc.UpdateDao;
import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.DefaultFacade;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.FileHelper;
import de.illilli.opendata.service.isochrone.askfor.AskForIsochrone;
import de.illilli.opendata.service.isochrone.jdbc.DeleteIsochroneByClient;
import de.illilli.opendata.service.isochrone.jdbc.InsertIsochrone;
import de.illilli.opendata.service.isochrone.jdbc.Isochron2DTO;
import de.illilli.opendata.service.isochrone.jdbc.UpdateIsochron;
import de.illilli.opendata.service.isochrone.jdbc.UpdateIsochronForDonat;

/**
 * Die LoadFacade erwartet die zu verarbeitenden Dateien im per "data.directory"
 * verabredeten Verzeichnis zu finden. Liegen keine Dateien vor, wird der
 * Prozess ohne weiteres abgebrochen.
 */
public class LoadFacade implements Facade {

	private static final Logger logger = Logger.getLogger(LoadFacade.class);

	private String json;

	public LoadFacade(String client) throws SQLException, IOException, NamingException {
		int fileCounter = 0;
		int featureCounter = 0;
		// first open connection
		Connection conn = ConnectionFactory.getConnection();
		// bisherige Daten löschen
		new UpdateDao(new DeleteIsochroneByClient(client), conn).execute();
		// get directory to check for files
		File dir = FileHelper.getDataDirectory(client);
		logger.info("use directory '" + dir.getAbsolutePath() + "'");
		List<File> files = FileHelper.getFiles(client);
		// read files to process
		for (File file : files) {
			// read just geojson-Files
			if (file.getName().endsWith(FileHelper.GEOJSON_TYPE)) {
				// read file and deserialize to geojson-Feature List
				AskFor<List<Feature>> askfor = new AskForIsochrone(new FileInputStream(file));
				List<Feature> features = askfor.getData();
				// insert data
				for (Feature feature : features) {
					new InsertDao(new InsertIsochrone(new Isochron2DTO(client, feature)), conn).execute();
					featureCounter++;
				}
				// update for donuts
				// geom has to be already in database
				for (Feature feature : features) {
					if ((Integer) feature.getProperty("id") == 0) {
						new UpdateDao(new UpdateIsochron(new Isochron2DTO(client, feature)), conn).execute();
					} else {
						new UpdateDao(new UpdateIsochronForDonat(new Isochron2DTO(client, feature)), conn).execute();
					}
				}
			}
			fileCounter++;
		}
		// dont't forget to close connection
		// closing at end of procedure
		conn.close();
		String msg = fileCounter + " files processed; " + featureCounter + " features loaded";
		json = new DefaultFacade(DefaultFacade.INFO, msg).getJson();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return json;
	}

}
