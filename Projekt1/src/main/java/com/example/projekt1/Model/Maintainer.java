package com.example.projekt1.Model;

public class Maintainer extends Person {

    public final static Maintainer NULL_MAINTAINER = new Maintainer("-", "");

    public Maintainer(String firstName, String lastName) {
        super(firstName, lastName);
    }

}
