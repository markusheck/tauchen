package de.heckconsulting.tauchen;



public class LogoutPageWicket6 extends MasterPageWicket6 {
	
	public LogoutPageWicket6() {
		getSession().invalidate();
		setResponsePage( LoginPageWicket6.class );
	}

}