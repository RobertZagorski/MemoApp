package com.rzagorski.memoapp.ui.list.active;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzagorski.memoapp.R;
import com.rzagorski.memoapp.model.Memo;
import com.rzagorski.memoapp.ui.base.BaseRecyclerViewFragment;
import com.rzagorski.memoapp.ui.list.MemoListActivity;
import com.rzagorski.memoapp.ui.memo.MemoActivity;
import com.rzagorski.memoapp.utils.interfaces.ListItemClickDelegate;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class ActiveListFragment extends BaseRecyclerViewFragment<ActiveListAdapter, Memo> implements ActiveMemoList, ListItemClickDelegate {

    @Inject ActiveListPresenter mPresenter;

    @Inject
    public ActiveListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }
        MemoListActivity memoListActivity = (MemoListActivity) getActivity();
        memoListActivity.getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.selectMemos();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    public int getListViewId() {
        return R.id.list;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(), getActivity().getResources().getInteger(R.integer.grid_span));
    }

    @Override
    public void showMemos(List<Memo> memoList) {
        addAllItems(memoList);
        abstractListAdapter.setClickDelegate(this);
    }

    @Override
    public void onItemClick(Memo memo) {
        mPresenter.onMemoClick(memo);
        Intent intent = new Intent(getActivity(), MemoActivity.class);
        getActivity().startActivity(intent);
    }
}
