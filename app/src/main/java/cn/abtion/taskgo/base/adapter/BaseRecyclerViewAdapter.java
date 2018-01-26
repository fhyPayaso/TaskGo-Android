package cn.abtion.taskgo.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @author FanHongyu.
 * @since 18/1/17 01:04.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BaseRecyclerViewAdapter<Data> extends RecyclerView.Adapter<BaseRecyclerViewAdapter
        .BaseViewHolder<Data>> {


    private List<Data> mDataList;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnItemClicked<Data> onItemClickedListener;

    public BaseRecyclerViewAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }


    /**
     * 设置数据
     *
     * @param data 数据
     */
    public void setDataList(Collection<Data> data) {
        this.mDataList.clear();
        this.mDataList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 获取当前列表的数据
     */
    public List<Data> getDataList() {
        return this.mDataList;
    }


    /**
     * 获取item数量
     *
     * @return 数量
     */
    @Override
    public int getItemCount() {
        return mContext == null ? 0 : mDataList.size();
    }

    /**
     * 添加一条数据
     *
     * @param data 添加的数据
     */
    public void addItem(Data data) {
        this.mDataList.add(data);
        //每次更改数据之后刷新RecyclerView
        notifyDataSetChanged();
    }

    /**
     * 添加多条数据
     *
     * @param collection 数据
     */
    public void addItems(Collection<Data> collection) {
        this.mDataList.addAll(collection);
        notifyDataSetChanged();
    }

    /**
     * 移除数据
     *
     * @param data 移除的数据
     */
    public void removeItem(Data data) {
        this.mDataList.remove(data);
        notifyDataSetChanged();
    }

    /**
     * 移除数据（带动画）
     *
     * @param position pos
     */
    public void removeItem(int position) {
        this.mDataList.remove(position);
        //该方法不会使position及其之后位置的itemView重新onBindViewHolder
        notifyItemRemoved(position);
        //所以需要从position到列表末尾进行数据刷新
        if (position != mDataList.size()) {
            notifyItemRangeChanged(position, mDataList.size() - position);
        }
    }

    /**
     * 清空列表
     */
    public void clearAllItems() {
        this.mDataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 刷新页面
     */
    public void refresh() {
        notifyDataSetChanged();
    }

    public void refresh(int position) {
        notifyItemChanged(position);
    }


    /**
     * 获取position 处数据
     */
    public Data getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder<Data> holder, final int position) {

        //监听点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClicked(mDataList.get(position), holder);
                }
            }

        });
        //ViewHolder绑定数据
        holder.bind(mDataList.get(position), position);
    }



    /**
     * 点击事件接口
     */
    public interface OnItemClicked<Data> {


        void onItemClicked(Data data, BaseViewHolder holder);
    }


    public abstract static class BaseViewHolder<Data> extends RecyclerView.ViewHolder {

        private Data mData;

        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Data data, int position) {
            mData = data;
            onBind(data, position);
        }

        /**
         * 绑定model和layout数据
         *
         * @param data
         * @param position
         */
        protected abstract void onBind(Data data, int position);

    }

}