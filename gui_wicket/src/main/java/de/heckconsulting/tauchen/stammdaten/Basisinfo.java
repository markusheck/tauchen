package de.heckconsulting.tauchen.stammdaten;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.heckconsulting.tauchen.MasterPage;
import de.heckconsulting.tauchen.base.UiNavigation;
import de.heckconsulting.tauchen.base.UiTopNavItem.NavItemType;

@UiNavigation(itemType=NavItemType.pencil, label="basis", sortOrder="1")
public class Basisinfo extends MasterPage {
	
	public Basisinfo() {
		super();
	}

	public Basisinfo( PageParameters param ) {
		super();					
	}
	
	
}
