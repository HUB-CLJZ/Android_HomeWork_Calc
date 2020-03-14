package com.example.textinputdemo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
public class MainActivity extends AppCompatActivity {
    private TextInputLayout tilUserName;
    private EditText etName;
    private TextInputLayout tilUserPassword;
    private EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (str.contains("+")) {
                    tilUserName.setError("输入错误");
                } else {
                    tilUserName.setErrorEnabled(false);
                }
                if (str.length() > tilUserName.getCounterMaxLength()) {
                    tilUserName.setError("输入字符超过限制");
                }else {
                    tilUserName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (str.length() > tilUserPassword.getCounterMaxLength()) {
                    tilUserPassword.setError("输入字符超过限制");
                } else {
                    tilUserPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {
        tilUserName = findViewById(R.id.til_user_name);
        etName = findViewById(R.id.et_name);
        tilUserPassword = findViewById(R.id.til_user_password);
        etPassword = findViewById(R.id.et_password);
    }
}