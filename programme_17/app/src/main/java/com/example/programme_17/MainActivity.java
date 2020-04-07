package com.example.programme_17;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/***
 * 与14题类似
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_open = (Button) findViewById(R.id.btn_open);

        btn_open.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
                final View view = View.inflate(this, R.layout.dialog_layout, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(view);
                Button btn_login = view.findViewById(R.id.btn_login);
                Button btn_quit = view.findViewById(R.id.btn_quit);
                final AlertDialog dialog = builder.show();
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                btn_quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                break;
            default:
                    break;
        }
    }
}
