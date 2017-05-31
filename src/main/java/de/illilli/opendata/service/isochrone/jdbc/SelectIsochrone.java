package de.illilli.opendata.service.isochrone.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectIsochrone implements Select<IsochronDTO> {

	static final String sqlFileName = "/sql/selectIsochrone.sql";
	private String client;

	public SelectIsochrone(String client) {
		this.client = client;
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = SelectClients.class.getResourceAsStream(sqlFileName);
		StringBuffer sql = new StringBuffer(IOUtils.toString(inputStream));
		sql.append(" where client = ? ");
		return sql.toString();
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { this.client };
	}

	@Override
	public Class<IsochronDTO> getDtoClazz() {
		return IsochronDTO.class;
	}

}
