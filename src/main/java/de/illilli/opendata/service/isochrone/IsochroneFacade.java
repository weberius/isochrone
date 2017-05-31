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
import de.illilli.opendata.service.isochrone.jdbc.DTOList2Isochrone;
import de.illilli.opendata.service.isochrone.jdbc.IsochronDTO;
import de.illilli.opendata.service.isochrone.jdbc.SelectIsochrone;
import de.illilli.opendata.service.isochrone.model.Isochron;

public class IsochroneFacade implements Facade {

	List<IsochronDTO> dtoList = new ArrayList<IsochronDTO>();

	public IsochroneFacade(String client) throws SQLException, NamingException, IOException {
		Connection conn = ConnectionFactory.getConnection();
		dtoList = new SelectListDao<IsochronDTO>(new SelectIsochrone(client), conn).execute();
		conn.close();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		List<Isochron> data = new DTOList2Isochrone(this.dtoList).getData();
		return new Gson().toJson(data);
	}

}
