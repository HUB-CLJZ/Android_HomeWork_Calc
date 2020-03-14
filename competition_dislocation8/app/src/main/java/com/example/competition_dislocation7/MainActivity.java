package com.example.competition_dislocation7;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private ListAdaper listAdaper;
    private List<Data> datas;
    private ListView listView;
    private MyDataBaseHelper dataBaseHelper;
    private String TAG = "MainActivity";
    private Spinner spinner;
    //排序的字段
    private String[] sortArrays = {"crossroads","crossroads","red_lamp","red_lamp","yello_lamp","yello_lamp","green_lamp","green_lamp"};
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        btn = findViewById(R.id.btn_search);
        spinner = findViewById(R.id.spin_oder);
        /**
         * 不调用此方法，数据库中无数据
         * 不调用此方法，数据库中无数据
         * 不调用此方法，数据库中无数据
         */
        addTestData();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position%2 == 0) {
                    sortMethod(sortArrays[position],true);
                    listView.setVisibility(View.INVISIBLE);
                } else {
                    sortMethod(sortArrays[position],false);
                    listView.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.VISIBLE);
            }
        });
    }
    /**
     * @description:添加测试数据的方法，不调用数据库中无数据
     * @author: CLJZ
     * @date: 2020/1/11  12:54
     * @param: []
     * @return: void
     */
    public  void addTestData() {

        Data data = new Data(1,9,9,9);
        Data data1 = new Data(2,8,8,8);
        Data data2 = new Data(3,7,7,7);
        datas.add(data);
        datas.add(data1);
        datas.add(data2);
        for (int i = 0; i < datas.size(); i++) {
            System.out.println("\n"+datas.get(i).getCrossroads()+"-----"+datas.get(i).getRed_lamp()+"-----"
            +datas.get(i).getYello_lamp()+"-----"+datas.get(i).getGreen_lamp());
        }

        //向数据库中插入数据
        for (int i = 0; i < datas.size(); i++) {
            SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("crossroads",datas.get(i).getCrossroads());
            contentValues.put("red_lamp",datas.get(i).getRed_lamp());
            contentValues.put("yello_lamp",datas.get(i).getYello_lamp());
            contentValues.put("green_lamp",datas.get(i).getGreen_lamp());
            database.insert("road_table",null,contentValues);
            database.close();
        }
    }
    /**
     * @description:确定排序的字段，排序的规则
     * @author: CLJZ
     * @date: 2020/1/11  12:51
     * @param: [排序的字段, 排序的规则]
     * @return: void
     */
    public void sortMethod(String sortArgs, boolean isRevoce) {
        //每次重新初始化数组，重新设置适配器，防止数据重复
        datas = new ArrayList<>();
        dataBaseHelper = new MyDataBaseHelper(this);
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();
        String sort = isRevoce ? "asc":"desc";
        Cursor cursor = database.rawQuery("select * from road_table order by "+sortArgs+" "+sort,null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Integer crossroads = cursor.getInt(1);
                Integer red_lamp = cursor.getInt(2);
                Integer yellow_lamp = cursor.getInt(3);
                Integer green_lamp = cursor.getInt(4);
                Log.d(TAG, "onCreate: "+(crossroads+"-----"+red_lamp+"-----"+yellow_lamp+"-----"+green_lamp));
                Data data = new Data(crossroads,red_lamp,yellow_lamp,green_lamp);
                datas.add(data);
            }
        }
        database.close();
        cursor.close();
        listAdaper = new ListAdaper(datas);
        listView.setAdapter(listAdaper);
    }
}