package hotel.services;

import java.util.HashSet;
import java.util.Set;

import hotel.dao.ReservationDAO;
import hotel.dto.DailyDTO;
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
		reservation.setId(dto.getId());
		reservation.setCheckin(dto.getCheckin());
		reservation.setCheckout(dto.getCheckout());
		dto.setValue(CalcDaily.valorDiarias(500.00, reservation.getCheckin(), reservation.getCheckout()));
		reservation.setValue(dto.getValue());
		reservation.setPayment(dto.getPayment());
		reservation.setIdReservation(id);
		return reservationRepository.update(dto.getIdReservation(), reservation);
	}

	public Long delete(Long id) {
		System.out.println("Service delete: " + id);
		reservationRepository.delete(id);
		return id;
	}
	
	public Double DailyTotalService(DailyDTO dailyDTO) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
//		String formatE = formatter.format(dailyDTO.dateCheckin());
//		String formatS = formatter.format(dailyDTO.dateCheckout());
//		java.sql.Date in = java.sql.Date.valueOf(formatE);
//		java.sql.Date out = java.sql.Date.valueOf(formatS);
		return CalcDaily.valorDiarias(dailyDTO.value(), dailyDTO.dateCheckin(), dailyDTO.dateCheckout());
	}

}
