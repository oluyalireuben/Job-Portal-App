package com.example.jobapplication;

public class JobModel {
    private String JobTitle;
    private String CompanyName;
    private String Location;

    public JobModel(String jobTitle, String companyName, String location) {
        JobTitle = jobTitle;
        CompanyName = companyName;
        Location = location;

    }

    public String getJobTitle() {        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

}
