package com.example.jobapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobapplication.models.JobModel;

import java.util.ArrayList;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    private final ArrayList<JobModel> jobs;
    Context context;
    static OnJobClicked onJobClicked;


    public JobsAdapter(ArrayList<JobModel> jobs, Context context, OnJobClicked onMessageClicked) {
        this.jobs = jobs;
        this.context = context;
        JobsAdapter.onJobClicked = onMessageClicked;
    }

    public interface OnJobClicked {
        void onJobClicked(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.job_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView1.setText(jobs.get(position).getJobTitle());
        holder.textView2.setText(jobs.get(position).getCompanyName());
        holder.textView3.setText(jobs.get(position).getLocation());

        holder.itemView.setOnClickListener(view -> {
            onJobClicked.onJobClicked(position);
        });

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.TV_jobtitle);
            textView2 = itemView.findViewById(R.id.TV_companyname);
            textView3 = itemView.findViewById(R.id.TV_location);
        }
    }

}