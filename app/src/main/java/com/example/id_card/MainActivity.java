package com.example.id_card;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView githubIcon = findViewById(R.id.github);

        githubIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to open GitHub page
                openGitHubUserPage();
            }
        });

        ImageView gmailImageView = findViewById(R.id.gmail);
        // Set an onClickListener to the Gmail icon
        gmailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to send an email
                sendEmail();
            }
        });

        ImageView phoneImageView = findViewById(R.id.phone);
        // Set an onClickListener to the Phone icon
        phoneImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to make a phone call
                makePhoneCall();
            }
        });

        ImageView facebookIcon = findViewById(R.id.facebook);
        facebookIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to open the Facebook page
                openFacebookPage();
            }
        });

        ImageView linkedinIcon = findViewById(R.id.linkedin);
        linkedinIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to open the LinkedIn page
                openLinkedinPage();
            }
        });
    }

    // Method to open the GitHub page
    private void openGitHubUserPage() {
        String url = "https://github.com/sasjb"; // Replace with your GitHub URL
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    // Method to create and send an email
    protected void sendEmail() {
        Log.i("Send email", "Preparing to send email");

        String[] TO = {"abdussalamsojib2148@gmail.com"};
        String[] CC = {}; // CC email addresses (empty if not used)

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:")); // Only email apps should handle this
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Subject Here");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email body here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i("Finished sending email", "Intent sent to email client");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            Log.e("Send email", "No email client found", ex);
        }
    }

    // Method to make a phone call
    private void makePhoneCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:01878966234"));

        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Request the CALL_PHONE permission if not already granted
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            // Permission is already granted, make the call
            startActivity(callIntent);
        }
    }

    private void openFacebookPage() {
        String facebookUrl = "https://abdussalam2148.surge.sh/"; // Replace with your Facebook URL
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
        startActivity(browserIntent);
    }

    private void openLinkedinPage() {
        String linkedinUrl = "https://www.linkedin.com/in/abdus-salam-2b308b285/"; // Replace with your LinkedIn URL
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));
        startActivity(browserIntent);
    }
}
