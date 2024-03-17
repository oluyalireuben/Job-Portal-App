package com.example.jobapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class JobSeekerJobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_job);

        CardView availableJobs = findViewById(R.id.cardAvailableJobs);
        availableJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobSeekerJobActivity.this, AvailableJobsActivity.class));
            }
        });

        CardView applyjob = findViewById(R.id.cardApplyJob);
        applyjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobSeekerJobActivity.this, ApplyAvailableJobsActivity.class));
            }
        });

        CardView addcompany = findViewById(R.id.cardJSSavedJobs);
        addcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobSeekerJobActivity.this, JobSeekerSavedJobsActivity.class));
            }
        });

        CardView viewjobs = findViewById(R.id.cardAppliedJobs);
        viewjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobSeekerJobActivity.this, AppliedJobsActivity.class));
            }
        });

    }
}