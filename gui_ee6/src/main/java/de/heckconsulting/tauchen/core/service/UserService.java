package de.heckconsulting.tauchen.core.service;

import java.util.List;

import javax.inject.Inject;

import org.dozer.Mapper;

import de.heckconsulting.tauchen.core.dto.UserDTO;
import de.heckconsulting.tauchen.db.dao.UserDAO;
import de.heckconsulting.tauchen.db.dbo.UserDBO;

public class UserService {

	@Inject
	private UserDAO userDao;

	@Inject
	Mapper mapper;

	public UserService() {
	}
	
	public UserDTO validateLogin(String username, String password) {
		List<UserDBO> result = userDao.findByUsernameAndPassword(username, password);
		if ( result != null && result.size() > 0 ) {
			return mapper.map( result.get(0), UserDTO.class);
		} else {
			return null;
		}
	}
	

}
