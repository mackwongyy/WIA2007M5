package com.example.wia2007;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class P5_AI_Finance_Chatbot extends AppCompatActivity {
    private static final String TAG = "P5_AI_Finance_Chatbot";
    private static final String OPENAI_API_KEY = "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";
    private ArrayList<Message> messageList;
    private MessageAdapter messageAdapter;
    private RecyclerView recyclerView;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_finance_chatbot);

        recyclerView = findViewById(R.id.AI_Chat);
        TextView welcomeMessage = findViewById(R.id.AI_Chatbot_Welcome_Message);
        EditText userMessage = findViewById(R.id.AI_Chat_User_Message);
        ImageButton sendButton = findViewById(R.id.AI_Chatbot_Send_Button);
        messageList = new ArrayList<>();

        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        sendButton.setOnClickListener((v) -> {
            String message = userMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                userMessage.setText("");
                addToChat(message, Message.SENT_BY_USER); // Add user message to chat
                if (isNetworkAvailable()) {
                    callAPI(message);
                } else {
                    addToChat("No internet connection. Please check your network settings.", Message.SENT_BY_AI_CHATBOT);
                }
                welcomeMessage.setVisibility(View.GONE);
            }
        });
    }

    void addToChat(String message, String sender) {
        runOnUiThread(() -> {
            messageList.add(new Message(message, sender));
            messageAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
        });
    }

    void callAPI(String message) {
        messageList.add(new Message("AI is typing...", Message.SENT_BY_AI_CHATBOT));
        messageAdapter.notifyDataSetChanged();

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "gpt-3.5-turbo-instruct");
            jsonBody.put("prompt", message);
            jsonBody.put("max_tokens", 100000);
            jsonBody.put("temperature", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer " + OPENAI_API_KEY) // Ensure "Bearer " is prefixed
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    JSONObject jsonObject = null;

                    try {
                        assert response.body() != null;
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        messageList.remove(messageList.size() - 1);
                        addToChat(result.trim(), Message.SENT_BY_AI_CHATBOT);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    messageList.remove(messageList.size() - 1);
                    addToChat("Failed to load response due to " + response.message(), Message.SENT_BY_AI_CHATBOT);
                    Log.e(TAG, "API call failed: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                messageList.remove(messageList.size() - 1);
                addToChat("Failed to load response due to " + e.getMessage(), Message.SENT_BY_AI_CHATBOT);
                Log.e(TAG, "API call failed: " + e.getMessage());
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        Log.d(TAG, "Network available: " + isConnected);
        return isConnected;
    }
}