package de.heckconsulting.tauchen;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import de.heckconsulting.tauchen.base.MyAuthenticatedWebSession;
import de.heckconsulting.tauchen.base.UiNavigation;
import de.heckconsulting.tauchen.base.UiTopNavItem;
import eu.infomas.annotation.AnnotationDetector;
import eu.infomas.annotation.AnnotationDetector.TypeReporter;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.cometsoftware.Start#main(String[])
 */
@Component(value = "wicketApplication")
public class WicketApplication extends AuthenticatedWebApplication {

	@Autowired
	private ApplicationContext applicationContext;

	List<UiTopNavItem> dynTopNavItem = new ArrayList<UiTopNavItem>();

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();
		
		getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
		getMarkupSettings().setStripWicketTags(true);
		
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext, true));
	    getApplicationSettings().setPageExpiredErrorPage(LoginPage.class);
	    scanAnnotations();
	   
	}
	

	
	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return MyAuthenticatedWebSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return super.newSession(request, response);
	}

	final TypeReporter typeReporter = new TypeReporter() {
		
		@Override
		public Class<? extends Annotation>[] annotations() {
			return new Class[] {UiNavigation.class} ;
		}
		
		@Override
		public void reportTypeAnnotation(Class<? extends Annotation> anno, String classname) {
			
			try {
				Class<? extends WebPage> parser = (Class<? extends WebPage >) Class.forName(classname);
				Object dtp = parser.getAnnotation(anno);
				
				if (dtp instanceof UiNavigation){
					UiNavigation a = (UiNavigation) dtp;
					UiTopNavItem  item = new UiTopNavItem();
					if (a.label() != null)
						item.setLabel(a.label());
					else 
						item.setLabel("NO LABEL SET!");
					if (a.roles() != null && a.roles().length > 0)
						item.setRoles(a.roles());
					item.setPageClass(parser);
					item.setType(a.itemType());
					item.setSortOrder(a.sortOrder());
					dynTopNavItem.add(item);
				}
				
				parser = null;
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
		}
	};

	private void scanAnnotations(){
		final AnnotationDetector cf = new AnnotationDetector(typeReporter);
		try {
			cf.detect("de.heckconsulting.tauchen.stammdaten");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
