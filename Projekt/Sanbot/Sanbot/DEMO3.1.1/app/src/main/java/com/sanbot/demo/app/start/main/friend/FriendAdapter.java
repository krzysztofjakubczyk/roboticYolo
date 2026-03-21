package com.sanbot.demo.app.start.main.friend;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseAdapter;
import com.sanbot.demo.app.start.main.friend.detail.FriendDetailActivity;
import com.sanbot.demo.entity.UserInfo;
import com.sanbot.lib.view.CircleImageView;

import java.util.List;
import java.util.Locale;

public class FriendAdapter extends BaseAdapter<UserInfo> {

    private static final String TAG = "FriendAdapter";


    public FriendAdapter(List<UserInfo> list) {
        super(list);


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FriendViewHolder(createView(parent, R.layout.item_friend));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FriendViewHolder viewHolder = (FriendViewHolder) holder;
        UserInfo userInfo = mList.get(position);
        String remark = userInfo.getRemark();
        if(TextUtils.isEmpty(remark)){
            viewHolder.nameView.setText(userInfo.getNickname());
        }else{
            viewHolder.nameView.setText(String.format(Locale.getDefault(), "%s(%s)", userInfo.getRemark(), userInfo.getNickname()));
        }
        Glide.with(mContext).load(userInfo.getAvatarUrl())
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).dontAnimate()
                .into(viewHolder.avatarView);
    }


    private class FriendViewHolder extends RecyclerView.ViewHolder {

        CircleImageView avatarView;
        TextView nameView;
        Button detailBtn;

        private FriendViewHolder(View itemView) {
            super(itemView);

            avatarView = (CircleImageView) itemView.findViewById(R.id.item_friend_iv);
            nameView = (TextView) itemView.findViewById(R.id.item_friend_name_tv);
            detailBtn = (Button) itemView.findViewById(R.id.item_friend_detail_btn);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(v, getLayoutPosition(), mList.get(getLayoutPosition()));
                    }
                }
            });

            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserInfo userInfo = mList.get(getLayoutPosition());
                    FriendDetailActivity.startActivity((Activity) mContext, userInfo.getUid());
                }
            });

        }
    }
}
