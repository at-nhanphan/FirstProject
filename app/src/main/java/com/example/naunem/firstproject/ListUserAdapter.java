package com.example.naunem.firstproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by naunem on 10/03/2017.
 */

public class ListUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<User> mLists = new ArrayList<>();
    Context mContext;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private boolean mLoading;
    private int mVisibleThreshold = 5;
    private int mLastVisibleItem;
    private int mTotalItemCount;
    private OnLoadMoreListener mOnLoadMoreListener;
    private MyOnClickListener mMyOnClickListener;

    public ListUserAdapter(Context context, ArrayList<User> lists, RecyclerView recyclerView) {
        this.mContext = context;
        this.mLists = lists;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    mTotalItemCount = linearLayoutManager.getItemCount();
                    mLastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    Toast.makeText(mContext, "fdfdf" + mTotalItemCount, Toast.LENGTH_SHORT).show();
                    if (!mLoading && mTotalItemCount <= (mLastVisibleItem + mVisibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.onLoadMore();
                        }
                        mLoading = true;
                    }
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
            vh = new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_loading, parent, false);
            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            User user = mLists.get(position);
            ((ViewHolder) holder).mImgAvatar.setImageResource(user.getImage());
            ((ViewHolder) holder).mTvName.setText(user.getName());
            ((ViewHolder) holder).mTvAge.setText(user.getAge());
            ((ViewHolder) holder).mTvGender.setText(user.getGender());
            ((ViewHolder) holder).mImgFavorite.setSelected(user.isFavorite());
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgAvatar;
        TextView mTvName;
        TextView mTvAge;
        TextView mTvGender;
        ProgressBar mProgressBar;
        ImageView mImgFavorite;
        boolean click = false;
        Bundle bundle = new Bundle();
        User user;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImgAvatar = (ImageView) itemView.findViewById(R.id.imgLogo);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvAge = (TextView) itemView.findViewById(R.id.tvAge);
            mTvGender = (TextView) itemView.findViewById(R.id.tvGender);
            mImgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext.getApplicationContext(), DetailUserActivity.class);
                    bundle.putParcelable("data", mLists.get(getLayoutPosition()));
                    intent.putExtra("object", bundle);
                    mContext.startActivity(intent);
                    Log.d("dfdfdf", "onClick: " + getLayoutPosition());
                }
            });
            mImgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user = mLists.get(getLayoutPosition());
                    user.setFavorite(!user.isFavorite());
                    notifyDataSetChanged();
                }
            });
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void setLoaded() {
        mLoading = false;
    }

    @Override
    public int getItemViewType(int position) {
        return mLists.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

}
