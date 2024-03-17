package com.example.jobapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobapplication.models.Shortlisted;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private final ArrayList<Shortlisted> shortlist;
    Context context;
    static OnClicked onClicked;


    public NotificationsAdapter(ArrayList<Shortlisted> shortlist, Context context, OnClicked onMessageClicked) {
        this.shortlist = shortlist;
        this.context = context;
        NotificationsAdapter.onClicked = onMessageClicked;
    }

    public interface OnClicked {
        void onClicked(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.congratsrow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView1.setText(shortlist.get(position).getName());
        holder.textView2.setText(shortlist.get(position).getJobTitle());
        holder.textView3.setText(shortlist.get(position).getCompanyName());
        holder.textView4.setText(shortlist.get(position).getLocation());


        holder.itemView.setOnClickListener(view -> {
            onClicked.onClicked(position);
        });

    }

    @Override
    public int getItemCount() {
        return shortlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3, textView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.applicantname);
            textView2 = itemView.findViewById(R.id.jobtitle);
            textView3 = itemView.findViewById(R.id.jobcompany);
            textView4 = itemView.findViewById(R.id.location);
        }
    }

}