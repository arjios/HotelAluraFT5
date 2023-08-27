package hotel.repositories;

import hotel.entities.User;

public interface UserRepository {
	
	User findByName(String name);

}
