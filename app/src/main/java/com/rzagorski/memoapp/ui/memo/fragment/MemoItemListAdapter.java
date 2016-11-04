package com.rzagorski.memoapp.ui.memo.fragment;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.rzagorski.memoapp.R;
import com.rzagorski.memoapp.ui.base.RecyclerBaseAdapter;
import com.rzagorski.memoapp.utils.abstracts.TextChangeWatcher;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class MemoItemListAdapter extends RecyclerBaseAdapter<MemoItemListAdapter.MemoItemViewHolder, String> {

    public MemoItemListAdapter(Context mContext, List<String> list) {
        super(mContext, list);
    }

    @Override
    public int getRowView() {
        return R.layout.new_memo_list_item;
    }

    @Override
    public MemoItemViewHolder createViewHolder(View rowView) {
        return new MemoItemViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(MemoItemViewHolder holder, int position) {
        String memoItem = getItemAt(position);
        if (memoItem == null || memoItem.equals(holder.title.getText().toString())) {
            return;
        }
        holder.title.setText(memoItem);
    }

    public class MemoItemViewHolder extends RecyclerBaseAdapter.ViewHolder implements View.OnLongClickListener {
        EditText title;

        public MemoItemViewHolder(View view) {
            super(view);
            itemView.setOnLongClickListener(this);
            title = (EditText) view.findViewById(R.id.memo_item);
            title.setOnLongClickListener(this);
            title.addTextChangedListener(new TextChangeWatcher() {
                @Override
                public void afterTextChanged(Editable editable) {
                    setItemAtWithoutNotifying(getAdapterPosition(), editable.toString());
                }
            });
        }

        @Override
        public void onClick(View v) {
            title.requestFocus();
        }

        @Override
        public boolean onLongClick(View view) {
            removeItemAt(getAdapterPosition());
            return true;
        }
    }
}
