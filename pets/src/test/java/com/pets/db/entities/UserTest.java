package com.pets.db.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    String name = "Ali";
    String surname = "Baba";
    String email = "alibaba@gmail.com";
    String phoneNumber = "341-223-1142";
    String imageUrl = "";
    String locale = "Tel-Aviv";
    User user = new User(name, surname, email, phoneNumber, imageUrl, locale);

    @Test
    void getters() {
        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(email, user.getEmail());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(imageUrl, user.getImageUrl());
        assertEquals(locale, user.getLocale());
    }

    @Test
    void setters() {
        String name = "Dudu";
        String surname = "Faruk";
        String email = "dudufaruk@gmail.com";
        String phoneNumber = "111-333-2222";
        String imageUrl = "";
        String locale = "Madrid";

        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setImageUrl(imageUrl);
        user.setLocale(locale);

        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(email, user.getEmail());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(imageUrl, user.getImageUrl());
        assertEquals(locale, user.getLocale());
    }
}