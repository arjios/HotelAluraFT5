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
		String sql = "SELECT * FROM user WHERE nome = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = FactoryConnection.createPoolConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()) {
				user.setId(rs.getLong("id"));
				user.setNome(rs.getString("nome"));
				user.setSenha(rs.getString("senha"));	
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu erro na busca pelo nome do usuario.", "UserDAO", 0);
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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
		return user;
	}

}
