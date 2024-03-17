package com.example.jobapplication.models;

import java.io.Serializable;

public class RecruiterProfile implements Serializable{
    String name;
    String email;
    String phone;
    String city;
    String gender;
    String date;
    String company;
    String experience;


    public RecruiterProfile() {

    }

    public RecruiterProfile(String name, String email, String phone, String city, String gender, String date, String company, String experience) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.gender = gender;
        this.date = date;
        this.company = company;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
