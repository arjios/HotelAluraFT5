package hotel.services;

import java.util.HashSet;
import java.util.Set;

import hotel.dto.ReservationDTO;
import hotel.entities.Reservation;
import hotel.repositories.ReservationRepository;

public class ReservationService {
	
	ReservationRepository reservationRepository;
	
	public Set<ReservationDTO> findAll() {
		Set<ReservationDTO> dto = new HashSet<>();
		Set<Reservation> reservations = reservationRepository.findAll();
		for (Reservation reservation : reservations) {
			dto.add(new ReservationDTO(reservation));
		}
		return dto;	
	}
	
	public ReservationDTO findByReservation(Long id) {
		Reservation reservation = reservationRepository.findByReservation(id);
		return new ReservationDTO(reservation);
	}
	
	public void insert(ReservationDTO dto) {
		Reservation reservation = new Reservation();
		reservation.setCheckin(dto.getCheckin());
		reservation.setCheckout(dto.getCheckout());
		reservation.setPayment(dto.getPayment());
		reservation.setIdReservation(dto.getIdReservation());
		reservationRepository.insert(reservation);
	}
	
	public Reservation update(Long id, Reservation dto) {
		Reservation reservation = new Reservation();
		reservation.setCheckin(dto.getCheckin());
		reservation.setCheckout(dto.getCheckout());
		reservation.setPayment(dto.getPayment());
		reservation.setIdReservation(dto.getIdReservation());
		return reservationRepository.update(id, reservation);
	}

	public Long delete(Long id) {
		reservationRepository.delete(id);
		return id;
	}


}
