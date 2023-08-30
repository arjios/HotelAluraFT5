package hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import hotel.dao.factory.FactoryConnection;
import hotel.entities.Reservation;
import hotel.repositories.ReservationRepository;

public class ReservationDAO implements ReservationRepository {
	
	private Reservation reservation;
	private Set<Reservation> reservations = new HashSet<>();

	@Override
	public Set<Reservation> findAll() {
		try {
			Connection con = FactoryConnection.createPoolConnection();
			Statement st =  con.createStatement();
			st.execute("SELECT * FROM tb_reservation");
			ResultSet rs = st.getResultSet();
			while(rs.next()) {			
				reservation.setId(rs.getLong("id"));
				reservation.setCheckin(rs.getDate("checkin"));
				reservation.setCheckout(rs.getDate("checkout"));
				reservation.setValue(rs.getDouble("value"));	
				reservation.setPayment(rs.getNString("payment"));
				reservation.setIdReservation(rs.getLong("idReservation"));	
				reservations.add(reservation);
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocorreu erro na leitura das Reservations.");
			JOptionPane.showMessageDialog(null, "Ocorreu erro na leitura das Reservations.");
			e.printStackTrace();
		} 
		return reservations;
	}

	@Override
	public Reservation findByReservation(Long id) {
		String st = "SELECT id, checkin, checkout, value, payment  FROM tb_reservation "
				+ "WHERE idReservation = ?";
		try {
			Connection con = FactoryConnection.createPoolConnection();
			PreparedStatement ps = con.prepareStatement(st);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				reservation.setId(rs.getLong("id"));
				reservation.setCheckin(rs.getDate("checkin"));
				reservation.setCheckout(rs.getDate("checkout"));
				reservation.setValue(rs.getDouble("value"));	
				reservation.setPayment(rs.getNString("payment"));
				reservation.setIdReservation(rs.getLong("idReservation"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocorreu erro na leitura da Reservation.");
			e.printStackTrace();
		} 
		return reservation;
	}

	@Override
	public Reservation insert(Reservation entity) {
		String st = "INSERT INTO tb_reservation(checkin, checkout, value, payment, idReservation) VALUES (?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(st);
			ps.setDate(1, entity.getCheckin());
			ps.setDate(2, entity.getCheckout());
			ps.setDouble(3, entity.getValue());
			ps.setString(4, entity.getPayment());
			ps.setLong(5, entity.getIdReservation());
			ps.execute();
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
				e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Reservation update(Long id, Reservation reservation) {
		String sql = "UPDATE tb_reservation " + 
				"SET checkin = ?, checkout = ?, value = ?, payment = ? " + 
				"WHERE idReservation = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(sql);
			ps.setDate(1, reservation.getCheckin());
			ps.setDate(2, reservation.getCheckout());
			ps.setDouble(3, reservation.getValue());
			ps.setString(4, reservation.getPayment());
			ps.setLong(5, reservation.getIdReservation());
			ps.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu erro na leitura da Reserva.", "Error: Reservation Update.", 0);
			e.printStackTrace();
		}	
		return reservation;
	}

	@Override
	public Long delete(Long id) {
		String st = "DELETE FROM tb_reservation WHERE id = ?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(st);
			ps.setLong(1, id);
			ps.execute();
		} catch(Exception e) {
			System.out.println("Ocorreu erro na leitura na Delete reserva.");
			id = null;
			e.printStackTrace();
		}
		return id;
	}
}
