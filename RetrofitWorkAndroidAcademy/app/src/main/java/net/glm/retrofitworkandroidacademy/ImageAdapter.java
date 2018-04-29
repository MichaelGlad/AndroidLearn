package net.glm.retrofitworkandroidacademy;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.glm.retrofitworkandroidacademy.databinding.ImageItemBinding;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    static final String MY_LOG = "My Logs in Adapter";

    private List<Image> images;

    public ImageAdapter(List<Image> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageItemBinding imageItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.image_item,
                parent,
                false);

        return new ImageViewHolder(parent.getContext(), imageItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
//        holder.imageItemBinding.setImage(images.get(position));
        Log.d(MY_LOG,"Web Url is -"+ images.get(position).getWebformatURL());
        Picasso.with(holder.holderContext).load(images.get(position).getWebformatURL()).into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageItemBinding imageItemBinding;
        ImageView mImageView;
        Context holderContext;

        public ImageViewHolder(Context holderContext , ImageItemBinding imageItemBinding) {
            super(imageItemBinding.cvImageCard);
            this.holderContext = holderContext;
            this.imageItemBinding = imageItemBinding;
            mImageView = imageItemBinding.ivMainCardImage;
        }

//        public void onBind (int position){
//            Image image = images.get(position);
//
//
//
//
//        }
    }


}
