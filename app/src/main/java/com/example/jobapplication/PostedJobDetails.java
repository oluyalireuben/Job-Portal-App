package com.example.jobapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.jobapplication.models.JobModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PostedJobDetails extends AppCompatActivity {
    TextView tvAbout, tvJobTitle, tvDate, tvCompanyName, tvLocation, tvJobField, tvSalary, tvSelection, tvEligible, tvSkills,
            tvCompanyProfile, tvWebsite, tvCompanyEmail, tvPhone;
    TextView textview;
    ArrayList<JobModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_job_details);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        arrayList = new ArrayList<>();

        tvJobTitle=findViewById(R.id.TV_jobtitle);
        tvCompanyName=findViewById(R.id.TV_companyname);
        tvDate=findViewById(R.id.TV_date);
        tvLocation=findViewById(R.id.TV_location);
        tvSalary=findViewById(R.id.TV_salary);
        tvSelection=findViewById(R.id.TV_selection);
        tvEligible=findViewById(R.id.TV_eligible);
        tvSkills=findViewById(R.id.TV_skill);
        tvAbout=findViewById(R.id.TV_about);
        tvJobField=findViewById(R.id.TV_industrydescription);
        tvCompanyProfile=findViewById(R.id.TV_cprofile);
        tvWebsite=findViewById(R.id.TV_url);
        tvCompanyEmail=findViewById(R.id.TV_email);
        tvPhone=findViewById(R.id.TV_phone);

        textview = findViewById(R.id.textviewHeader);
        tvJobTitle = findViewById(R.id.TV_jobtitle);
        tvCompanyName = findViewById(R.id.TV_companyname);
        tvDate = findViewById(R.id.TV_date);
        tvLocation = findViewById(R.id.TV_location);
        tvSalary = findViewById(R.id.TV_salary);
        tvSelection = findViewById(R.id.TV_selection);
        tvEligible = findViewById(R.id.TV_eligible);
        tvSkills = findViewById(R.id.TV_skill);
        tvAbout = findViewById(R.id.TV_about);
        tvJobField = findViewById(R.id.TV_industrydescription);
        tvCompanyProfile = findViewById(R.id.TV_cprofile);
        tvWebsite = findViewById(R.id.TV_url);
        tvCompanyEmail = findViewById(R.id.TV_email);
        tvPhone = findViewById(R.id.TV_phone);

        String textView = getIntent().getStringExtra("jobTitle");
        String jobTitle = getIntent().getStringExtra("jobTitle");
        String companyName = getIntent().getStringExtra("companyName");
        String location = getIntent().getStringExtra("location");
        String date = getIntent().getStringExtra("date");
        String salary = getIntent().getStringExtra("salary");
        String selection = getIntent().getStringExtra("selection");
        String eligibility = getIntent().getStringExtra("eligibility");
        String skills = getIntent().getStringExtra("skills");
        String about = getIntent().getStringExtra("about");
        String field = getIntent().getStringExtra("field");
        String profile = getIntent().getStringExtra("profile");
        String website = getIntent().getStringExtra("website");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");

        textview.setText(textView);
        tvJobTitle.setText(jobTitle);
        tvCompanyName.setText(companyName);
        tvLocation.setText(location);
        tvDate.setText(date);
        tvSalary.setText(salary);
        tvSelection.setText(selection);
        tvEligible.setText(eligibility);
        tvSkills.setText(skills);
        tvAbout.setText(about);
        tvJobField.setText(field);
        tvCompanyProfile.setText(profile);
        tvWebsite.setText(website);
        tvCompanyEmail.setText(email);
        tvPhone.setText(phone);


    }
}