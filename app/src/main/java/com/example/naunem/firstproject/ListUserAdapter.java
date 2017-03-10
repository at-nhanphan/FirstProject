package com.example.naunem.firstproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by naunem on 10/03/2017.
 */

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ViewHolder> {
    ArrayList<User> mLists = new ArrayList<>();
    Context mContext;
    public ListUserAdapter(Context context, ArrayList<User> lists){
        this.mContext = context;
        this.mLists = lists;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mLists.get(position);

        holder.mTvName.setText(user.getName());
        holder.mTvAge.setText(user.getAge());
        holder.mTvGender.setText(user.getGender());
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgAvatar;
        TextView mTvName;
        TextView mTvAge;
        TextView mTvGender;
        public ViewHolder(View itemView) {
            super(itemView);
            mImgAvatar = (ImageView) itemView.findViewById(R.id.imgLogo);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvAge = (TextView) itemView.findViewById(R.id.tvAge);
            mTvGender = (TextView) itemView.findViewById(R.id.tvGender);
        }
    }
}
