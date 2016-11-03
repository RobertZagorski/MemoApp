package com.rzagorski.memoapp.ui.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rzagorski.memoapp.MemoApp;
import com.rzagorski.memoapp.R;
import com.rzagorski.memoapp.di.list.ListActivityComponent;
import com.rzagorski.memoapp.di.list.ListActivityModule;
import com.rzagorski.memoapp.utils.interfaces.ComponentCreator;

public class MemoListActivity extends AppCompatActivity implements ComponentCreator<ListActivityComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);
    }

    @Override
    public ListActivityComponent getComponent() {
        return ((MemoApp) getApplication()).getApplicationComponent()
                .provide(new ListActivityModule(this));
    }
}
