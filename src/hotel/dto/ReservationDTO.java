package hotel.dto;

import java.sql.Date;

import hotel.entities.Reservation;

public class ReservationDTO {

	private Long id;
	private Date checkin;
	private Date checkout;
	private String payment;
	private Integer idReservation;
	
	public ReservationDTO() {

	}

	public ReservationDTO(Long id, Date checkin, Date checkout, 
			String payment, Integer idReservation) {
		this.id = id;
		this.checkin = checkin;
		this.checkout = checkout;
		this.payment = payment;
		this.idReservation = idReservation;
	}

	public ReservationDTO(Reservation entity) {
		id = entity.getId();
		checkin = entity.getCheckin();
		checkout = entity.getCheckout();
		payment = entity.getPayment();
		idReservation = entity.getIdReservation();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

}
