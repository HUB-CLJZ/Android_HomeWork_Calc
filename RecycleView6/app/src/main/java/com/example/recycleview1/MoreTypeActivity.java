package com.example.recycleview1;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recycleview1.adapters.MoreTypeAdapter;
import com.example.recycleview1.beans.Datas;
import com.example.recycleview1.beans.MoreTypeBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author TrillGates
 * @date 17/4/4
 * God bless my code!
 */
public class MoreTypeActivity extends AppCompatActivity {
    private static final String TAG = "MoreTypeActivity";
    private RecyclerView mRecyclerView;
    private List<MoreTypeBean> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_type_activity);
        //find the view here
        mRecyclerView = this.findViewById(R.id.more_type_list);
        //准备数据
        mData = new ArrayList<>();
        initDatas();
        //创建和设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        //创建适配器
        MoreTypeAdapter adapter = new MoreTypeAdapter(mData);
        //设置适配器
        mRecyclerView.setAdapter(adapter);
    }

    private void initDatas() {
        Random random = new Random();
        for (int i = 0; i < Datas.icons.length; i++) {
            MoreTypeBean data = new MoreTypeBean();
            data.pic = Datas.icons[i];
            data.type = random.nextInt(3);
            Log.d(TAG, "type == " + data.type);
            mData.add(data);
        }
    }
}
