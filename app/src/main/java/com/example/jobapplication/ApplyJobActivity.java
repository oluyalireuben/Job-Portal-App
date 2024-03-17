package com.example.jobapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobapplication.databinding.ActivityApplyJobBinding;
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
import java.util.Objects;

public class ApplyJobActivity extends AppCompatActivity {

//    TextView tvAbout, tvJobTitle, tvDate, tvCompanyName, tvLocation, tvJobField, tvSalary, tvSelection, tvEligible, tvSkills,
//            tvCompanyProfile, tvWebsite, tvCompanyEmail, tvPhone;

    String jobTitle, companyName, date, location, salary, selection, eligibility, skills, about, field, profile,
            website, email, phone;

    TextView textview;
    LinearLayout llApply, llSave;

    ArrayList<JobModel> jobs;

    ActivityApplyJobBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApplyJobBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        llApply = findViewById(R.id.LL_apply);
        llSave = findViewById(R.id.LL_save);


        jobTitle = getIntent().getStringExtra("jobTitle");
        companyName = getIntent().getStringExtra("companyName");
        location = getIntent().getStringExtra("location");
        date = getIntent().getStringExtra("date");
        salary = getIntent().getStringExtra("salary");
        selection = getIntent().getStringExtra("selection");
        eligibility = getIntent().getStringExtra("eligibility");
        skills = getIntent().getStringExtra("skills");
        about = getIntent().getStringExtra("about");
        field = getIntent().getStringExtra("field");
        profile = getIntent().getStringExtra("profile");
        website = getIntent().getStringExtra("website");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");

        binding.TVJobtitle.setText(jobTitle);
        binding.TVCompanyname.setText(companyName);
        binding.TVLocation.setText(location);
        binding.TVDate.setText(date);
        binding.TVSalary.setText(salary);
        binding.TVSelection.setText(selection);
        binding.TVEligible.setText(eligibility);
        binding.TVSkill.setText(skills);
        binding.TVAbout.setText(about);
        binding.TVIndustrydescription.setText(field);
        binding.TVCprofile.setText(profile);
        binding.TVUrl.setText(website);
        binding.TVEmail.setText(email);
        binding.TVPhone.setText(phone);

        llApply.setOnClickListener(view -> {
            FirebaseDatabase.getInstance().getReference("Job seeker Profile").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            JobSeekerProfile jobSeekerProfile = snapshot.getValue(JobSeekerProfile.class);
                            if (jobSeekerProfile != null) {

                                applyJob(
                                        binding.TVJobtitle,
                                        binding.TVCompanyname,
                                        binding.TVDate,
                                        binding.TVLocation,
                                        binding.TVSalary,
                                        binding.TVSelection,
                                        binding.TVEligible,
                                        binding.TVSkill,
                                        binding.TVAbout,
                                        binding.TVIndustrydescription,
                                        binding.TVCprofile,
                                        binding.TVUrl,
                                        binding.TVEmail,
                                        binding.TVPhone
                                );
                                startActivity(new Intent(ApplyJobActivity.this, ApplyActivity.class)
                                        .putExtra("jobTitle", jobTitle)
                                        .putExtra("companyName", companyName)
                                        .putExtra("location", location)
                                        .putExtra("jobSeekerProfile", jobSeekerProfile));


                            } else {
                                startActivity(new Intent(ApplyJobActivity.this, JobSeekerProfileActivity.class));
                                Toast.makeText(ApplyJobActivity.this, "You have not updated your profile", Toast.LENGTH_SHORT).show();
                            }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        });
        llSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveJob();
                Toast.makeText(ApplyJobActivity.this, "You have successfully saved the Job, proceed to Apply", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ApplyJobActivity.this, JobSeekerSavedJobsActivity.class));
            }
        });


    }

    private void saveJob() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Saved Jobs");
        JobModel jobDetails = new JobModel(
                jobTitle, companyName, date, location, salary, selection, eligibility, skills, about, field, profile, website, email, phone
        );


        ref.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(jobDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ApplyJobActivity.this, "Insertion Complete", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ApplyJobActivity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void applyJob(TextView tvJobTitle, TextView tvCompanyName, TextView tvDate, TextView tvLocation, TextView tvSalary, TextView tvSelection,
                          TextView tvEligible, TextView tvSkills, TextView tvAbout, TextView tvJobField, TextView tvCompanyProfile, TextView tvWebsite,
                          TextView tvCompanyEmail, TextView tvPhone) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        JobModel jobDetails = new JobModel(tvJobTitle.getText().toString(), tvCompanyName.getText().toString(), tvDate.getText().toString(),
                tvLocation.getText().toString(), tvSalary.getText().toString(), tvSelection.getText().toString(),
                tvEligible.getText().toString(), tvSkills.getText().toString(), tvAbout.getText().toString(),
                tvJobField.getText().toString(), tvCompanyProfile.getText().toString(), tvWebsite.getText().toString(),
                tvCompanyEmail.getText().toString(), tvPhone.getText().toString());

        String key = ref.push().getKey();

        ref.child("Job Seeker Applied Jobs").child(key).setValue(jobDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ApplyJobActivity.this, "Insertion Complete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}