package com.example.userregistactivity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private EditText et_password;
    private Button btn_send;
    private EditText et_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = findViewById(R.id.et_name);
        et_password =  findViewById(R.id.et_password);
        btn_send =  findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_name.getText()) || TextUtils.isEmpty(et_password.getText())) {
                    Toast.makeText(MainActivity.this,"输入的账号或密码为空",Toast.LENGTH_SHORT).show();
                } else if (et_name.getText().length() > 13 || et_password.getText().length() > 16) {
                    Toast.makeText(MainActivity.this,"输入的账号或密码长度不符合",Toast.LENGTH_SHORT).show();
                } else  if(!TextUtils.isDigitsOnly(et_name.getText())) {
                    Toast.makeText(MainActivity.this,"输入的账号不是数字",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.indexOf(et_password.getText()," ") != -1) {
                    Toast.makeText(MainActivity.this, "输入密码为空格", Toast.LENGTH_SHORT).show();
                } else {
                    passDate();
                }
            }
        });
    }
    public void passDate(){
        Intent intent = new Intent(this,ShowActivity.class);
        intent.putExtra("name",et_name.getText().toString().trim());
        intent.putExtra("password",et_password.getText().toString().trim());
        startActivity(intent);
    }

}
