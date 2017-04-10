package com.example.naunem.firstproject.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.interfaces.MyOnClickListener;
import com.example.naunem.firstproject.interfaces.OnLoadMoreListener;
import com.example.naunem.firstproject.models.ListItem;
import com.example.naunem.firstproject.models.Title;
import com.example.naunem.firstproject.models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * UserAdapter class
 * Created by naunem on 10/03/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<User> mLists = new ArrayList<>();
    private final Context mContext;
    private final int VIEW_PROGRESS = 0;
    private boolean mLoading;
    private final int mVisibleThreshold = 2;
    private int mLastVisibleItem;
    private int mTotalItemCount;
    private OnLoadMoreListener mOnLoadMoreListener;
    private final MyOnClickListener mMyOnClickListener;
    private ArrayList<User> mFavorites = new ArrayList<>();

    public ArrayList<User> getLists() {
        return mLists;
    }

    public UserAdapter(Context context, ArrayList<User> lists, RecyclerView recyclerView, MyOnClickListener listener) {
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
        final int VIEW_TITLE = 2;
        final int VIEW_ITEM = 1;
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
        ListItem object = mLists.get(position);
        if (holder instanceof TitleViewHolder) {
            if (object instanceof Title) {
                Title title = (Title) object;
                ((TitleViewHolder) holder).mTvTitle.setText(title.getTitle());
            }
        } else if (holder instanceof ViewHolder) {
            if (object instanceof User) {
                User user = (User) object;
                if (!TextUtils.isEmpty(user.getImage())){
                    Picasso.with(mContext)
                            .load(user.getImage())
                            .fit()
                            .centerCrop()
                            .error(R.drawable.ic_boy)
                            .into(((ViewHolder) holder).mImgAvatar);
                } else {
                    ((ViewHolder) holder).mImgAvatar.setImageResource(R.drawable.ic_girl);
                }
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
        private final ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImgAvatar;
        private final TextView mTvName;
        private final TextView mTvAge;
        private final TextView mTvGender;
        private final ImageView mImgFavorite;
        private User user;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImgAvatar = (ImageView) itemView.findViewById(R.id.imgLogo);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvAge = (TextView) itemView.findViewById(R.id.tvAge);
            mTvGender = (TextView) itemView.findViewById(R.id.tvGender);
            mImgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMyOnClickListener.onClickListener(getLayoutPosition());
                }
            });

            mImgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user = mLists.get(getLayoutPosition());
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
            return VIEW_PROGRESS;
        } else {
            return mLists.get(position).getType();
        }
    }

}
