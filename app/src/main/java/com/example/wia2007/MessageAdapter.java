package com.example.wia2007;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ai_chatbot_item, parent, false);
        return new ViewHolder(chatView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (message.getSender().equals(Message.SENT_BY_USER)) {
            holder.AIChatView.setVisibility(View.GONE);
            holder.UserChatView.setVisibility(View.VISIBLE);
            holder.UserTextView.setText(message.getMessage());
        } else {
            holder.AIChatView.setVisibility(View.VISIBLE);
            holder.UserChatView.setVisibility(View.GONE);
            holder.AITextView.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout AIChatView, UserChatView;
        TextView AITextView, UserTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AIChatView = itemView.findViewById(R.id.LinearLayoutAIChat);
            UserChatView = itemView.findViewById(R.id.LinearLayoutUserChat);
            AITextView = itemView.findViewById(R.id.AIChatText);
            UserTextView = itemView.findViewById(R.id.UserChatText);
        }
    }
}