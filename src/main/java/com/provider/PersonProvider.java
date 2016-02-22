package com.provider;

import com.domain.Person;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.*;

/**
 * @author Chhaya-MX - Created on 2/25/2015
 */
public class PersonProvider extends SortableDataProvider<Person, String> {

    private List<Person> personList = new ArrayList<Person>();
    private SortableDataProviderComparator comparator = new SortableDataProviderComparator();

    class SortableDataProviderComparator implements Comparator<Person>, Serializable {
        public int compare(final Person o1, final Person o2) {
            PropertyModel<Comparable> model1 = new PropertyModel<Comparable>(o1, getSort().getProperty());
            PropertyModel<Comparable> model2 = new PropertyModel<Comparable>(o2, getSort().getProperty());

            int result = model1.getObject().compareTo(model2.getObject());

            if (!getSort().isAscending()) {
                result = -result;
            }

            return result;
        }
    }

    public PersonProvider(List<Person> PersonList) {
        Person latest = new Person();
        latest.setId(2000);
        latest.setLastName("111");
        latest.setFirstName("222");
        latest.setAge(30);
        PersonList.add(latest);

        latest = new Person();
        latest.setId(3000);
        latest.setLastName("333");
        latest.setFirstName("444");
        latest.setAge(30);
        PersonList.add(latest);

        setSort("firstName", SortOrder.ASCENDING);
    }

    public PersonProvider() {

        Person latest = new Person();
        latest.setId(1000);
        latest.setLastName("111");
        latest.setFirstName("222");
        latest.setAge(20);
        personList.add(latest);

        latest = new Person();
        latest.setId(2000);
        latest.setLastName("333");
        latest.setFirstName("444");
        latest.setAge(30);
        personList.add(latest);

        latest = new Person();
        latest.setId(3000);
        latest.setLastName("555");
        latest.setFirstName("666");
        latest.setAge(30);
        personList.add(latest);

        latest = new Person();
        latest.setId(4000);
        latest.setLastName("777");
        latest.setFirstName("888");
        latest.setAge(30);
        personList.add(latest);

        setSort("firstName", SortOrder.ASCENDING);


    }

    @Override
    public Iterator<? extends Person> iterator(final long first, final long count) {

        // Get the data
        List<Person> newList = new ArrayList<Person>(personList);

        // Sort the data
        Collections.sort(newList, comparator);

        // Return the data for the current page - this can be determined only after sorting
        return newList.subList((int) first, (int) (first + count)).iterator();

    }

    @Override
    public long size() {
        return personList.size();
    }

    @Override
    public IModel<Person> model(final Person Person) {

        return new IModel<Person>() {
            @Override
            public Person getObject() {
                return Person;
            }

            @Override
            public void setObject(Person object) {

            }

            @Override
            public void detach() {

            }
        };
    }

    @Override
    public void detach() {

    }


    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> PersonList) {
        this.personList = PersonList;
    }
}
