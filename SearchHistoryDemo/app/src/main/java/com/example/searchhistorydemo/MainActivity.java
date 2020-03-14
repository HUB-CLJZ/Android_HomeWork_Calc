package com.example.searchhistorydemo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtSearch;
    private Button mBtnSearch;
    private ListView mLvList;
    private LinerAdaper adaper;
    private List<String> datas;
    private TextView tv_clean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        datas = new ArrayList<>();

    }

    private void initView() {
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mLvList = (ListView) findViewById(R.id.lv_list);

        mBtnSearch.setOnClickListener(this);
        tv_clean = (TextView) findViewById(R.id.tv_clean);
        tv_clean.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                datas.add(mEtSearch.getText().toString());
                adaper = new LinerAdaper(datas);
                mLvList.setAdapter(adaper);
                adaper.notifyDataSetChanged();
                mEtSearch.setText("");

                if (datas.size() >= 11) {


                    mLvList.setScrollbarFadingEnabled(true);
                }

                break;
            case R.id.tv_clean:
                datas.clear();
                adaper.notifyDataSetChanged();
                break;
        }
    }

}
