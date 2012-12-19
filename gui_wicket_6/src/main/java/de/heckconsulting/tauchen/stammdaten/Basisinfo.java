package de.heckconsulting.tauchen.stammdaten;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;

import de.heckconsulting.tauchen.MasterPageWicket6;
import de.heckconsulting.tauchen.base.UiNavigation;
import de.heckconsulting.tauchen.base.UiTopNavItem.NavItemType;

@UiNavigation(itemType = NavItemType.pencil, label = "basis", sortOrder = "1")
public class Basisinfo extends MasterPageWicket6 {

	public Basisinfo() {
		super();
		addAutoCompleteField();
	}

	public Basisinfo(PageParameters param) {
		super();
		addAutoCompleteField();
	}

	private void addAutoCompleteField() {
		Form<Void> form = new Form<Void>("form");
		add(form);

		final AutoCompleteTextField<String> field = new AutoCompleteTextField<String>("ac", new Model<String>("")) {
			@Override
			protected Iterator<String> getChoices(String input) {
				if (Strings.isEmpty(input)) {
					List<String> emptyList = Collections.emptyList();
					return emptyList.iterator();
				}

				List<String> choices = new ArrayList<String>(10);

				Locale[] locales = Locale.getAvailableLocales();

				for (final Locale locale : locales) {
					final String country = locale.getDisplayCountry();

					if (country.toUpperCase().startsWith(input.toUpperCase())) {
						choices.add(country);
						if (choices.size() == 10) {
							break;
						}
					}
				}

				return choices.iterator();
			}
		};
		form.add(field);

	}
}
