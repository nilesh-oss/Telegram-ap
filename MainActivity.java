package com.example.telegrambot;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String BOT_TOKEN = "8032263125:AAHqwxg5gA07TXXKg1aJzVaDvlJjloqzZ98";
    private final String CHAT_ID = "6186505738";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = findViewById(R.id.sendBtn);
        sendButton.setOnClickListener(v -> sendTelegramMessage("Hello from Android Educational App!"));
    }

    private void sendTelegramMessage(String message) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.telegram.org/bot" + BOT_TOKEN +
                     "/sendMessage?chat_id=" + CHAT_ID + "&text=" + message;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Telegram response: " + response.body().string());
            }
        });
    }
}
