package de.illilli.opendata.service.isochrone.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;

public class DeleteIsochroneByClient implements InsertOrUpdate {

	static final String sqlFileName = "/sql/deleteIsochrone.sql";
	private String client;

	public DeleteIsochroneByClient(String client) {
		this.client = client;
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { this.client };
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = DeleteIsochroneByClient.class.getResourceAsStream(sqlFileName);
		StringBuffer sql = new StringBuffer(IOUtils.toString(inputStream));
		sql.append(" where client = ? ");
		return sql.toString();
	}

}
