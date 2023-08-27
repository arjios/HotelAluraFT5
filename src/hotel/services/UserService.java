package hotel.services;

import hotel.dao.UserDAO;
import hotel.dto.UserDTO;
import hotel.entities.User;
import hotel.repositories.UserRepository;

public class UserService {

	UserRepository userRepository = new UserDAO();
	
	public UserService() {
	}

	public UserDTO findUserByName(String name) {
		User user = userRepository.findByName(name);
		return new UserDTO(user);
	}
	
}
