package hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import hotel.dao.factory.FactoryConnection;
import hotel.entities.Guest;
import hotel.repositories.GuestRepository;

public class GuestDAO implements GuestRepository {

	@Override
	public Set<Guest> findAll() {
		Set<Guest> guests = new HashSet<>();
		try {
			Connection con = FactoryConnection.createPoolConnection();
			Statement st =  con.createStatement();
			st.execute("SELECT * FROM guest");
			ResultSet rs = st.getResultSet();
			while(rs.next()) {
				Guest guest = new Guest();
				guest.setId(rs.getLong("id"));
				guest.setName(rs.getString("name"));
				guest.setLastName(rs.getString("lastName"));
				guest.setPhone(rs.getString("phone"));				
				guest.setDateBirth(rs.getDate("dateBirth").toLocalDate());
				guest.setIdReservation(rs.getInt("idReservation"));
				guest.setCountry(rs.getString("country"));
				guests.add(guest);
			}
		} catch(Exception e) {
			System.out.println(e.getStackTrace() + " Erro: " + e.getMessage());
		}
		return guests;
	}

	@Override
	public Guest findByIdGuest(Long id) {
		return null;
	}

	@Override
	public void insert(Guest guest) {
		String sql = "INSERT INTO guest" +
					"(name, lastName, phone, dateBirth, idReservation, country)" +
					"VALUES (?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, guest.getName());
			ps.setString(2, guest.getLastName());
			ps.setNString(3,  guest.getPhone());		
			LocalDate localDate = guest.getDateBirth();
			ps.setDate(4,  Date.valueOf(localDate));
			ps.setLong(5, guest.getIdReservation());
			ps.setNString(6, guest.getCountry());
			ps.execute();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Guest update(Long id_reserva, Guest guest) {
		String sql = "UPDATE guest " + 
				"SET name = ?, lastName = ?, phone = ?, dateBirth = ?, country  = ? " + 
				"WHERE idReservation = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(sql);
	
			ps.setString(1, guest.getName());
			ps.setString(2, guest.getLastName());
			ps.setString(3, guest.getPhone());
			LocalDate localDate = guest.getDateBirth();
			ps.setDate(4,  Date.valueOf(localDate));
			ps.setString(5,  guest.getCountry());
			ps.setLong(6, guest.getIdReservation());
			ps.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu erro na leitura de Guest.", "Error: Guest Update.", 0);
			e.printStackTrace();
		}	
		return guest;
	}

	@Override
	public Long delete(Long id) {
		String sql = "DELETE FROM guest WHERE id_reserva = ?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ps.execute();
		} catch (Exception e) {
			System.out.println("Ocorreu erro na leitura da Reserva.");
			id = null;
			e.printStackTrace();
		}
		return id;
	}

}
