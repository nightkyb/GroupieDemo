package com.github.groupiedemo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

/**
 * @author nightkyb created at 2019/6/19 15:56
 */
public class TopicItemWithImage extends Item<TopicItemWithImage.ItemViewHolder> {
    private Topic topic;

    public TopicItemWithImage(Topic topic) {
        super(topic.getTopicId());
        this.topic = topic;
    }

    @Override
    public int getLayout() {
        return R.layout.item_topic_with_image;
    }

    @NonNull
    @Override
    public ItemViewHolder createViewHolder(@NonNull View itemView) {
        return new ItemViewHolder(itemView);
    }

    @Override
    public void bind(@NonNull ItemViewHolder viewHolder, int position) {
        viewHolder.tvUsername.setText(topic.getUsername());
        viewHolder.tvContent.setText(topic.getContent());
        viewHolder.tvTime.setText(topic.getTime());
        // 设置网络图片
        // viewHolder.ivImage.setImageResource();
    }

    static class ItemViewHolder extends ViewHolder {
        TextView tvUsername;
        TextView tvContent;
        ImageView ivImage;
        TextView tvTime;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvContent = itemView.findViewById(R.id.tv_content);
            ivImage = itemView.findViewById(R.id.iv_image);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
