package com.rzagorski.memoapp.ui.list.active;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.rzagorski.memoapp.R;
import com.rzagorski.memoapp.model.Memo;
import com.rzagorski.memoapp.ui.base.RecyclerBaseAdapter;
import com.rzagorski.memoapp.utils.interfaces.ListItemClickDelegate;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class ActiveListAdapter extends RecyclerBaseAdapter<ActiveListAdapter.ActiveViewHolder, Memo> {

    private ListItemClickDelegate clickDelegate;

    public ActiveListAdapter(Context mContext, List<Memo> list) {
        super(mContext, list);
    }

    @Override
    public int getRowView() {
        return R.layout.active_list_item;
    }

    @Override
    public ActiveViewHolder createViewHolder(View rowView) {
        return new ActiveViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ActiveViewHolder holder, int position) {
        Memo memo = getItemAt(position);
        holder.title.setText(memo.getTitle());
        holder.dateCreated.setText(memo.getDateCreated().toString());
    }

    public void setClickDelegate(ListItemClickDelegate clickDelegate) {
        this.clickDelegate = clickDelegate;
    }

    public class ActiveViewHolder extends RecyclerBaseAdapter.ViewHolder {
        TextView title;
        TextView dateCreated;

        public ActiveViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            dateCreated = (TextView) view.findViewById(R.id.date);
        }

        @Override
        public void onClick(View v) {
            if (clickDelegate == null) {
                return;
            }
            Memo memo = getItemAt(getAdapterPosition());
            clickDelegate.onItemClick(memo);
        }
    }
}
