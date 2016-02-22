package com.admin;

import com.domain.Person;
import com.provider.PersonProvider;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.egrid.EditableGrid;
import org.wicketstuff.egrid.column.EditableTextFieldPropertyColumn;
import org.wicketstuff.egrid.provider.EditableListDataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chhaya-MX - Created on 2/19/2016
 */
public class PersonPanel extends Panel {

    private FeedbackPanel sbpanel;

    public PersonPanel(String id) {
        super(id);

        sbpanel = new FeedbackPanel("personStatusBar");
        sbpanel.setMarkupId("personStatusId");
        sbpanel.setOutputMarkupId(true);
        add(sbpanel);

        Form<Void> form = new Form<Void>("personForm");
        form.setMarkupId("personFormId");
        form.setOutputMarkupId(true);
        add(form);

        addTable("personTable", form);

    }
    private void addTable(String property, final Form<Void> form) {

        PersonProvider personDataProvider = new PersonProvider();

        EditableGrid<Person, String> grid = new EditableGrid<Person, String>(property, getColumns(), new EditableListDataProvider<Person, String>(personDataProvider.getPersonList()), 15, Person.class) {


            private static final long serialVersionUID = -579896599742011700L;

            @Override
            protected void onError(AjaxRequestTarget target) {
                target.add(sbpanel);
            }

            @Override
            protected void onCancel(AjaxRequestTarget target) {
                target.add(sbpanel);
            }

            @Override
            protected void onDelete(AjaxRequestTarget target, IModel<Person> rowModel) {
                target.add(sbpanel);
            }

            @Override
            protected void onSave(AjaxRequestTarget target, IModel<Person> rowModel) {
                Person personRecord = rowModel.getObject();
                sbpanel.info(personRecord.getFirstName() + " : " + personRecord.getLastName() + " record updated successfully.");
                target.add(sbpanel);
            }

            @Override
            protected boolean allowDelete(Item<Person> rowItem) {
                return false;
            }

            @Override
            protected boolean displayAddFeature() {
                return true;
            }

            @Override
            protected void onAdd(AjaxRequestTarget target, Person newRow) {
                sbpanel.info(newRow.getFirstName() + " : " + newRow.getLastName() + " record added successfully.");
                target.add(sbpanel);
            }
        };

        grid.setMarkupId("personGridId");
        grid.setOutputMarkupId(true);
        form.add(grid);
    }

    private List<EditableTextFieldPropertyColumn<Person, String>> getColumns() {

        List<EditableTextFieldPropertyColumn<Person, String>> columns = new ArrayList<EditableTextFieldPropertyColumn<Person, String>>();

        columns.add(new EditableTextFieldPropertyColumn<Person, String>(Model.of("First Name"), "firstName"));
        columns.add(new EditableTextFieldPropertyColumn<Person, String>(Model.of("Middle Name"), "middleName"));
        columns.add(new EditableTextFieldPropertyColumn<Person, String>(Model.of("Last Name"), "lastName"));
        columns.add(new EditableTextFieldPropertyColumn<Person, String>(Model.of("Age"), "age"));

        return columns;

    }
}
