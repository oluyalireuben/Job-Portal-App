package com.example.jobapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jobapplication.models.RecruiterProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecruiterDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_dashboard);

        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "Welcome"+" "+username, Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


        CardView job = findViewById(R.id.cardJobs);
        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecruiterDashboardActivity.this, JobActivity.class));
            }
        });

        CardView candidates = findViewById(R.id.cardCandidates);
        candidates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecruiterDashboardActivity.this, Applicants.class));
            }
        });

        CardView recprofile = findViewById(R.id.cardRecruiterProfile);
        recprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Recruiter Profile").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                RecruiterProfile recruiterProfile = snapshot.getValue(RecruiterProfile.class);
                                if (recruiterProfile != null) {
                                    startActivity(new Intent(RecruiterDashboardActivity.this, RecruiterProfileActivity.class)
                                            .putExtra("recruiterProfile" , recruiterProfile));
                                } else {
                                    startActivity(new Intent(RecruiterDashboardActivity.this, JobSeekerProfileActivity.class));
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });

        CardView exit = findViewById(R.id.cardRecLogout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecruiterDashboardActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_care:
                            // Start CustomerCareActivity Services or perform relevant action
                            startActivity(new Intent(RecruiterDashboardActivity.this, CustomerCare.class));
                            Toast.makeText(RecruiterDashboardActivity.this, "Customer Care Services", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.nav_faq:
                            // Start FAQActivity or perform relevant action
                            startActivity(new Intent(RecruiterDashboardActivity.this, FAQActivity.class));
                            Toast.makeText(RecruiterDashboardActivity.this, "Frequently Asked Questions", Toast.LENGTH_SHORT).show();
                            return true;

                        case R.id.nav_chat:
                            startActivity(new Intent(RecruiterDashboardActivity.this, Chats.class)
                                    .putExtra("is_viewing_as_recruiter" , true));
                            return true;
                        case R.id.nav_terms:
                            // Start TermsAndConditionsActivity or perform relevant action
                            startActivity(new Intent(RecruiterDashboardActivity.this, TermsandConditionsActivity.class));
                            Toast.makeText(RecruiterDashboardActivity.this, "Terms and Conditions applies", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.nav_about:
                            // Start AboutActivity or perform relevant action
                            startActivity(new Intent(RecruiterDashboardActivity.this, AboutActivity.class));
                            Toast.makeText(RecruiterDashboardActivity.this, "About Recruitment Android Application", Toast.LENGTH_SHORT).show();
                            return true;
                        }

                    finish();
                    return false;
                }

            };
}