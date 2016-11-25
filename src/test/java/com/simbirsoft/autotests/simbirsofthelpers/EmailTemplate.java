package com.simbirsoft.autotests.simbirsofthelpers;

import java.util.Random;

public class EmailTemplate {

    private String eMail;
    private String message;
    private String fio;
    private String firstName;
    private String secondName;
    private String city;
    private String phone;


    public String getEmailTemplate() {
        setEmail("test@" + eMail + ".com");
        return eMail;
    }

    public String getMessageTemplate() {
        setMessage(message +
                "\r\nThe letter was sent by the automation test. This letter does not require an answer. It can be removed.");
        return message;
    }

    public String getFirstNameTemplate() {
        setFio("Test "+firstName);
        return firstName;
    }

    public String getSecondNameTemplate() {
        setFio(secondName);
        return secondName;
    }

    public String getCityTemplate() {
        setCity(city);
        return city;
    }

    public String getPhoneTemplate() {
        setPhone(phone);
        return phone;
    }

    public EmailTemplate setFirstName(String nameF) {
        this.firstName = nameF;
        return this;
    }

    public EmailTemplate setSecondName(String nameI) {
        this.secondName = nameI;
        return this;
    }

    public EmailTemplate setEmail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    public EmailTemplate setMessage(String message) {
        this.message = message;
        return this;
    }

    public EmailTemplate setFio(String fio) {
        this.fio = fio;
        return this;
    }

    public EmailTemplate setCity(String city) {
        this.city = city;
        return this;
    }

    public EmailTemplate setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    private int getRandom(int from, int to) {
        Random rnd = new Random();
        int res = rnd.nextInt(to);
        res = res < from ? getRandom(from,to) : res;
        return res;
    }

}
