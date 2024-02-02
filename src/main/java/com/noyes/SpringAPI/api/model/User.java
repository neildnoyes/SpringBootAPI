package com.noyes.SpringAPI.api.model;


public class User {

    /**
     * User properties
     */
    private int id;
    private String first_name;
    private String last_name;
    private int age;
    private String email;


    /**
     * User constructor
     */
    public User(int id, String first_name, String last_name, int age, String email){
        this.id = id;
        this.age = age;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    /**
     * User getters & setters
     */
    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public int get_age() {
        return age;
    }

    public void set_age(int age) {
        this.age = age;
    }

    public String get_email() {
        return email;
    }

    public void set_email(String email) {
        this.email = email;
    }

    public String get_first_name() {
        return first_name;
    }

    public void set_first_name(String first_name) {
        this.first_name = first_name;
    }

    public String get_last_name() {
        return last_name;
    }

    public void set_last_name(String last_name) {
        this.last_name = last_name;
    }

}
