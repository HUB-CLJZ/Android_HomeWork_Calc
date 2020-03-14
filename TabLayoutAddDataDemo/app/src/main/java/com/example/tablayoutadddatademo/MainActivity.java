package com.example.tablayoutadddatademo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {
    private TableLayout tab;
    private ArrayList<String> tabCol = new ArrayList<>();
    private ArrayList<String> tabH = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 4; i++) {
            tabCol.add(i+"");
            tabH.add(i+"");
        }
        tab = findViewById(R.id.tab);
        
        for (int i = 0; i < tabCol.size(); i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setShowDividers(LinearLayout.SHOW_DIVIDER_BEGINNING|LinearLayout.SHOW_DIVIDER_MIDDLE|LinearLayout.SHOW_DIVIDER_END);

            tableRow.setDividerDrawable(ContextCompat.getDrawable(this,
                    R.drawable.line_h));
            tableRow.setOrientation(LinearLayout.HORIZONTAL);
            TextView textView = null;

            for (int j = 0; j < tabH.size(); j++) {
                textView = new TextView(this);
                textView.setPadding(16,16,16,16);
                textView.setWidth(1);
                textView.setText("str");
                textView.setGravity(Gravity.CENTER);
                tableRow.addView(textView);
            }
            tab.addView(tableRow);

        }
    }

}