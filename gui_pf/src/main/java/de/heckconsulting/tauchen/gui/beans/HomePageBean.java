package de.heckconsulting.tauchen.gui.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TableGenerator;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

import de.heckconsulting.tauchen.core.service.AccordionService;

//@TableGenerator(name="sequence", table="t_generator_table", pkColumnName="PRIMARY_KEY_COLUMN", valueColumnName="VALUE_COLUMN", pkColumnValue="REF_CODE_ID",allocationSize=1,initialValue=1000)


@Named
@SessionScoped
public class HomePageBean implements Serializable {

	// public AccordionBean() {
	//
	// System.out.println( "Bin im Constructor");
	//
	// FacesContext facesContext=FacesContext.getCurrentInstance();
	// ApplicationAssociate application =
	// ApplicationAssociate.getInstance(facesContext.getExternalContext());
	// BeanManager beanManager = application.getBeanManager();
	// Map<String, BeanBuilder> beanMap=beanManager.getRegisteredBeans();
	// Set<Map.Entry<String, BeanBuilder>>beanEntries=beanMap.entrySet();
	// for(Map.Entry<String, BeanBuilder> bean:beanEntries){
	// String beanName=bean.getKey();
	// if(!beanManager.isManaged(beanName)){
	// continue;
	// }
	// BeanBuilder builder=bean.getValue();
	// System.out.println( builder.getBeanClass() );
	// }
	//
	//
	// WebApplicationContext ctx =
	// FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
	// service = ctx.getBean(AccordionServiceImpl.class);
	// }

	@Inject
	private AccordionService service;

	public AccordionService getService() {
		return service;
	}

	public void setService(AccordionService service) {
		this.service = service;
	}

	private String accordionText1 = "bla1";

	private String accordionText2 = "bla2";

	public String getAccordionText1() {
		return service.getAccordionText(Long.valueOf("1"));
	}

	public void setAccordionText1(String accordionText1) {
		this.accordionText1 = accordionText1;
	}

	public String getAccordionText2() {
		return this.accordionText2; //service.getAccordionText(Long.valueOf("2"));
	}

	public void setAccordionText2(String accordionText2) {
		this.accordionText2 = accordionText2;
	}

	public void onChange(TabChangeEvent event) {
		Tab activeTab = event.getTab();
		System.out.println(activeTab.getTitle());
	}

	private String text;

	public List<String> complete(String query) {
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < 10; i++)
			results.add(query + i);
		return results;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
