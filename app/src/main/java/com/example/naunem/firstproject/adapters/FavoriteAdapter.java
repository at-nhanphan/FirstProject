package com.example.naunem.firstproject.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.interfaces.OnLoadMoreListener;
import com.example.naunem.firstproject.models.User;

import java.util.ArrayList;

/**
 * Created by naunem on 14/03/2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int mTotalItem;
    private final int THRESHOLD = 5;
    private int mLastItem;
    private boolean mIsLoading;
    private OnLoadMoreListener mLoadMoreListener;
    private ArrayList<User> mUsers = new ArrayList<>();
    private final Context mContext;

    public FavoriteAdapter(Context mContext, ArrayList<User> mUsers, RecyclerView recyclerView) {
        this.mUsers = mUsers;
        this.mContext = mContext;

        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mTotalItem = layoutManager.getItemCount();
                mLastItem = layoutManager.findLastVisibleItemPosition();
                if (!mIsLoading && mTotalItem <= (mLastItem + THRESHOLD)) {
                    if (mLoadMoreListener != null) {
                        mLoadMoreListener.onLoadMore();
                    }
                    mIsLoading = true;
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_loading, parent, false);
            return new ProgressViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerView.ViewHolder) {
            if (mUsers != null) {
                User user = mUsers.get(position);
//                ((MyViewHolder) holder).mImgAvatar.setImageResource(user.getImage());
                ((MyViewHolder) holder).mTvName.setText(user.getName());
                ((MyViewHolder) holder).mTvAge.setText(user.getAge());
                ((MyViewHolder) holder).mTvGender.setText(user.getGender());
                ((MyViewHolder) holder).mImgFavorite.setSelected(user.isFavorite());
            } else {
                ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgAvatar;
        private TextView mTvName;
        private TextView mTvAge;
        private TextView mTvGender;
        private ImageView mImgFavorite;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImgAvatar = (ImageView) itemView.findViewById(R.id.imgLogo);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvAge = (TextView) itemView.findViewById(R.id.tvAge);
            mTvGender = (TextView) itemView.findViewById(R.id.tvGender);
            mImgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);
        }
    }
    public class ProgressViewHolder extends RecyclerView.ViewHolder {

        private final ProgressBar progressBar;
        public ProgressViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mUsers.get(position) != null ? 1 : 0;

    }
}
