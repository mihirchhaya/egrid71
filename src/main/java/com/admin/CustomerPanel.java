package com.admin;

import com.domain.Customer;
import com.provider.CustomerProvider;
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
public class CustomerPanel extends Panel {

    private FeedbackPanel sbpanel;

    public CustomerPanel(String id) {
        super(id);

        sbpanel = new FeedbackPanel("customerStatusBar");
        sbpanel.setMarkupId("customerStatusId");
        sbpanel.setOutputMarkupId(true);
        add(sbpanel);

        Form<Void> form = new Form<Void>("customerForm");
        form.setMarkupId("customerFormId");
        form.setOutputMarkupId(true);
        add(form);

        addTable("customerTable", form);

    }
    private void addTable(String property, final Form<Void> form) {

        CustomerProvider customerDataProvider = new CustomerProvider();

        EditableGrid<Customer, String> grid = new EditableGrid<Customer, String>(property, getColumns(), new EditableListDataProvider<Customer, String>(customerDataProvider.getCustomerList()), 15, Customer.class) {


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
            protected void onDelete(AjaxRequestTarget target, IModel<Customer> rowModel) {
                target.add(sbpanel);
            }

            @Override
            protected void onSave(AjaxRequestTarget target, IModel<Customer> rowModel) {
                Customer customerRecord = rowModel.getObject();
                sbpanel.info(customerRecord.getFirstName() + " : " + customerRecord.getLastName() + " record updated successfully.");
                target.add(sbpanel);
            }

            @Override
            protected boolean allowDelete(Item<Customer> rowItem) {
                return false;
            }

            @Override
            protected boolean displayAddFeature() {
                return true;
            }

            @Override
            protected void onAdd(AjaxRequestTarget target, Customer newRow) {
                sbpanel.info(newRow.getFirstName() + " : " + newRow.getLastName() + " record added successfully.");
                target.add(sbpanel);
            }
        };

        grid.setMarkupId("customerGridId");
        grid.setOutputMarkupId(true);
        form.add(grid);
    }

    private List<EditableTextFieldPropertyColumn<Customer, String>> getColumns() {

        List<EditableTextFieldPropertyColumn<Customer, String>> columns = new ArrayList<EditableTextFieldPropertyColumn<Customer, String>>();

        columns.add(new EditableTextFieldPropertyColumn<Customer, String>(Model.of("First Name"), "firstName"));
        columns.add(new EditableTextFieldPropertyColumn<Customer, String>(Model.of("Middle Name"), "middleName"));
        columns.add(new EditableTextFieldPropertyColumn<Customer, String>(Model.of("Last Name"), "lastName"));
        columns.add(new EditableTextFieldPropertyColumn<Customer, String>(Model.of("Age"), "age"));

        return columns;

    }




}
