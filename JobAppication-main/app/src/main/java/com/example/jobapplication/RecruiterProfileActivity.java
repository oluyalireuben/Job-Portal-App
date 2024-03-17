package com.example.jobapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jobapplication.models.JobSeekerProfile;
import com.example.jobapplication.models.RecruiterProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

public class RecruiterProfileActivity extends AppCompatActivity {
    public EditText etFullnames, etEmail, etMobileNo, etCity, etGender, etCompany, etYearsworked;
    Button btn;
    public LinearLayout llUpdate;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_profile);

        progressBar = findViewById(R.id.progressbar);
        etFullnames = findViewById(R.id.ET_RFullnames);
        etEmail = findViewById(R.id.ET_REmail);
        etMobileNo = findViewById(R.id.ET_RPhone);
        etCity = findViewById(R.id.ET_RCity);
        etGender = findViewById(R.id.ET_RGender);
        etCompany = findViewById(R.id.ET_Companyname);
        etYearsworked = findViewById(R.id.ET_Years);
        btn = findViewById(R.id.buttonDatePicker);

        llUpdate = findViewById(R.id.LL_update);

        RecruiterProfile recruiterProfile = (RecruiterProfile) getIntent().getSerializableExtra("recruiterProfile");
        if (recruiterProfile != null) {
            etFullnames.setText(recruiterProfile.getName());
            etEmail.setText(recruiterProfile.getEmail());
            etMobileNo.setText(recruiterProfile.getPhone());
            etCity.setText(recruiterProfile.getCity());
            etGender.setText(recruiterProfile.getGender());
            etCompany.setText(recruiterProfile.getCompany());
            etYearsworked.setText(recruiterProfile.getExperience());
            btn.setText(recruiterProfile.getDate());


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
        String Gender = etGender.getText().toString();
        String DOB = btn.getText().toString().trim();
        String Company = etCompany.getText().toString();
        String YearsWorked = etYearsworked.getText().toString();

        if (Fullnames.isEmpty()) {
            Toast.makeText(RecruiterProfileActivity.this, "Enter Full names", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Email.isEmpty()) {
            Toast.makeText(RecruiterProfileActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (MobileNo.isEmpty()) {
            Toast.makeText(RecruiterProfileActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (City.isEmpty()) {
            Toast.makeText(RecruiterProfileActivity.this, "Enter City", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Gender.isEmpty()) {
            Toast.makeText(RecruiterProfileActivity.this, "Enter your gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Company.isEmpty()) {
            Toast.makeText(RecruiterProfileActivity.this, "Enter Company Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (YearsWorked.isEmpty()) {
            Toast.makeText(RecruiterProfileActivity.this, "Enter Year Worked", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference("Recruiter Profile/" + FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(
                        new RecruiterProfile(
                                Fullnames, Email, MobileNo, City, Gender, DOB, Company, YearsWorked
                        )
                ).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                        etFullnames.setText("");
                        etEmail.setText("");
                        etMobileNo.setText("");
                        etCity.setText("");
                        etCompany.setText("");
                        etYearsworked.setText("");
                        etGender.setText("");
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(this, RecruiterDashboardActivity.class));


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