package com.example.competition_dislocation3;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;
import static android.util.Config.DEBUG;

/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    public static final String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    private EditText etUseranme;
    private EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUseranme = findViewById(R.id.et_useranme);
        etPassword = findViewById(R.id.et_password);

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(etUseranme.getText().toString());
                boolean isMatch = m.matches();
                if(!isMatch) {
                    Toast.makeText(MainActivity.this, "您的手机号是错误格式！！！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}


