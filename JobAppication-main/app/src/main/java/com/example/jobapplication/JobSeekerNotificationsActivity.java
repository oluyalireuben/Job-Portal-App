package com.example.jobapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jobapplication.databinding.ActivityJobSeekerNotificationsBinding;
import com.example.jobapplication.models.JobModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobSeekerNotificationsActivity extends AppCompatActivity {

    ActivityJobSeekerNotificationsBinding binding;
    RecyclerView recyclerView;
    ArrayList<JobModel> jobs;
    private String company, jobTitle, location;
    NotificationAdapter.OnNotifyClicked onNotifyClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJobSeekerNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onNotifyClicked = position -> startActivity(new Intent(JobSeekerNotificationsActivity.this, NotificationDetails.class)
                .putExtra("jobTitle", jobs.get(position).getJobTitle())
                .putExtra("companyName", jobs.get(position).getCompanyName())
                .putExtra("location", jobs.get(position).getLocation())
                .putExtra("date", jobs.get(position).getDate())
                .putExtra("salary", jobs.get(position).getSalary())
                .putExtra("selection", jobs.get(position).getSelectionProcess())
                .putExtra("eligibility", jobs.get(position).getEligibility())
                .putExtra("skills", jobs.get(position).getPreferedSkills())
                .putExtra("about", jobs.get(position).getAboutJob())
                .putExtra("field", jobs.get(position).getJobField())
                .putExtra("email", jobs.get(position).getEmail())
                .putExtra("website", jobs.get(position).getWebsite())
                .putExtra("phone", jobs.get(position).getPhoneNumber())
                .putExtra("profile", jobs.get(position).getCompanyProfile()));

        recyclerView = binding.notificationsRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobs = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Job Seeker Applied Jobs");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobs.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    jobs.add(ds.getValue(JobModel.class));

                }
                recyclerView.setAdapter(new NotificationAdapter(jobs, JobSeekerNotificationsActivity.this, onNotifyClicked));
                binding.notificationsRecyclerView.setVisibility(View.VISIBLE);
                binding.notificationsProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}