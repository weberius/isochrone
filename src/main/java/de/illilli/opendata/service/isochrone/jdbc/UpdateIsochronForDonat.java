package de.illilli.opendata.service.isochrone.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;

public class UpdateIsochronForDonat implements InsertOrUpdate {

	static final String sqlFileName = "/sql/updateIsochronForDonut.sql";
	private IsochronDTO dto;

	public UpdateIsochronForDonat(IsochronDTO dto) {
		this.dto = dto;
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { //
				dto.getCenter(), //
				dto.getId(), //
				dto.getCenter(), //
				dto.getId() - 1, //
				dto.getClient(), //
				dto.getCenter(), //
				dto.getId() };
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertIsochrone.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

}
