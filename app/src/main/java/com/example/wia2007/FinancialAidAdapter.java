package com.example.wia2007;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FinancialAidAdapter extends RecyclerView.Adapter<FinancialAidAdapter.ViewHolder> {
    private final ArrayList<FinancialAid> financialAidList;
    private final OnItemClickListener listener;
    private int selectedPosition = -1;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public FinancialAidAdapter(ArrayList<FinancialAid> financialAidList, OnItemClickListener listener) {
        this.financialAidList = financialAidList;
        this.listener = listener;
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
        holder.imageView.setImageResource(aid.getImageID());
        holder.nameTextView.setText(aid.getAidName());
        holder.amountTextView.setText("Amount: RM" + aid.getAidAmount());
        holder.slotsTextView.setText("Type: " + aid.getAidSlots());
        holder.datelineTextView.setText("Dateline: " + aid.getAidDateline());

        holder.itemView.setBackgroundColor(selectedPosition == position ? Color.GREEN : Color.TRANSPARENT);

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            listener.onItemClick(position);
            notifyDataSetChanged();
        });

        holder.imageView.post(() -> {
            int imageHeight = holder.imageView.getHeight() + 50;
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height = imageHeight;
            holder.itemView.setLayoutParams(layoutParams);
        });
    }

    @Override
    public int getItemCount() {
        return financialAidList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, amountTextView, slotsTextView, datelineTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.FinancialAidImage);
            nameTextView = itemView.findViewById(R.id.FinancialAidName);
            amountTextView = itemView.findViewById(R.id.FinancialAidAmount);
            slotsTextView = itemView.findViewById(R.id.FinancialAidSlots);
            datelineTextView = itemView.findViewById(R.id.FinancialAidDateline);
        }
    }
}
