package com.example.collapsingtoolbarlayoutdemo.adapters;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.collapsingtoolbarlayoutdemo.R;
import com.example.collapsingtoolbarlayoutdemo.beans.ItemBean;
import java.util.List;
/**
 * @author admin
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {
    /*准备的数据*/
    private List<ItemBean> mData;

    public ListViewAdapter(List<ItemBean> mData) {
        this.mData = mData;
    }
    /**
     * @description:用于创建条目View
     * @author: CLJZ
     * @date: 2019/12/28  16:06
     * @param: [parent, viewType]
     * @return: com.example.recycleview1.adapters.ListViewAdapter.InnerHolder
     */
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传递条目界面
        View view = View.inflate(parent.getContext(), R.layout.item_list_view,null);
        //界面传递给适配器
        return new InnerHolder(view);
    }
    /**
     * @description:绑定holer，一般用来设置数据
     * @author: CLJZ
     * @date: 2019/12/28  16:08
     * @param: [holder, position]
     * @return: void
     */
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
       //设置传递的数据
       holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            //找到条目的控件
            mIcon = itemView.findViewById(R.id.item_icon);
            mTitle = itemView.findViewById(R.id.item_title);
        }
        public void setData(ItemBean itemBean) {
            //开始设置数据
            mIcon.setImageResource(itemBean.getIcon());
            mTitle.setText(itemBean.getTitle());
        }
    }
}