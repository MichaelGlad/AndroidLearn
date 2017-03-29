package net.glm.contextualaction;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 29/03/2017.
 */

public class AnimalsRecyclerAdapter extends RecyclerView.Adapter <AnimalsRecyclerAdapter.AnimalViewHolder> {
    List<Animal> animalArrayList = new ArrayList<>();

    public AnimalsRecyclerAdapter (List<Animal> animalArrayList){
        this.animalArrayList = animalArrayList;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        AnimalViewHolder animalViewHolder = new AnimalViewHolder(view);
        return animalViewHolder;
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        holder.animalImage.setImageResource(animalArrayList.get(position).getImage());
        holder.txtAnimalName.setText(animalArrayList.get(position).getName());
        holder.txtAnimalMail.setText(animalArrayList.get(position).getMail());

    }

    @Override
    public int getItemCount() {
        return animalArrayList.size();
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder {
        ImageView animalImage;
        TextView txtAnimalName,txtAnimalMail;

        public AnimalViewHolder(View itemView) {


            super(itemView);
            animalImage = (ImageView) itemView.findViewById(R.id.image);
            txtAnimalName = (TextView) itemView.findViewById(R.id.txt_animals_name);
            txtAnimalMail = (TextView) itemView.findViewById(R.id.txt_animals_mail);


        }
    }

    public void  setFilter (ArrayList<Animal> newList){
        animalArrayList = new ArrayList<>();
        animalArrayList.addAll(newList);
        notifyDataSetChanged();
    }


}
