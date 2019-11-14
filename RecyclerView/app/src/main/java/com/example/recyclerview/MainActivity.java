package com.example.recyclerview;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Integer> Data;
    private HomeAdapter homeAdapter;
    private int[] img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(homeAdapter = new HomeAdapter());
    }

    private void initData() {
        Data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Data.add(i);
        }

        img = new int[] {R.drawable.rank1,R.drawable.rank2,
                R.drawable.rank3,R.drawable.rank4,
                R.drawable.rank5,R.drawable.rank6,
                R.drawable.rank7,R.drawable.rank8,
                R.drawable.rank9,R.drawable.rank10};
    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(MainActivity.this).
                                  inflate(R.layout.item_home,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView.setText("这是第"+Data.get(position).toString()+"个精灵");
            holder.imageView.setImageResource(img[position]);
        }

        @Override
        public int getItemCount() {
            return Data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            ImageView imageView;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.Num);
                imageView = itemView.findViewById(R.id.ivNum);
            }
        }
    }
}
