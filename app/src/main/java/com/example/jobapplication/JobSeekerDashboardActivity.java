package com.example.jobapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jobapplication.databinding.ActivityJobSeekerDashboardBinding;
import com.example.jobapplication.models.JobSeekerProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobSeekerDashboardActivity extends Drawer {
    ActivityJobSeekerDashboardBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_dashboard);
        binding = ActivityJobSeekerDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("JobSeeker Dashboard");

        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "");
        Toast.makeText(getApplicationContext(), "Welcome😎😎" + " " + username, Toast.LENGTH_SHORT).show();


        CardView jsprofile = findViewById(R.id.cardJSProfile);
        jsprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Job seeker Profile").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                JobSeekerProfile jobSeekerProfile = snapshot.getValue(JobSeekerProfile.class);
                                if (jobSeekerProfile != null) {
                                    startActivity(new Intent(JobSeekerDashboardActivity.this, JobSeekerProfileActivity.class)
                                            .putExtra("jobSeekerProfile" , jobSeekerProfile));
                                } else {
                                    startActivity(new Intent(JobSeekerDashboardActivity.this, JobSeekerProfileActivity.class));
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });

        CardView jsjobs = findViewById(R.id.cardJSJobs);
        jsjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobSeekerDashboardActivity.this, JobSeekerJobActivity.class));
            }
        });

        CardView jsnotifications = findViewById(R.id.cardJSNotifications);
        jsnotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobSeekerDashboardActivity.this, JobSeekerNotificationsActivity.class));
            }
        });

        CardView logout = findViewById(R.id.cardJSLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobSeekerDashboardActivity.this, LoginActivity.class));
                finish();
            }
        });


    }
}