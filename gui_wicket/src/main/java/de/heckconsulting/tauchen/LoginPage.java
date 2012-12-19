package de.heckconsulting.tauchen;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.heckconsulting.tauchen.core.service.UserService;

public class LoginPage extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserService userService;

	private String username;
	private String password;

	public LoginPage(final PageParameters parameters) {
		super(parameters);
		setDefaultModel(new CompoundPropertyModel(this));
		RequiredTextField usernameFld = new RequiredTextField("username");
		PasswordTextField passwordFld = new PasswordTextField("password");

		Form logonForm = new Form<Void>("logonForm") {

			@Override
			protected void onSubmit() {
				AuthenticatedWebSession session = AuthenticatedWebSession.get();
				if (session.signIn(username, password)) {
					setResponsePage(getApplication().getHomePage());
				} else {
					error(getString("login.failed"));

				}

			}

		};

		logonForm.add(usernameFld);
		logonForm.add(passwordFld);
		logonForm.add(getLogonButton());
		add(logonForm);
	}

	private AjaxButton getLogonButton() {

		AjaxButton btn = new AjaxButton("logonButton") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				target.add(form);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(form);
			}
		};

		return btn;
	}
}
