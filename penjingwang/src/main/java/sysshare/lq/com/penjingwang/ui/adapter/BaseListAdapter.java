package sysshare.lq.com.penjingwang.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import sysshare.lq.com.penjingwang.common.ListDataHolder;
import sysshare.lq.com.penjingwang.widget.LMRecyclerView;


public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<ListDataHolder> {

    private List<T> mList;

    //刷新所有数据
    public void notifyAllDatas(List<T> mList, LMRecyclerView recyclerView) {
        this.mList = mList;
        recyclerView.notifyDataSetChanged();
    }

    //刷新单条数据
    public void notifyItemDataChanged(int position, LMRecyclerView recyclerView) {
        recyclerView.notifyItemChanged(position);
    }

    //移除单条数据
    public void notifyItemDataRemove(int position, LMRecyclerView recyclerView) {
        recyclerView.notifyItemRemoved(position);
    }


    @Override
    public ListDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ListDataHolder.createViewHolder(parent, getLayoutId(viewType));
    }

    protected abstract int getLayoutId(int viewType);


    @Override
    public void onBindViewHolder(ListDataHolder holder, int position) {
        //初始化View
        T bean = mList.get(position);
        //绑定数据
        bindDatas(holder, bean, holder.getItemViewType(), position);
    }


    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public abstract void bindDatas(ListDataHolder holder, T bean, int itemType, int position);

}
