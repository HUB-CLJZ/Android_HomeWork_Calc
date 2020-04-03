package com.example.code_06;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private TextView tv_road_conditions_text;
    private TextView tv_road_conditions_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        if(Integer.valueOf(tv_road_conditions_value.getText().toString()) >= 50) {
            tv_road_conditions_text.setBackgroundResource(R.drawable.shape_rectangular_red);
        }

    }

    private void initView() {
        tv_road_conditions_text = (TextView) findViewById(R.id.tv_road_conditions_text);
        tv_road_conditions_value = (TextView) findViewById(R.id.tv_road_conditions_value);
    }
}
