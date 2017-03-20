package com.example.naunem.firstproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.SqliteUser;

import java.util.ArrayList;

/**
 * Created by naunem on 20/03/2017.
 */

public class SqliteUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<SqliteUser> users = new ArrayList<>();
    private final Context mContext;
    public SqliteUserAdapter(Context mContext, ArrayList<SqliteUser> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_sqlite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SqliteUser user = users.get(position);
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).mTvId.setText(String.valueOf(user.getId()));
            ((ViewHolder) holder).mTvName.setText(user.getName());
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvId;
        private TextView mTvName;
        public ViewHolder(View itemView) {
            super(itemView);
            mTvId = (TextView) itemView.findViewById(R.id.tvId);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}
