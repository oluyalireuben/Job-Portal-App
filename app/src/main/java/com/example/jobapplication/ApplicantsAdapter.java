package com.example.jobapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobapplication.models.Applicant;

import java.util.ArrayList;

public class ApplicantsAdapter extends RecyclerView.Adapter<ApplicantsAdapter.ViewHolder> {

    private final ArrayList<Applicant> applicants;
    Context context;
    static OnApplicantClicked onApplicantClicked;


    public ApplicantsAdapter(ArrayList<Applicant> applicants, Context context, OnApplicantClicked onMessageClicked) {
        this.applicants = applicants;
        this.context = context;
        ApplicantsAdapter.onApplicantClicked = onMessageClicked;
    }

    public interface OnApplicantClicked {
        void onApplicantClicked(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.candidate_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView1.setText(applicants.get(position).getName());
        holder.textView2.setText(applicants.get(position).getJobTitle());
        holder.textView3.setText(applicants.get(position).getCompanyName());
        holder.textView4.setText(applicants.get(position).getLocation());

        holder.itemView.setOnClickListener(view -> {
            onApplicantClicked.onApplicantClicked(position);
        });

    }

    @Override
    public int getItemCount() {
        return applicants.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3, textView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.tvcandidateName);
            textView2 = itemView.findViewById(R.id.tvJobTitle);
            textView3 = itemView.findViewById(R.id.TV_companyname);
            textView4 = itemView.findViewById(R.id.TV_location);
        }
    }

}