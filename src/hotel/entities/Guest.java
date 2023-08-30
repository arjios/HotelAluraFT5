package hotel.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Guest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String lastName;
	private String phone;
	private LocalDate dateBirth;
	private String country;
	
	public Guest() {
	}

	public Guest(Long id, String name, String lastName, String phone, 
			LocalDate dateBirth, String country) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.dateBirth = dateBirth;
		this.country = country;
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

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
