package com.example.jobapplication.models;

public class JobModel {
    private String JobTitle;
    private String CompanyName;
    private String Date;
    private String Location;
    private String Salary;
    private String SelectionProcess;
    private String Eligibility;
    private String PreferedSkills;
    private String AboutJob;
    private String JobField;
    private String CompanyProfile;
    private String Website;
    private String Email;
    private String PhoneNumber;

    public JobModel() {
    }

    public JobModel(String jobTitle, String companyName, String lastDateOfApplication, String location, String salary, String selectionProcess, String eligibility, String preferedSkills, String aboutJob, String jobField, String companyProfile, String website, String email, String phoneNumber) {
        JobTitle = jobTitle;
        CompanyName = companyName;
        Date = lastDateOfApplication;
        Location = location;
        Salary = salary;
        SelectionProcess = selectionProcess;
        Eligibility = eligibility;
        PreferedSkills = preferedSkills;
        AboutJob = aboutJob;
        JobField = jobField;
        CompanyProfile = companyProfile;
        Website = website;
        Email = email;
        PhoneNumber = phoneNumber;
    }

    public String getCompanyProfile() {
        return CompanyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        CompanyProfile = companyProfile;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getJobTitle() {
        return JobTitle;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getSelectionProcess() {
        return SelectionProcess;
    }

    public void setSelectionProcess(String selectionProcess) {
        SelectionProcess = selectionProcess;
    }

    public String getEligibility() {
        return Eligibility;
    }

    public void setEligibility(String eligibility) {
        Eligibility = eligibility;
    }

    public String getPreferedSkills() {
        return PreferedSkills;
    }

    public void setPreferedSkills(String preferedSkills) {
        PreferedSkills = preferedSkills;
    }

    public String getAboutJob() {
        return AboutJob;
    }

    public void setAboutJob(String aboutJob) {
        AboutJob = aboutJob;
    }

    public String getJobField() {
        return JobField;
    }

    public void setJobField(String jobField) {
        JobField = jobField;
    }

}
