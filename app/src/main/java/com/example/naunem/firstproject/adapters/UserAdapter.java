package com.example.naunem.firstproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.activities.FavoriteActivity;
import com.example.naunem.firstproject.interfaces.MyOnClickListener;
import com.example.naunem.firstproject.interfaces.OnLoadMoreListener;
import com.example.naunem.firstproject.models.ItemList;
import com.example.naunem.firstproject.models.Title;
import com.example.naunem.firstproject.models.User;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by naunem on 10/03/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ItemList> mLists = new ArrayList<>();
    Context mContext;
    private final int VIEW_TITLE = 2;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private boolean mLoading;
    private int mVisibleThreshold = 2;
    private int mLastVisibleItem;
    private int mTotalItemCount;
    private OnLoadMoreListener mOnLoadMoreListener;
    private MyOnClickListener mMyOnClickListener;
    private ArrayList<User> mFavorites = new ArrayList<>();

    public ArrayList<ItemList> getmLists() {
        return mLists;
    }

    public UserAdapter(Context context, ArrayList<ItemList> lists, RecyclerView recyclerView, MyOnClickListener listener) {
        this.mContext = context;
        this.mLists = lists;
        this.mMyOnClickListener = listener;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    mTotalItemCount = linearLayoutManager.getItemCount();
                    mLastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    Toast.makeText(mContext, "size: " + mTotalItemCount, Toast.LENGTH_SHORT).show();
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
        if (viewType == VIEW_TITLE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
            vh = new TitleViewHolder(v);
        } else if (viewType == VIEW_ITEM) {
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
        ItemList objects = mLists.get(position);
        if (holder instanceof TitleViewHolder) {
            if (objects instanceof Title) {
                Title title = (Title) objects;
                ((TitleViewHolder) holder).mTvTitle.setText(title.getmTitle());
            }
        } else if (holder instanceof ViewHolder) {
            if (objects instanceof User) {
                User user = (User) objects;
                ((ViewHolder) holder).mImgAvatar.setImageResource(user.getImage());
                ((ViewHolder) holder).mTvName.setText(user.getName());
                ((ViewHolder) holder).mTvAge.setText(user.getAge());
                ((ViewHolder) holder).mTvGender.setText(user.getGender());
                ((ViewHolder) holder).mImgFavorite.setSelected(user.isFavorite());
            }
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

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgAvatar;
        TextView mTvName;
        TextView mTvAge;
        TextView mTvGender;
        ProgressBar mProgressBar;
        ImageView mImgFavorite;
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
                    mMyOnClickListener.onClickListener(getLayoutPosition());
                }
            });

            mImgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user = (User) mLists.get(getLayoutPosition());
                    user.setFavorite(!user.isFavorite());
                    notifyDataSetChanged();
//                    if (user.isFavorite()) {
//                        Intent intent = new Intent(mContext, FavoriteActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putParcelable("favorite", user);
//                        intent.putExtra("data", bundle);
//                        ((Activity) mContext).startActivityForResult(intent, 2);
//                    }
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
        if (mLists.get(position) == null) {
            return VIEW_PROG;
        } else {
            return mLists.get(position).getType();
        }
    }

}
