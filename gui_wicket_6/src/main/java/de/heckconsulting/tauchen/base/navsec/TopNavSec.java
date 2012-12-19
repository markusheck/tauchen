package de.heckconsulting.tauchen.base.navsec;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class TopNavSec extends Panel implements IHeaderContributor {
	private static final long serialVersionUID = -6262494973710106214L;
	public static final String LINK_ID = "linkid";
	public static final String LINK_TEXT_ID = "linktext";
	public static final String ICON = "icon";
	
	private final List<TopNavSecItem> topMenuItems = new ArrayList<TopNavSecItem>();
	
	private TopNavSecItem currentItem;

	public TopNavSec(String id) {
		super(id);
		
		add(new ListView<TopNavSecItem>("menubarlinks", new PropertyModel(this, "topMenuItems")) {
			
			private static final long serialVersionUID = -5875124377225299067L;

			@Override
			protected void populateItem(ListItem<TopNavSecItem> item) {
				final TopNavSecItem menuItem = (TopNavSecItem) item.getModelObject();
				AjaxLink link = menuItem.getLink();
				item.add(link);
				item.add(AttributeModifier.replace("class", (menuItem.isCurrent()?"current":"")));
			}
			
		});
	}

	/** Add all menus at once */
	public void setMenuItems(List<TopNavSecItem> menuItems) {
		this.topMenuItems.clear();
		for (TopNavSecItem topNavSecItem : menuItems) {
			topNavSecItem.setOwner(this);
		}
		this.topMenuItems.addAll(menuItems);
	}

	public TopNavSecItem getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(TopNavSecItem currentItem) {
		for (TopNavSecItem topNavSecItem : topMenuItems) {
			topNavSecItem.setCurrent(false);
		}
		currentItem.setCurrent(true);
		this.currentItem = currentItem;
	}

}

