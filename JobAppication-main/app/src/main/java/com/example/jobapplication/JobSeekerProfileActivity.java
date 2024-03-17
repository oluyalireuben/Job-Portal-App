package com.example.jobapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jobapplication.models.JobSeekerProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

public class JobSeekerProfileActivity extends AppCompatActivity {

    public EditText etFullnames, etEmail, etMobileNo, etCity, etCourseName, etGender, etSchoolName, etCompletionYear, etCompany, etYearsworked;
    Button btn;
    public LinearLayout llUpdate;
    Spinner spinner;
    private ProgressBar progressBar;
    String[] award = {"Select" , "Masters" , "Degree" , "Diploma", "Certificate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_profile);

        progressBar = findViewById(R.id.progress);
        etFullnames = findViewById(R.id.ET_Fullnames);
        etEmail = findViewById(R.id.ET_Email);
        etMobileNo = findViewById(R.id.ET_Phone);
        etCity = findViewById(R.id.ET_City);
        etCourseName = findViewById(R.id.ET_Coursename);
        etSchoolName = findViewById(R.id.ET_Universityname);
        etCompletionYear = findViewById(R.id.ET_Completionyear);
        spinner = findViewById(R.id.spinner1);
        etCompany = findViewById(R.id.ET_WECompanyname);
        etYearsworked = findViewById(R.id.ET_WEYears);
        btn = findViewById(R.id.buttonDatePicker);
        etGender = findViewById(R.id.ET_Gender);
        llUpdate = findViewById(R.id.LL_update);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, award);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        JobSeekerProfile jobSeekerProfile = (JobSeekerProfile) getIntent().getSerializableExtra("jobSeekerProfile");
        if (jobSeekerProfile != null) {
            etFullnames.setText(jobSeekerProfile.getName());
            etEmail.setText(jobSeekerProfile.getEmail());
            etMobileNo.setText(jobSeekerProfile.getPhone());
            etCity.setText(jobSeekerProfile.getCity());
            etCourseName.setText(jobSeekerProfile.getCourse());
            etSchoolName.setText(jobSeekerProfile.getSchool());
            etCompletionYear.setText(jobSeekerProfile.getYearOfCompletion());
            etCompany.setText(jobSeekerProfile.getCompany());
            etYearsworked.setText(jobSeekerProfile.getExperience());
            btn.setText(jobSeekerProfile.getDob());
            etGender.setText(jobSeekerProfile.getGender());

            llUpdate.setOnClickListener(view -> updateUserProfile());
        } else {
            llUpdate.setOnClickListener(view -> updateUserProfile());
        }

    }

    private void updateUserProfile() {

        String Fullnames = etFullnames.getText().toString();
        String Email = etEmail.getText().toString();
        String MobileNo = etMobileNo.getText().toString();
        String City = etCity.getText().toString();
        String CourseName = etCourseName.getText().toString();
        String SchoolName = etSchoolName.getText().toString();
        String CompletionYear = etCompletionYear.getText().toString();
        String Award = spinner.getSelectedItem().toString().trim();
        String Company = etCompany.getText().toString();
        String YearsWorked = etYearsworked.getText().toString();
        String DOB = btn.getText().toString().trim();
        String Gender = etGender.getText().toString();

        if (Fullnames.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter Full names", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Email.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (MobileNo.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (City.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter City", Toast.LENGTH_SHORT).show();
            return;
        }
        if (CourseName.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter Course Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Gender.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter your gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if (SchoolName.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter School Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (CompletionYear.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter Completion Year", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Award.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter Award", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Company.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter Company Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (YearsWorked.isEmpty()) {
            Toast.makeText(JobSeekerProfileActivity.this, "Enter Year Worked", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference("Job seeker Profile/" + FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(
                        new JobSeekerProfile(
                                Fullnames, Email, MobileNo, City, Gender, DOB,
                                CourseName, SchoolName, CompletionYear, Company, YearsWorked
                        )
                ).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                        etFullnames.setText("");
                        etEmail.setText("");
                        etMobileNo.setText("");
                        etCity.setText("");
                        etCourseName.setText("");
                        etSchoolName.setText("");
                        etCompletionYear.setText("");
                        etCompany.setText("");
                        etYearsworked.setText("");
                        etGender.setText("");
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(this, JobSeekerDashboardActivity.class));


                    } else {
                        Toast.makeText(this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
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