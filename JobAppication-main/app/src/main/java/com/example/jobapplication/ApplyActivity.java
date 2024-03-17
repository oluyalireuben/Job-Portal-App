package com.example.jobapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.Edits;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobapplication.databinding.ActivityApplyBinding;
import com.example.jobapplication.models.Applicant;
import com.example.jobapplication.models.JobModel;
import com.example.jobapplication.models.JobSeekerProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class ApplyActivity extends AppCompatActivity {
    ActivityApplyBinding binding;
    LinearLayout llApply;
    TextView name, tvJobTitle, tvCompanyName,tvLocation;
    Spinner spinner;
    Button btn;
    ArrayList<JobModel> jobs;

    EditText link;
    String[] award = {"Select" , "Masters" , "Degree" , "Diploma", "Certificate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityApplyBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        llApply = findViewById(R.id.LL_apply);
        name = findViewById(R.id.ET_Fullnames);
        link = findViewById(R.id.ET_Linkedin);
        tvJobTitle = findViewById(R.id.tvjobTiltle);
        tvCompanyName = findViewById(R.id.tvcompanyName);
        tvLocation = findViewById(R.id.tvlocation);
        spinner = findViewById(R.id.spinner1);
        btn = findViewById(R.id.buttonDatePicker);

        String JobTitle = getIntent().getStringExtra("jobTitle");
        String CompanyName = getIntent().getStringExtra("companyName");
        String Location = getIntent().getStringExtra("location");

        tvJobTitle.setText(JobTitle);
        tvCompanyName.setText(CompanyName);
        tvLocation.setText(Location);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, award);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        JobSeekerProfile profile = (JobSeekerProfile) getIntent().getSerializableExtra("jobSeekerProfile");
        // Autofill the fields if the job seeker has already updated their profile
        if (profile != null) {
            binding.ETFullnames.setText(profile.getName());
            binding.ETEmail.setText(profile.getEmail());
            binding.ETPhone.setText(profile.getPhone());
            binding.ETCity.setText(profile.getCity());
            binding.ETGender.setText(profile.getGender());
            binding.ETCoursename.setText(profile.getCourse());
            binding.ETUniversityname.setText(profile.getSchool());
            binding.ETCompletionyear.setText(profile.getYearOfCompletion());
            binding.ETWECompanyname.setText(profile.getCompany());
            binding.ETWEYears.setText(profile.getExperience());

        } else {

        }
        llApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                candidates(name, tvJobTitle, tvCompanyName, tvLocation, link, spinner);
                Toast.makeText(ApplyActivity.this, "You have successfully appied for this Job", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ApplyActivity.this, NotifyDialog.class));
            }
        });

    }
    private void candidates(TextView name, TextView tvJobTitle, TextView tvCompanyName, TextView tvLocation, EditText link, Spinner spinner){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Applicant applicant = new Applicant(name.getText().toString(),tvJobTitle.getText().toString(), tvCompanyName.getText().toString(),
                tvLocation.getText().toString(), link.getText().toString(), spinner.getSelectedItem().toString());

        String key = ref.push().getKey();

        ref.child("Applicants").child(key).setValue(applicant).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ApplyActivity.this, "Insertion Complete", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showDatePickerDialog(View v) {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        btn.setText(selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    }
                },
                year, month, day);

        // Show the date picker dialog
        datePickerDialog.show();
    }
}