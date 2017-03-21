package com.example.naunem.firstproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.SqliteUser;
import com.example.naunem.firstproject.models.User;

import java.util.ArrayList;

/**
 * Created by naunem on 20/03/2017.
 */

public class SqliteUserAdapter extends RecyclerView.Adapter<SqliteUserAdapter.ViewHolder>{

    private ArrayList<User> users = new ArrayList<>();
    private Context mContext;

    public SqliteUserAdapter(Context mContext, ArrayList<User> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_sqlite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        holder.mTvId.setText(String.valueOf(user.getId()));
        holder.mTvName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvId;
        private TextView mTvName;
        public ViewHolder(View itemView) {
            super(itemView);
            mTvId = (TextView) itemView.findViewById(R.id.tvId);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}
