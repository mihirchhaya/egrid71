package com.admin;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		PersonPanel personPanel = new PersonPanel("personPnl");
		personPanel.setOutputMarkupId(true);
		personPanel.setMarkupId("personPnlId");

		CustomerPanel customerPanel = new CustomerPanel("customerPnl");
		customerPanel.setOutputMarkupId(true);
		customerPanel.setMarkupId("customerPnlId");

		Form<Void> form = new Form<Void>("editableGridsForm");
		form.setMarkupId("formId");
		form.setOutputMarkupId(true);

		form.add(customerPanel);
		form.add(personPanel);

		add(form);
    }
}
