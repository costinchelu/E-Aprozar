package ro.ase.service;

import ro.ase.entity.InfoUser;
import ro.ase.model.UserRegistration;

public interface UserServiceInterface {
	
	boolean userAlreadyExist(String email);
	
	void save(UserRegistration user);
	
	InfoUser findByEmail(String email);
	
	void update(InfoUser user);
}
