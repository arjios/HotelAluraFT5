package hotel.controllers;

import hotel.dao.UserDAO;
import hotel.dto.UserDTO;
import hotel.services.UserService;

public class UserController {

	UserService userService = new UserService(new UserDAO());
	
	public UserDTO findUserByName(String name) {
		return userService.findUserByName(name);
	}
	
}
