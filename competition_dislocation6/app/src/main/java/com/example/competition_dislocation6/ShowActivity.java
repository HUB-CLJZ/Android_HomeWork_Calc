package com.example.competition_dislocation6;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
/**
 * @author admin
 *
 */
public class ShowActivity extends AppCompatActivity {
    private TableLayout tab;
    private ArrayList<String> datas = new ArrayList<>();
    private ArrayList<String> r_datas = new ArrayList<>();
    private MyHelplerDataBase myHelplerDataBase;
    private int index = 0;
    private int r_index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        myHelplerDataBase = new MyHelplerDataBase(this);
        SQLiteDatabase readableDatabase = myHelplerDataBase.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select * from recharge_information",null);
        tab = findViewById(R.id.tab);
        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor!= null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    //设置ID
                    Integer _id = cursor.getInt(0);
                    Integer car_number = cursor.getInt(1);
                    Integer car_recharge_amount = cursor.getInt(2);
                    String time = cursor.getString(3);
                    datas.add(_id+"");
                    datas.add(car_number+"");
                    datas.add(car_recharge_amount+"");
                    datas.add(time);
                    System.out.println(_id+"---"+car_number+"---"+car_recharge_amount+"---"+time);
                }
            }
            readableDatabase.close();
        }

        for (int i = 0; i < datas.size()/4; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setShowDividers(LinearLayout.SHOW_DIVIDER_BEGINNING|LinearLayout.SHOW_DIVIDER_MIDDLE|LinearLayout.SHOW_DIVIDER_END);

            tableRow.setDividerDrawable(ContextCompat.getDrawable(this,
                    R.drawable.line_h));
            tableRow.setOrientation(LinearLayout.HORIZONTAL);
            TextView textView = null;
            for (int j = 0; j < 4; j++) {
                textView = new TextView(this);
                textView.setPadding(16,16,16,16);
                textView.setWidth(1);
                textView.setText(datas.get(index));
                index++;
                textView.setGravity(Gravity.CENTER);
                tableRow.addView(textView);
            }
            tab.addView(tableRow);
        }

        SQLiteDatabase readableDatabaseASC = myHelplerDataBase.getReadableDatabase();
        Cursor cursorASC = readableDatabaseASC.rawQuery("select * from recharge_information order by _id desc ",null);
        for (int i = 0; i < cursorASC.getCount(); i++) {
            if (cursorASC!= null && cursorASC.getCount() > 0) {
                while (cursorASC.moveToNext()) {
                    //设置ID
                    Integer _id = cursorASC.getInt(0);
                    Integer car_number = cursorASC.getInt(1);
                    Integer car_recharge_amount = cursorASC.getInt(2);
                    String time = cursorASC.getString(3);
                    r_datas.add(_id+"");
                    r_datas.add(car_number+"");
                    r_datas.add(car_recharge_amount+"");
                    r_datas.add(time);
                    System.out.println(_id+"---"+car_number+"---"+car_recharge_amount+"---"+time);
                }
            }
            readableDatabaseASC.close();
        }

        for (int i = 0; i < datas.size()/4; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setShowDividers(LinearLayout.SHOW_DIVIDER_BEGINNING|LinearLayout.SHOW_DIVIDER_MIDDLE|LinearLayout.SHOW_DIVIDER_END);
            tableRow.setDividerDrawable(ContextCompat.getDrawable(this,
                    R.drawable.line_h));
            tableRow.setOrientation(LinearLayout.HORIZONTAL);
            TextView textView = null;
            for (int j = 0; j < 4; j++) {
                textView = new TextView(this);
                textView.setPadding(16,16,16,16);
                textView.setWidth(1);
                textView.setText(r_datas.get(r_index));
                System.out.println(r_index);
                r_index++;
                textView.setGravity(Gravity.CENTER);
                tableRow.addView(textView);
            }
            tab.addView(tableRow);
        }
    }
}