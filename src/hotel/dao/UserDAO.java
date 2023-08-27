package hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import hotel.dao.factory.FactoryConnection;
import hotel.entities.User;
import hotel.repositories.UserRepository;

public class UserDAO implements UserRepository {
	
	private User user = new User();

	@Override
	public User findByName(String name) {
		String st = "SELECT * FROM tb_user WHERE name = ? ";
		try {
			Connection con = FactoryConnection.createPoolConnection();
			PreparedStatement ps = con.prepareStatement(st);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));	
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu erro na busca pelo nome do usuario.", "UserDAO", 0);
			e.printStackTrace();
		} 

		return user;
	}

}
