package com.example.wia2007;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CustomAdapter {
//extends RecyclerView.Adapter<CustomAdapter.ViewHolder>



    //private List<FinancialAidItem> financialAidList;

    //public CustomAdapter(List<FinancialAidItem> financialAidList) {
    //    this.financialAidList = financialAidList;
    //}

    /*@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_financial_aid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FinancialAidItem item = financialAidList.get(position);
        holder.financialAidNameTextView.setText(item.getName());
        holder.amountTextView.setText("Amount: RM" + item.getAmount());
        holder.availableSlotsTextView.setText("Available Slots: " + item.getAvailableSlots());
        holder.datelineTextView.setText("Dateline: " + item.getDateline());
    }

    @Override
    public int getItemCount() {
        return financialAidList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView financialAidNameTextView;
        public TextView amountTextView;
        public TextView availableSlotsTextView;
        public TextView datelineTextView;

        public ViewHolder(View view) {
            super(view);
            financialAidNameTextView = view.findViewById(R.id.financialAidNameTextView);
            amountTextView = view.findViewById(R.id.amountTextView);
            availableSlotsTextView = view.findViewById(R.id.availableSlotsTextView);
            datelineTextView = view.findViewById(R.id.datelineTextView);
        }
    }*/
}