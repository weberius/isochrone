package de.illilli.opendata.service.isochrone.jdbc;

import de.illilli.opendata.service.isochrone.model.Client;

public class DTO2Client extends Client {

	public DTO2Client(ClientDTO dto) {
		super.setName(dto.getClient());
		super.setX(dto.getX());
		super.setY(dto.getY());
		super.setLink("/isochrone/service/clients/" + dto.getClient());
	}

}
