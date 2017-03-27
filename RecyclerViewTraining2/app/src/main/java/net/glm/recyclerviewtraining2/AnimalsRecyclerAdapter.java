package net.glm.recyclerviewtraining2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
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
    Context insideAdapterContext;

    public AnimalsRecyclerAdapter(ArrayList<Animal> animalArrayList, Context insideAdapterContext) {
        this.animalArrayList = animalArrayList;
        this.insideAdapterContext = insideAdapterContext;

    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        AnimalViewHolder animalViewHolder = new AnimalViewHolder(view,animalArrayList,insideAdapterContext);
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
        TextView animalsName,animalsMail;
        ArrayList<Animal> animalsInHolder;
        Context context;

        public AnimalViewHolder(View itemView,ArrayList<Animal> animalsInHolder, Context context) {
            super(itemView);

            this.animalsInHolder = animalsInHolder;
            this.context = context;
            itemView.setOnClickListener(this);
            image = (ImageView) itemView.findViewById(R.id.image);
            animalsName = (TextView) itemView.findViewById(R.id.txtAnimalsName);
            animalsMail = (TextView) itemView.findViewById(R.id.txtAnimalsMail);
        }



        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
              Animal animal = animalsInHolder.get(position);
            Intent intent = new Intent (context,AnimalDetails.class);
            intent.putExtra(IMAGE_ID,animal.getImage());
            intent.putExtra(NAME,animal.getName());
            intent.putExtra(MAIL,animal.getMail());
            context.startActivity(intent);



        }
    }
}
