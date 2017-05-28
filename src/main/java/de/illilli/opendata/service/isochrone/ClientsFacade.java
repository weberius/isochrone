package de.illilli.opendata.service.isochrone;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.isochrone.jdbc.ClientDTO;
import de.illilli.opendata.service.isochrone.jdbc.DTOList2Clients;
import de.illilli.opendata.service.isochrone.jdbc.SelectClients;
import de.illilli.opendata.service.isochrone.model.Client;

public class ClientsFacade implements Facade {

	private List<ClientDTO> dtoList = new ArrayList<ClientDTO>();

	public ClientsFacade() throws SQLException, NamingException, IOException {
		Connection conn = ConnectionFactory.getConnection();
		dtoList = new SelectListDao<ClientDTO>(new SelectClients(), conn).execute();
		conn.close();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		List<Client> data = new DTOList2Clients(this.dtoList).getData();
		return new Gson().toJson(data);
	}

}
