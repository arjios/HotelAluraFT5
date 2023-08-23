package hotel.services;

import hotel.dto.UserDTO;
import hotel.entities.User;
import hotel.repositories.UserRepository;

public class UserService {

	UserRepository userRepository;
	
	public UserDTO findUserByName(String name) {
		User user = userRepository.findByName(name);
		return new UserDTO(user);
	}
	
}
