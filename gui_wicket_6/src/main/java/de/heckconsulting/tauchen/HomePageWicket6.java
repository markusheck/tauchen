package de.heckconsulting.tauchen;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation({"ROLE_USER","ROLE_ADMIN"})
public class HomePageWicket6 extends MasterPageWicket6 {

	private static final long serialVersionUID = 7669131224432782195L;
	
	public HomePageWicket6() {
		System.out.println( "Bin in HomePage.class");
	}

}
