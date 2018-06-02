package com.nk.upgradedb;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image)
    public TextView image;
    @BindView(R.id.name)
    public TextView name;

    public UserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
