package com.zufar.bookshelf.entity;

public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE");

    private String value;

    Gender(String value) {
        this.value = value;

    }
    public String getValue() {
        return value;
    }

    public void setValue(String name) {
        this.value = name;
    }

}