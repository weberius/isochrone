package de.illilli.opendata.service.isochrone.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectClients implements Select<ClientDTO> {

	static final String sqlFileName = "/sql/selectClients.sql";

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = SelectClients.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

	@Override
	public Object[] getParameter() {
		return new Object[0];
	}

	@Override
	public Class<ClientDTO> getDtoClazz() {
		return ClientDTO.class;
	}

}
