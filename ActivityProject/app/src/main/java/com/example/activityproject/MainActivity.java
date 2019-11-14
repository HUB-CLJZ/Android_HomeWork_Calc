package com.example.activityproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_name;
    private EditText et_password;
    private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        btn_send = findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passData();
            }
        });
    }

    public void passData() {
        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
        intent.putExtra("name", et_name.getText().toString());
        intent.putExtra("password", et_password.getText().toString());
        startActivityForResult(intent,0x000);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0x000 && resultCode==0x000) {
            Bundle bundle = data.getExtras();
            String name = bundle.getString("name");
            String password = bundle.getString("password");
            Toast.makeText(MainActivity.this,"姓名是"+name,Toast.LENGTH_LONG);
            Toast.makeText(MainActivity.this,"密码是"+password,Toast.LENGTH_LONG);
        }
    }
}
