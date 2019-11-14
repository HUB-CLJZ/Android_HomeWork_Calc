package com.example.broadcastreceiver;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText emIpnumber;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emIpnumber = findViewById(R.id.em_ipnumber);
        sp = getSharedPreferences("config",MODE_PRIVATE);
    }

    public void click(View view) {
        String number = emIpnumber.getText().toString().trim();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("number",number);
        Toast.makeText(this, number, Toast.LENGTH_SHORT).show();
        editor.commit();
        
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }
}
