package net.glm.recyclerviewtraining2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michael on 17/03/2017.
 */

public class AnimalsRecyclerAdapter extends RecyclerView.Adapter<AnimalsRecyclerAdapter.AnimalViewHolder> {

    public static final String IMAGE_ID = "image_id";
    public static final String NAME = "name";
    public static final String MAIL = "mail";
    ArrayList<Animal> animalArrayList = new ArrayList<>();


    public AnimalsRecyclerAdapter(ArrayList<Animal> animalArrayList) {
        this.animalArrayList = animalArrayList;


    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        AnimalViewHolder animalViewHolder = new AnimalViewHolder(view, animalArrayList, parent.getContext());
        return animalViewHolder;
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        holder.image.setImageResource(animalArrayList.get(position).getImage());
        holder.animalsName.setText(animalArrayList.get(position).getName());
        holder.animalsMail.setText(animalArrayList.get(position).getMail());


    }

    @Override
    public int getItemCount() {
        return animalArrayList.size();
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView animalsName, animalsMail;
        ArrayList<Animal> animalsInHolder;
        Context parentContext;

        public AnimalViewHolder(View itemView, ArrayList<Animal> animalsInHolder, Context context) {
            super(itemView);

            this.animalsInHolder = animalsInHolder;
            this.parentContext = context;
            itemView.setOnClickListener(this);
            image = (ImageView) itemView.findViewById(R.id.image);
            animalsName = (TextView) itemView.findViewById(R.id.txtAnimalsName);
            animalsMail = (TextView) itemView.findViewById(R.id.txtAnimalsMail);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Animal animal = animalsInHolder.get(position);
            Intent intent = new Intent(parentContext, AnimalDetails.class);
            intent.putExtra(IMAGE_ID, animal.getImage());
            intent.putExtra(NAME, animal.getName());
            intent.putExtra(MAIL, animal.getMail());
            if (Build.VERSION.SDK_INT >= 21) {

                Pair<View, String> imagePair = Pair.create((View)image, image.getTransitionName());
                Pair<View, String> namePair = Pair.create((View)animalsName, animalsName.getTransitionName());
                Pair<View, String> mailPair = Pair.create((View) animalsMail, animalsMail.getTransitionName());

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) parentContext, imagePair,namePair,mailPair);
                parentContext.startActivity(intent, optionsCompat.toBundle());

            }else parentContext.startActivity(intent);


        }
    }
}
