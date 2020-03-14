package com.example.home1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
/**
 * @author admin
 *
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //获得fragment的管理者
                FragmentManager manager = getSupportFragmentManager();
                //开启事务
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_view,new SlidingFragment());
                fragmentTransaction.commit();
            }
        },2000);
    }

    /**
     * @description:显示菜单
     * @author: CLJZ
     * @date: 2020/1/2  20:37
     * @param: [menu]
     * @return: boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * @description:设置菜单选项
     * @author: CLJZ
     * @date: 2020/1/2  20:34
     * @param: [item]
     * @return: boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            //ListView的部分
            case R.id.list_view_vertical_stander:
                //isVertical是否是垂直，isReverse是否是反向
                Log.d(TAG, "点击了ListView里头的垂直标准");
//                showList(true,false);

                break;
            case R.id.list_view_vertical_reverse:
                Log.d(TAG, "点击了ListView里头的垂直反向");
//                showList(true,true);
                break;
            case R.id.list_view_horizontal_stander:
                Log.d(TAG, "点击了ListView里头的水平标准");
//                showList(false,false);
                break;
            case R.id.list_view_horizontal_reverse:
                Log.d(TAG, "点击了ListView里头的水平反向");
//                showList(false,true);
                break;

            //GridView部分
            case R.id.grid_view_vertical_stander:
//                showGrid(true,false);
                break;
            case R.id.grid_view_vertical_reverse:
//                showGrid(true,true);
                break;
            case R.id.grid_view_horizontal_stander:
//                showGrid(false,false);
                break;
            case R.id.grid_view_horizontal_reverse:
//                showGrid(false,true);
                break;

            //瀑布流部分
            case R.id.stagger_view_vertical_stander:
//                showStagger(true,false);
                break;
            case R.id.stagger_view_vertical_reverse:
//                showStagger(true,true);
                break;
            case R.id.stagger_view_horizontal_stander:
//                showStagger(false,false);
                break;
            case R.id.stagger_view_horizontal_reverse:
//                showStagger(false,true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
