package com.example.datapicker;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {
    private  int year,month,day;
    private DatePicker datePicker;

    private Button btnDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker = findViewById(R.id.data_picker);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                MainActivity.this.year = i;
                MainActivity.this.month = i1;
                MainActivity.this.day = i2;
                show(i,i1,i2);
            }
        });

        btnDate = findViewById(R.id.btnDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = year + "-"+ month + "-" + dayOfMonth;
                        btnDate.setText(date);
                    }
                },2018,10,5).show();
            }
        });
    }

    private void show(int year, int month, int day) {
        String str = year+"年"+(month+1)+"月"+day+"日";
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT);
    }
}
