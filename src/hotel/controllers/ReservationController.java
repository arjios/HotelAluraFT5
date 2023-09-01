package hotel.controllers;

import java.sql.Date;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import hotel.dto.DailyDTO;
import hotel.dto.ReservationDTO;
import hotel.services.ReservationService;

public class ReservationController {

	private ReservationService reservationService = new ReservationService();
	private ReservationDTO reservationDTO = new ReservationDTO();

	public Set<ReservationDTO> findAllReservas() {
		Set<ReservationDTO> obj = reservationService.findAll();
		return obj;
	}
	
	public ReservationDTO findReservaByName(Long idReserva) {	
		return reservationService.findByReservation(idReserva);
	}
	
	public ReservationDTO insert(ReservationDTO dto) {
		reservationDTO = reservationService.insert(dto);
		return reservationDTO;
	}

	public ReservationDTO update(DefaultTableModel dtm) {

		ReservationDTO rdto = new ReservationDTO();
		for(int i=1; i< dtm.getRowCount(); i++) {
			
			rdto = reservationService.findByReservation(Long.valueOf(dtm.getValueAt(i, 0).toString()));
			
			rdto.setCheckin(Date.valueOf(dtm.getValueAt(i, 1).toString()));

			rdto.setCheckout(Date.valueOf(dtm.getValueAt(i, 2).toString()));

			rdto.setValue(Double.valueOf(dtm.getValueAt(i, 3).toString()));
			
			rdto.setPayment(dtm.getValueAt(i, 4).toString());
			
			rdto.setIdReservation(Long.valueOf((dtm.getValueAt(i, 5).toString())));

			reservationService.update(Long.valueOf(dtm.getValueAt(i, 0).toString()), rdto);
		}
		return reservationDTO;
	}
	
	public Long deletarReserva(Object obj) {
		System.out.println("Deleta reservas: " + Long.valueOf(obj.toString()));
		Long id = reservationService.delete(Long.valueOf(obj.toString()));
		return id;
	}
	
	public Double calcValue(Double d, Date formatE, Date formatS) {
		DailyDTO dailyDTO = new DailyDTO(d, formatE, formatS);
		return reservationService.DailyTotalService(dailyDTO);
	}
	
}

