package hotel.services;

import java.util.HashSet;
import java.util.Set;

import hotel.dao.ReservationDAO;
import hotel.dto.ReservationDTO;
import hotel.entities.Reservation;
import hotel.repositories.ReservationRepository;
import hotel.services.utils.CalcDaily;

public class ReservationService {
	
	ReservationRepository reservationRepository = new ReservationDAO();
	
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
	
	public ReservationDTO insert(ReservationDTO dto) {
		Reservation reservation = new Reservation();
		reservation.setCheckin(dto.getCheckin());
		reservation.setCheckout(dto.getCheckout());
		reservation.setPayment(dto.getPayment());
		dto.setValue(CalcDaily.valorDiarias(500.00, reservation.getCheckin(), reservation.getCheckout()));
		reservation.setValue(dto.getValue());
		reservation.setIdReservation(dto.getIdReservation());
		
		reservation = reservationRepository.insert(reservation);
		dto = new ReservationDTO(reservation);
		return dto;
	}
	
	public Reservation update(Long id, ReservationDTO dto) {
		Reservation reservation = new Reservation();
		reservation.setCheckin(dto.getCheckin());
		reservation.setCheckout(dto.getCheckout());
		dto.setValue(CalcDaily.valorDiarias(500.00, reservation.getCheckin(), reservation.getCheckout()));
		System.out.println("Reservation: " + dto.getValue());
		reservation.setPayment(dto.getPayment());
		reservation.setIdReservation(dto.getIdReservation());
		return reservationRepository.update(id, reservation);
	}

	public Long delete(Long id) {
		reservationRepository.delete(id);
		return id;
	}

}
