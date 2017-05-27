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
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.FileHelper;
import de.illilli.opendata.service.isochrone.askfor.AskForIsochrone;
import de.illilli.opendata.service.isochrone.jdbc.DeleteIsochroneByClient;
import de.illilli.opendata.service.isochrone.jdbc.InsertIsochronByClient;
import de.illilli.opendata.service.isochrone.jdbc.Isochron2DTO;

/**
 * Die LoadFacade erwartet die zu verarbeitenden Dateien im per "data.directory"
 * verabredeten Verzeichnis zu finden. Liegen keine Dateien vor, wird der
 * Prozess ohne weiteres abgebrochen.
 */
public class LoadFacade implements Facade {

	private static final Logger logger = Logger.getLogger(LoadFacade.class);

	private String json;

	public LoadFacade(String client) throws SQLException, IOException, NamingException {

		Connection conn = ConnectionFactory.getConnection();

		File dir = FileHelper.getDataDirectory(client);
		logger.info("use directory '" + dir.getAbsolutePath() + "'");
		List<File> files = FileHelper.getFiles(client);

		for (File file : files) {
			// read just geojson-Files
			if (file.getName().endsWith(FileHelper.GEOJSON_TYPE)) {
				// 1. read file and deserialize to geojson-Feature List
				AskFor<List<Feature>> askfor = new AskForIsochrone(new FileInputStream(file));
				List<Feature> features = askfor.getData();
				// 2. bisherige Daten löschen
				new UpdateDao(new DeleteIsochroneByClient(client), conn).execute();
				// 3. Daten neu einlesen
				for (Feature feature : features) {
					new InsertDao(new InsertIsochronByClient(new Isochron2DTO(client, feature)), conn).execute();
				}
			}
		}

		conn.close();

	}

	@Override
	public String getJson() throws JsonProcessingException {
		return json;
	}

}
