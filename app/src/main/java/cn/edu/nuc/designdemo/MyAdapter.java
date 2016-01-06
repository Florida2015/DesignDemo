package cn.edu.nuc.designdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Flming2015 on 2016/1/6.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements SwipeDismissBehavior.OnDismissListener {
    private Context context;
    private List<String> list;
    private RecyclerView recyclerView;
    private MyViewHolder holder;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        holder = new MyViewHolder(view);
        //得到布局中TextView控件的参数，用它来设置textView 的参数，
        // 比如设置控件的高、宽、动画（消失，移动、旋转等）的等信息
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) holder.text.getLayoutParams();
        //创建动画的移除的行为，也就是移除时的动画
        SwipeDismissBehavior behavior = new SwipeDismissBehavior();
        behavior.setListener(this);
        params.setBehavior(behavior);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDismiss(final View view) {
        //!!!!!
        ViewCompat.setAlpha(view, 1);
        View parent = (View) view.getParent();
        Log.d("MyAdapter", "parent = " + parent);
        final int position = recyclerView.getChildAdapterPosition(parent);
        final String ss = list.get(position);
        list.remove(position);
        notifyItemRemoved(position);

        Snackbar.make(holder.coordinatorLayout1,"删除一个Text",Snackbar.LENGTH_LONG).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //之前删除的View，在添加回来
               list.add(position,ss);
                notifyItemInserted(position);
            }
        }).show();

    }

    @Override
    public void onDragStateChanged(int state) {

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView text;
        private CoordinatorLayout coordinatorLayout1;

        public MyViewHolder(View itemView) {
            super(itemView);
            coordinatorLayout1 = (CoordinatorLayout) itemView.findViewById(R.id.coordinator1);
            text = ((TextView) itemView.findViewById(android.R.id.text1));
        }
    }
}