package com.example.wia2007;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FinancialAidAdapter extends RecyclerView.Adapter<FinancialAidAdapter.ViewHolder> {
    private final ArrayList<FinancialAid> financialAidList;
    private int selectedPosition = -1;
    private int textSize;

    public FinancialAidAdapter(ArrayList<FinancialAid> financialAidList) {
        this.financialAidList = financialAidList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.financial_aid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FinancialAid aid = financialAidList.get(position);
        holder.nameTextView.setText(aid.getAidName());
        holder.amountTextView.setText("Amount: RM" + aid.getAidAmount());
        holder.typeTextView.setText("Type: " + aid.getAidSlots());
        holder.deadlineTextView.setText("Deadline: " + aid.getAidDateline());

        // Change background color if selected
        holder.itemView.setBackgroundColor(selectedPosition == position ? Color.GREEN : Color.TRANSPARENT);

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return financialAidList.size();
    }

    public void setTextSizes(int textSize) {
        this.textSize = textSize;
        notifyDataSetChanged();
    }

    public void setTextColour(String colour) {
        this.colour = colour;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, amountTextView, typeTextView, deadlineTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            deadlineTextView = itemView.findViewById(R.id.deadlineTextView);
        }
    }
}