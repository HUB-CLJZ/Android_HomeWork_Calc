package com.example.qquiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String et_name = bundle.getString("u_name");
        String et_password = bundle.getString("u_password");

        name = findViewById(R.id.user);
        password = findViewById(R.id.password);

        name.setText(String.valueOf(et_name));
        password.setText(String.valueOf(et_password));

    }
}
