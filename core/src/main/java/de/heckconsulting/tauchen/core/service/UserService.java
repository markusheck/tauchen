package de.heckconsulting.tauchen.core.service;

import de.heckconsulting.tauchen.core.dto.UserDTO;

public interface UserService {
	
	UserDTO validateLogin( String username, String password );

}
