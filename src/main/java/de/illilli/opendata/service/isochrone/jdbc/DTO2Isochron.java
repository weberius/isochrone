package de.illilli.opendata.service.isochrone.jdbc;

import de.illilli.opendata.service.isochrone.model.Isochron;

public class DTO2Isochron extends Isochron {

	public DTO2Isochron(IsochronDTO dto) {
		super.setNumber(dto.getNumber());
		super.setId(dto.getId());
		super.setClient(dto.getClient());
		super.setArea(dto.getArea());
		super.setReachfactor(dto.getReachfactor());
		super.setValue(dto.getValue());
		double[] center = new double[] { dto.getX(), dto.getY() };
		super.setCenter(center);
	}
}
