package com.rzagorski.memoapp.ui.memo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rzagorski.memoapp.MemoApp;
import com.rzagorski.memoapp.R;
import com.rzagorski.memoapp.di.memo.MemoActivityComponent;
import com.rzagorski.memoapp.di.memo.MemoActivityModule;
import com.rzagorski.memoapp.utils.interfaces.ComponentCreator;

public class MemoActivity extends AppCompatActivity implements ComponentCreator<MemoActivityComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
    }

    @Override
    public MemoActivityComponent getComponent() {
        return ((MemoApp) getApplication()).getMemoComponent()
                .provide(new MemoActivityModule(this));
    }
}
