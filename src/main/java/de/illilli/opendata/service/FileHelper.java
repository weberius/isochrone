package de.illilli.opendata.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

	public static final String GEOJSON_TYPE = "geojson";
	public static final String JSON_TYPE = "json";

	/**
	 * Gibt das Arbeitsverzeichnis als File-Objekt zurück. Das Verzeichnis wird
	 * in config.properties, "data directory" definiert. Es ist notwendig den
	 * client zu spezifizieren. Wenn das entsprechende Verzeichnis nicht
	 * existiert, wird es automatisch angelegt.
	 * 
	 * @param client
	 * @return
	 */
	public static File getDataDirectory(String client) {

		String dir = Config.getProperty("data.directory") + File.separator + client + File.separator;
		File directory = new File(dir);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		return directory;
	}

	/**
	 * Gibt alle Unterverzeichnisse des data directory zurück. Es werden keine
	 * Dateien berücksichtigt.
	 * 
	 * @return
	 */
	public static List<File> getDataDirectories() {

		List<File> dirs = new ArrayList<>();
		File dir = new File(Config.getProperty("data.directory"));
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				dirs.add(file);
			}
		}
		return dirs;
	}

	/**
	 * Gibt alle Dateien im client-Verzeichnis zurück. Sind weitere
	 * Unterverzeichnisse vorhanden, werden diese ignoriert.
	 * 
	 * @param client
	 * @return Liste der Dateien.
	 */
	public static List<File> getFiles(String client) {

		List<File> files = new ArrayList<>();
		File dir = getDataDirectory(client);
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				files.add(file);
			}
		}
		return files;
	}
}
