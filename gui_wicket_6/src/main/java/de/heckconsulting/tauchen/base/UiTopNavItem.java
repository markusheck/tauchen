package de.heckconsulting.tauchen.base;

import org.apache.wicket.markup.html.WebPage;

public class UiTopNavItem implements Comparable<UiTopNavItem> {
	
	public static enum NavItemType {dashboard, tables, chart, modal, pencil, gallery, anchor};
	private String[] roles;
	
	private Class<? extends WebPage> pageClass;
	protected NavItemType type;
	private String label;
	private String sortOrder;
	
	public Class<? extends WebPage> getPageClass() {
		return pageClass;
	}

	public void setPageClass(Class<? extends WebPage> pageClass) {
		this.pageClass = pageClass;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setType(NavItemType type){
		this.type = type;
	}
	
	public NavItemType getType() {
		return type;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public int compareTo(UiTopNavItem o) {
		Long sortOrder1 = Long.valueOf( this.sortOrder );
		Long sortOrder2 = Long.valueOf( o.getSortOrder() );
        return sortOrder1.compareTo( sortOrder2 );		
		//return this.sortOrder.compareTo( o.sortOrder );
	}
	
}
