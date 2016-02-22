package com.domain;

import java.io.Serializable;

/**
 * @author Chhaya-MX - Created on 2/25/2015
 */
public class Person implements Serializable {

    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private int age;
    private String remove;

    public Person(){

    }
    public Person(int id, String lastName, String firstName, String middleName, int age) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRemove() {
        return "remove:".concat(lastName);
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }
}
