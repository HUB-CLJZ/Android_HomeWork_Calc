package com.example.recycleview1.adapters;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recycleview1.R;
import com.example.recycleview1.beans.ItemBean;
import java.util.List;
/**
 * @author admin
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {
    private List<ItemBean> mData;
    public ListViewAdapter(List<ItemBean> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListViewAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_list_view,null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.InnerHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            mData.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView title;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.item_icon);
            title = itemView.findViewById(R.id.item_title);
        }

        public void setData(ItemBean itemBean) {
            icon.setImageResource(itemBean.getIcon());
            title.setText(itemBean.getTitle());
        }
    }
}