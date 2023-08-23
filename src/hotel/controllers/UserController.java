package hotel.controllers;

import hotel.dto.UserDTO;
import hotel.services.UserService;

public class UserController {

	UserService userService = new UserService();
	
	public UserDTO findUserByName(String name) {
		return userService.findUserByName(name);
	}
	
}
