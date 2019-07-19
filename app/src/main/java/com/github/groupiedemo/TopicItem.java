package com.github.groupiedemo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

/**
 * @author nightkyb created at 2019/6/19 15:56
 */
public class TopicItem extends Item<TopicItem.ItemViewHolder> {
    public Topic topic;
    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onImageClick();

        void onVideoClick(String videoUrl);

        void onShareClick(int topicId);
    }

    public TopicItem(Topic topic, OnItemClickListener onItemClickListener) {
        super(topic.getTopicId());
        this.topic = topic;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getLayout() {
        return R.layout.item_topic;
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
        viewHolder.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onShareClick(topic.getTopicId());
            }
        });
    }

    static class ItemViewHolder extends ViewHolder {
        TextView tvUsername;
        TextView tvContent;
        TextView tvTime;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
