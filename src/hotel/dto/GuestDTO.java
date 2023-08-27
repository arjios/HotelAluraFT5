package hotel.dto;

import java.sql.Date;
import java.time.LocalDate;

import hotel.entities.Guest;

public class GuestDTO {
	
	private Long id;
	private String name;
	private String lastName;
	private String phone;
	private LocalDate dateBirth;
	private Integer idReservation;
	private String country;
	
	public GuestDTO() {
	}

	public GuestDTO(Long id, String name, String lastName, String phone, 
			LocalDate dateBirth, Integer idReservation, String country) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.dateBirth = dateBirth;
		this.idReservation = idReservation;
		this.country = country;
	}
	
	public GuestDTO(Guest entity) {
		id = entity.getId();
		name = entity.getName();
		lastName = entity.getLastName();
		phone = entity.getPhone();
		dateBirth = entity.getDateBirth();
		idReservation = entity.getIdReservation();
		country = entity.getCountry();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth.toLocalDate();
	}

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
