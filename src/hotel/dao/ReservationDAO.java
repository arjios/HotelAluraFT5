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
			st.execute("SELECT * FROM RESERVA");
			ResultSet rs = st.getResultSet();
			while(rs.next()) {			
				reservation.setId(rs.getLong("id"));
				reservation.setCheckin(rs.getDate("checkin"));
				reservation.setCheckout(rs.getDate("checkout"));
				reservation.setIdReservation(rs.getInt("idReservation"));				
				reservation.setPayment(rs.getNString("payment"));		
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
		String st = "SELECT id, data_entrada, data_saida, id_reservation, forma_pagamento FROM RESERVA "
				+ "WHERE id_reservation = ?";
		try {
			Connection con = FactoryConnection.createPoolConnection();
			PreparedStatement ps = con.prepareStatement(st);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				reservation.setId(rs.getLong("id"));
				reservation.setCheckin(rs.getDate("checkin"));
				reservation.setCheckout(rs.getDate("checkout"));
				reservation.setIdReservation(rs.getInt("idReservation"));				
				reservation.setPayment(rs.getNString("payment"));
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
		String st = "INSERT INTO RESERVA(data_entrada, data_saida, id_reservation, forma_pagamento) VALUES (?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(st);
			ps.setDate(1, entity.getCheckin());
			ps.setDate(2, entity.getCheckout());
			ps.setLong(3, entity.getIdReservation());
			ps.setString(4, entity.getPayment());		
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
		String sql = "UPDATE reservation " + 
				"SET data_entrada = ?, data_saida = ?, forma_pagamento = ? " + 
				"WHERE id_reservation = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(sql);
			ps.setDate(1, reservation.getCheckin());
			ps.setDate(2, reservation.getCheckout());
			ps.setString(3, reservation.getPayment());
			ps.setLong(4, reservation.getIdReservation());
			ps.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu erro na leitura da Reservation.", "Error: Reservation Update.", 0);
			e.printStackTrace();
		}	
		return reservation;
	}

	@Override
	public Long delete(Long idReservation) {
		String st = "DELETE FROM reservation WHERE id_reservation = ?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			System.out.println("DELETE");
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(st);
			ps.setLong(1, idReservation);
			ps.execute();
		} catch (Exception e) {
			System.out.println("Ocorreu erro na leitura da Reservation.");
			idReservation = null;
			e.printStackTrace();
		}
		return idReservation;
	}
	
}
