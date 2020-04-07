package com.example.programme_37;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    private EditText et_money;
    private EditText et_cycle;
    private Button btn_generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        spinner = (Spinner) findViewById(R.id.spinner);
        et_money = (EditText) findViewById(R.id.et_money);
        et_cycle = (EditText) findViewById(R.id.et_cycle);
        btn_generate = (Button) findViewById(R.id.btn_generate);

        btn_generate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_generate:
                sendMessage();
                break;
            default:
                break;
        }
    }

    private void sendMessage() {
        Intent intent = new Intent(MainActivity.this,PayInfoActivity.class);
        String car_numebr = spinner.getSelectedItem().toString();
        String money = et_money.getText().toString();
        Integer cycle = Integer.parseInt(et_cycle.getText().toString());

        Log.d("CLJZ", "sendMessage: "+car_numebr+"---"+money+"---"+cycle);

        intent.putExtra("car_numebr",car_numebr);
        intent.putExtra("money",money);
        intent.putExtra("cycle",cycle);

        startActivity(intent);
    }

}
