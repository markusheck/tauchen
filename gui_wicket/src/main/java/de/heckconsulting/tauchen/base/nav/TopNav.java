package de.heckconsulting.tauchen.base.nav;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class TopNav extends Panel implements IHeaderContributor {
	private static final long serialVersionUID = -6262494973710106214L;
	public static final String LINK_ID = "linkid";
	public static final String LINK_TEXT_ID = "linktext";
	public static final String ICON = "icon";
	private TopNavItem currentItem;
	
	private final List<TopNavItem> topMenuItems = new ArrayList<TopNavItem>();
	

	public TopNav(String id) {
		super(id);
		setOutputMarkupId(true);
		add(new ListView<TopNavItem>("menubarlinks", new PropertyModel(this, "topMenuItems")) {
			
			private static final long serialVersionUID = -5875124377225299067L;

			@Override
			protected void populateItem(ListItem<TopNavItem> item) {
				final TopNavItem menuItem = (TopNavItem) item.getModelObject();
				Link link = menuItem.getLink();
				item.add(link);
				item.add(AttributeModifier.replace("class", (menuItem.isCurrent()?"current":"")));
			}
			
		});
	}

	/** Add one menu item */
	public void addMenu(TopNavItem menu) {
		if (!menu.link.getId().equals(LINK_ID)) {
			throw new IllegalArgumentException(
					"The id must be SuckerfishMenuPanel.LINK_ID");
		}
		topMenuItems.add(menu);
	}

	/** Add all menus at once */
	public void setMenuItems(List<TopNavItem> menuItems) {
		this.topMenuItems.clear();
		for (TopNavItem topNavItem : menuItems) {
			topNavItem.setOwner(this);
		}
		this.topMenuItems.addAll(menuItems);
	}

	
	
	public TopNavItem getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(TopNavItem currentItem) {
		for (TopNavItem topNavItem : topMenuItems) {
			topNavItem.setCurrent(false);
		}
		currentItem.setCurrent(true);
		this.currentItem = currentItem;
	}
	
	

}

