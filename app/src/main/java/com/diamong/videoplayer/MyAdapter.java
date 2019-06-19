package com.diamong.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<VideoHolder> {
    private Context context;
    ArrayList<File> videoArrayList;

    public MyAdapter(Context context, ArrayList<File> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);

        return new VideoHolder(mView);
    }

    @Override
    public void onBindViewHolder(final VideoHolder videoHolder, int i) {

        videoHolder.txtFileName.setText(MainActivity.fileArrayList.get(i).getName());
        Bitmap bitmapThumbnail= ThumbnailUtils.createVideoThumbnail(videoArrayList.get(i).getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
        videoHolder.imageThumbNail.setImageBitmap(bitmapThumbnail);

        videoHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VideoPlayer.class);
                intent.putExtra("position",videoHolder.getAdapterPosition());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (videoArrayList.size()>0){
            return videoArrayList.size();
        }else return 1;
    }
}

class VideoHolder extends RecyclerView.ViewHolder {

    TextView txtFileName;
    ImageView imageThumbNail;
    CardView mCardView;

    public VideoHolder(View view) {
        super(view);

        txtFileName = view.findViewById(R.id.txt_videofilename);
        imageThumbNail = view.findViewById(R.id.iv_thumbnail);
        mCardView = view.findViewById(R.id.my_cardview);
    }
}
