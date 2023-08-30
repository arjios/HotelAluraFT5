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
			st.execute("SELECT * FROM tb_guest");
			ResultSet rs = st.getResultSet();
			while(rs.next()) {
				Guest guest = new Guest();
				guest.setId(rs.getLong("id"));
				guest.setName(rs.getString("name"));
				guest.setLastName(rs.getString("lastName"));
				guest.setPhone(rs.getString("phone"));				
				guest.setDateBirth(rs.getDate("dateBirth").toLocalDate());
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
	public Guest insert(Guest guest) {
		String sql = "INSERT INTO tb_guest" +
					"(name, lastName, phone, dateBirth, country)" +
					"VALUES (?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, guest.getName());
			ps.setString(2, guest.getLastName());
			ps.setNString(3,  guest.getPhone());		
			LocalDate localDate = guest.getDateBirth();
			ps.setDate(4,  Date.valueOf(localDate));
			ps.setNString(5, guest.getCountry());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				guest.setId(rs.getLong(1));
			}
			return guest;
			
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
		return guest;
	}

	@Override
	public Guest update(Long id_reserva, Guest guest) {
		String sql = "UPDATE tb_guest " + 
				"SET name = ?, lastName = ?, phone = ?, dateBirth = ?, country  = ? " + 
				"WHERE id = ? ";
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
			ps.setLong(6, guest.getId());
			ps.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu erro na leitura de Guest.", "Error: Guest Update.", 0);
			e.printStackTrace();
		}	
		return guest;
	}

	@Override
	public Long delete(Long id) {
		String sql = "DELETE FROM tb_guest WHERE id = ?";
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
