package com.example.jobapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.jobapplication.databinding.ActivityApplicantsBinding;
import com.example.jobapplication.databinding.ActivityAvailableJobsBinding;
import com.example.jobapplication.models.Applicant;
import com.example.jobapplication.models.JobModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Applicants extends AppCompatActivity {
    ActivityApplicantsBinding binding;
    RecyclerView recyclerView;
    ArrayList<Applicant> applicants;
    private String company, jobTitle, location;
    ApplicantsAdapter.OnApplicantClicked onApplicantClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApplicantsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchJob(applicants, newText);
                return true;
            }
        });
        onApplicantClicked = position -> startActivity(new Intent(Applicants.this, ApplicantsDetails.class)
                .putExtra("name", applicants.get(position).getName())
                .putExtra("jobTitle", applicants.get(position).getJobTitle())
                .putExtra("companyName", applicants.get(position).getCompanyName())
                .putExtra("location", applicants.get(position).getLocation())
                .putExtra("link", applicants.get(position).getLink())
                .putExtra("award", applicants.get(position).getAward()));

        recyclerView = binding.applicantRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        applicants = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Applicants");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                applicants.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    applicants.add(ds.getValue(Applicant.class));

                }
                recyclerView.setAdapter(new ApplicantsAdapter(applicants, Applicants.this, onApplicantClicked));
                binding.applicantRecyclerView.setVisibility(View.VISIBLE);
                binding.applicantProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void searchJob(ArrayList<Applicant> arrayList, String query) {
        ArrayList<Applicant> filteredList = new ArrayList<>();
        if (query.isEmpty()) {
            recyclerView.setAdapter(new ApplicantsAdapter(applicants, Applicants.this, onApplicantClicked));
        } else {
            for (Applicant applicant : arrayList) {
                if (applicant.getJobTitle().toLowerCase().contains(query) ||
                        applicant.getCompanyName().toLowerCase().contains(query) ||
                        applicant.getLocation().toLowerCase().contains(query)) {

                    filteredList.add(applicant);
                }
                recyclerView.setAdapter(new ApplicantsAdapter(filteredList, Applicants.this, onApplicantClicked));
            }
        }
    }
}
