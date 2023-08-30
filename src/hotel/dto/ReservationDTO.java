package hotel.dto;

import java.sql.Date;

import hotel.entities.Reservation;

public class ReservationDTO {

	private Long id;
	private Date checkin;
	private Date checkout;
	private Double value;
	private String payment;
	private Long idReservation;
	
	public ReservationDTO() {

	}

	public ReservationDTO(Long id, Date checkin, Date checkout, Double value,
			String payment, Long idReservation) {
		this.id = id;
		this.checkin = checkin;
		this.checkout = checkout;
		this.value = value;
		this.payment = payment;
		this.idReservation = idReservation;
	}

	public ReservationDTO(Reservation entity) {
		id = entity.getId();
		checkin = entity.getCheckin();
		checkout = entity.getCheckout();
		value = entity.getValue();
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Long getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}

}
