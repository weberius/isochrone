package de.illilli.opendata.service.isochrone;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.DefaultFacade;
import de.illilli.opendata.service.Facade;

@Path("/")
public class Service {

	private final static Logger logger = Logger.getLogger(Service.class);
	public static final String ENCODING = Config.getProperty("encoding");

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	/**
	 * <p>
	 * Diese Schnittstelle erlaubt es zu prüfen, ob der Service generell
	 * erreichbar ist. die zu erwartende Antwort lautet
	 * </p>
	 * 
	 * <pre>
	 * {"level":"info","msg":"alive"}
	 * </pre>
	 *
	 * <p>
	 * Beispiel:
	 * <a href="http://localhost:8080/isochrone/service/ping">/isochrone/service
	 * /ping</a>
	 * </p>
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/ping")
	public String ping() throws JsonProcessingException {
		Facade facade = new DefaultFacade(DefaultFacade.INFO, "alive");
		return facade.getJson();
	}

	/**
	 * <p>
	 * Mit Hilfe dieser Schnittstelle werden die Daten in die Datenbank
	 * eingelesen. Die Daten müssen im GeoJson-Format und im in der config
	 * definierten Verzeichnis vorliegen. Wird der Service aufgerufen, werden
	 * die bisherigen Daten gelöscht und die ggf. aktualisierten Daten werden
	 * eingelesen.
	 * </p>
	 * <p>
	 * Von der Kommandozeile kann die Schnittstelle mit folgendem Kommando
	 * aufgerufen werden:
	 * </p>
	 * <code>curl -X PUT http://localhost:8080/baumkataster/service/load/{client}</code>
	 * 
	 * @param client
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/load/{client}")
	public String load(@PathParam("client") String client)
			throws JsonParseException, JsonMappingException, IOException, SQLException, NamingException {

		logger.info("/load called");
		Facade facade = null;

		if ("localhost".equals(request.getServerName())) {
			facade = new LoadFacade(client);
		} else {
			facade = new DefaultFacade(DefaultFacade.INFO, "Data not loaded; Method only available on localhost");
		}
		return facade.getJson();
	}

}
