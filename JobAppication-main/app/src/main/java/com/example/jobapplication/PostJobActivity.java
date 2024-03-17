package com.example.jobapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobapplication.models.JobModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;

import java.util.Calendar;

public class PostJobActivity extends AppCompatActivity {
    public EditText job_title, company_name,location,salary,selection_process,eligibility,prefered_skills,about_job,
            company_profile,website,company_email,phone_number;

    public Button last_date;
    public Spinner spinner;
    public LinearLayout post, save;

    String[] JobField = {"Select Job Field" , "IT" , "Computer Science" , "Graphics", "Computer Forensics", "IT & Computer Science", "IT, Computer Science and Forensics", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        job_title=findViewById(R.id.ET_jobtitle);
        company_name=findViewById(R.id.ET_companyname);
        last_date=findViewById(R.id.buttonDatePicker);
        location=findViewById(R.id.ED_Location);
        salary=findViewById(R.id.ED_Salary);
        selection_process=findViewById(R.id.ED_SelectionProcess);
        eligibility=findViewById(R.id.ED_Elegibility);
        prefered_skills=findViewById(R.id.ED_PreferedSkills);
        about_job=findViewById(R.id.ED_AboutJob);
        spinner=findViewById(R.id.spinner);
        company_profile=findViewById(R.id.ED_CompanyProfile);
        website=findViewById(R.id.ED_Website);
        company_email=findViewById(R.id.ED_Email);
        phone_number=findViewById(R.id.ED_Phonenumber);
        post=findViewById(R.id.LL_apply);
        save=findViewById(R.id.LL_save);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, JobField);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


       post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postJob(job_title,company_name,last_date,location,salary,selection_process,eligibility,prefered_skills,about_job, spinner,company_profile,website,company_email,phone_number);
                Toast.makeText(PostJobActivity.this,"You have successfully posted the Job",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PostJobActivity.this, PostedJobsActivity.class));
            }

        });
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               saveJob(job_title,company_name,last_date,location,salary,selection_process,eligibility,prefered_skills,about_job, spinner,company_profile,website,company_email,phone_number);
               Toast.makeText(PostJobActivity.this, "You have successfully saved the Job, proceed to Post", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(PostJobActivity.this, SavedJobsActivity.class));
           }
       });

    }
    private void saveJob(EditText job_title, EditText company_name, Button last_date,EditText location, EditText salary, EditText selection_process, EditText eligibility, EditText prefered_skills, EditText about_job, Spinner spinner, EditText company_profile, EditText website, EditText company_email, EditText phone_number) {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        JobModel jobDetails=new JobModel(job_title.getText().toString(),company_name.getText().toString(),last_date.getText().toString(),
                location.getText().toString(),salary.getText().toString(),selection_process.getText().toString(),
                eligibility.getText().toString(),prefered_skills.getText().toString(),about_job.getText().toString(),
                spinner.getSelectedItem().toString(),company_profile.getText().toString(),website.getText().toString(),
                company_email.getText().toString(), phone_number.getText().toString());

        String key=ref.push().getKey();

        ref.child("Recruiter Saved Jobs").child(key).setValue(jobDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(PostJobActivity.this,"Insertion Complete",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postJob(EditText job_title, EditText company_name, Button last_date,EditText location, EditText salary, EditText selection_process, EditText eligibility, EditText prefered_skills, EditText about_job, Spinner spinner, EditText company_profile, EditText website, EditText company_email, EditText phone_number) {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        JobModel jobDetails=new JobModel(job_title.getText().toString(),company_name.getText().toString(),last_date.getText().toString(),
                location.getText().toString(),salary.getText().toString(),selection_process.getText().toString(),
                eligibility.getText().toString(),prefered_skills.getText().toString(),about_job.getText().toString(),
                spinner.getSelectedItem().toString(),company_profile.getText().toString(),website.getText().toString(),
                company_email.getText().toString(), phone_number.getText().toString());

        String key=ref.push().getKey();

        ref.child("Jobs").child(key).setValue(jobDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(PostJobActivity.this,"Insertion Complete",Toast.LENGTH_SHORT).show();
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
                        last_date.setText(selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    }
                },
                year, month, day);

        // Show the date picker dialog
        datePickerDialog.show();
    }
}