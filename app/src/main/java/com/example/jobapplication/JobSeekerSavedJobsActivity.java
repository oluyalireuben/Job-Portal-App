package com.example.jobapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobapplication.databinding.ActivityJobSeekerSavedJobsBinding;
import com.example.jobapplication.models.JobModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class JobSeekerSavedJobsActivity extends AppCompatActivity {
    ActivityJobSeekerSavedJobsBinding binding;
    RecyclerView recyclerView;
    ArrayList<JobModel> jobs;
    JobsAdapter.OnJobClicked onJobClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJobSeekerSavedJobsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchJob(jobs, newText);
                return true;
            }
        });
        onJobClicked = position -> startActivity(new Intent(JobSeekerSavedJobsActivity.this, JobSeekerSavedJobsDetails.class)
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

        recyclerView = binding.jobsRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobs = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Saved Jobs")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                jobs.add(snapshot.getValue(JobModel.class));

                recyclerView.setAdapter(new JobsAdapter(jobs, JobSeekerSavedJobsActivity.this, onJobClicked));
                binding.jobsRecyclerView.setVisibility(View.VISIBLE);
                binding.jobsProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void searchJob(ArrayList<JobModel> arrayList, String query) {
        ArrayList<JobModel> filteredList = new ArrayList<>();
        if (query.isEmpty()) {
            recyclerView.setAdapter(new JobsAdapter(jobs, JobSeekerSavedJobsActivity.this, onJobClicked));
        } else {
            for (JobModel jobModel : arrayList) {
                if (jobModel.getJobTitle().toLowerCase().contains(query) ||
                        jobModel.getCompanyName().toLowerCase().contains(query) ||
                        jobModel.getLocation().toLowerCase().contains(query)) {

                    filteredList.add(jobModel);
                }
                recyclerView.setAdapter(new JobsAdapter(filteredList, JobSeekerSavedJobsActivity.this, onJobClicked));
            }
        }
    }
}