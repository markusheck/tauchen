package de.heckconsulting.tauchen.base.navsec;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import de.heckconsulting.tauchen.base.MyAuthenticatedWebSession;




/**
 * 
 * @author hkalk
 *
 */
@SuppressWarnings("rawtypes")
public class TopNavSecItem implements Serializable {
	private static final long serialVersionUID = -1065515952162705561L;
	final AjaxLink<String> link;
	private boolean current = false;
	private TopNavSec owner; 
	private TopNavSecItem me = this;
	
	public TopNavSecItem(final IModel<WebMarkupContainer> replacer, String label, final String...roles) {
		this.link = new AjaxLink<String>(TopNavSec.LINK_ID) {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				WebMarkupContainer wmc = replacer.getObject();
				
				Component comp = getPanel("replacePoint");
				comp.setOutputMarkupId(true);
				
				wmc.get("replacePoint").replaceWith(comp);
				target.appendJavaScript("$('.datatable').dataTable();");
				target.add(wmc);
				target.add(comp);
				
				if (getOwner() != null){
					getOwner().setCurrentItem(me);
				}
			}
			
			@Override
			public boolean isVisible() {	
				return isInRoles(roles);
			}
		};
		this.link.add(new Label(TopNavSec.LINK_TEXT_ID, new StringResourceModel(label, new Model())));
	}

	
	
	public TopNavSecItem(IModel<WebMarkupContainer> replacer, String label) {
		this(replacer, label, null, "_ALL_");
	}
	
	

	public AjaxLink getLink() {
		return link;
	}
	
	private boolean isInRoles(String...checkroles){
		for (String role : checkroles) {
			if ("_ALL_".equals(role))
				return true;
		}
		MyAuthenticatedWebSession sess = MyAuthenticatedWebSession.get();
		return sess.hasOneOf(checkroles);
	}
	

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}
	
	
	public Panel getPanel(String id) {
		return null;
	}



	public TopNavSec getOwner() {
		return owner;
	}



	public void setOwner(TopNavSec owner) {
		this.owner = owner;
	}
	
}