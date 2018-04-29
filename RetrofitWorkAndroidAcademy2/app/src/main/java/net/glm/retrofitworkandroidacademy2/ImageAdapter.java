package net.glm.retrofitworkandroidacademy2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.glm.retrofitworkandroidacademy2.Model.Hit;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    static final String MY_LOG = "My Logs in Adapter";

    private List<Hit> hits;

    public ImageAdapter(List<Hit> hits) {
        this.hits = hits;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        ImageViewHolder mImageViewHolder = new ImageViewHolder(view);


        return mImageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Log.d(MY_LOG,"Web Url is -"+ hits.get(position).getWebformatURL());
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        View holderView;

        ImageView ivMainCardImage;
        TextView tvImageOwnerCard;
        TextView tvLikesDataCard;
        TextView tvViewsDataCard;
        TextView tvDownloadsDataCard;


        public ImageViewHolder(View view) {
            super(view);
            this.holderView = view;
           ivMainCardImage = holderView.findViewById(R.id.iv_main_card_image);
           tvImageOwnerCard = holderView.findViewById(R.id.tv_image_owner_card);
           tvLikesDataCard = holderView.findViewById(R.id.tv_likes_data_card);
           tvViewsDataCard = holderView.findViewById(R.id.tv_views_data_card);
           tvDownloadsDataCard = holderView.findViewById(R.id.tv_downloads_data_card);
        }

        public void onBind (int position){
            Hit mHit = hits.get(position);
            Picasso.with(holderView.getContext()).load(mHit.getWebformatURL()).into(ivMainCardImage);
            tvImageOwnerCard.setText(mHit.getUser());
            tvLikesDataCard.setText(mHit.getLikes().toString());
            tvViewsDataCard.setText(mHit.getViews().toString());
            tvDownloadsDataCard.setText(mHit.getDownloads().toString());

        }
    }


}
