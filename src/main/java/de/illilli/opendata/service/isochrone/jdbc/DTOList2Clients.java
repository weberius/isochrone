package de.illilli.opendata.service.isochrone.jdbc;

import java.util.ArrayList;
import java.util.List;

import de.illilli.opendata.service.isochrone.model.Client;

public class DTOList2Clients {

	private List<Client> data = new ArrayList<Client>();

	public DTOList2Clients(List<ClientDTO> dtoList) {
		for (ClientDTO dto : dtoList) {
			data.add(new DTO2Client(dto));
		}
	}

	public List<Client> getData() {
		return data;
	}

}
