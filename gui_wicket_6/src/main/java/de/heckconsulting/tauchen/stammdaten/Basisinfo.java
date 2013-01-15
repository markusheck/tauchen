package de.heckconsulting.tauchen.stammdaten;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;

import de.heckconsulting.tauchen.MasterPageWicket6;
import de.heckconsulting.tauchen.base.UiNavigation;
import de.heckconsulting.tauchen.base.UiTopNavItem.NavItemType;

@UiNavigation(itemType = NavItemType.pencil, label = "basis", sortOrder = "1")
public class Basisinfo extends MasterPageWicket6 {
	
	private ArrayList<Integer> intList;
	private DropDownChoice<Integer> ddc;

	public Basisinfo() {
		super();
		addAutoCompleteField();
		addDropDownChoice();
	}

	public Basisinfo(PageParameters param) {
		super();
		addAutoCompleteField();
		addDropDownChoice();
	}
	
	private void addDropDownChoice() {
		Form<Void> form2 = new Form<Void>("form2");
		add(form2);
		intList = new ArrayList<Integer>();
		intList.add( Integer.valueOf(1));
		ddc = new DropDownChoice<Integer>("testddc", new Model(), intList ) {

			@Override
			protected void onSelectionChanged(Integer newSelection) {
				for( int i = 0; i < newSelection; i++ ) {
					intList.add( Integer.valueOf( i + intList.size() ));
				}
			}

			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				return true;
			}
			
		}; 
		ddc.setOutputMarkupId(true);
		AjaxButton adjustButton = new AjaxButton("adjustButton") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				for( int i = 0; i < 5; i++ ) {
					intList.add( Integer.valueOf( i + intList.size() ));
				}
				DropDownChoice<Integer> newDdc = new DropDownChoice<Integer>("testddc", new Model(), intList );
				newDdc.setOutputMarkupId(true);
				ddc.replaceWith( newDdc );
				ddc = newDdc;
				target.add( ddc );
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				System.out.println( "Fehler123!!!" );
				
			}
			
		};		
		form2.add(ddc);
		form2.add( adjustButton );
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
