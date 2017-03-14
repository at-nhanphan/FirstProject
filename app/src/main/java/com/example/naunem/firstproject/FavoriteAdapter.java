package com.example.naunem.firstproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by naunem on 14/03/2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    ArrayList<User> mUsers = new ListUserAdapter().getmLists();
    Context mContext;

    public FavoriteAdapter(Context mContext, ArrayList<User> mUsers) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mUsers != null) {
            User user = mUsers.get(position);
            holder.mImgAvatar.setImageResource(user.getImage());
            holder.mTvName.setText(user.getName());
            holder.mTvAge.setText(user.getAge());
            holder.mTvGender.setText(user.getGender());
            holder.mImgAvatar.setSelected(user.isFavorite());
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgAvatar;
        TextView mTvName;
        TextView mTvAge;
        TextView mTvGender;
        ImageView mImgFavorite;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImgAvatar = (ImageView) itemView.findViewById(R.id.imgLogo);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvAge = (TextView) itemView.findViewById(R.id.tvAge);
            mTvGender = (TextView) itemView.findViewById(R.id.tvGender);
            mImgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);
        }
    }

}
