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
	
	private Reservation reservation = new Reservation();
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
	public Reservation findByReservation(Long idIndex) {
		String st = "SELECT id, checkin, checkout, value, payment, idReservation FROM tb_reservation "
				+ "WHERE idReservation = ?";
		try {
			Connection con = FactoryConnection.createPoolConnection();
			PreparedStatement ps = con.prepareStatement(st);
			ps.setLong(1, idIndex);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				reservation.setId(rs.getLong("id"));
				reservation.setCheckin(rs.getDate("checkin"));
				reservation.setCheckout(rs.getDate("checkout"));
				reservation.setValue(rs.getDouble("value"));	
				reservation.setPayment(rs.getNString("payment"));
				reservation.setIdReservation(rs.getLong("idReservation"));
				ps.execute();
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
		System.out.println(id);
		String sql = "UPDATE tb_reservation " + 
				"SET id = ?, checkin = ?, checkout = ?, value = ?, payment = ? " + 
				"WHERE idReservation = ?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ps.setDate(2, reservation.getCheckin());
			ps.setDate(3, reservation.getCheckout());
			ps.setDouble(4, reservation.getValue());
			ps.setString(5, reservation.getPayment());
			ps.setLong(6, reservation.getIdReservation());
			ps.execute();
			System.out.println("Reserva: " + reservation.getIdReservation());
			ps.close();
			con.close();
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
		System.out.println("Reservation DAO Delete==================");
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(st);
			ps.setLong(1, id);
			ps.execute();
			con.close();
			ps.close();
		} catch(Exception e) {
			System.out.println("Ocorreu erro ao deletar a Reserva: " + id);
			id = null;
			e.printStackTrace();
		}
		return id;
	}
}
