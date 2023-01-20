package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        EditText email = findViewById(R.id.email);
        EditText subject = findViewById(R.id.subject);
        EditText message = findViewById(R.id.messageBody);
        AppCompatButton sendButton = findViewById(R.id.sendButton);
        AppCompatButton returnButton = findViewById(R.id.returnButton);

        sendButton.setOnClickListener(view -> {
            String destinationEmail = "minhlongjoe@gmail.com";

            String userEmail = email.getText().toString().trim();
            String userSubject = subject.getText().toString().trim();
            String userMessage = message.getText().toString().trim();

            if (userEmail.isEmpty()) {
                Toast.makeText(ContactUsActivity.this, "Please enter your email address!", Toast.LENGTH_SHORT).show();
            }

            else if (userSubject.isEmpty()) {
                Toast.makeText(ContactUsActivity.this, "Please enter a subject!", Toast.LENGTH_SHORT).show();
            }

            else if (userMessage.isEmpty()) {
                Toast.makeText(ContactUsActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
            } else {
             String composedEmail = "mailed to: " + destinationEmail +
                     "?&subject= " + Uri.encode(userSubject) +
                     "?&body= " + Uri.encode(userMessage);

             Intent intent = new Intent(Intent.ACTION_SEND);
             intent.setData(Uri.parse(composedEmail));

             try {
                 startActivity(Intent.createChooser(intent, "Sent email..."));
             } catch (Exception e) {
                 Toast.makeText(ContactUsActivity.this, "Failed to send request: " + e.getMessage(), Toast.LENGTH_SHORT).show();
             }
            }
        });

        returnButton.setOnClickListener(view -> finish());
    }
}