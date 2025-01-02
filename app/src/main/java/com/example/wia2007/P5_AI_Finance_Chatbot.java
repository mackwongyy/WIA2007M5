package com.example.wia2007;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

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
    private static P5_AI_Finance_Chatbot BuildConfig;
    private static final String OPENAI_API_KEY = BuildConfig.OPENAI_API_KEY; // API key from BuildConfig
    private static Button backButton;
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
        backButton = findViewById(R.id.backButton);

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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P5_AI_Finance_Chatbot.this, P5_HomepageFMT.class);
                startActivity(intent);
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
        // Add "AI is typing..." message
        addToChat("AI is typing...", Message.SENT_BY_AI_CHATBOT);

        // Create the JSON body for the API request
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "gpt-3.5-turbo");
            jsonBody.put("messages", new JSONArray()
                    .put(new JSONObject().put("role", "user").put("content", message))
            );
            jsonBody.put("max_tokens", 1000);
            jsonBody.put("temperature", 0.7);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create the OkHttp client and request
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + OPENAI_API_KEY) // Use the API key from BuildConfig
                .post(body)
                .build();

        // Make the API call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    messageList.remove(messageList.size() - 1); // Remove "AI is typing..." message
                    addToChat("Failed to load response due to " + e.getMessage(), Message.SENT_BY_AI_CHATBOT);
                    Log.e(TAG, "API call failed: " + e.getMessage());
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        assert response.body() != null;
                        String responseBody = response.body().string();
                        Log.d(TAG, "API response: " + responseBody); // Log the response
                        JSONObject jsonObject = new JSONObject(responseBody);
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0)
                                .getJSONObject("message")
                                .getString("content");

                        runOnUiThread(() -> {
                            messageList.remove(messageList.size() - 1); // Remove "AI is typing..." message
                            addToChat(result.trim(), Message.SENT_BY_AI_CHATBOT);
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        runOnUiThread(() -> {
                            messageList.remove(messageList.size() - 1); // Remove "AI is typing..." message
                            addToChat("Failed to parse response.", Message.SENT_BY_AI_CHATBOT);
                        });
                    }
                } else {
                    String errorBody = response.body() != null ? response.body().string() : "No error body";
                    Log.e(TAG, "API call failed: " + response.message() + ", Code: " + response.code() + ", Body: " + errorBody); // Log the error
                    runOnUiThread(() -> {
                        messageList.remove(messageList.size() - 1); // Remove "AI is typing..." message
                        addToChat("Failed to load response due to " + response.message(), Message.SENT_BY_AI_CHATBOT);
                    });
                }
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