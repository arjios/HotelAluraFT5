package hotel.controllers;

import java.sql.Date;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import hotel.dto.GuestDTO;
import hotel.services.GuestService;

public class GuestController {

	GuestService guestService = new GuestService();
	
	public Set<GuestDTO> findAll() {
		Set<GuestDTO> guests = guestService.findAll();
		return guests;
	}
	
	public void insert(GuestDTO dto) {
		guestService.insert(dto);
	}
	
	public GuestDTO update(DefaultTableModel dtm) {
		GuestDTO dto = new GuestDTO();
		
		for(int i=1; i<dtm.getRowCount(); i++) {
			dto.setId(Long.valueOf(dtm.getValueAt(i, 0).toString()));
			dto.setName(dtm.getValueAt(i, 1).toString());
			dto.setLastName(dtm.getValueAt(i, 2).toString());
			dto.setDateBirth(Date.valueOf(dtm.getValueAt(i, 3).toString()));		
			dto.setCountry(dtm.getValueAt(i, 4).toString());
			dto.setPhone(dtm.getValueAt(i, 5).toString());
			dto.setIdReservation(Integer.valueOf(dtm.getValueAt(i, 6).toString()));		
			guestService.update(Long.valueOf(dto.getIdReservation()), dto);
			dto = new GuestDTO();
		}
		return dto;
	}

	public Long delete(Object obj) {
		Long id = guestService.delete(obj);
		return id;
	}
	
}
