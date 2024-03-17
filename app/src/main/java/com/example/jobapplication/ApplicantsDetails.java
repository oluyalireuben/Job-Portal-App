package com.example.jobapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobapplication.models.Applicant;
import com.example.jobapplication.models.JobModel;
import com.example.jobapplication.models.Shortlisted;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ApplicantsDetails extends AppCompatActivity {
    TextView tvName, tvJobTitle, tvCompanyName, tvLocation, tvLink, tvAward;
    LinearLayout linearLayout;
    ArrayList<Applicant> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants_details);

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
                Shortlisted(tvName, tvJobTitle, tvCompanyName, tvLocation, tvLink, tvAward);
                Toast.makeText(ApplicantsDetails.this, "You have successfully shortlisted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ApplicantsDetails.this, Applicants.class));
            }
        });

    }
    private void Shortlisted(TextView tvName, TextView tvJobTitle, TextView tvCompanyName, TextView tvLocation, TextView tvLink, TextView tvAward){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Shortlisted shortlisted = new Shortlisted(tvName.getText().toString(),tvJobTitle.getText().toString(), tvCompanyName.getText().toString(),
                tvLocation.getText().toString(), tvLink.getText().toString(),tvAward.getText().toString());

        String key = ref.push().getKey();

        ref.child("Shortlisted").child(key).setValue(shortlisted).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ApplicantsDetails.this, "Insertion Complete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}