package hotel.services;

import java.util.HashSet;
import java.util.Set;

import hotel.dao.ReservationDAO;
import hotel.dto.GuestDTO;
import hotel.entities.Guest;
import hotel.repositories.GuestRepository;

public class GuestService {	
	
	GuestRepository guestRepository;
	
	public GuestService(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}
	
	ReservationService reservationService = new ReservationService(new ReservationDAO());
	
	public Set<GuestDTO> findAll() {
		Set<GuestDTO> guestsDTO = new HashSet<>();
		Set<Guest> guests = guestRepository.findAll();
		for (Guest guest : guests) {
			guestsDTO.add(new GuestDTO(guest));
		}
		return guestsDTO;
	}
	
	public GuestDTO findGuest(Long id) {
		Guest guest = guestRepository.findByIdGuest(id);
		return new GuestDTO(guest);
	}

	public GuestDTO insert(GuestDTO dto) {
		Guest guest = new Guest();
		guest.setName(dto.getName());
		guest.setLastName(dto.getLastName());
		guest.setPhone(dto.getPhone());
		guest.setDateBirth(dto.getDateBirth());
		guest.setCountry(dto.getCountry());
		return new GuestDTO(guestRepository.insert(guest));
	}
	
	public Guest update(Long id, GuestDTO dto) {
		Guest guest = new Guest();
		guest.setName(dto.getName());
		guest.setLastName(dto.getLastName());
		guest.setPhone(dto.getPhone());
		guest.setDateBirth(dto.getDateBirth());
		guest.setCountry(dto.getCountry());
		return guestRepository.update(id, guest);
	}

	public Long delete(Object obj) {
		String id_txt = obj.toString();
		Long id = (long) Integer.parseUnsignedInt(id_txt);
		if(reservationService.delete(id) != null) {
			id = guestRepository.delete(id);
		} else {
			id = null;
		}
		return id;
	}

}
