package hotel.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date checkin;
	private Date checkout;
	private Double value;
	private String payment;
	private Long idReservation;
	
	public Reservation() {
	}
	
	public Reservation(Long id, Date checkin, Date checkout, Double value, String payment, Long idReservation) {
		this.id = id;
		this.checkin = checkin;
		this.checkout = checkout;
		this.value = value;
		this.payment = payment;
		this.idReservation = idReservation;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(checkin);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(checkin, other.checkin);
	}

}
