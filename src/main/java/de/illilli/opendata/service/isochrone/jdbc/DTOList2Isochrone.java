package de.illilli.opendata.service.isochrone.jdbc;

import java.util.ArrayList;
import java.util.List;

import de.illilli.opendata.service.isochrone.model.Isochron;

public class DTOList2Isochrone {

	private List<Isochron> data = new ArrayList<Isochron>();

	public DTOList2Isochrone(List<IsochronDTO> dtoList) {
		for (IsochronDTO dto : dtoList) {
			data.add(new DTO2Isochron(dto));
		}
	}

	public List<Isochron> getData() {
		return data;
	}

}
