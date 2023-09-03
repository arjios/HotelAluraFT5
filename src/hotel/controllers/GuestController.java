package hotel.controllers;

import java.sql.Date;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import hotel.dao.GuestDAO;
import hotel.dto.GuestDTO;
import hotel.services.GuestService;

public class GuestController {

	GuestService guestService = new GuestService(new GuestDAO());
	
	public Set<GuestDTO> findAll() {
		Set<GuestDTO> guests = guestService.findAll();
		return guests;
	}
	
	public GuestDTO insert(GuestDTO dto) {
		return guestService.insert(dto);
	}
	
	public GuestDTO update(Integer linha, DefaultTableModel dtm) {
		
		GuestDTO dto = new GuestDTO();
		
		dto.setId(Long.valueOf(dtm.getValueAt(linha, 0).toString()));
		dto.setName(dtm.getValueAt(linha, 1).toString());
		dto.setLastName(dtm.getValueAt(linha, 2).toString());
		dto.setDateBirth(Date.valueOf(dtm.getValueAt(linha, 3).toString()));		
		dto.setCountry(dtm.getValueAt(linha, 4).toString());
		dto.setPhone(dtm.getValueAt(linha, 5).toString());
		
		guestService.update(dto.getId(), dto);		

		return dto;
	}

	public Long delete(Object obj) {
		Long id = guestService.delete(obj);
		return id;
	}
	
}
