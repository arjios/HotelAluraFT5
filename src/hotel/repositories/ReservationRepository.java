package hotel.repositories;

import java.util.Set;

import hotel.entities.Reservation;

public interface ReservationRepository {
	
	Set<Reservation> findAll();
	Reservation findByReservation(Long id);
	Reservation insert(Reservation reservation);
	Reservation update(Long id, Reservation reservation);
	Long delete(Long id);
	
}
