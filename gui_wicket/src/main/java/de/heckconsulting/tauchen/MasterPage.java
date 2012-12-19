package de.heckconsulting.tauchen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

import com.cooldatasoft.common.MenuItem;
import com.cooldatasoft.horizontal.dropdown.sunrisegloss.SunriseGlossDropDownMenu;

import de.heckconsulting.tauchen.base.BasePage;
import de.heckconsulting.tauchen.base.UiTopNavItem;
import de.heckconsulting.tauchen.base.UiTopNavItem.NavItemType;
import de.heckconsulting.tauchen.base.nav.TopNav;
import de.heckconsulting.tauchen.base.nav.TopNavItem;
import de.heckconsulting.tauchen.base.navsec.TopNavSec;
import de.heckconsulting.tauchen.base.navsec.TopNavSecItem;
import de.heckconsulting.tauchen.stammdaten.Basisinfo;


public class MasterPage extends BasePage {
	
	private static final long serialVersionUID = 3066337791339811638L;
	
	public MasterPage() {
		
		TopNav topnav = new TopNav("topnav");
		
		List<TopNavItem> topnavItems = new ArrayList<TopNavItem>();
		
		TopNavItem itemDashboard = new TopNavItem(NavItemType.dashboard,  "dashboard", HomePage.class);
			
		WicketApplication uiApp = (WicketApplication) getApplication();
		
		List<UiTopNavItem> dynTopNav = uiApp.dynTopNavItem;
		
		topnavItems.add(itemDashboard);
				
		Collections.sort(dynTopNav);
		
		for (UiTopNavItem ui : dynTopNav ) {
			TopNavItem item;
			if ( ui.getRoles() != null && ui.getRoles().length > 0 ) {
				item = new TopNavItem(ui.getType(), ui.getLabel(), ui.getPageClass(), ui.getRoles() );
			} else {				
				item = new TopNavItem(ui.getType(), ui.getLabel(), ui.getPageClass());
			}
			topnavItems.add(item);
		}
		topnav.setMenuItems(topnavItems);
		//add(topnav);
		
		TopNavSec topNavSec = new TopNavSec("navtabs");
		topNavSec.setMenuItems(getNavTabs());
		
		//add(topNavSec);
        List<MenuItem> primaryMenuList = buildMenu();
        //add your menu to your wicket page
        add(new SunriseGlossDropDownMenu("sunriseGlossMenu", primaryMenuList));		addLogoutLink();
	}
	
    private List<MenuItem> buildMenu() {
        //Create one seperator menu item
        MenuItem seperatorMenuItem = new MenuItem(true);
        //Define Primary Menu items (menuText,destinationWebPage)               
        MenuItem primaryMenu1 = new MenuItem("MENU 1", Basisinfo.class );
        MenuItem primaryMenu2 = new MenuItem("MeNu 2", Basisinfo.class );
        MenuItem primaryMenu3 = new MenuItem("Menu 3", Basisinfo.class );
        MenuItem primaryMenu4 = new MenuItem("MenU 4", Basisinfo.class );
        MenuItem primaryMenu5 = new MenuItem("menu 5", Basisinfo.class );
        //Define submenu items
                MenuItem subMenu1 = new MenuItem("submenu 1", Basisinfo.class );
                MenuItem subMenu2 = new MenuItem("subMENU 2", Basisinfo.class );
                MenuItem subMenu3 = new MenuItem("SUBMENU 3", Basisinfo.class );
                MenuItem subMenu4 = new MenuItem("Submenu 4", Basisinfo.class );
                MenuItem subMenu5 = new MenuItem("SuBmEnU 5", Basisinfo.class );
                //define submenu titles if you need one         
                MenuItem subMenuTitle1 = new MenuItem("Submenu Title 1");
                MenuItem subMenuTitle2 = new MenuItem("Submenu Title 2");
    //Add submenus/submenu titles/seperators to the primary menu at your choice of order
        primaryMenu1.getSubMenuItemList().add(subMenu1);
        primaryMenu1.getSubMenuItemList().add(subMenu2);
        primaryMenu1.getSubMenuItemList().add(seperatorMenuItem);
        primaryMenu1.getSubMenuItemList().add(subMenuTitle1);
        primaryMenu1.getSubMenuItemList().add(subMenu3);
        primaryMenu1.getSubMenuItemList().add(subMenu4);
        primaryMenu1.getSubMenuItemList().add(seperatorMenuItem);
        primaryMenu1.getSubMenuItemList().add(subMenuTitle2);
        primaryMenu1.getSubMenuItemList().add(subMenu5);
        
        //Create a List which contains the primary menu items in it.    
        List<MenuItem> primaryMenuList = new ArrayList<MenuItem>();
        primaryMenuList.add(primaryMenu1);
        primaryMenuList.add(seperatorMenuItem);
        primaryMenuList.add(primaryMenu2);
        primaryMenuList.add(seperatorMenuItem);
        primaryMenuList.add(primaryMenu3);
        primaryMenuList.add(seperatorMenuItem);
        primaryMenuList.add(primaryMenu4);
        primaryMenuList.add(seperatorMenuItem);
        primaryMenuList.add(primaryMenu5);
        primaryMenuList.add(seperatorMenuItem);
        
        return primaryMenuList;
}
	
	public List<TopNavSecItem> getNavTabs(){
		List<TopNavSecItem> list = new ArrayList<TopNavSecItem>();
		return list;
	}

	private void addLogoutLink(){
		AjaxLink<String> link = new AjaxLink<String>("logoutLink") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(LogoutPage.class);
			}
		};
		add(link);
	}


}