package com.sanbot.demo.app.start.main.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseAdapter;
import com.sanbot.demo.entity.Message;
import com.sanbot.demo.entity.UserInfo;
import com.sanbot.lib.view.CircleImageView;
import com.sanbot.net.NetInfo;

import java.util.List;
import java.util.Locale;

/**
 * 聊天适配器（文字，图片）
 *
 * @author youngbin
 */

public class ChatAdapter extends BaseAdapter<Message> {

    public static final int TYPE_TEXT_TO = 100;
    public static final int TYPE_TEXT_FROM = 101;
    public static final int TYPE_IMAGE_TO = 102;
    public static final int TYPE_IMAGE_FROM = 103;
    public static final int TYPE_VIDEO_FROM = 104;
    public static final int TYPE_VIDEO_TO = 105;
    public static final int TYPE_AUDIO_FROM = 106;
    public static final int TYPE_AUDIO_TO = 107;

    private int mOwnerUid;
    private UserInfo mToUser;

    public ChatAdapter(int ownerUid, UserInfo userInfo, List<Message> list) {
        super(list);
        mOwnerUid = ownerUid;
        mToUser = userInfo;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = get(position);
        int type = -1;
        switch (message.getType()) {
            case NetInfo.CONTENT_TYPE_TEXT:
                //文字
                type = mOwnerUid == message.getFromId() ? TYPE_TEXT_TO : TYPE_TEXT_FROM;
                break;
            case NetInfo.CONTENT_TYPE_IMAGE:
                //图片
                type = mOwnerUid == message.getFromId() ? TYPE_IMAGE_TO : TYPE_IMAGE_FROM;
                break;
            case NetInfo.CONTENT_TYPE_LIVE_VIDEO:
                //视频聊天
                type = mOwnerUid == message.getFromId() ? TYPE_VIDEO_TO : TYPE_VIDEO_FROM;
                break;
            case NetInfo.CONTENT_TYPE_LIVE_AUDIO:
                //语音聊天
                type = mOwnerUid == message.getFromId() ? TYPE_AUDIO_TO : TYPE_AUDIO_FROM;
                break;
            default:
                type = mOwnerUid == message.getFromId() ? TYPE_TEXT_TO : TYPE_TEXT_FROM;
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_TEXT_TO:
                viewHolder = new TextToViewHolder(createView(parent, R.layout.item_chat_to_text));
                break;
            case TYPE_TEXT_FROM:
                viewHolder = new TextFromViewHolder(createView(parent, R.layout.item_chat_from_text));
                break;
            case TYPE_IMAGE_TO:
                viewHolder = new ImageToViewHolder(createView(parent, R.layout.item_chat_to_image));
                break;
            case TYPE_IMAGE_FROM:
                viewHolder = new ImageFromViewHolder(createView(parent, R.layout.item_chat_from_image));
                break;
            case TYPE_VIDEO_TO:
                viewHolder = new VideoToViewHolder(createView(parent, R.layout.item_chat_to_text));
                break;
            case TYPE_VIDEO_FROM:
                viewHolder = new VideoFromViewHolder(createView(parent, R.layout.item_chat_from_text));
                break;
            case TYPE_AUDIO_TO:
                viewHolder = new AudioToViewHolder(createView(parent, R.layout.item_chat_to_text));
                break;
            case TYPE_AUDIO_FROM:
                viewHolder = new AudioFromViewHolder(createView(parent, R.layout.item_chat_from_text));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = get(position);
        if (holder instanceof TextToViewHolder) {
            TextToViewHolder viewHolder = (TextToViewHolder) holder;
            viewHolder.dateTv.setText(message.getDateText());
            viewHolder.contentTv.setText(message.getContent());
            if (mToUser != null) {
                Glide.with(mContext).load(mToUser.getAvatarUrl())
                        .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).dontAnimate()
                        .into(viewHolder.avatarIv);
            }
        } else if (holder instanceof TextFromViewHolder) {
            TextFromViewHolder viewHolder = (TextFromViewHolder) holder;
            viewHolder.dateTv.setText(message.getDateText());
            viewHolder.contentTv.setText(message.getContent());
        } else if (holder instanceof ImageToViewHolder) {
            ImageToViewHolder viewHolder = (ImageToViewHolder) holder;
            viewHolder.dateTv.setText(message.getDateText());
            if (mToUser != null) {
                Glide.with(mContext).load(mToUser.getAvatarUrl())
                        .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).dontAnimate()
                        .into(viewHolder.avatarIv);
            }

            Glide.with(mContext).load(message.getContent()).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher).dontAnimate()
                    .into(viewHolder.contentIv);

        } else if (holder instanceof ImageFromViewHolder) {
            ImageFromViewHolder viewHolder = (ImageFromViewHolder) holder;
            viewHolder.dateTv.setText(message.getDateText());
            if (mToUser != null) {
                Glide.with(mContext).load(mToUser.getAvatarUrl())
                        .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).dontAnimate()
                        .into(viewHolder.avatarIv);
            }

            Glide.with(mContext).load(message.getContent()).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher).dontAnimate()
                    .into(viewHolder.contentIv);

        } else if (holder instanceof VideoToViewHolder) {
            VideoToViewHolder viewHolder = (VideoToViewHolder) holder;
            viewHolder.dateTv.setText(message.getDateText());
            viewHolder.contentTv.setText(String.format(Locale.getDefault(), "%s:%s", mContext.getString(R.string.chat_video), message.getContent()));
        } else if (holder instanceof VideoFromViewHolder) {
            VideoFromViewHolder viewHolder = (VideoFromViewHolder) holder;
            viewHolder.dateTv.setText(message.getDateText());
            viewHolder.contentTv.setText(String.format(Locale.getDefault(), "%s:%s", mContext.getString(R.string.chat_video),message.getContent()));
        }else if (holder instanceof AudioToViewHolder) {
            AudioToViewHolder viewHolder = (AudioToViewHolder) holder;
            viewHolder.dateTv.setText(message.getDateText());
            viewHolder.contentTv.setText(String.format(Locale.getDefault(), "%s:%s", mContext.getString(R.string.chat_audio),message.getContent()));
        } else if (holder instanceof AudioFromViewHolder) {
            AudioFromViewHolder viewHolder = (AudioFromViewHolder) holder;
            viewHolder.dateTv.setText(message.getDateText());
            viewHolder.contentTv.setText(String.format(Locale.getDefault(), "%s:%s",mContext.getString(R.string.chat_audio), message.getContent()));
        }
    }

    private class TextToViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv;
        CircleImageView avatarIv;
        TextView contentTv;

        private TextToViewHolder(View itemView) {
            super(itemView);

            dateTv = (TextView) itemView.findViewById(R.id.chat_to_text_date_tv);
            avatarIv = (CircleImageView) itemView.findViewById(R.id.chat_to_text_avatar_iv);
            contentTv = (TextView) itemView.findViewById(R.id.chat_to_text_content_tv);

        }
    }

    private class TextFromViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv;
        CircleImageView avatarIv;
        TextView contentTv;

        private TextFromViewHolder(View itemView) {
            super(itemView);
            dateTv = (TextView) itemView.findViewById(R.id.chat_from_text_date_tv);
            avatarIv = (CircleImageView) itemView.findViewById(R.id.chat_from_text_avatar_iv);
            contentTv = (TextView) itemView.findViewById(R.id.chat_from_text_content_tv);
        }
    }

    private class ImageToViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv;
        CircleImageView avatarIv;
        ImageView contentIv;

        private ImageToViewHolder(View itemView) {
            super(itemView);

            dateTv = (TextView) itemView.findViewById(R.id.chat_to_image_date_tv);
            avatarIv = (CircleImageView) itemView.findViewById(R.id.chat_to_image_avatar_iv);
            contentIv = (ImageView) itemView.findViewById(R.id.chat_to_image_content_iv);

        }
    }

    private class ImageFromViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv;
        CircleImageView avatarIv;
        ImageView contentIv;

        private ImageFromViewHolder(View itemView) {
            super(itemView);
            dateTv = (TextView) itemView.findViewById(R.id.chat_from_image_date_tv);
            avatarIv = (CircleImageView) itemView.findViewById(R.id.chat_from_image_avatar_iv);
            contentIv = (ImageView) itemView.findViewById(R.id.chat_from_image_content_iv);
        }
    }

    private class VideoToViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv;
        CircleImageView avatarIv;
        TextView contentTv;

        private VideoToViewHolder(View itemView) {
            super(itemView);

            dateTv = (TextView) itemView.findViewById(R.id.chat_to_text_date_tv);
            avatarIv = (CircleImageView) itemView.findViewById(R.id.chat_to_text_avatar_iv);
            contentTv = (TextView) itemView.findViewById(R.id.chat_to_text_content_tv);

        }
    }

    private class VideoFromViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv;
        CircleImageView avatarIv;
        TextView contentTv;

        private VideoFromViewHolder(View itemView) {
            super(itemView);
            dateTv = (TextView) itemView.findViewById(R.id.chat_from_text_date_tv);
            avatarIv = (CircleImageView) itemView.findViewById(R.id.chat_from_text_avatar_iv);
            contentTv = (TextView) itemView.findViewById(R.id.chat_from_text_content_tv);
        }
    }

    private class AudioToViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv;
        CircleImageView avatarIv;
        TextView contentTv;

        private AudioToViewHolder(View itemView) {
            super(itemView);

            dateTv = (TextView) itemView.findViewById(R.id.chat_to_text_date_tv);
            avatarIv = (CircleImageView) itemView.findViewById(R.id.chat_to_text_avatar_iv);
            contentTv = (TextView) itemView.findViewById(R.id.chat_to_text_content_tv);

        }
    }

    private class AudioFromViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv;
        CircleImageView avatarIv;
        TextView contentTv;

        private AudioFromViewHolder(View itemView) {
            super(itemView);
            dateTv = (TextView) itemView.findViewById(R.id.chat_from_text_date_tv);
            avatarIv = (CircleImageView) itemView.findViewById(R.id.chat_from_text_avatar_iv);
            contentTv = (TextView) itemView.findViewById(R.id.chat_from_text_content_tv);
        }
    }

}
