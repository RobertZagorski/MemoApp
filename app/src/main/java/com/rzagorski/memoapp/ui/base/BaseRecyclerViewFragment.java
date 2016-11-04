package com.rzagorski.memoapp.ui.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Robert Zagorski on 2015-07-07.
 */
public abstract class BaseRecyclerViewFragment<V extends RecyclerBaseAdapter, L> extends Fragment {
    protected RecyclerView mRecyclerView;
    protected V abstractListAdapter;
    protected RecyclerView.ItemDecoration itemDecoration;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupList(getView());
    }

    public void setupList(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(getListViewId());
        if (mRecyclerView == null) {
            Log.d("AbsRecyclerViewFragment", "recyclerView is null");
            return;
        }

        try {
            initRecyclerView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView() throws IllegalAccessException, java.lang.InstantiationException, InvocationTargetException {
        Class className = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Constructor c = className.getDeclaredConstructors()[0];
        c.setAccessible(true);
        abstractListAdapter = (V) c.newInstance(getActivity(), new ArrayList<L>());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(abstractListAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        stopRecyclerView();
    }

    public void stopRecyclerView() {
        if (mRecyclerView == null || itemDecoration == null) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.removeItemDecoration(itemDecoration);
            }
        });
    }

    public abstract int getListViewId();

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    public void addItems(Collection<L> list) {
        abstractListAdapter.addAllItems(list);
    }

    public L getItemAt(int position) {
        return (L) abstractListAdapter.getItemAt(position);
    }

    public void addItem(L item) {
        abstractListAdapter.addItem(item);
    }

    public void addItemWithNotify(L item) {
        abstractListAdapter.addItemWithNotify(item);
    }

    public void addItemAt(int position, L item) {
        abstractListAdapter.addItemAt(position, item);
    }

    public void addItemAtWithoutNotify(int position, L item) {
        abstractListAdapter.addItemAtWithoutNotify(position, item);
    }

    public void removeItemAt(int position) {
        abstractListAdapter.removeItemAt(position);
    }

    public void clearAdapter() {
        abstractListAdapter.clear();
    }

    public void addAllItems(Collection<L> list) {
        abstractListAdapter.addAllItems(list);
    }

    public void setItemAt(int position, L item) {
        abstractListAdapter.setItemAt(position, item);
    }

    public void setItemAtWithoutNotifying(int position, L item) {
        abstractListAdapter.setItemAtWithoutNotifying(position, item);
    }

    public void notifyItemChanged(int position) {
        abstractListAdapter.notifyItemChanged(position);
    }

    public void notifyItemRemoved(int position) {
        abstractListAdapter.notifyItemRemoved(position);
    }

    public void notifyItemAdded(int position) {
        abstractListAdapter.notifyItemInserted(position);
    }

    public void notifyItemRangeChanged(int from, int count) {
        abstractListAdapter.notifyItemRangeChanged(from, count);
    }

    public void notifyAdapter() {
        abstractListAdapter.notifyDataSetChanged();
    }

    public int getItemCount() {
        return abstractListAdapter.getItemCount();
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    public void disableOverscroll() {
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }
}
