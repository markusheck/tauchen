package de.heckconsulting.tauchen.gui.beans;

import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import de.heckconsulting.tauchen.core.dto.UserDTO;
import de.heckconsulting.tauchen.core.service.UserService;
import de.heckconsulting.tauchen.gui.authorization.Constants;

@Model
@Named
@SessionScoped
public class LoginPageBean implements Serializable {
	
	@Inject
	private UserService userService;
	
    private String username;
    private String password;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String doLogin() {
		UserDTO user = userService.validateLogin(username, password);
		if ( user != null ) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getApplication().createValueBinding("#{" + Constants.VISIT_KEY_SCOPE + Constants.VISIT_KEY + "}").setValue(facesContext, user);
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			session.setAttribute(Constants.VISIT_KEY, user);
			return "restricted/HomePage.jsf";
		}
		return "LoginPage";
	}
    
    

}
