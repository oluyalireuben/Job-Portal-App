package com.example.jobapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobapplication.models.Applicant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShortlistedDetails extends AppCompatActivity {
    TextView tvName, tvJobTitle, tvCompanyName, tvLocation, tvLink, tvAward;
    LinearLayout linearLayout;
    ArrayList<Applicant> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortlisted_details);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        arrayList = new ArrayList<>();

        tvName=findViewById(R.id.tvName);
        tvJobTitle=findViewById(R.id.tvjobTiltle);
        tvCompanyName=findViewById(R.id.tvcompanyName);
        tvLocation=findViewById(R.id.tvlocation);
        tvLink=findViewById(R.id.tvLink);
        tvAward=findViewById(R.id.tvAward);
        linearLayout = findViewById(R.id.LL_shortlist);

        String name = getIntent().getStringExtra("name");
        String jobTitle = getIntent().getStringExtra("jobTitle");
        String companyName = getIntent().getStringExtra("companyName");
        String location = getIntent().getStringExtra("location");
        String link = getIntent().getStringExtra("link");
        String award = getIntent().getStringExtra("award");

        tvName.setText(name);
        tvJobTitle.setText(jobTitle);
        tvCompanyName.setText(companyName);
        tvLocation.setText(location);
        tvLink.setText(link);
        tvAward.setText(award);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShortlistedDetails.this, JobSeekerNotificationsActivity.class));
            }
        });
    }
}