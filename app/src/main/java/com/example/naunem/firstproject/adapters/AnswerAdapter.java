package com.example.naunem.firstproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.Item;
import com.example.naunem.firstproject.models.Owner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * AnswerApdapter class
 * Created by naunem on 05/04/2017.
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<Item> mItems;

    public AnswerAdapter(Context context, ArrayList<Item> items) {
        this.mContext = context;
        this.mItems = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Owner owner = mItems.get(position).getOwner();
        Picasso.with(mContext)
                .load(owner.getProfileImage())
                .centerCrop()
                .fit()
                .into(holder.imgLogo);
        holder.tvName.setText(owner.getName());
        holder.tvId.setText(String.valueOf(owner.getId()));
        holder.tvUserType.setText(owner.getUserType());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLogo;
        private TextView tvName;
        private TextView tvId;
        private TextView tvUserType;
        ViewHolder(View itemView) {
            super(itemView);
            imgLogo = (ImageView) itemView.findViewById(R.id.imgLogo);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvId = (TextView) itemView.findViewById(R.id.tvAge);
            tvUserType = (TextView) itemView.findViewById(R.id.tvGender);
        }
    }

    public void updateAnswers(ArrayList<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}
