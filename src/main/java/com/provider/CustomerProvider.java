package com.provider;

import com.domain.Customer;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.*;

/**
 * @author Chhaya-MX - Created on 2/25/2015
 */
public class CustomerProvider extends SortableDataProvider<Customer, String> {

    private List<Customer> customerList = new ArrayList<Customer>();
    private SortableDataProviderComparator comparator = new SortableDataProviderComparator();

    class SortableDataProviderComparator implements Comparator<Customer>, Serializable {
        public int compare(final Customer o1, final Customer o2) {
            PropertyModel<Comparable> model1 = new PropertyModel<Comparable>(o1, getSort().getProperty());
            PropertyModel<Comparable> model2 = new PropertyModel<Comparable>(o2, getSort().getProperty());

            int result = model1.getObject().compareTo(model2.getObject());

            if (!getSort().isAscending()) {
                result = -result;
            }

            return result;
        }
    }

    public CustomerProvider(List<Customer> customerList) {
        Customer latest = new Customer();
        latest.setId(2000);
        latest.setLastName("LMN");
        latest.setFirstName("OPQ");
        latest.setAge(30);
        customerList.add(latest);

        latest = new Customer();
        latest.setId(3000);
        latest.setLastName("OPQ");
        latest.setFirstName("RST");
        latest.setAge(30);
        customerList.add(latest);

        setSort("firstName", SortOrder.ASCENDING);
    }

    public CustomerProvider() {

        Customer latest = new Customer();
        latest.setId(1000);
        latest.setLastName("XYZ");
        latest.setFirstName("ABC");
        latest.setAge(20);
        customerList.add(latest);

        latest = new Customer();
        latest.setId(2000);
        latest.setLastName("LMN");
        latest.setFirstName("OPQ");
        latest.setAge(30);
        customerList.add(latest);

        latest = new Customer();
        latest.setId(3000);
        latest.setLastName("OPQ");
        latest.setFirstName("RST");
        latest.setAge(30);
        customerList.add(latest);

        latest = new Customer();
        latest.setId(4000);
        latest.setLastName("HIJ");
        latest.setFirstName("KLM");
        latest.setAge(30);
        customerList.add(latest);

        setSort("firstName", SortOrder.ASCENDING);


    }

    @Override
    public Iterator<? extends Customer> iterator(final long first, final long count) {

        // Get the data
        List<Customer> newList = new ArrayList<Customer>(customerList);

        // Sort the data
        Collections.sort(newList, comparator);

        // Return the data for the current page - this can be determined only after sorting
        return newList.subList((int) first, (int) (first + count)).iterator();

    }

    @Override
    public long size() {
        return customerList.size();
    }

    @Override
    public IModel<Customer> model(final Customer customer) {

        return new IModel<Customer>() {
            @Override
            public Customer getObject() {
                return customer;
            }

            @Override
            public void setObject(Customer object) {

            }

            @Override
            public void detach() {

            }
        };
    }

    @Override
    public void detach() {

    }


    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
