package hotel.repositories;

import java.util.Set;

import hotel.entities.Guest;

public interface GuestRepository {

	Set<Guest> findAll();
	Guest findByIdGuest(Long id);
	void insert(Guest guest);
	Guest update(Long id_reserva, Guest guest);
	Long delete(Long id);
	
}
