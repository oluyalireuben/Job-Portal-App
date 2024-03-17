package com.example.jobapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobapplication.models.JobModel;
import com.example.jobapplication.models.JobSeekerProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SavedJobsDetails extends AppCompatActivity {
    TextView tvAbout, tvJobTitle, tvDate, tvCompanyName, tvLocation, tvJobField, tvSalary, tvSelection, tvEligible, tvSkills,
            tvCompanyProfile, tvWebsite, tvCompanyEmail, tvPhone;

    TextView textview;
    LinearLayout llApply;

    ArrayList<JobModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_jobs_details);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        arrayList = new ArrayList<>();

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

        llApply = findViewById(R.id.LL_Post);

        llApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postJob(tvJobTitle,tvCompanyName,tvDate,tvLocation,tvSalary,tvSelection,tvEligible,tvSkills,tvAbout, tvJobField,tvCompanyProfile,tvWebsite,tvCompanyEmail,tvPhone);
                Toast.makeText(SavedJobsDetails.this, "You have successfully posted the Job", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SavedJobsDetails.this, PostedJobsActivity.class));
            }
        });

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
    private void postJob(TextView tvJobTitle, TextView tvCompanyName, TextView tvLocation, TextView tvDate, TextView tvSalary,
                         TextView tvSelection, TextView tvEligible, TextView tvSkills, TextView tvAbout,
                         TextView tvJobField, TextView tvCompanyProfile, TextView tvWebsite, TextView tvCompanyEmail,
                         TextView tvPhone)
    {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        JobModel jobDetails=new JobModel(tvJobTitle.getText().toString(),tvCompanyName.getText().toString(),
                tvLocation.getText().toString(), tvDate.getText().toString(),tvSalary.getText().toString(),
                tvSelection.getText().toString(), tvEligible.getText().toString(),tvSkills.getText().toString(),
                tvAbout.getText().toString(), tvJobField.getText().toString(),tvCompanyProfile.getText().toString(),
                tvWebsite.getText().toString(), tvCompanyEmail.getText().toString(), tvPhone.getText().toString());

        String key=ref.push().getKey();

        ref.child("Jobs").child(key).setValue(jobDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(SavedJobsDetails.this,"Insertion Complete",Toast.LENGTH_SHORT).show();
            }
        });
    }
}