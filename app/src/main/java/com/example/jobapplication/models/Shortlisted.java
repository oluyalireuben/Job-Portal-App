package com.example.jobapplication.models;

public class Shortlisted {
    String name;
    String jobTitle;
    String companyName;
    String location;
    String link;
    String award;

    public Shortlisted() {
    }

    public Shortlisted(String name, String jobTitle, String companyName, String location, String link, String award) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.location = location;
        this.link = link;
        this.award = award;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
