package de.heckconsulting.tauchen.core.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.heckconsulting.tauchen.core.dto.UserDTO;
import de.heckconsulting.tauchen.core.service.UserService;
import de.heckconsulting.tauchen.db.dao.UserDAO;
import de.heckconsulting.tauchen.db.dbo.UserDBO;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Autowired
	Mapper mapper;

	@Override
	public UserDTO validateLogin(String username, String password) {
		List<UserDBO> result = userDao.findByUsernameAndPassword(username, password);
		if ( result != null && result.size() > 0 ) {
			return mapper.map( result.get(0), UserDTO.class);
		} else {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		List<UserDBO> dboUserList = userDao.findByUserName(userName);
		User user = null;
		
		if (dboUserList != null && !dboUserList.isEmpty()) {
			UserDBO dbo = dboUserList.get(0);
			UserDTO dto = mapper.map( dbo, UserDTO.class);
			
			String username = dbo.getUserName();
		    String password = dbo.getPassword();
		    
		    boolean enabled = true;
		    boolean accountNonExpired = true;
		    boolean credentialsNonExpired = true;
		    boolean accountNonLocked = true;

		    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		    authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		    	
		    user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		}
		else
			throw new UsernameNotFoundException("user not found");

		return user;
	}

}
