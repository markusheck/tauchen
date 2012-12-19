package de.heckconsulting.tauchen;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

@AuthorizeInstantiation({"ROLE_USER","ROLE_ADMIN"})
public class HomePage extends MasterPage {

	public HomePage() {
		System.out.println( "Bin in HomePage2.class");
	}

}
