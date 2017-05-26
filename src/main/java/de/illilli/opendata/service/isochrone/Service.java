package de.illilli.opendata.service.isochrone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

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

}
