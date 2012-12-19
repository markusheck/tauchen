package de.heckconsulting.tauchen.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage extends WebPage {
	
	private FeedbackPanel feedbackPanel;
	private IModel<String> title = new ResourceModel("title", "Not set");
	private static final Logger log = LoggerFactory.getLogger(BasePage.class);
	
	public BasePage() {
        add(new Label("title", title) );
    }

	
}
