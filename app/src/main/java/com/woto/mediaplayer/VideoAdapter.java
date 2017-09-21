package com.woto.mediaplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fangxubin on 2017/6/15.
 */

public class VideoAdapter extends ArrayAdapter<Video> {
    private int resourceId;

    public VideoAdapter(Context context, int textViewResourceId, List<Video> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取当前Video实例
        Video video = getItem(position);
        View view;
        ViewHolder viewHolder;

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.videoImage = (ImageView) view.findViewById(R.id.video_image);
            viewHolder.videoName = (TextView) view.findViewById(R.id.video_name);
            //将VideoHolder存储在View中
            view.setTag(viewHolder);
        }else{
            view = convertView;
            //重新获取ViewHolder
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.videoImage.setImageResource(video.getImageId());
        viewHolder.videoName.setText(video.getName());
        return view;
    }

    class ViewHolder {
        ImageView videoImage;
        TextView videoName;
    }
}
