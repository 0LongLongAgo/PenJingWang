package sysshare.lq.com.penjingwang.ui.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sysshare.lq.com.penjingwang.R;
import sysshare.lq.com.penjingwang.common.Const;
import sysshare.lq.com.penjingwang.ui.adapter.BaseListAdapter;
import sysshare.lq.com.penjingwang.ui.mvp.presenter.BasePresenter;
import sysshare.lq.com.penjingwang.ui.mvp.view.IView;
import sysshare.lq.com.penjingwang.widget.NoAlphaItemAnimator;


public abstract class BaseAbRecycleviewActivity<P extends BasePresenter<V>, V extends IView, T> extends BasePresenterActivity<P, V>   {
    protected SwipeRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected BaseListAdapter mListAdapter;
    protected int page;
    protected int state;
    protected boolean isAutoLoadMore = true;//是否显示自动加载
    protected List<T> mListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new NoAlphaItemAnimator());

//        mRecyclerView.addFooterAutoLoadMoreListener(this);
//        mListAdapter = getListAdapter();
        if (mListAdapter != null) {
//            mRecyclerView.addHeaderView(initHeaderView());
            mRecyclerView.setAdapter(mListAdapter);
            loadDatas();
        }
    }

    protected abstract void loadDatas();


    @Override
    protected void initViews() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
//        mContainerLayout = (ContainerLayout) findViewById(R.id.containerLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_recyclerview;
    }

    //是否能够自动加载更多
    protected abstract boolean isCanLoadMore();

//    @Override
//    public List<T> getData() {
//        return mListData;
//    }
//
//    //显示内容
//    @Override
//    public void showContent() {
//        mContainerLayout.showContent();
//        mListAdapter.notifyAllDatas(mListData, mRecyclerView);
//    }

    //下拉刷新监听
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();
        }
    };

    // 刷新列表
    public void refreshData() {
        state = Const.PAGE_STATE.STATE_REFRESH;
        isAutoLoadMore = true;
        page = 0;

    }



}
