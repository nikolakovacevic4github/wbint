package com.wbint.wbclient.model.user;

public class User {
    private String givenName;
    private String surname;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}