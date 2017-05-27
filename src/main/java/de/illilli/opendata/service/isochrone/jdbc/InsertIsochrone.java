package de.illilli.opendata.service.isochrone.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;

public class InsertIsochrone implements InsertOrUpdate {

	static final String sqlFileName = "/sql/insertIsochrone.sql";
	private IsochronDTO dto;

	public InsertIsochrone(IsochronDTO dto) {
		this.dto = dto;
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { //
				dto.getId(), //
				dto.getClient(), //
				dto.getValue(), //
				dto.getArea(), //
				dto.getReachfactor(), //
				dto.getCenter(), //
				dto.getGeom() //
		};
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertIsochrone.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

}
