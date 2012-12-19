package de.heckconsulting.tauchen;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

import de.heckconsulting.tauchen.base.BasePage;

public class MasterPageWicket6 extends BasePage {

	private static final long serialVersionUID = 3066337791339811638L;

	public MasterPageWicket6() {

		// add(topNavSec);*
		add( new AjaxLink( "link1" ) {
			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(HomePageWicket6.class);
			}
		});
		addLogoutLink();
		
	}

	private void addLogoutLink() {
		AjaxLink<String> link = new AjaxLink<String>("logoutLink") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(LogoutPageWicket6.class);
			}
		};
		add(link);
	}

}