package com.example.jobapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobapplication.models.JobModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private final ArrayList<JobModel> jobs;
    Context context;
    static OnNotifyClicked onNotifyClicked;


    public NotificationAdapter(ArrayList<JobModel> jobs, Context context, OnNotifyClicked onMessageClicked) {
        this.jobs = jobs;
        this.context = context;
        NotificationAdapter.onNotifyClicked = onMessageClicked;
    }

    public interface OnNotifyClicked {
        void onNotifyClicked(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notifyrow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView1.setText(jobs.get(position).getJobTitle());
        holder.textView2.setText(jobs.get(position).getCompanyName());

        holder.itemView.setOnClickListener(view -> {
            onNotifyClicked.onNotifyClicked(position);
        });

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.jobtitle);
            textView2 = itemView.findViewById(R.id.jobcompany);
        }
    }

}