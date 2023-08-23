package hotel.services;

import java.util.HashSet;
import java.util.Set;

import hotel.dto.GuestDTO;
import hotel.entities.Guest;
import hotel.repositories.GuestRepository;

public class GuestService {	
	
	GuestRepository guestRepository;
	
	public Set<GuestDTO> findAll() {
		Set<GuestDTO> guestsDTO = new HashSet<>();
		Set<Guest> guests = guestRepository.findAll();
		for (Guest guest : guests) {
			guestsDTO.add(new GuestDTO(guest));
		}
		return guestsDTO;
	}

	public void insert(GuestDTO dto) {
		Guest guest = new Guest();
		guest.setName(dto.getName());
		guest.setLastName(dto.getLastName());
		guest.setPhone(dto.getPhone());
		guest.setDateBirth(dto.getDateBirth());
		guest.setIdReservation(dto.getIdReservation());
		guest.setCountry(dto.getCountry());
		guestRepository.insert(guest);
	}
	
	public Guest update(Long id, GuestDTO dto) {
		Guest guest = new Guest();
		guest.setName(dto.getName());
		guest.setLastName(dto.getLastName());
		guest.setPhone(dto.getPhone());
		guest.setDateBirth(dto.getDateBirth());
		guest.setIdReservation(dto.getIdReservation());
		guest.setCountry(dto.getCountry());
		return guestRepository.update(id, guest);
	}

	public Long delete(Object obj) {
		String id_txt = obj.toString();
		Long id = (long) Integer.parseUnsignedInt(id_txt);
//		ReservationService reservaService = new ReservaService();
//		if(reservationService.delete(id) != null) {
//			id = guestRepository.delete(id);
//		} else {
//			id = null;
//		}
		return id;
	}

}
