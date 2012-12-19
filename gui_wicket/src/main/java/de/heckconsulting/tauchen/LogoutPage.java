package de.heckconsulting.tauchen;



public class LogoutPage extends MasterPage {
	
	public LogoutPage() {
		getSession().invalidate();
		setResponsePage( LoginPage.class );
	}

}