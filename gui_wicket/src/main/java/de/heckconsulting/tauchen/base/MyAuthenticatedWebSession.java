package de.heckconsulting.tauchen.base;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


public class MyAuthenticatedWebSession extends AuthenticatedWebSession {
    
    private static final long serialVersionUID = -6391743156006030160L;

	private static final Logger logger = LoggerFactory.getLogger(MyAuthenticatedWebSession.class);

    @SpringBean(name="authenticationManager")
    private AuthenticationManager authenticationManager;

    public MyAuthenticatedWebSession(Request request) {
        super(request);
        injectDependencies();
        ensureDependenciesNotNull();
    }

    private void ensureDependenciesNotNull() {
        if (authenticationManager == null) {
            throw new IllegalStateException("AdminSession requires an authenticationManager.");
        }
    }

    private void injectDependencies() {
        Injector.get().inject(this);
    }

    @Override
    public boolean authenticate(String username, String password) {
        boolean authenticated = false;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            authenticated = authentication.isAuthenticated();
        } catch (org.springframework.security.core.AuthenticationException e) {
        	logger.warn("User '%s' failed to login. Reason: %s", username, e.getMessage());
            authenticated = false;
        }
        return authenticated;
    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        getRolesIfSignedIn(roles);
        return roles;
    }

    private void getRolesIfSignedIn(Roles roles) {
        if (isSignedIn()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            addRolesFromAuthentication(roles, authentication);
        }
    }

    private void addRolesFromAuthentication(Roles roles, Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
    }
    
    public static MyAuthenticatedWebSession get(){
    	return (MyAuthenticatedWebSession) Session.get();
    }
    
    public boolean hasOneOf(String...checkroles){
		
		for (String check : checkroles) {
			Roles roles = this.getRoles();
			for (String role : roles) {
				if (check.equalsIgnoreCase(role))
					return true;
			}
		}
		return false;
	}

}

    
    

