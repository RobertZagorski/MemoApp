package com.rzagorski.memoapp.ui.memo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzagorski.memoapp.R;
import com.rzagorski.memoapp.ui.base.BaseRecyclerViewFragment;
import com.rzagorski.memoapp.ui.memo.MemoActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class MemoFragment extends BaseRecyclerViewFragment<MemoItemListAdapter, String> implements MemoDetailsView {

    @Inject MemoFragmentPresenter mPresenter;

    @BindView(R.id.fab) FloatingActionButton FABButton;

    Unbinder unbinder;

    @Inject
    public MemoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }
        MemoActivity memoActivity = (MemoActivity) getActivity();
        memoActivity.getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_memo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        createViews();
    }

    private void createViews() {
        unbinder = ButterKnife.bind(this, getActivity().getWindow().getDecorView());
        FABButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewRow();
            }
        });
    }

    private void addNewRow() {
        MemoFragment.this.addItemWithNotify("");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MemoFragment.this.mRecyclerView.smoothScrollToPosition(MemoFragment.this.mRecyclerView.getAdapter().getItemCount() - 1);
            }
        }, 250);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.detachView();
    }

    @Override
    public int getListViewId() {
        return R.id.memo_item_list;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }
}
