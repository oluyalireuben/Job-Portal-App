package com.example.jobapplication.models;

import java.io.Serializable;

public class JobSeekerProfile implements Serializable {
    String name;
    String email;
    String phone;
    String city;
    String gender;
    String dob;
    String course;
    String school;
    String yearOfCompletion;
    String company;
    String experience;

    public JobSeekerProfile() {
    }

    public JobSeekerProfile(
            String name, String email, String phone,
            String city, String gender, String dob, String course,
            String school, String yearOfCompletion, String company, String experience
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.gender = gender;
        this.dob = dob;
        this.course = course;
        this.school = school;
        this.yearOfCompletion = yearOfCompletion;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getYearOfCompletion() {
        return yearOfCompletion;
    }

    public void setYearOfCompletion(String yearOfCompletion) {
        this.yearOfCompletion = yearOfCompletion;
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
