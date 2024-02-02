package com.example.servo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ComplaintDetails extends AppCompatActivity {

    TextView type;
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);

        type = findViewById(R.id.detailsType);
        desc = findViewById(R.id.detailsDesc);

        Intent intent = getIntent();

        String typeStr = intent.getStringExtra("Rtype");
        String descStr = intent.getStringExtra("Rdesc");

        type.setText(typeStr);
        desc.setText(descStr);


    }
}