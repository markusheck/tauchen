package de.heckconsulting.tauchen.base.nav;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import de.heckconsulting.tauchen.base.MyAuthenticatedWebSession;
import de.heckconsulting.tauchen.base.UiTopNavItem;
import de.heckconsulting.tauchen.base.UiTopNavItem.NavItemType;

/**
 * 
 * @author hkalk
 *
 */
@SuppressWarnings("rawtypes")
public class TopNavItem extends UiTopNavItem  implements Serializable {
	
	
	
	private static final long serialVersionUID = -8782406718408678223L;
	final Link link;
	private int badge = 0;
	private boolean current = false;
	private TopNav owner;
	private TopNavItem me = this;
	
	private WebMarkupContainer badgeSpan = new WebMarkupContainer("badge");

	public TopNavItem(NavItemType ntype, Link link, String label) {
		type = ntype;
		this.link = link;
		this.link.add(new Label(TopNav.LINK_TEXT_ID, new StringResourceModel(label, new Model())));
		addIcon(link);
		addBadge(link);
		addAjaxBehavior(link);
	}
	
	public TopNavItem(NavItemType ntype, String label) {
		type = ntype;
		this.link = new Link(TopNav.LINK_ID) {
			static final long serialVersionUID = -4958603689207612907L;

			@Override
			public void onClick() {
				me.onClick();
			}
			
		};
		this.link.add(new Label(TopNav.LINK_TEXT_ID, new StringResourceModel(label, new Model())));
		addIcon(link);
		addBadge(link);
		addAjaxBehavior(link);
	}
	
	
	public TopNavItem(NavItemType ntype, String label, final String...roles) {
		type = ntype;
		this.link = new Link(TopNav.LINK_ID) {
			static final long serialVersionUID = -4958603689207612907L;

			@Override
			public void onClick() {
				
			}
			@Override
			public boolean isVisible() {	
				return isInRoles(roles);
			}
			
		};
		this.link.add(new Label(TopNav.LINK_TEXT_ID, new StringResourceModel(label, new Model())));
		addIcon(link);
		addBadge(link);
		addAjaxBehavior(link);
	}

	private void onClick(){
		
	}
	
	/**
	 * 
	 * @param label
	 * @param pageClass
	 */
	public TopNavItem(NavItemType ntype, String label, Class<? extends WebPage> pageClass) {
		type = ntype;
		this.link = new BookmarkablePageLink<Void>(TopNav.LINK_ID, pageClass);
		this.link.add(new Label(TopNav.LINK_TEXT_ID, new StringResourceModel(label, new Model())));
		addIcon(link);
		addBadge(link);
		addAjaxBehavior(link);
	}
	
	private void addAjaxBehavior(Link link2) {
		link.add(new AjaxEventBehavior("onClick") {
			
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				getOwner().setCurrentItem(me);
				target.add(getOwner());
			}
			
//			@Override
//			protected void onComponentTag(ComponentTag tag) {
//				tag.getAttributes().put("onclick", "return getConfirmation();");
//				tag.setModified(true);
//			}
		});
	}
	
	
//	public void renderHead(IHeaderResponse response) {
//		//for internationalized confirm/warning message
//		StringResourceModel warningUnsavedChangesModel = new StringResourceModel("warningUnsavedChanges", new Model());
//		
//		CharSequence js = "function getConfirmation() {"
//				+ "if(isDirty) { return confirm('" + warningUnsavedChangesModel.getString() + "'); }" + "}";
//		response.renderJavaScript(js, getClass().getName());
//	}

	/**
	 * 
	 * @param label
	 * @param pageClass
	 * @param roles
	 */
	public TopNavItem(NavItemType ntype, String label, Class<? extends WebPage> pageClass, final String...roles) {
		type = ntype;
		this.link = new BookmarkablePageLink<Void>(TopNav.LINK_ID, pageClass){
			private static final long serialVersionUID = -2899601093517629661L;

			@Override
			public boolean isVisible() {
				return isInRoles(roles);
			}
		};
		this.link.add(new Label(TopNav.LINK_TEXT_ID, new StringResourceModel(label, new Model())));
		addIcon(link);
		addBadge(link);
		addAjaxBehavior(link);
		
	}
	
	

	public Link getLink() {
		badgeSpan.setVisible(hasBadge());
		return link;
	}
	
	private boolean isInRoles(String...checkroles){
		MyAuthenticatedWebSession sess = MyAuthenticatedWebSession.get();
		return sess.hasOneOf(checkroles);
	}

	public int getBadge() {
		return badge;
	}

	public void setBadge(int badge) {
		this.badge = badge;
	}
	
	public boolean hasBadge(){
		return badge > 0;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}
	
	
	private void addIcon(Link link){
		WebMarkupContainer iconSpan = new WebMarkupContainer("icon");
		iconSpan.add(AttributeModifier.replace("class", "icon " + getType().name()));
		link.add(iconSpan);
	}
	
	
	private void addBadge(Link link){
		badgeSpan.setVisible(hasBadge());
		IModel<String> badgeModel = new IModel<String>() {
			@Override
			public void detach() {
			}

			@Override
			public String getObject() {
				return ""+getBadge();
			}

			@Override
			public void setObject(String arg0) {
			}
		};
		
		Label badgeCount = new Label("badgeCount", badgeModel);
		badgeSpan.add(badgeCount);
		
		link.add(badgeSpan);
		
	}

	public TopNav getOwner() {
		return owner;
	}

	public void setOwner(TopNav owner) {
		this.owner = owner;
	}
}