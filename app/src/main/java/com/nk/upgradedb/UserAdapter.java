package com.nk.upgradedb;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<User> userArrayList;

    public UserAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserViewHolder viewHolder = (UserViewHolder) holder;
        User user = userArrayList.get(viewHolder.getAdapterPosition());
        String strName = mContext.getResources().getString(R.string.name, user.getFirstName(), user.getLastName());
        viewHolder.name.setText(strName);
        viewHolder.email.setText(user.getEmail());
        String shortName = String.valueOf(user.getFirstName().charAt(0)) + String.valueOf(user.getLastName().charAt(0));
        viewHolder.image.setText(shortName);
        viewHolder.image.setBackground(getCircularBackground(getMatColor()));
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }


    private int getMatColor() {
        int returnColor = Color.BLACK;
        int arrayId = mContext.getResources().getIdentifier("mdcolor_500", "array", mContext.getApplicationContext().getPackageName());

        if (arrayId != 0) {
            TypedArray colors = mContext.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.BLACK);
            colors.recycle();
        }
        return returnColor;
    }

    private Drawable getCircularBackground(int color) {
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.circular_bg);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable;
    }
}
